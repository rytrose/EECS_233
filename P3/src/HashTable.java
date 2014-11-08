import java.util.ArrayList;
import java.util.LinkedList;


/**
 * This is a hash table for String keys.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HashTable {
	
	/**
	 * Stores the hash table.
	 */
	private ArrayList<LinkedList<HashEntry>> table;
	
	/**
	 * Stores the table size.
	 */
	private double tableSize;
	
	/**
	 * Stores the number of items in the table.
	 */
	private double numItems = 0;
	
	/**
	 * Creates a table of default size 100.
	 */
	public HashTable(){
		table = new ArrayList<LinkedList<HashEntry>>(100);
		tableSize = 100;
		for(int i = 0; i < tableSize; i++){
			table.add(i, new LinkedList<HashEntry>());
		}
	}
	
	/**
	 * Creates a table of specified size.
	 * @param size specified size of the table
	 */
	public HashTable(int size){
		table = new ArrayList<LinkedList<HashEntry>>(size);
		tableSize = size;
		for(int i = 0; i < tableSize; i++){
			table.add(i, new LinkedList<HashEntry>());
		}
	}
	
	/**
	 * Stores a key-value pair in the hash table.
	 * @param key String key of the entry
	 * @param value int value of the entry
	 */
	public void put(String key, int value){
		// if the load factor is less than 1
		if(((numItems + 1) / tableSize) < 1.0){
			HashEntry entry = new HashEntry(key, value);
			int hash = Math.abs(entry.getKey().hashCode()) % (int)(tableSize);
			table.get(hash).add(entry);
			numItems = numItems + 1;
		}
		else{
			doubleSize();
			HashEntry entry = new HashEntry(key, value);
			int hash = Math.abs(entry.getKey().hashCode()) % (int)(tableSize);
			table.get(hash).add(entry);
			numItems = numItems + 1;
		}
	}
	
	/**
	 * Stores a key-value pair in the hash table, but given a specific hash code.
	 * @param key String key of the entry
	 * @param value int value of the entry
	 * @param hashCode the specific hash code for this entry
	 */
	public void put(String key, int value, int hashCode){
		// if the load factor is less than 1
		if(((numItems + 1) / tableSize) < 1){
			HashEntry entry = new HashEntry(key, value);
			int hash = Math.abs(hashCode) % (int)(tableSize);
			table.get(hash).add(entry);
			numItems = numItems + 1;
		}
		else{
			doubleSize();
			HashEntry entry = new HashEntry(key, value);
			int hash = Math.abs(hashCode) % (int)(tableSize);
			table.get(hash).add(entry);
			numItems = numItems + 1;
		}
	}
	
	/**
	 * Updates the value of an entry with a given key.
	 * @param key String key of the entry to be changed
	 * @param value int updated value of the entry
	 */
	public void update(String key, int value){
		int hash = Math.abs(key.hashCode()) % (int)(tableSize);
		LinkedList<HashEntry> list = table.get(hash);
		if(list.size() == 0)
			put(key, value);
		else{
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getKey() == key)
					list.get(i).setValue(value);
			}
		}
	}
	
	/**
	 * Returns the value associated with a given key, or -1 if the key does not exist.
	 * @param key String key value to be gotten
	 */
	public int get(String key){
		int hash = Math.abs(key.hashCode()) % (int)(tableSize);
		LinkedList<HashEntry> list = table.get(hash);
		if(list.size() == 0)
			return -1;
		else{
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getKey() == key)
					return list.get(i).getValue();
			}
		}
		return -1;
	}
	
	/**
	 * Returns the value associated with a given key and hash value, or -1 if the key does not exist.
	 * @param key String key value to be gotten
	 * @param hashCode the specific hash code for this entry
	 */
	public int get(String key, int hashCode){
		int hash = Math.abs(hashCode) % (int)(tableSize);
		LinkedList<HashEntry> list = table.get(hash);
		if(list.size() == 0)
			return -1;
		else{
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getKey() == key)
					return list.get(i).getValue();
			}
		}
		return -1;
	}
	
	// HELPERS
	
	/**
	 * Returns the table of the HashTable.
	 */
	public ArrayList<LinkedList<HashEntry>> getTable(){
		return table;
	}
	
	/**
	 * Returns the number of items in the table.
	 */
	public double getNumItems(){
		return numItems;
	}
	
	/**
	 * Doubles the size and rehashes the hash table.
	 */
	private void doubleSize(){
		HashTable newTable = new HashTable((int)(tableSize) * 2);
		for(LinkedList<HashEntry> l : table){
			for(HashEntry entry : l){
				newTable.put(entry.getKey(), entry.getValue());
			}
		}
		tableSize = tableSize *2;
		numItems = newTable.getNumItems();
		table = newTable.getTable();
	}
	
	
}
