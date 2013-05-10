package fatworm.page;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

import fatworm.io.File;

public class BTreePage extends VarPage {

	// for each page/block, offset in the file is #pageid * pageSize
	// header: #child, parentPageID, prevPageID, nextPageID, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// note that if a key is partial, then offsetTable contains one entry -1;
	// for each key:
	// Field
	
	public Integer parentPageID;
	public int cntChild = 0;
	public byte[] buffer;
	public BTreePage(File f, int pageid, boolean create) throws IOException {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		byte [] tmp = new byte[File.pageSize];
		if(!create){
			dataFile.read(tmp, pageID);
			fromBytes(tmp);
		}else{
			nextPageID = -1;
			prevPageID = -1;
		}
	}

	@Override
	public void fromBytes(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		cntChild = buf.getInt();
		nextPageID = buf.getInt();
		for(int i=0;i<cntChild;i++){
			offsetTable.add(buf.getInt());
		}
		int length = File.pageSize - headerSize();
		buffer = new byte[length];
		System.arraycopy(b, headerSize(), buffer, 0, length);
		Collections.reverse(Arrays.asList(buffer));
	}

	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

}
