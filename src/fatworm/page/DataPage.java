package fatworm.page;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

import fatworm.io.File;
import fatworm.util.ByteBuilder;

/**
 * @author liuexp
 *
 */
public class DataPage extends VarPage {
	// for each page/block, offset in the file is #pageid * pageSize
	// header: #records, nextPageID, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// note that if the record is partial, then offsetTable contains one entry -1;
	// for each record:
	// first the nullmap, then go for each field according to metadata.
	
//	public List<Record> records;
	// when a record doesn't fit into a page, I can't re-construct the record now.
	// so I just leave the remaining byte[] into buffer
	// FIXME wait a second maybe it's much better to just leave it as byte[] as it's easier to wrap up RecordAdapter
//	public ByteBuffer buffer;
	// this is a reverted buffer
	private byte[] buffer;
	private int cntRecord=0;
	
	public DataPage(File f, Integer id, boolean create) throws IOException{
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = id;
		byte [] tmp = new byte[File.pageSize];
		if(!create){
			dataFile.read(tmp, pageID);
			fromBytes(tmp);
		}else{
			nextPageID = -1;
		}
	}
	@Override
	public void fromBytes(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		cntRecord = buf.getInt();
		nextPageID = buf.getInt();
		for(int i=0;i<cntRecord;i++){
			offsetTable.add(buf.getInt());
		}
		int length = File.pageSize - headerSize();
		buffer = new byte[length];
		System.arraycopy(b, headerSize(), buffer, 0, length);
		Collections.reverse(Arrays.asList(buffer));
	}
	@Override
	public byte[] toBytes() {
		ByteBuilder b = new ByteBuilder();
		b.putInt(cntRecord);
		b.putInt(nextPageID);
		for(Integer i:offsetTable){
			b.putInt(i);
		}
		byte[] ret = new byte[File.pageSize];
		System.arraycopy(b.getByteArray(), 0, ret, 0, headerSize());
		
		int length = File.pageSize - headerSize();
		byte [] tmp = Arrays.copyOf(buffer, length);
		Collections.reverse(Arrays.asList(tmp));
		System.arraycopy(tmp, 0, ret, headerSize(), length);
		
		return ret;
	}
	
	/**
	 * @param i the i-th data
	 * @param r the byte[] for the data
	 * @return whether there's enough space for an update
	 */
	public boolean tryUpdateData(int i, byte[] r){
		//TODO do it here or somewhere else?
		return false;
	}
	@Override
	public int headerSize() {
		return (cntRecord + 2) * 4;
	}
}
