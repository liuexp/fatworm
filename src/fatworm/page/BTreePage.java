package fatworm.page;

import java.util.List;
import fatworm.io.File;
import fatworm.util.Util;

// FIXME dont need offsetTable, and there're 2 kind of pages for btreeBuffermanager(the other for handling string keys)
// 4096 = 4 * 5 + 4 * 510 + 4 * 509 (INT key)
// 4096 > 4 * 5 + 4 * 340 + 8 * 339 (Long key)
public class BTreePage extends VarPage {
	private static final byte ROOTNODE = 1;
	private static final byte INTERNALNODE = 2;
	private static final byte LEAFNODE = 3;
	// Page structure:
	// Page/Node type: 0 for string data, ROOTNODE for root node, etc.
	// for string data, #segments, every segment is an int for the length, and then the string.
	// (deprecated)for root, #children, children list, key list.
	// (deprecated)for internal node, parent, #children, children list, key list.
	// now for EVERY (INT-key) node, parent, prev, next, #children, 510 x children list, 509 x key list.
	
	public byte type;
	public Integer parentPageID;
	public Integer prevPageID;
	public Integer cntChildren;
	public List<Integer> children;
	public List<Integer> key;
	public BTreePage(File dataFile, int pageid) {
		// TODO Auto-generated constructor stub
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
	
	public boolean isLeaf(){
		return type == LEAFNODE;
	}
	public boolean isRoot(){
		return type == ROOTNODE;
	}
	public boolean isInternal(){
		return type == INTERNALNODE;
	}
	public int keySize(int type){
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
}
