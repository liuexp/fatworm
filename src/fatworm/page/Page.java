package fatworm.page;


/**
 * @author liuexp
 *
 */
public interface Page {
	
	/**
	 * used to initialize a page from a byte[]
	 * @param b
	 * @throws Throwable 
	 */
	public void fromBytes(byte [] b) throws Throwable;
	public byte[] toBytes() throws Throwable;
	public void flush() throws Throwable;
	public Long getTime();
	public Integer getID();
	public boolean isPartial();
	public int remainingSize();
	public int headerSize();
	public void markFree();
	public void beginTransaction();
	public void commit() throws Throwable;
	public boolean isInTransaction();
}
