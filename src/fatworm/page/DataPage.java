package fatworm.page;

import java.io.IOException;
import java.nio.ByteBuffer;
import fatworm.io.File;

public class DataPage extends VarPage {
	// for each page/block, offset in the file is #pageid * pageSize
	// header: #records, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// note that if the record is partial, then offsetTable contains one entry -1;
	// for each record:
	// first the nullmap, then go for each field according to metadata.
	// footer for page: next pageid, -1 for end of table/relation. do we need prev pageid as well?
	
//	public List<Record> records;
	// when a record doesn't fit into a page, I can't re-construct the record now.
	// so I just leave the remaining byte[] into buffer
	public ByteBuffer buffer;
	public int cntRecord=0;
	
	public DataPage(File f, Integer id) throws IOException{
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = id;
		byte [] tmp = new byte[File.pageSize];
		dataFile.read(tmp, pageID);
		fromBytes(tmp);
	}
	@Override
	public void fromBytes(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		cntRecord = buf.getInt();
		nextPageID = buf.getInt();
		for(int i=0;i<cntRecord;i++){
			offsetTable.add(buf.getInt());
		}
		if(offsetTable.peekLast() != -1) buf.position(offsetTable.peekLast());
		buffer = buf.slice();
	}
	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int headerSize() {
		return (cntRecord + 2) * 4;
	}
}
