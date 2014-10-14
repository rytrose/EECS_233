import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.lang.reflect.Field;

/**
 * Creates the initial linked list of Huffman Nodes before building the Huffman tree.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HuffmanList extends LinkedList<HuffmanNode>{

	public HuffmanList(byte[] b){
		ByteCounter counter = new ByteCounter(b);
		counter.setOrder("countInc");
		for(int i = 0; i < counter.getSize(); i++)
			add(new HuffmanNode(counter.getByteAt(i), counter.getCountAt(i)));
	}
	
	public HuffmanList(String s) throws IOException{
		ByteCounter counter = new ByteCounter(s);
		counter.setOrder("countInc");
		for(int i = 0; i < counter.getSize(); i++)
			add(new HuffmanNode(counter.getByteAt(i), counter.getCountAt(i)));
	}
	
	public HuffmanList(byte[] b, int[] array) throws Exception{
		ByteCounter test = new ByteCounter(b);
		if(test.getSize() != b.length)
			throw new Exception();
		for(int i = 0; i < array.length; i++){
			if(array[i] < 1)
				throw new Exception();
		}
		ArrayList<Byte> newBytes = new ArrayList<Byte>();
		for(int i = 0; i < b.length; i++)
			newBytes.add(b[i]);
		ArrayList<Integer> newCounts = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++)
			newCounts.add(array[i]);		
		Class byteCounter = ByteCounter.class;
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