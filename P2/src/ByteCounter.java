import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Computes the count of each byte in a byte array or file, and stores the result.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class ByteCounter {
	
	/**
	 * Stores the bytes to be counted. 
	 */
	private ArrayList<Byte> byteArray;
	
	/**
	 * Stores the count of each byte.
	 */
	private ArrayList<Integer> countArray;
	
	/**
	 * Constructor that initializes the byte array with a given array.
	 * @param b array of bytes to be counted
	 */
	public ByteCounter(byte[] b){
		for(byte i : b){
			if(!byteArray.contains(i)){
				byteArray.add(i);
				countArray.add(1);
			}
			else{
				Integer value = new Integer(countArray.get(byteArray.indexOf(i)));
				countArray.set(byteArray.indexOf(i), value++);
			}	
		}	
	}
	
	/**
	 * Constructor that initializes the byte array read from a file whose path string is input.
	 * @param s path string of the file to be counted
	 * @throws IOException 
	 */
	public ByteCounter(String s) throws IOException{
		this(Files.readAllBytes(Paths.get(s)));
	}
	
	/**
	 * Returns the count of a particular byte in the array.
	 * @param b the byte to be counted in the array
	 * @return returns the number of instances of the byte in the array
	 */
	public int getCount(byte b){
		return 0;
	}
	
	/**
	 * Returns an int array of the counts of each byte in a given array.
	 * @param b the byte array to have bytes counted
	 * @return returns an int array of the count of each byte
	 */
	public int[] getCount(byte[] b){
		int[] count = new int[b.length];
		for(int i = 0; i < b.length; i++)
			count[i] = getCount(b[i]);
		return count;
	}
	
	
	public byte[] getElements(){
		return null;
	}
	
	public void setOrder(String order){
		
	}
	
	public String toString(){
		return "";
	}
	
	public String toString(String format){
		return "";
	}	
}
