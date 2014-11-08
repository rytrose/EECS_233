
/**
 * This is a hash table for String keys.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HashTable {
	
	/**
	 * Creates a table of default size -----.
	 */
	public HashTable(){
		
	}
	
	/**
	 * Creates a table of specified size.
	 * @param size specified size of the table
	 */
	public HashTable(int size){
		
	}
	
	/**
	 * Stores a key-value pair in the hash table.
	 * @param key String key of the entry
	 * @param value int value of the entry
	 */
	public void put(String key, int value){
		
	}
	
	/**
	 * Stores a key-value pair in the hash table, but given a specific hash code.
	 * @param key String key of the entry
	 * @param value int value of the entry
	 * @param hashCode the specific hash code for this entry
	 */
	public void put(String key, int value, int hashCode){
		
	}
	
	/**
	 * Updates the value of an entry with a given key.
	 * @param key String key of the entry to be changed
	 * @param value int updated value of the entry
	 */
	public void update(String key, int value){
		
	}
	
	/**
	 * Returns the value associated with a given key, or -1 if the key does not exist.
	 * @param key String key value to be gotten
	 */
	public int get(String key){
		return -1;
	}
	
	/**
	 * Returns the value associated with a given key and hash value, or -1 if the key does not exist.
	 * @param key String key value to be gotten
	 * @param hashCode the specific hash code for this entry
	 */
	public int get(String key, int hashCode){
		return -1;
	}
}
