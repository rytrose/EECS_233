/**
 * ADT of an Array List that stores numbers.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class NumArrayList implements NumList {
	/**
	 * The array which holds the list of numbers.
	 */
	private double[] array;
	
	/**
	 * The size, i.e. number of elements, in the list.
	 */
	private int size;
	
	/**
	 * Constructor without a parameter creates an empty array.
	 */
	public NumArrayList(){
		array = new double[10];
		size = 0;
	}
	
	/**
	 * Constructor with a parameter creates an array of the given size.
	 * @param cap size of the array
	 */
	public NumArrayList(int cap){
		array = new double[cap];
		size = 0;
	}
	
	/**
	 * Returns the array of the list.
	 */
	public double[] getArray(){
		return array;
	}
	
	/**
	 * Sets the array of the list.
	 * @param newArray new array for the list
	 */
	public void setArray(double[] newArray){
		array = newArray;
	}
	
	/**
	 * Returns the size, i.e. number of elements, in the list.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Sets the size, i.e. number of elements in the list.
	 */
	public void setSize(int value){
		size = value;
	}
	
	/**
	 * Removes the duplicates of the list, maintaining order, and the first value of each element.
	 */
	public void removeDuplicates(){
		
	}
	
	/**
	 * Returns the capacity of the array that holds the list.
	 */
	public int capacity(){
		return array.length;
	}
	
	/**
	 * Adds a new value to the end of the list.
	 * @param value the value to be added to the list
	 */
	public void add(double value){
		if(capacity() == size()){
			double[] newArray = new double[capacity() * 2];
			for(int i = 0; i < capacity(); i++){
				newArray[i] = lookup(i);
			}
			setArray(newArray);
			getArray()[size()] = value;
			setSize(size() + 1);
		}
		else{
			getArray()[size()] = value;
			setSize(size() + 1);
		}		
	}
	
	/**
	 * Checks if another list is equal to this list.
	 * @param otherList the other list to be compared to this list
	 */
	public boolean equals(NumList otherList){
		String thisString = this.toString();
		String otherString = otherList.toString();
		return thisString.equals(otherString);
	}
	
	/**
	 * Inserts a new element at the ith index. If i > the number of elements in the list, add the value to the end of the list.
	 * @param i index of insertion
	 * @param value the value to be inserted into the list
	 */
	public void insert(int i, double value){
		if(i >= size()){
			add(value);
		}
		else{
			if(size() == capacity()){
				double[] newArray = new double[capacity() * 2];
				for(int index = 0; index < i; index++)
					newArray[index] = lookup(index);
				newArray[i] = value;
				for(int index1 = i + 1; index1 < size(); index1++)
					newArray[index1] = lookup(index1 - 1);
				setSize(size() + 1);
				setArray(newArray);
			}
			else{
				double[] newArray = new double[capacity()];
				for(int index = 0; index < i; index++)
					newArray[index] = lookup(index);
				newArray[i] = value;
				for(int index1 = i + 1; index1 - 1 < size(); index1++)
					newArray[index1] = lookup(index1 - 1);
				setSize(size() + 1);
				setArray(newArray);
			}
		}
	}
	
	
	/**
	 * Removes the i-th element.
	 * @param i index at which the value should be removed
	 */
	public void remove(int i){
		if(i < size()){
			double[] newArray = new double[capacity()];
			for(int index = 0; index < i; index++)
				newArray[index] = lookup(index);
			setSize(size() - 1);
			for(int index1 = i; index1 < size(); index1++)
				newArray[index1] = lookup(index1 + 1);
			setArray(newArray);
			
		}
	}
	
	/**
	 * Checks is a given value is in the list.
	 * @param value value that is being checked to the list
	 */
	public boolean contains(double value){
		for(int i = 0; i < size(); i++){
			if(lookup(i) == value)
				return true;
		}
		return false;
	}
	
	/**
	 * Looks up the value of the list at a specific index.
	 * @param i index at which the value is being looked up
	 */
	public double lookup(int i) throws IndexOutOfBoundsException{
		double value = 0;
		try{
			value = getArray()[i];
		}
		catch(IndexOutOfBoundsException e){
			System.err.println("IndexOutOfBoundsException:" + e.getMessage());
		}
		return value;
	}
	
	/**
	 * Turns the list contents into a String, separated by spaces.
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		if(size() == 0)
			return "";
		else{
			for(int i = 0; i < size() - 1; i++){
				builder.append(lookup(i));
				builder.append(' ');
			}
			builder.append(lookup(size() - 1));
			return builder.toString();
		}	
	}
	
}
	

