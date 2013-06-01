package fatworm.logicplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.absyn.Id;
import fatworm.driver.DBEngine;
import fatworm.driver.Database.Index;
import fatworm.driver.IOTable;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.driver.Table;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.util.Env;
import fatworm.util.Util;
import fatworm.io.Cursor;

public class FetchTable extends Plan {

	public String tableName;
	public Table table;
	Schema schema;
	Cursor cursor;
	public List<String> orderField = new LinkedList<String>(); // only those with type ASC should be collected.
	public FetchTable(String table) {
		super();
		tableName = table;
		this.table = DBEngine.getInstance().getTable(table);
		if(table==null)Util.error("meow");
		this.schema = new Schema(this.table.getSchema().tableName);
		for(String old:this.table.getSchema().columnName){
			String now = this.table.schema.tableName + "." + Util.getAttr(old);
			schema.columnDef.put(now, this.table.getSchema().getColumn(old));
			schema.columnName.add(now);
		}
		schema.primaryKey = this.table.getSchema().primaryKey;
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		if(parent instanceof Select && DBEngine.getInstance().turnOnIndex){
			List<Cond> condList = new ArrayList<Cond>();
			Map<String, List<Cond>> condGroup = new HashMap<String, List<Cond>>();
			Map<String, Interval> condInt = new HashMap<String, Interval>();
			Select cur = (Select) parent;
			// first collect the conditions
			while(true){
				if(cur.pred instanceof BinaryExpr){
					BinaryExpr cond = (BinaryExpr)cur.pred;
					if(cond.op != BinaryOp.NEQ &&(cond.l instanceof Id && cond.r.isConst)||(cond.r instanceof Id && cond.l.isConst)){
						Cond tmp = new Cond((BinaryExpr)cur.pred);
						condList.add(tmp);
						if(!condGroup.containsKey(tmp.name))
							condGroup.put(tmp.name, new ArrayList<Cond>());
						condGroup.get(tmp.name).add(tmp);
					}
				}
				if(cur.parent instanceof Select)
					cur = (Select)cur.parent;
				else 
					break;
			}
			// check for non-trivial interval specification.
			boolean nonEmpty = true;
			for(Map.Entry<String, List<Cond>> e : condGroup.entrySet()){
				List<Cond> conds = e.getValue();
				Interval x = new Interval();
				for(Cond c:conds){
					x.intersect(c.getInteval());
					if(x.isEmpty()){
						nonEmpty = false;
						break;
					}
				}
				if(!nonEmpty)
					break;
				condInt.put(e.getKey(), x);
			}
			if(!nonEmpty){
				cursor = new EmptyCursor();
				return;
			}
			// if there's an index built on those column, return IndexCursor
			List<String> condName = new LinkedList<String>(condInt.keySet());
			Interval bestInt = null;
			int minDiff = Integer.MAX_VALUE;
			Index bestIdx = null;
			for(String name:condName){
				if(table.hasIndexOn(name)){
					Index index = table.getIndexOn(name);
					Interval interval = condInt.get(name);
					int curDiff = interval.getRange();
					if(curDiff < minDiff||bestInt==null){
						minDiff = curDiff;
						bestInt = interval;
						bestIdx = index;
					}
				}
			}
			if(bestInt != null){
				if(table instanceof IOTable){
					IOTable iot = (IOTable) table;
					cursor = iot.scope(bestIdx, bestInt.min, bestInt.max);
					return;
				}
			}
		}
		cursor = table.open();
	}
	@Override
	public String toString(){
		return "Table (from="+tableName+")";
	}

	@Override
	public boolean hasNext() {
		return cursor.hasThis();
	}

	@Override
	public Record next() {
		Record ret = cursor.fetchRecord();
		try {
			cursor.next();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void reset() {
		try {
			cursor.reset();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		cursor.close();
	}

	@Override
	public List<String> getColumns() {
		List<String> z = new LinkedList<String> (schema.columnName);
		return z;
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}
	
	private class Cond{
		public BinaryOp op;
		public String name;
		public Field value;
		public Cond(BinaryExpr e){
			op = e.op;
			if(e.l instanceof Id){
				name = Util.getAttr(((Id)e.l).name);
				value = e.r.eval(new Env());
			}else{
				name = Util.getAttr(((Id)e.r).name);
				value = e.l.eval(new Env());
			}
		}
		public Interval getInteval() {
			switch(op){
			case EQ:
				return new Interval(value, value);
			case LESS:
			case LESS_EQ:
				return new Interval(null, value);
			case GREATER:
			case GREATER_EQ:
				return new Interval(value, null);
			}
			Util.error("Missing interval generation for operator:" + op);
			return null;
		}
	}
	
	private class Interval{
		public Field min;
		public Field max;
		public Interval(){
			min = null;
			max = null;
		}
		public Interval(Field l, Field r){
			min = l;
			max = r;
		}
		public void intersect(Interval x){
			if(isNull(min) || (!isNull(x.min) && min.applyWithComp(BinaryOp.LESS, x.min)))
				min = x.min;
			if(isNull(max) || (!isNull(x.max) && max.applyWithComp(BinaryOp.GREATER, x.max)))
				max = x.max;
		}
		private boolean isNull(Field x){
			return x==null||x.type==java.sql.Types.NULL;
		}
		public boolean isEmpty(){
			return !isNull(min) && !isNull(max) && min.applyWithComp(BinaryOp.GREATER, max);
		}
		public int getRange(){
			if(min instanceof INT && max instanceof INT){
				int diff = ((INT)max).v - ((INT)min).v;
				if(diff>=0)return diff;
			}
			return Integer.MAX_VALUE;
		}
	}
	
	private class EmptyCursor implements Cursor{

		@Override
		public void reset() throws Throwable {
		}

		@Override
		public void next() throws Throwable {
		}

		@Override
		public void prev() throws Throwable {
		}

		@Override
		public Object fetch(String col) {
			return null;
		}

		@Override
		public Object[] fetch() {
			return null;
		}

		@Override
		public void delete() throws Throwable {
		}

		@Override
		public void close() {
		}

		@Override
		public boolean hasNext() throws Throwable {
			return false;
		}

		@Override
		public Record fetchRecord() {
			return null;
		}

		@Override
		public Integer getIdx() {
			return null;
		}

		@Override
		public boolean hasThis() {
			return false;
		}
		
	}

	@Override
	public void rename(String oldName, String newName) {
		
	}
}
