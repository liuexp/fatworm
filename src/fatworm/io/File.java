package fatworm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class File {
	private static final long maxBlocks = 1024 * 1024 / 4;
	private static final long blockSize = 4 * 1024;			//4KB
	RandomAccessFile file = null;
	ByteBuffer buf = null;

	public File(String fileName) throws IOException{
		load(fileName);
	}

	private void load(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "rw");
		buf = file.getChannel().map(MapMode.READ_WRITE, 0, maxBlocks * blockSize);
	}
}
