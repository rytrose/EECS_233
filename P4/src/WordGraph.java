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
		
		
		public boolean equals(WordNode n){
			return word.equals(n.word);
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
		
		public boolean equals(WordPair n){
			return targetWord.equals(n.targetWord);
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
		int i = 0;
		int j = 1;
		int k = 2;
		
		while(k < list.size()){
			
		}
	}
}
