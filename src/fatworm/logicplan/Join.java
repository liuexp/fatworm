package fatworm.logicplan;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Join extends Plan {

	public Plan left,right;
	public Record curLeft;
	public Schema schema;
	public Join(Plan left, Plan right) {
		super();
		this.left = left;
		this.right = right;
		this.left.parent = this;
		this.right.parent = this;
		curLeft = null;
		myAggr.addAll(this.left.getAggr());
		myAggr.addAll(this.right.getAggr());
		this.schema = new Schema(left.getSchema().tableName + " * " + right.getSchema().tableName);
//		this.schema.columnName.addAll(left.getSchema().columnName);
//		this.schema.columnName.addAll(right.getSchema().columnName);
//		this.schema.columnDef.putAll(left.getSchema().columnDef);
//		this.schema.columnDef.putAll(right.getSchema().columnDef);
		// FIXME watch it for column name => lowerCase
		Set<String> commonName = new HashSet<String> (left.getSchema().columnName);
		commonName.retainAll(right.getSchema().columnName);
		String ltbl = left.getSchema().tableName;
		for(String colName : left.getSchema().columnName){
			if(commonName.contains(colName)){
				this.schema.columnName.add(ltbl + "." + colName);
				this.schema.columnDef.put(ltbl + "." + colName, left.getSchema().columnDef.get(colName));
			}else{
				this.schema.columnName.add(colName);
				this.schema.columnDef.put(colName, left.getSchema().columnDef.get(colName));
			}
		}
		String rtbl = right.getSchema().tableName;
		for(String colName : right.getSchema().columnName){
			if(commonName.contains(colName)){
				this.schema.columnName.add(rtbl + "." + colName);
				this.schema.columnDef.put(rtbl + "." + colName, right.getSchema().columnDef.get(colName));
			}else{
				this.schema.columnName.add(colName);
				this.schema.columnDef.put(colName, right.getSchema().columnDef.get(colName));
			}
		}
		if(ltbl.equalsIgnoreCase("A")&& rtbl.equalsIgnoreCase("B")){
		}
	}

	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		curLeft = null;
		Env env = envGlobal.clone();
		left.eval(env);
		right.eval(env);
	}
	@Override
	public String toString(){
		return "Join (left="+left.toString()+", right="+right.toString()+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Join not eval");
		return right.hasNext();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Join not eval");
		Record r = right.next();
		if(curLeft == null)
			curLeft = left.next();
		Record ret = new Record(schema);
		ret.cols.addAll(curLeft.cols);
		ret.cols.addAll(r.cols);
		if(!right.hasNext()&&left.hasNext()){
			right.reset();
			curLeft = left.next();
		}
		return ret;
	}

	@Override
	public void reset() {
		left.reset();
		right.reset();
		curLeft = null;
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		left.close();
		right.close();
	}

	@Override
	public List<String> getColumns() {
		List<String> z = new LinkedList<String> (left.getColumns());
		z.addAll(right.getColumns());
		return z;
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = new LinkedList<String> (left.getRequestedColumns());
		z.addAll(right.getRequestedColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		left.rename(oldName, newName);
		right.rename(oldName, newName);
		
	}
}
