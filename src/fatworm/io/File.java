package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class File {
	public static final int pageSize = 4 * 1024;			//4KB
	RandomAccessFile file = null;
	ByteBuffer buf = null;
	
	//TODO organization of the file: fatworm.data, fatworm.data.free, fatworm.btree, fatworm.btree.free
	
	public File(String fileName) throws IOException{
		load(fileName);
	}

	private void load(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "rw");
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
}
