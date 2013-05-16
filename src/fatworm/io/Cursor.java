package fatworm.io;

public interface Cursor {

	public void reset();
	
	public void next();
	
	public void prev();
	
	public Object fetch(String col);
	
	public Object[] fetch();
	
	public void delete() throws Throwable;
	
	public void close();

	public boolean hasNext();
}
