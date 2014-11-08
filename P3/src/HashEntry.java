
/**
 * Class used as an entry into the HashTable class.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HashEntry {
	/**
	 * Stores the key of the entry.
	 */
	private String key;
	
	/**
	 * Stores the value of the entry.
	 */
	private int value;
	
	/**
	 * Creates a new HashEntry given a String key and int value.
	 */
	public HashEntry(String key, int value){
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Returns the key of the entry.
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * Returns the value of the entry.
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Sets the value of the entry.
	 * @param val the desired value update
	 */
	public void setValue(int val){
		value = val;
	}
}
