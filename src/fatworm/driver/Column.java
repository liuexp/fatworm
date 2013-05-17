package fatworm.driver;

import java.io.Serializable;

import fatworm.field.Field;

public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4721155166503505246L;
	
	public String name;
	//public Type type;
	public int type;
	public boolean notNull, autoIncrement, primaryKey;
	public int ai;
	//Watch this defaultValue shouldn't be instanceof Field, cause Field is not serializable
	public Object defaultValue;
	public int A, B;
	
	/*public static enum Type {
		INT, FLOAT, CHAR, DATETIME, BOOLEAN, DECIMAL, TIMESTAMP, VARCHAR
	}*/
	
	public Column(String n, int ty, boolean notNull, boolean autoIncrement, Field defaultValue){
		this.name = n;
		this.type = ty;
		this.notNull = notNull;
		this.autoIncrement = autoIncrement;
		this.defaultValue = defaultValue;
		this.ai = 1;
	}
	public Column(String n, int ty){
		this(n, ty, false, false, null);
	}
	public boolean isAutoInc(){
		return autoIncrement;
	}
	public int getAutoInc(){
		return ai++;
	}
	public Field getDefault(){
		return Field.fromObject(defaultValue);
	}
	
	@Override
	public String toString(){
		return name;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Column)
			return name.equalsIgnoreCase(((Column)o).name);
		else return name.equalsIgnoreCase(o.toString());
	}
	public boolean hasDefault() {
		return defaultValue != null;
	}
}
