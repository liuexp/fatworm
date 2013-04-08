package fatworm.driver;

//FIXME this might/should be deprecated?
public abstract class Scan {

	public Scan() {
		// TODO Auto-generated constructor stub
	}

	abstract public void close();

	abstract public boolean next();

	public Schema getSchema(){
		return null;
	}

}
