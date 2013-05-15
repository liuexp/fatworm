package fatworm.page;

import java.io.IOException;
import java.util.LinkedList;

import fatworm.io.File;

/**
 * @author liuexp
 * Variable length data/key page, features offsetTable.
 */
public abstract class VarPage implements Page {
	
	public LinkedList<Integer> offsetTable = new LinkedList<Integer>();

	protected Integer pageID;
	public Integer nextPageID;
	public Integer prevPageID;
	public boolean dirty = false;
	protected File dataFile;
	protected Long lastTime;
	
	@Override
	public void flush() throws Throwable {
		if(!dirty) return;
		dirty = false;
		dataFile.write(toBytes(), pageID);
	}
	@Override
	public Long getTime() {
		return lastTime;
	}
	@Override
	protected void finalize() throws Throwable {
		flush();
	}
	@Override
	public Integer getID() {
		return pageID;
	}
	@Override
	public boolean isPartial() {
		return offsetTable.peekLast() == -1;
	}
	@Override
	public int headerSize() {
		return -1;
	}
	@Override
	public int remainingSize() {
		return isPartial() ? 0 : offsetTable.peekLast() - headerSize();
	}
	@Override
	public void markFree() {
		offsetTable = new LinkedList<Integer>();
		dirty = false;
	}


}
