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
		list = new NumArrayList(array.length)
;		for(int i = 0; i < array.length; i++){
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
	 * @return returns the size of the set
	 */
	public int size(){
		return getList().size();
	}
	
	/**
	 * Returns true if the parameter can be found in the set.
	 * @return returns true if the parameter is in the set
	 */
	public boolean contains(double value){
		return getList().contains(value);
	}
	
	/**
	 * Returns a new NumSet that is the intersection of two given NumSets.
	 * @param S1 first set
	 * @param S2 second set
	 * @return returns a new set that is the intersection of S1 and S2
	 */
	public static NumSet intersect(NumSet S1, NumSet S2){
		if(S1.size() > S2.size()){
			int intersectArraySize = 0;
			for(int i = 0; i < S2.size(); i++){
				if(S1.contains(S2.getList().lookup(i))){
					intersectArraySize++;
				}
			}
			if(intersectArraySize == 0)
				return null;
			double[] array = new double[intersectArraySize];
			int index = 0;
			for(int i = 0; i < intersectArraySize; i++){
				if(S1.contains(S2.getList().lookup(i))){
					array[index] = S2.getList().lookup(i);
					index++;
				}
			}
			NumSet intersection = new NumSet(array);
			return intersection;
		}
		else{
			int intersectArraySize = 0;
			for(int i = 0; i < S1.size(); i++){
				if(S2.contains(S1.getList().lookup(i))){
					intersectArraySize++;
				}
			}
			if(intersectArraySize == 0)
				return null;
			double[] array = new double[intersectArraySize];
			int index = 0;
			for(int i = 0; i < intersectArraySize; i++){
				if(S2.contains(S1.getList().lookup(i))){
					array[index] = S1.getList().lookup(i);
					index++;
				}
			}
			NumSet intersection = new NumSet(array);
			return intersection;
		}
	}
	
	/**
	 * Returns a new NumSet that is the union of the given sets.
	 * @param S1 first set
	 * @param S2 second set
	 * @return returns a new set that is the union of S1 and S2
	 */
	public static NumSet union(NumSet S1, NumSet S2){
		double[] array = new double[S1.size() + S2.size()];
		for(int i = 0; i < S1.size(); i++)
			array[i] = S1.getList().lookup(i);
		for(int i = S1.size(); i < (S1.size() + S2.size()); i++)
			array[i] = S2.getList().lookup(i - S1.size());
		NumSet set = new NumSet(array);
		set.getList().removeDuplicates();
		return set;
	}
	
	/**
	 * Turns the list contents into a String, separated by spaces.
	 */
	public String toString(){
		return getList().toString();
	}
	
	
	/**
	 * Returns true if the given sets are equivalent in their contents.
	 * @param S1 first set
	 * @param S2 second set
	 * @return returns true if the given sets are equivalent
	 */
	public static boolean equivalence(NumSet S1, NumSet S2){
		if(S1.size() != S2.size())
			return false;
		for(int i = 0; i < S1.size(); i++){
			if(!S1.contains(S2.getList().lookup(i)))
				return false;
		}
		return true;
	}	
}
