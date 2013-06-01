package fatworm.io;

import fatworm.driver.Record;

public interface Cursor {

	public void reset();
	
	public void next() throws Throwable;
	
	public void prev() throws Throwable;
	
	public Object fetch(String col);
	
	public Object[] fetch();
	
	public void delete() throws Throwable;
	
	public void close();

	public boolean hasNext() throws Throwable;
	
	public Record fetchRecord();
	
	public Integer getIdx();

	public boolean hasThis();
}
