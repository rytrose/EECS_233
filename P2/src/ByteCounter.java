import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Computes the count of each byte in a byte array or file, and stores the result.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class ByteCounter {
	
	/**
	 * Stores the byte array of bytes to be counted. 
	 */
	private byte[] byteArray;
	
	/**
	 * Constructor that initializes the byte array with a given array.
	 * @param b array of bytes to be counted
	 */
	public ByteCounter(byte[] b){
		byteArray = b;
	}
	
	/**
	 * Constructor that initializes the byte array read from a file whose path string is input.
	 * @param s path string of the file to be counted
	 */
	public ByteCounter(String s){
		try {
			byteArray = Files.readAllBytes(Paths.get(s));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File does not exist in hierarchy.");
		}
	}
	
	/**
	 * Returns the count of a particular byte in the array.
	 * @param b the byte to be counted in the array
	 * @return returns the number of instances of the byte in the array
	 */
	public int getCount(byte b){
		int count = 0;
		for(int i = 0; i < byteArray.length; i++){
			if(byteArray[i] == b)
				count++;
		}
		return count;
	}
	
	/**
	 * Returns an array of the counts of each byte in a given array.
	 * @param b
	 * @return
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
