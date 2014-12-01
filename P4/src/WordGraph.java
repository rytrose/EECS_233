import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Creates a graph of words from a file.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class WordGraph {
	
	/**
	 * Node for each word in the graph.
	 */
	public class WordNode{
		
		public String word;
		public int count;
		public LinkedList<WordPair> adjFor = new LinkedList<WordPair>();
		public LinkedList<WordPair> adjBack = new LinkedList<WordPair>();
		public boolean encountered;
		public WordNode parent = null;
		
		public WordNode(String s, int i){
			word = s;
			count = i;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof WordNode){
				return word.equals(((WordNode) o).word);
			}
			else
				return false;
		}
		
		public void increaseCount(int i){
			count = count + i;
		}
				
	}
	
	/**
	 * Pair of words, aka an edge in the graph.
	 */
	public class WordPair{
		
		public WordNode targetWord;
		public int count;
		
		public WordPair(WordNode n, int i){
			targetWord = n;
			count = i;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof WordPair){
				return targetWord.equals(((WordPair) o).targetWord);
			}
			else
				return false;
		}
		
		public void increaseCount(int i){
			count = count + i;
		}
		
	}
	
	private int numWords;
	private int maxWords;
	private ArrayList<String> list;
	private ArrayList<WordNode> graph;

	public WordGraph(String file) throws FileNotFoundException{
		Tokenizer t = new Tokenizer(file);
		list = t.wordList();
		graph = new ArrayList<WordNode>(list.size());
		numWords = 0;
		maxWords = list.size();
		
		// Handle first word
		WordNode first = new WordNode(list.get(0), 1);
		graph.add(first);
		WordNode second = new WordNode(list.get(1), 1);
		graph.add(second);
		WordPair firstSecond = new WordPair(second, 1);
		first.adjFor.add(firstSecond);
		
		// Handle all words but last
		int k = 2;
		
		WordNode iNode = first;
		WordNode jNode = second;
		WordNode kNode = null;
		
		while(k < list.size()){
			// Checks if JI pair already occurs
			WordPair tempJI = new WordPair(iNode, 1);
			if(jNode.adjBack.contains(tempJI)){
				WordPair wordPairJI = jNode.adjBack.get(jNode.adjBack.indexOf(tempJI));
				wordPairJI.increaseCount(1);
			}
			else
				jNode.adjBack.add(tempJI);
		
			// Look at k
			WordNode temp = new WordNode(list.get(k), 1);
			// If the node is already in the graph, increase its count
			if(graph.contains(temp)){
				kNode = graph.get(graph.indexOf(temp));
				kNode.increaseCount(1);
			}
			// Else add the node to the graph
			else{
				kNode = new WordNode(list.get(k), 1);
				graph.add(kNode);
			}
		
			// Checks if JK pair already occurs
			WordPair tempJK = new WordPair(kNode, 1);
			if(jNode.adjFor.contains(tempJK)){
				WordPair wordPairJK = jNode.adjFor.get(jNode.adjFor.indexOf(tempJK));
				wordPairJK.increaseCount(1);
			}
			else
				jNode.adjFor.add(tempJK);
			k++;
			iNode = jNode;
			jNode = kNode;
			kNode = null;
		}
		
		// Handle last word
		// Checks if JI pair already occurs
		WordPair tempJI = new WordPair(iNode, 1);
		if(jNode.adjBack.contains(tempJI)){
			WordPair wordPairJI = jNode.adjBack.get(jNode.adjBack.indexOf(tempJI));
			wordPairJI.increaseCount(1);
		}
		else
			jNode.adjBack.add(tempJI);
	}
	
	
}
