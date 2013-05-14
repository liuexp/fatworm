package fatworm.io;


abstract class BKey implements Comparable<BKey>{
	public abstract void delete() throws Throwable;	// to reclaim space from data/string page.
	public abstract int compareTo(BKey o);
	public int keySize(){
		return Integer.SIZE / Byte.SIZE;
	}
	public abstract byte[] toByte() throws Throwable;
}