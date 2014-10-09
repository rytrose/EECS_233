import java.util.Iterator;
import java.util.LinkedList;

/**
 * Creates the initial linked list of Huffman Nodes before building the Huffman tree.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HuffmanList<T> implements Iterable<T>{
	
	private LinkedList<HuffmanNode> list;
	
	public HuffmanList(byte[] b){

	}
	
	public HuffmanList(String s){
		
	}
	
	public HuffmanList(byte[] b, int[] array){
		
	}
	
	public Iterator<T> iterator(){
		return new Iterator<T>(){
			
			public boolean hasNext(){
				return true;
			}
			
			public T next(){
				return null;
			}
			
			public void remove(){
				
			}
			
		};
	}
}