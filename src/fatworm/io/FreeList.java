package fatworm.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import fatworm.util.Util;

public class FreeList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3724475685010140393L;
	public LinkedList<Integer> freeList = new LinkedList<Integer> ();
	public int nextPageNumber = 0;
	
	public static void write(String file, FreeList l) throws IOException{
		String freeFile = Util.getFreeFile(file);
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(freeFile)));
		out.writeObject(l);
		out.close();
	}
	public static FreeList read(String file) throws IOException, ClassNotFoundException{
		String freeFile = Util.getFreeFile(file);
		FreeList ret;
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(freeFile)));
			ret = (FreeList)in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			ret = new FreeList();
		}
		return ret;
	}
	
	public FreeList(){
		
	}
	
	public void add(int pid){
		freeList.add(pid);
	}
	
	public int poll(){
		return freeList.isEmpty()?nextPageNumber++:freeList.poll();
	}
}
