package fatworm.io;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

import fatworm.page.BTreePage;
import fatworm.page.DataPage;
import fatworm.page.Page;
import fatworm.page.RawPage;
import fatworm.util.Util;

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
	
	public synchronized BTreePage getBTreePage(BTree btree, int pageid, int type, boolean create) throws Throwable{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (BTreePage) ret2;
		BTreePage p = new BTreePage(btree, dataFile, pageid, type, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	public synchronized DataPage getDataPage(int pageid, boolean create) throws Throwable{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (DataPage) ret2;
		DataPage p = new DataPage(dataFile, pageid, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	public synchronized RawPage getRawPage(int pageid, boolean create) throws Throwable{
		Page ret2 = getPageHelper(pageid);
		if(ret2 != null)return (RawPage) ret2;
		RawPage p = new RawPage(dataFile, pageid, create);
		pages.put(pageid, p);
		victimQueue.add(p);
		return p;
	}
	
	private synchronized Page getPageHelper(int pageid) throws Throwable {
		if(pages.containsKey(pageid))
			return pages.get(pageid);
		synchronized(victimQueue) {
			while(pages.size() >= maxPages){
				Page victim = victimQueue.pollFirst();
				Collection<Page> tmpV = new LinkedList<Page> ();
				while(victim.isInTransaction() && !victimQueue.isEmpty()){
					tmpV.add(victim);
					victim = victimQueue.pollFirst();
				}
				if(victim.isInTransaction())
					Util.warn("flushing out a page still in transaction, will try to recover.");
				victim.flush();
				pages.remove(victim.getID());
				victimQueue.addAll(tmpV);
			}
		}
		return null;
	}

	public void close() throws Throwable {
		for(Page p : pages.values()){
			p.flush();
		}
		dataFile.close();
		FreeList.write(fileName, freeList);
	}
	
	public synchronized void releasePage(Integer pageID){
		freeList.add(pageID);
		Page z = pages.get(pageID);
		if(z != null){
			z.markFree();
			victimQueue.remove(z);
		}
		//TODO
	}
	
	public synchronized Integer newPage(){
		return freeList.poll();
	}

	public synchronized RawPage newRawPage(int size) throws Throwable {
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
