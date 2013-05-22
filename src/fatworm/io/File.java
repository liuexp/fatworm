package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import fatworm.page.Page;

public class File {
	public static final int pageMult = 64;
	public static final int pageSize = pageMult * 1024;			//64KB
	RandomAccessFile file = null;
	ByteBuffer buf = null;
	FileChannel fc = null;
	
	//TODO organization of the file: fatworm.data, fatworm.data.free, fatworm.btree, fatworm.btree.free
	
	public File(String fileName) throws IOException{
		load(fileName);
	}

	private void load(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "rws");
		fc = file.getChannel();
	}
	
	public void write(byte[] b, int block) throws IOException{
		file.seek(block * pageSize);
		file.write(b);
	}
	
	public void read(byte[] b, int block) throws IOException {
		file.seek(block * pageSize);
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
         fc.read(bb, pageID * pageSize);
      }
      catch (IOException e) {
         throw new RuntimeException("cannot read page " + pageID);
      }
   }

   public synchronized void write(ByteBuffer bb, Integer pageID) {
      try {
         bb.rewind();
         fc.write(bb, pageID * pageSize);
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
	         return (int)(fc.size() / pageSize);
	      }
	      catch (IOException e) {
	         throw new RuntimeException("cannot access " + file);
	      }
	   }
}
