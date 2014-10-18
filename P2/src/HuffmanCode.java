import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Creates the tree and Huffman code.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HuffmanCode {
	
	/**
	 * Stores the original HuffmanList.
	 */
	private HuffmanList originalList;
	
	/**
	 * Stores the HuffmanList that has been formed into a tree.
	 */
	private HuffmanList tree;
	
	/**
	 * Creates a Huffman tree given an array of bytes.
	 * @param b array of bytes to make the Huffman tree
	 */
	public HuffmanCode(byte[] b){
		HuffmanList list = new HuffmanList(b);
		originalList = list;
		while(list.size() > 1){
			HuffmanNode[] lowestTwo = lowestTwo(list);
			HuffmanNode newNode = new HuffmanNode((byte) 0, lowestTwo[0].count + lowestTwo[1].count);
			newNode.left = lowestTwo[0];
			newNode.right = lowestTwo[1];
			list.add(newNode);
			list.remove(lowestTwo[0]);
			list.remove(lowestTwo[1]);
		}
		tree = list;
		traverseAndBuild(tree.element());
	}
	
	/**
	 * Creates a Huffman tree given a String that is an address of a text file.
	 * @param s a location of a file from which to make the Huffman tree
	 * @throws IOException
	 */
	public HuffmanCode(String s) throws IOException{
		this(Files.readAllBytes(Paths.get(s)));
	}
	
	/**
	 * Creates a Huffman tree from an array of bytes, and an array of the bytes' respective counts.
	 * @param b byte array 
	 * @param array array of respective counts for each byte
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 */
	public HuffmanCode(byte[] b, int[] array) throws IllegalArgumentException, NoSuchFieldException, SecurityException, IllegalAccessException{
		HuffmanList list = new HuffmanList(b, array);
		originalList = list;
		while(list.size() > 1){
			HuffmanNode[] lowestTwo = lowestTwo(list);
			HuffmanNode newNode = new HuffmanNode((byte) 0, lowestTwo[0].count + lowestTwo[1].count);
			newNode.left = lowestTwo[0];
			newNode.right = lowestTwo[1];
			list.add(newNode);
			list.remove(lowestTwo[0]);
			list.remove(lowestTwo[1]);
		}
		tree = list;
		traverseAndBuild(tree.element());
	}
	
	public boolean[] code(byte b){
		if(traverseAndSearch(tree.getFirst(), b) == null)
			throw new IllegalArgumentException();
		return traverseAndSearch(tree.getFirst(), b).code;
	}
	
	public String codeString(byte b) throws Exception{
		boolean[] code = code(b);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < code.length ; i++){
			if(code[i] = true)
				builder.append(1);
			else
				builder.append(0);
		}
		return builder.toString();
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int i = originalList.size() - 1; i > 0 ; i--){
			builder.append(originalList.get(i).b);
			builder.append(':');
			builder.append(' ');
			try {
				builder.append(codeString(originalList.get(i).b));
			} catch (Exception e) {
				e.printStackTrace();
			}
			builder.append('\n');
		}
		builder.append(originalList.get(0).b);
		builder.append(':');
		builder.append(' ');
		try {
			builder.append(codeString(originalList.get(0).b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * Returns the HuffmanNodes with the two smallest counts.
	 * @param list list from which the lowest two nodes are taken
	 * @return an array where index 0 is the smallest count, and index 1 is the second smallest
	 */
	private HuffmanNode[] lowestTwo(HuffmanList list){
		HuffmanNode smallest = null;
		HuffmanNode second = null;
		smallest = list.get(0);
		if(list.size() == 2 && list.get(1).count > list.get(0).count)
			second = list.get(1);
		else{
			second = smallest;
			smallest = list.get(1);
		}
		if(list.size() > 2){
			for(int i = 2; i < list.size(); i++){
				if(list.get(i).count < smallest.count){
					second = smallest;
					smallest = list.get(i);
				}
				else if(list.get(i).count < second.count)
					second = list.get(i);
			}
		}	
		return new HuffmanNode[]{smallest, second};
	}
	
	/**
	 * Traverses the tree and builds code for the nodes.
	 * @param root HuffmanNode that serves as the root for the traversal.
	 */
	private void traverseAndBuild(HuffmanNode root){
		ArrayList<Boolean> code = new ArrayList<Boolean>();
		if(root.left != null){
			code.add(false);
			traverseAndBuild(root.left);
		}
		if(root.right != null){
			code.add(true);
			traverseAndBuild(root.right);
		}
		if(root.left == null && root.right == null){
			boolean[] encoded = new boolean[code.size()];
			for(int i = 0; i < encoded.length; i ++){
				encoded[i] = code.get(i).booleanValue();
			}
			root.code = encoded;
		}
	}
	
	/**
	 * Traverses the string until it finds a node with a given byte.
	 * @param root the root of the Huffman tree
	 * @param b byte that is being searched for
	 * @return the HuffmanNode containing the byte desired
	 */
	private HuffmanNode traverseAndSearch(HuffmanNode root, byte b){
		if(root.b == b)
			return root;
		if(root.left != null)
			return traverseAndSearch(root.left, b);
		if(root.right != null)
			return traverseAndSearch(root.right, b);
		return null;
	}
}
