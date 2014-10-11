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
	
	private int size = 0;
	
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
	
	
	public byte[] getElements(){
		String saveOrder = order;
		byte[] array = new byte[byteArray.size()];
		setOrder("byte");
		for(int i = 0; i < byteArray.size(); i++)
			array[i] = byteArray.get(i).byteValue();
		order = saveOrder;
		return array;
	}
	
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
		if(order == "countInc"){
			this.order = "countInc";
			newByte.add(byteArray.get(0));
			newCount.add(countArray.get(0));
			for(int i = 1; i < countArray.size(); i++){
				// travel left while the count is less than the next count left
				int j = i;
				while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) < 0)
					j--;
				// if counts are equal, place according to byte order
				if(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0){
					while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0)
						j--;
					// if this byte is smaller, place to the left
					if(byteArray.get(i).compareTo(newByte.get(j)) < 0){
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
					// if this byte is bigger, place to the right
					else{
						newCount.add(j + 1, countArray.get(i));
						newByte.add(j + 1, byteArray.get(i));
					}
				}
				else{
					newCount.add(j, countArray.get(i));
					newByte.add(j, byteArray.get(i));
				}
			}
			byteArray = new ArrayList<Byte>(newByte);
			countArray = new ArrayList<Integer>(newCount);
		}
		if(order == "countDec"){
			this.order = "countDec";
			newByte.add(byteArray.get(0));
			newCount.add(countArray.get(0));
			for(int i = 1; i < countArray.size(); i++){
				// travel left while the count is greater than the next count left
				int j = i;
				while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) > 0)
					j--;
				// if counts are equal, place according to byte order
				if(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0){
					while(j > 0 && countArray.get(i).compareTo(newCount.get(j - 1)) == 0)
						j--;
					// if this byte is smaller, place to the left
					if(byteArray.get(i).compareTo(newByte.get(j)) < 0){
						newCount.add(j, countArray.get(i));
						newByte.add(j, byteArray.get(i));
					}
					// if this byte is bigger, place to the right
					else{
						newCount.add(j + 1, countArray.get(i));
						newByte.add(j + 1, byteArray.get(i));
					}
				}
				else{
					newCount.add(j, countArray.get(i));
					newByte.add(j, byteArray.get(i));
				}
			}
			byteArray = new ArrayList<Byte>(newByte);
			countArray = new ArrayList<Integer>(newCount);
		}
	}
	
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
	
	public byte getByte(int i){
		return byteArray.get(i).byteValue();
	}
	
	public int getCount(int i){
		return countArray.get(i);
	}
	
	public int getSize(){
		return size;
	}
}
