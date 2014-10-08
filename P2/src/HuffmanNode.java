/**
 * Node class that functions as a linked list and binary tree node.
 * EECS 233, Prof. Lewicki
 * @author Ryan
 */
public class HuffmanNode {

	public byte b;
	
	public int count;
	
	public boolean[] code;
	
	public HuffmanNode next;
	
	public HuffmanNode left;
	
	public HuffmanNode right;
	
	public HuffmanNode(byte b, int c){
		this.count = c;
		this.b = b;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
}
