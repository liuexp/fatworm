package fatworm.field;


public abstract class Field {

	public int type;
	public Field() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object o){
		if(o == null)return false;
		return this.toString().equals(o.toString());
	}
}
