/**
 * ADT of a set that stores numbers.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class NumSet{
	/**
	 * NumArrayList that stores the list data.
	 */
	private NumArrayList list;
	
	/**
	 * Constructor that takes an array as the set values.
	 * @param array array parameter that defines the set
	 */
	public NumSet(double[] array){
		for(int i = 0; i < array.length; i++){
			list.add(array[i]);
		}
		list.removeDuplicates();
	}
	
	/**
	 * Returns the NumArrayList that is storing the set contents.
	 * @return the NumArrayList that is storing the set contents
	 */
	private NumArrayList getList(){
		return list;
	}
	
	/**
	 * Returns the size of the set.
	 */
	public int size(){
		return getList().size();
	}
	
	
	
	
	
}
