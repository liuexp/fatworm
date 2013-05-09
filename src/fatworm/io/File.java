package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class File {
	//private static final long maxBlocks = 1024 * 1024 / 4; 	//maximum #blocks buffer manager can hold
	private static final long blockSize = 4 * 1024;			//4KB
	RandomAccessFile file = null;
	ByteBuffer buf = null;
	
	//TODO organization of the file: fatworm.data, fatworm.freelist
	// only those page that's merely half full is considered free.
	// for each page/block, offset in the file is #pageid * blockSize
	// header: #records, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// for each record:
	// first the nullmap, then go for each field according to metadata.
	// footer for page: next pageid, -1 for end of table/relation. do we need prev pageid as well?
	
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
