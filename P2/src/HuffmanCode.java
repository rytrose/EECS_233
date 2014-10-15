import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creates the tree and Huffman code.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class HuffmanCode {
	
	private HuffmanList tree;
	
	public HuffmanCode(byte[] b){
		HuffmanList list = new HuffmanList(b);
		while(list.size() > 1){
			HuffmanNode[] lowestTwo = lowestTwo(list);
			HuffmanNode newNode = new HuffmanNode((byte) 0, lowestTwo[0].count + lowestTwo[1].count);
			newNode.left = lowestTwo[0];
			newNode.right = lowestTwo[1];
			list.remove(lowestTwo[0]);
			list.remove(lowestTwo[1]);
			list.add(newNode);
		}
		tree = list;
	}
	
	
	public HuffmanCode(String s) throws IOException{
		this(Files.readAllBytes(Paths.get(s)));
	}
	
	public HuffmanCode(byte[] b, int[] array) throws Exception{
		HuffmanList list = new HuffmanList(b, array);
		while(list.size() > 1){
			HuffmanNode[] lowestTwo = lowestTwo(list);
			HuffmanNode newNode = new HuffmanNode((byte) 0, lowestTwo[0].count + lowestTwo[1].count);
			newNode.left = lowestTwo[0];
			newNode.right = lowestTwo[1];
			list.remove(lowestTwo[0]);
			list.remove(lowestTwo[1]);
			list.add(newNode);
		}
		tree = list;
	}
	
	public boolean[] code(byte b){
		return null;
	}
	
	public String codeString(byte b){
		return "";
	}
	
	public String toString(){
		return "";
	}
	
	/**
	 * Returns the HuffmanNodes with the two smallest counts.
	 * @param list
	 * @return an array where index 0 is the smallest count, and index 1 is the second smallest
	 */
	private HuffmanNode[] lowestTwo(HuffmanList list){
		HuffmanNode smallest = null;
		HuffmanNode second = null;
		smallest = list.get(0);
		if(list.get(1).count > list.get(0).count)
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
}
