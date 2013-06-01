package fatworm.io;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

import fatworm.page.BTreePage;
import fatworm.page.DataPage;
import fatworm.page.Page;
import fatworm.page.RawPage;

public class BufferManager {
	private static final long maxPages = 1024 * 1024 / 4; 	//maximum #pages a buffer manager can hold
	public File dataFile;
	public FreeList freeList;
	public Map<Integer, Page> pages = new TreeMap<Integer, Page>();
	
	//TODO I'm using LRU instead of MRU for now.
	public TreeSet<Page> victimQueue= new TreeSet<Page> (new Comparator<Page>(){
		public int compare(Page a, Page b){
			return a.getTime().compareTo(b.getTime());
		}
	});
	
	public String fileName = null;
	
	public BufferManager(String file) throws IOException, ClassNotFoundException{
		dataFile = new File(file);
		try{
			freeList = FreeList.read(file);
		} catch (java.io.FileNotFoundException e) {
			freeList = new FreeList();
			FreeList.write(file, freeList);
		}
		fileName = file;
	}
	
	public BTreePage getBTreePage(int pageid, int type, boolean create) throws IOException{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (BTreePage) ret2;
		BTreePage p = new BTreePage(dataFile, pageid, type, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	public DataPage getDataPage(int pageid, boolean create) throws IOException{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (DataPage) ret2;
		DataPage p = new DataPage(dataFile, pageid, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	public RawPage getRawPage(int pageid, boolean create) throws IOException{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (RawPage) ret2;
		RawPage p = new RawPage(dataFile, pageid, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	private Page getPageHelper(int pageid) throws IOException {
		if(pages.containsKey(pageid))
			return pages.get(pageid);
		
		while(pages.size() >= maxPages){
			Page victim = victimQueue.pollFirst();
			victim.flush();
			pages.remove(victim.getID());
		}
		return null;
	}

	public void close() throws IOException {
		for(Page p : pages.values()){
			p.flush();
		}
		dataFile.close();
		FreeList.write(fileName, freeList);
	}
	
	public void releasePage(Integer pageID){
		freeList.add(pageID);
		Page z = pages.get(pageID);
		if(z != null){
			z.markFree();
			victimQueue.remove(z);
		}
		//TODO
	}
	
	public Integer newPage(){
		return freeList.poll();
	}

	public RawPage newRawPage(int size) throws Throwable {
		Integer ret = RawPage.newPoolPage(size);
		if(ret == null) {
			ret = newPage();
			return getRawPage(ret, true);
		}else {
			RawPage rp = getRawPage(ret, false);
			rp.newEntry();
			return rp;
		}
	}
}
