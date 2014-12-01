
/**
 * Class used as an entry into the HashTable class.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HashEntry implements Comparable<HashEntry>{
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
	
	/**
	 * Compares two HashEntrys by their value. Returns 1 if this HashEntry has a value greater than the compared,
	 * returns -1 if this HashEntry has a value less than the compared, and returns 0 if the values are equal.
	 * @param entry the other HashEntry being compared
	 */
	public int compareTo(HashEntry entry){
		if(getValue() > entry.getValue())
			return 1;
		else if(getValue() == entry.getValue()){
			return 0;
		}			
		else
			return -1;
	}
		
}
/*
  if(getKey().compareTo(entry.getKey()) > 1)
				return 2;
			else
				return -2;
 */
