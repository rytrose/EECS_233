/**
 * Node class that functions as a linked list and binary tree node.
 * EECS 233, Prof. Lewicki
 * @author Ryan
 */
public class HuffmanNode {

	private byte b;
	
	private int count;
	
	private boolean[] code;
	
	private HuffmanNode next;
	
	private HuffmanNode left;
	
	private HuffmanNode right;
	
	public HuffmanNode(int count, byte b){
		this.count = count;
		this.b = b;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
}
