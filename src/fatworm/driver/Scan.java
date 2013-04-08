package fatworm.driver;

//FIXME this might/should be deprecated?
public abstract class Scan {

	public Scan() {
		// TODO Auto-generated constructor stub
	}

	public abstract void close();

	public abstract boolean next();

	public Schema getSchema(){
		return null;
	}

}
