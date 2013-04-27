package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class File {
	private static final long maxBlocks = 1024 * 1024 / 4;
	private static final long blockSize = 4 * 1024;			//4KB
	RandomAccessFile file = null;
	ByteBuffer buf = null;
	
	//TODO organization of the file
	
	public File(String fileName) throws IOException{
		load(fileName);
	}

	private void load(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "rw");
	}
	
	public void read(byte[] b, int block, int count, int offset) throws IOException{
		file.seek(block * blockSize);
		file.read(b, offset, (int) (count * blockSize));
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
