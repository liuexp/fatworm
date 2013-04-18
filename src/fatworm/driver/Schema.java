package fatworm.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.field.Field;
import fatworm.parser.FatwormParser;
import fatworm.util.Util;


public class Schema {

	public String tableName;
	
	public List<String> columnName = new ArrayList<String> ();
	
	public Column primaryKey;
	public Map<String, Column> columnDef = new HashMap<String, Column>();
	
	public int findIndex(String x) {
		for(int i=0;i<columnName.size();i++){
			String y = columnName.get(i);
			if(x.equalsIgnoreCase(y) || x.equalsIgnoreCase(this.tableName + "." + y))
				return i;
			if(!y.contains("."))continue;
			// FIXME hack for table name mismatch
			if(Util.getAttr(x).equals(Util.getAttr(y)))
				return i;
		}
		return -1;
	}
	
	// NO tolerance on table name mismatch.
	public int findStrictIndex(String x) {
		for(int i=0;i<columnName.size();i++){
			String y = columnName.get(i);
			if(x.equalsIgnoreCase(y) || x.equalsIgnoreCase(this.tableName + "." + y))
				return i;
			if(!y.contains("."))continue;
		}
		return -1;
	}
	
	public Schema(CommonTree t){
		tableName = t.getChild(0).getText();
		for(int i=1;i<t.getChildCount();i++){
			Tree c = t.getChild(i);
			String colName = c.getChild(0).getText();
			switch(c.getType()){
			case FatwormParser.CREATE_DEFINITION:
				columnName.add(colName);
				Tree def = c.getChild(1);
				Column col = null;
				switch(def.getType()){
				case FatwormParser.BOOLEAN:
					col = new Column(colName, java.sql.Types.BOOLEAN);
					break;
				case FatwormParser.CHAR:
					col = new Column(colName, java.sql.Types.CHAR);
					break;
				case FatwormParser.DATETIME:
					col = new Column(colName, java.sql.Types.DATE);
					break;
				case FatwormParser.DECIMAL:
					col = new Column(colName, java.sql.Types.DECIMAL);
					break;
				case FatwormParser.FLOAT:
					col = new Column(colName, java.sql.Types.FLOAT);
					break;
				case FatwormParser.INT:
					col = new Column(colName, java.sql.Types.INTEGER);
					break;
				case FatwormParser.TIMESTAMP:
					col = new Column(colName, java.sql.Types.TIMESTAMP);
					break;
				case FatwormParser.VARCHAR:
					col = new Column(colName, java.sql.Types.VARCHAR);
					break;
					default:
						Util.error("Schema undefined def.");
				}
				columnDef.put(colName, col);
				for(int j=2;j<c.getChildCount();j++){
					Tree opt = c.getChild(j);
					switch(opt.getType()){
					case FatwormParser.AUTO_INCREMENT:
						col.autoIncrement = true;
						break;
					case FatwormParser.DEFAULT:
						col.defaultValue = Util.getField(col, opt.getChild(0));
						break;
					case FatwormParser.NULL:
						col.notNull = opt.getChildCount() != 0;
						break;
					}
				}
				break;
			case FatwormParser.PRIMARY_KEY:
				//Meow, this has to be postponed...
				
				break;
				default:
					Util.error("Schema undefined manipulation.");
			}
		}
		for(int i=1;i<t.getChildCount();i++){
			Tree c = t.getChild(i);
			String colName = c.getChild(0).getText();
			switch(c.getType()){
			case FatwormParser.CREATE_DEFINITION:
				break;
			case FatwormParser.PRIMARY_KEY:
				primaryKey = columnDef.get(colName);
				primaryKey.primaryKey = true;
				break;
			}
			
		}
	}

	public Schema(String t) {
		this();
		tableName = t;
	}

	public Schema() {
		tableName = "MEOW";
	}

	// FIXME The type info of Columns are not filled. 
	// 		 on inserting values from subquery, this type info might be needed.
	public void fromList(List<Expr> expr, Schema src) {
		tableName = "ProjectFrom("+src.tableName+")";
		if(expr.size()==0||Util.trim(expr.get(0).toString()).equals("*")){
			//System.out.println("meow");
			columnName.addAll(src.columnName);
			columnDef.putAll(src.columnDef);
		}else {
			for(int i=0;i<expr.size();i++){
				Expr e = expr.get(i);
				String colName = e.toString();
				Column col = src.columnDef.get(colName);
				if(col == null)
					col = new Column(colName, java.sql.Types.NULL);
				columnDef.put(colName, col);
				columnName.add(colName);
			}
		}
	}
	@Override
	public String toString(){
		return "[" + tableName + "]" + Util.deepToString(columnName);
	}
}
