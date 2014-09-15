/**
 * ADT of an Array List that stores numbers.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class NumArrayList {
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
}
	

