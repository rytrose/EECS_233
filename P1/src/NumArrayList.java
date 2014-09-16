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
	
	public double[] getArray(){
		return array;
	}
	
	/**
	 * Returns the size, i.e. number of elements, in the list.
	 */
	public int size(){
		return size;
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
		if(array.length == size){
			double[] newArray = new double[array.length * 2];
			for(int i = 0; i < array.length; i++){
				newArray[i] = array[i];
			}
			array = newArray;
			array[size] = value;
			size++;
		}
		else{
			array[size] = value;
			size++;
		}		
	}
	
	/**
	 * Checks if another list is equal to this list.
	 * @param otherList the other list to be compared to this list
	 */
	public boolean equals(NumList otherList){
		return true;
	}
	
	/**
	 * Inserts a new element at the ith index. If i > the number of elements in the list, add the value to the end of the list.
	 * @param i index of insertion
	 * @param value the value to be inserted into the list
	 */
	public void insert(int i, double value){
		
	}
	
	/**
	 * Removes the i-th element.
	 * @param i index at which the value should be removed
	 */
	public void remove(int i){
		
	}
	
	/**
	 * Checks is a given value is in the list.
	 * @param value value that is being checked to the list
	 */
	public boolean contains(double value){
		return true;
	}
	
	/**
	 * Looks up the value of the list at a specific index.
	 * @param i index at which the value is being looked up
	 */
	public double lookup(int i){
		return 0.0;
	}
	
	/**
	 * Turns the list contents into a String, separated by spaces.
	 */
	public String toString(){
		return "";
	}
	
}
	

