import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

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
	
	public HuffmanList(byte[] b, int[] array){
		
	}
}