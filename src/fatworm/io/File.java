package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import fatworm.page.Page;

public class File {
	public static final long recordPageMult = 16;
	public static final long btreePageMult = 4;
	public static final long recordPageSize = recordPageMult * 1024;			//64KB
	public static final long btreePageSize = btreePageMult * 1024;				//4KB
	public int type = 0;
	public static final int RECORDFILE = 0;
	public static final int BTREEFILE = 1;
	RandomAccessFile file = null;
	ByteBuffer buf = null;
	FileChannel fc = null;
	
	// organization of the file: fatworm.record, fatworm.record.free, fatworm.btree, fatworm.btree.free
	
	public File(String fileName, int type) throws IOException{
		this.type = type;
		load(fileName);
	}

	private void load(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "rws");
		fc = file.getChannel();
	}
	
	public void read(byte[] b, int block) throws IOException {
		file.seek(block * getPageSize());
		file.read(b);
	}

	public void close(){
		if(file == null)return;
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
   public synchronized void read(ByteBuffer bb, Integer pageID) {
      try {
         bb.clear();
         fc.read(bb, pageID * getPageSize());
      }
      catch (IOException e) {
         throw new RuntimeException("cannot read page " + pageID);
      }
   }

   public synchronized void write(ByteBuffer bb, Integer pageID) {
      try {
         bb.rewind();
         fc.write(bb, pageID * getPageSize());
      }
      catch (IOException e) {
         throw new RuntimeException("cannot write page " + pageID);
      }
   }

   public synchronized void append(ByteBuffer bb) {
      int newblknum = size();
      write(bb, newblknum);
   }
   
   public synchronized int size() {
      try {
         return (int)(fc.size() / getPageSize());
      }
      catch (IOException e) {
         throw new RuntimeException("cannot access " + file);
      }
   }

	private long getPageSize() {
		return type==RECORDFILE?recordPageSize:btreePageSize;
	}
}
