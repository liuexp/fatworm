package fatworm.driver;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.absyn.FuncCall;
import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.util.ByteBuilder;
import fatworm.util.Env;
import fatworm.util.Util;

public class Record implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6636550142198623635L;
	public List<Field> cols = new ArrayList<Field>();
//	public Integer nullMap = 0;
	public Schema schema;
	public Record() {
		// TODO Auto-generated constructor stub
	}
	public Record(Schema scm) {
		schema = scm;
	}
	public Field getCol(String by) {
		int idx = schema.findIndex(by);
		return getCol(idx);
	}
	public Field getCol(int idx) {
		if(idx<0){
			Util.warn("meow");
			return NULL.getInstance();
		}
		return cols.get(idx);
	}
	public void addCol(Field x){
		cols.add(x);
	}
	public void addColFromExpr(Env env, List<Expr> func) {
//		if(func.size()==0||Util.trim(func.get(0).toString()).equals("*")){
//			for(int i=0;i<schema.columnName.size();i++){
//				String col = schema.columnName.get(i);
//				Field f = env.get(col);
//				if(f==null)f = env.get(Util.getAttr(col));
//				cols.add(f);
//			}
//		}else{
			for(int i=0;i<func.size();i++){
				Expr e = func.get(i);
				Field f = env.get(e.toString());
				if(f == null||f instanceof FuncCall.ContField) f= e.eval(env);
				cols.add(f);
			}
//		}
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Record))
			return false;

		Record z = (Record) o;

		return Util.deepEquals(cols, z.cols);
	}
	
	@Override
	public int hashCode() {
		return Util.deepHashCode(cols);
	}
	@Override
	public String toString(){
		return "[\r\n" + schema.toString() + "\r\n]" + Util.deepToString(cols);
	}
	public void autoFill() {
		for(int i=0;i<schema.columnName.size();i++){
			String colName = schema.columnName.get(i);
			Column col = schema.getColumn(colName);
			if(col.hasDefault() || col.type == java.sql.Types.TIMESTAMP){
				cols.add(Util.getField(col, null));
			} else 
				cols.add(null);//FIXME is this okay?
		}
	}
	public void setField(String colName, Field field) {
		cols.set(schema.findIndex(colName), field);
	}
	public static Record fromByte(Schema scm, byte[] byteArray) {
		Record r = new Record(scm);
		ByteBuffer b = ByteBuffer.wrap(byteArray);
		Integer nullMap = b.getInt();
		for(int i=0;i<scm.columnName.size();i++){
			if((nullMap & 1) == 1){
				r.cols.add(NULL.getInstance());
			}else {
				r.cols.add(Field.fromBytes(b, scm.getColumn(i).type));
			}
			nullMap >>= 1;
		}
		return r;
	}
	public static byte[] toByte(Record r){
		ByteBuilder b = new ByteBuilder();
		Integer nullMap = 0;
		for(int i=r.schema.columnName.size()-1;i>=0;i--){
			if(r.cols.get(i)==null || r.cols.get(i).type == java.sql.Types.NULL){
				nullMap += 1;
			}
			nullMap <<= 1;
		}
		b.putInt(nullMap);
		for(int i=0;i<r.schema.columnName.size();i++){
			Column c = r.schema.getColumn(i);
			if(r.cols.get(i)!=null && r.cols.get(i).type != java.sql.Types.NULL)
				r.cols.get(i).pushByte(b, c.A, c.B);
		}
		byte[] ret = new byte[b.getSize()];
		byte[] tmp = b.getByteArray();
		System.arraycopy(tmp, 0, ret, 0, b.getSize());
		return ret;
	}
}
