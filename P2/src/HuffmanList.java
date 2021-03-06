import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.reflect.Field;

/**
 * Creates the initial linked list of Huffman Nodes before building the Huffman tree.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
@SuppressWarnings("serial")
public class HuffmanList extends LinkedList<HuffmanNode>{
	
	/**
	 * Constructor that builds a HuffmanList from an array of bytes.
	 * @param b array of bytes from which to build the HuffmanList 
	 */
	public HuffmanList(byte[] b){
		ByteCounter counter = new ByteCounter(b);
		counter.setOrder("countInc");
		for(int i = 0; i < counter.getSize(); i++)
			add(new HuffmanNode(counter.getByteAt(i), counter.getCountAt(i)));
	}
	
	/**
	 * Constructor that builds a HuffmanList from a file, whose file name is given via a String.
	 * @param s
	 * @throws IOException
	 */
	public HuffmanList(String s) throws IOException{
		ByteCounter counter = new ByteCounter(s);
		counter.setOrder("countInc");
		for(int i = 0; i < counter.getSize(); i++)
			add(new HuffmanNode(counter.getByteAt(i), counter.getCountAt(i)));
	}
	
	/**
	 * Constructor that builds a HuffmanList from a byte[] of bytes and an int[] of the byte's respective counts.
	 * @param b byte array of bytes to be in the HuffmanList
	 * @param array int array of the byte's respective counts
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 */
	public HuffmanList(byte[] b, int[] array) throws IllegalArgumentException, NoSuchFieldException, SecurityException, IllegalAccessException{
		ByteCounter test = new ByteCounter(b);
		if(test.getSize() != b.length)
			throw new IllegalArgumentException();
		for(int i = 0; i < array.length; i++){
			if(array[i] < 1)
				throw new IllegalArgumentException();
		}
		ArrayList<Byte> newBytes = new ArrayList<Byte>();
		for(int i = 0; i < b.length; i++)
			newBytes.add(b[i]);
		ArrayList<Integer> newCounts = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++)
			newCounts.add(array[i]);		
		Class<ByteCounter> byteCounter = ByteCounter.class;
		Field f = byteCounter.getDeclaredField("byteArray");
		f.setAccessible(true);
		f.set(test, newBytes);
		Field f1 = byteCounter.getDeclaredField("countArray");
		f1.setAccessible(true);
		f1.set(test, newCounts);
		test.setOrder("countInc");
		for(int i = 0; i < test.getSize(); i++)
			add(new HuffmanNode(test.getByteAt(i), test.getCountAt(i)));
	}
	
	
}	