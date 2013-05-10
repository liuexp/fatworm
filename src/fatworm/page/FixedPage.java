package fatworm.page;

import java.io.IOException;

import fatworm.io.File;

public abstract class FixedPage implements Page {

	public FixedPage() {
		// TODO Auto-generated constructor stub
	}

	protected Integer pageID;
	public Integer nextPageID;
	public Integer prevPageID;
	public boolean dirty = false;
	protected File dataFile;
	protected Long lastTime;
	public int size;
	
	@Override
	public void flush() throws IOException {
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
		return false;
	}
	@Override
	public int headerSize() {
		return -1;
	}
	@Override
	public int remainingSize() {
		return File.pageSize - size;
	}
	@Override
	public void markFree() {
		dirty = false;
	}

}

