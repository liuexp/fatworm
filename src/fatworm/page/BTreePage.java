package fatworm.page;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fatworm.io.BKey;
import fatworm.io.File;
import fatworm.util.Util;

// 4096 = 4 * 5 + 4 * 510 + 4 * 509 (INT key)
// 4096 > 4 * 5 + 4 * 340 + 8 * 339 (Long key)
public class BTreePage extends RawPage {
	private static final byte ROOTNODE = 1;
	private static final byte INTERNALNODE = 2;
	private static final byte LEAFNODE = 3;
	public static final int LongSize = Long.SIZE / Byte.SIZE;
	public static final int IntSize = Integer.SIZE / Byte.SIZE;
	// Page structure:
	// Page/Node type: ROOTNODE for root node, etc.
	// for string data, size, cnt, every segment is an int for the length, and then the string.
	// (deprecated)for root, #children, children list, key list.
	// (deprecated)for internal node, parent, #children, children list, key list.
	// now for EVERY (INT-key) node, parent, prev, next, #children, 510 x children list, 509 x key list.
	
	public byte nodeType;
	public Integer parentPageID;
	public Integer cntChild = 0;
	public List<Integer> children;
	public List<BKey> key;
	public int keyType;
	public BTreePage(File f, int pageid, int keyType, boolean create) throws IOException {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		byte [] tmp = new byte[File.pageSize];
		this.keyType = keyType;
		if(!create){
			dataFile.read(tmp, pageID);
			fromBytes(tmp);
		}else{
			nextPageID = -1;
			prevPageID = -1;
			parentPageID = -1;
			cntChild = 0;
			children = new LinkedList<Integer>();
			key = new LinkedList<BKey>();
		}
	}

	@Override
	public void fromBytes(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		nodeType = buf.get();
		parentPageID = buf.getInt();
		prevPageID = buf.getInt();
		nextPageID = buf.getInt();
		cntChild = buf.getInt();
		nextPageID = buf.getInt();
		for(int i=0;i<cntChild;i++){
			children.add(buf.getInt());
		}
		for(int i=0;i<cntChild-1;i++){
			if(keySize(keyType) == IntSize)
				key.add(buf.getInt());
			else
				key.add(buf.getLong());
		}
		this.buf = buf;
	}
	
	public boolean isLeaf(){
		return nodeType == LEAFNODE;
	}
	public boolean isRoot(){
		return nodeType == ROOTNODE;
	}
	public boolean isInternal(){
		return nodeType == INTERNALNODE;
	}
	public static int keySize(int type){
		switch(type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
		case java.sql.Types.FLOAT:
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.DECIMAL:
			return Integer.SIZE / Byte.SIZE;
		case java.sql.Types.DATE:
		case java.sql.Types.TIMESTAMP:
			return Long.SIZE / Byte.SIZE;
			default:
				Util.error("meow@BTreePage");
		}
		return 4;
	}
	
	@Override
	public int headerSize(){
		return 5 * Byte.SIZE;
	}
}
