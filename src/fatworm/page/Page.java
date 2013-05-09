package fatworm.page;

import java.io.IOException;


public interface Page {
	
	public void fromBytes(byte [] b);
	public byte[] toBytes();
	public void flush() throws IOException;
	public Long getTime();
	public Integer getID();
	public boolean isPartial();
	public int remainingSize();
	public int headerSize();
	public void markFree();
}
