import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Computes the count of each byte in a byte array or file, and stores the result.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class ByteCounter {
	
	/**
	 * Stores the bytes to be counted. 
	 */
	private ArrayList<Byte> byteArray = new ArrayList<Byte>();
	
	/**
	 * Stores the count of each byte.
	 */
	private ArrayList<Integer> countArray = new ArrayList<Integer>();
	
	/**
	 * Stores the size of the ByteCounter.
	 */
	private int size = 0;
	
	/**
	 * Keeps track of the current order of the ByteCounter.
	 */
	private String order = "default";
	
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
				Integer value = countArray.get(byteArray.indexOf(i));
				countArray.set(byteArray.indexOf(i), value + 1);
			}	
		}
		size = byteArray.size();
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
	 * @return returns the number of instances of the byte in the array, or -1 if the byte does not occur
	 */
	public int getCount(byte b){
		return countArray.get(byteArray.indexOf(b));
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
	
	/**
	 * Returns the elements, in byte order, of the ByteCounter.
	 * @return returns a byte array of the elements in this object
	 */
	public byte[] getElements(){
		String saveOrder = order;
		byte[] array = new byte[byteArray.size()];
		setOrder("byte");
		for(int i = 0; i < byteArray.size(); i++)
			array[i] = byteArray.get(i).byteValue();
		order = saveOrder;
		return array;
	}
	/**
	 * Sets the order of the elements to either: "byte" - increasing byte order, "countInc" - increasing count order,
	 * or "countDec" - decreasing count order
	 * @param order String name of the desired order
	 */
	public void setOrder(String order){
		LinkedList<Byte> newByte = new LinkedList<Byte>();
		LinkedList<Integer> newCount = new LinkedList<Integer>();
		if(order == "byte"){
			this.order = "byte";
			newByte.add(byteArray.get(0));
			newCount.add(countArray.get(0));
			for(int i = 1; i < byteArray.size(); i++){
				// travel left while the byte is less than the next byte left
				int j = i;
				while(j > 0 && byteArray.get(i).compareTo(newByte.get(j - 1)) < 0)
					j--;
				newByte.add(j, byteArray.get(i));
				newCount.add(j, countArray.get(i));
			}
			byteArray = new ArrayList<Byte>(newByte);
			countArray = new ArrayList<Integer>(newCount);
		}
		else if(order == "countInc"){
			this.order = "countInc";
			newByte.add(byteArray.get(0));
			newCount.add(countArray.get(0));
			for(int i = 1; i < countArray.size(); i++){
				// travel left while the count is less than the next count left
				int j = i;
				while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) < 0)
					j--;
				// post condition: (j - 1) is the index of a count less than or equal to this count and all counts at and to the right of j are greater
				
				// if counts are equal, place according to byte order
				if(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0){
					// if this byte is greater, place to the right of (j - l)
					if(byteArray.get(i).compareTo(newByte.get(j - 1)) > 0){
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
					// if this byte is smaller
					else{
						// move until there's no more bytes smaller, then add at j
						while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0 && byteArray.get(i).compareTo(newByte.get(j - 1)) < 0)
							j--;
						// post condition: j - 1 is not equal in count, or this byte is greater than j - 1 
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
				}
				// if counts are not equal, place at index j
				else{
					newCount.add(j, countArray.get(i));
					newByte.add(j, byteArray.get(i));
				}
			}
			byteArray = new ArrayList<Byte>(newByte);
			countArray = new ArrayList<Integer>(newCount);
		}
		else if(order == "countDec"){
			this.order = "countDec";
			newByte.add(byteArray.get(0));
			newCount.add(countArray.get(0));
			for(int i = 1; i < countArray.size(); i++){
				// new index to traverse list
				int j = i;
				// travel left while the count is less than the next count left
				while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) > 0)
					j--;
				// post condition: (j - 1) is the index of a count greater than or equal to this count and all counts at and to the right of j are less
				
				// if counts are equal, place according to byte order
				if(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0){
					// if this byte is greater, place to the right of (j - l)
					if(byteArray.get(i).compareTo(newByte.get(j - 1)) > 0){
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
					// if this byte is smaller
					else{
						// move until there's no more bytes smaller, then add at j
						while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0 && byteArray.get(i).compareTo(newByte.get(j - 1)) < 0)
							j--;
						// post condition: j - 1 is not equal in count, or this byte is greater than j - 1 
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
				}
				// if counts are not equal, place at index j
				else{
					newCount.add(j, countArray.get(i));
					newByte.add(j, byteArray.get(i));
				}
			}
			byteArray = new ArrayList<Byte>(newByte);
			countArray = new ArrayList<Integer>(newCount);
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Returns a String with the integer value of the byte, and its related count.
	 * @return returns the String representation of the bytes and their related counts
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < byteArray.size() - 1; i++){
			builder.append(byteArray.get(i).intValue());
			builder.append(':');
			builder.append(countArray.get(i));
			builder.append(' ');
		}
		builder.append(byteArray.get(byteArray.size() - 1).intValue());
		builder.append(':');
		builder.append(countArray.get(byteArray.size() - 1));
		return builder.toString();
	}
	
	/**
	 * Returns a String with the integer value of the byte, and its related count, in a specified format.
	 * @param format if the parameter is "char", the byte will be represented as an ASCII character
	 * @return returns the String representation of the bytes and their related counts
	 */
	public String toString(String format){
		if(format == "char"){
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < byteArray.size() - 1; i++){
				builder.append((char)(byteArray.get(i).byteValue()));
				builder.append(':');
				builder.append(countArray.get(i));
				builder.append(' ');
			}

			builder.append((char)(byteArray.get(byteArray.size() - 1).byteValue()));
			builder.append(':');
			builder.append(countArray.get(byteArray.size() - 1));
			return builder.toString();
		}
		else
			return toString();
	}
	
	/**
	 * Returns the byte at a particular index.
	 * @param i index at which to retrieve the byte
	 * @return returns the byte at a particular index
	 */
	public byte getByteAt(int i){
		return byteArray.get(i).byteValue();
	}
	
	/**
	 * Returns the count at a particular index.
	 * @param i index at which to retrieve the count
	 * @return returns the count at a particular index
	 */
	public int getCountAt(int i){
		return countArray.get(i);
	}
	/**
	 * Returns the size of the ByteCounter.
	 * @return returns the size of the ByteCounter
	 */
	public int getSize(){
		return size;
	}
}
