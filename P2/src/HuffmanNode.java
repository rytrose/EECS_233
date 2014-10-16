/**
 * Node class that functions as a linked list and binary tree node.
 * EECS 233, Prof. Lewicki
 * @author Ryan
 */
public class HuffmanNode {
	
	/**
	 * Stores the byte value of the node.
	 */
	public byte b;
	
	/**
	 * Stores the number of times the byte occurs in the original data.
	 */
	public int count;
	
	/**
	 * Stores the Huffman encoded value for the byte.
	 */
	public boolean[] code;
	
	/**
	 * Stores the left child HuffmanNode.
	 */
	public HuffmanNode left;
	
	/**
	 * Stores the right child HuffmanNode.
	 */
	public HuffmanNode right;
	
	/**
	 * Constructor that initializes the node with given parameters.
	 * @param b value of the byte for this node
	 * @param c count of the byte for this node
	 */
	public HuffmanNode(byte b, int c){
		this.count = c;
		this.b = b;
		left = null;
		right = null;
	}
	
	/**
	 * Returns the count of the node.
	 * @return returns the count of the node
	 */
	public int getCount(){
		return this.count;
	}
	
	/**
	 * Sets the count for this node.
	 * @param count the value to set the count
	 */
	public void setCount(int count){
		this.count = count;
	}
}
