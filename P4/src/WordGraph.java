import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Creates a graph of words from a file.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class WordGraph {
	
	/**
	 * Node for each word in the graph.
	 */
	public class WordNode implements Comparable<WordNode>{
		
		/**
		 * Holds the word of each node.
		 */
		public String word;
		
		
		/**
		 * Holds the number of times the word occurs in the text.
		 */
		public int count;
		
		/**
		 * Adjacency list of all the forward edges, i.e. words coming after this word in the text.
		 */
		public LinkedList<WordPair> adjFor = new LinkedList<WordPair>();
		
		/**
		 * Adjacency list of all the backward edges, i.e. words coming before this word in the text.
		 */
		public LinkedList<WordPair> adjBack = new LinkedList<WordPair>();
		
		/**
		 * Current minimum distance through that node. 
		 */
		public double min = Double.POSITIVE_INFINITY;
		
		/**
		 * Parent word that gives the max distance.
		 */
		public WordNode parent = null;
		
		/**
		 * Constructor that initialized the fields of the node.
		 * @param s word of the WordNode
		 * @param i number of times the word appears in the text
		 */
		public WordNode(String s, int i){
			word = s;
			count = i;
		}
		
		/**
		 * Defines equality of WordNodes as equal if the words are the same.
		 */
		@Override
		public boolean equals(Object o){
			if(o instanceof WordNode){
				return word.equals(((WordNode) o).word);
			}
			else
				return false;
		}
		
		/**
		 * Increases the count of the WordNode.
		 * @param i increment of increase
		 */
		public void increaseCount(int i){
			count = count + i;
		}
		
		/**
		 * Defines comparison of WordNodes as the comparison of their maxDistance from a node.
		 */
		@Override
		public int compareTo(WordNode other){
			return Double.compare(min, other.min);
		}
		
		/**
		 * Returns the String of the word in this node.
		 */
		public String toString(){
			return word;
		}
	}
	
	/**
	 * Pair of words, aka an edge in the graph.
	 */
	public class WordPair{
		
		/**
		 * Holds the target word for this edge.
		 */
		public WordNode targetWord;
		
		/**
		 * Holds the number of times this word pair appears in the text, i.e. the weight of the edge.
		 */
		public int count;
		
		/**
		 * Edge cost, i.e. measure of probability. 
		 */
		public double cost = 0.0;
		
		/**
		 * Constructor that initializes the target word and count.
		 * @param n the WordNode of the target word
		 * @param i number of times this pair occurs in the text
		 */
		public WordPair(WordNode n, int i){
			targetWord = n;
			count = i;
		}
		
		/**
		 * Defines equality of WordPairs as equal if the target word is the same.
		 */
		@Override
		public boolean equals(Object o){
			if(o instanceof WordPair){
				return targetWord.equals(((WordPair) o).targetWord);
			}
			else
				return false;
		}
		
		/**
		 * Increases the count of the WordPair.
		 * @param i increment of increase
		 */
		public void increaseCount(int i){
			count = count + i;
		}
		
	}
	
	/**
	 * Stores the tokenized list of words from the text.
	 */
	private ArrayList<String> list;
	
	/**
	 * Stores the vertexes, i.e. the graph itself.
	 */
	private ArrayList<WordNode> graph;

	/**
	 * Holds the number of words in the text.
	 */
	public int numWords;
	
	/**
	 * Constructor that tokenizes the words of the file, then constructs a graph of the words in the text.
	 * @param file input file of text to be made into a graph
	 * @throws FileNotFoundException
	 */
	public WordGraph(String file) throws FileNotFoundException{
		Tokenizer t = new Tokenizer(file);
		list = t.wordList();
		numWords = list.size();
		graph = new ArrayList<WordNode>(list.size());
		
		if(list.size() == 1){
			WordNode word = new WordNode(list.get(0), 1);
			graph.add(word);
		}
		else{
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
		
		// Compute all the edge costs
		for(WordNode node : graph){
			for(WordPair pair : node.adjBack)
				pair.cost = computeCost(node.count, pair.count);
			for(WordPair pair : node.adjFor)
				pair.cost = computeCost(node.count, pair.count);
		}
	}
	
	/**
	 * Returns the number of nodes in the graph.
	 * @return returns the number of nodes in the graph
	 */
	public int numNodes(){
		return graph.size();
	}
	
	/**
	 * Returns the number of edges in the graph.
	 * @return returns the number of edges in the graph
	 */
	public int numEdges(){
		int edges = 0;
		for(WordNode n : graph){
			edges = edges + n.adjBack.size() + n.adjFor.size();
		}
		return edges;
	}
	
	/**
	 * Returns the number of times a word occurs in the text.
	 * @param w the word whose count is desired
	 * @return returns the number of times word w occurs in the text
	 */
	public int wordCount(String w){
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		WordNode temp = new WordNode(w, -2);
		if(graph.contains(temp)){
			temp = graph.get(graph.indexOf(temp));
			return temp.count;
		}
		else
			return 0;
	}
	
	/**
	 * Returns the in-degree of a word in the text.
	 * @param w the word whose in-degree is desired
	 * @return returns the in-degree of word w in the text
	 */
	public int inDegree(String w){
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		WordNode temp = new WordNode(w, -2);
		if(graph.contains(temp)){
			temp = graph.get(graph.indexOf(temp));
			return temp.adjBack.size();
		}
		else
			return -1;
	}
	
	/**
	 * Returns the out-degree of a word in the text.
	 * @param w the word whose out-degree is desired
	 * @return returns the out-degree of word w in the text
	 */
	public int outDegree(String w){
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		WordNode temp = new WordNode(w, -2);
		if(graph.contains(temp)){
			temp = graph.get(graph.indexOf(temp));
			return temp.adjFor.size();
		}
		else
			return -1;
	}
	
	/**
	 * Returns a string array of all the unique words that directly precede a given word in the text.
	 * @param w the word whose preceding words are desired
	 * @return returns the all the unique words that precede a word w in the text 
	 */
	public String[] prevWords(String w){
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		WordNode temp = new WordNode(w, -2);
		if(graph.contains(temp)){
			temp = graph.get(graph.indexOf(temp));
			String[] prev = new String[temp.adjBack.size()];
			for(int i = 0; i < temp.adjBack.size(); i++)
				prev[i] = temp.adjBack.get(i).targetWord.word;
			return prev;
		}
		else
			return null;
	}
	
	/**
	 * Returns a string array of all the unique words that directly follow a given word in the text.
	 * @param w the word whose following words are desired
	 * @return returns the all the unique words that follow a word w in the text 
	 */
	public String[] nextWords(String w){
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		WordNode temp = new WordNode(w, -2);
		if(graph.contains(temp)){
			temp = graph.get(graph.indexOf(temp));
			String[] next = new String[temp.adjFor.size()];
			for(int i = 0; i < temp.adjFor.size(); i++)
				next[i] = temp.adjFor.get(i).targetWord.word;
			return next;
		}
		else
			return null;
	}
	
	/**
	 * Returns the cost of a sequence of words in the text.
	 * @param wordSeq the sequence of words whose count is desired
	 * @return returns the cost of a sequence of words in the text
	 */
	public double wordSeqCost(String[] wordSeq){
		String[] normWordSeq = new String[wordSeq.length];
		for(int i = 0; i < wordSeq.length; i++){
			String w = wordSeq[i];
			w = w.toLowerCase();
			w = w.replaceAll("\\s", "");
			w = w.replaceAll("\\W", "");
			normWordSeq[i] = w;
		}
		String w = normWordSeq[0];
		WordNode temp = new WordNode(w, 0);
		if(!graph.contains(temp))
			throw new IllegalArgumentException();
		temp = graph.get(graph.indexOf(temp));
		double totalCost = Math.log((double)(numWords)/(double)(temp.count));
		for(int i = 0; i < normWordSeq.length - 1; i++){
			WordNode temp1 = new WordNode(normWordSeq[i], 0);
			if(graph.contains(temp1)){
				// Find the word in the graph
				temp1 = graph.get(graph.indexOf(temp1));
				WordNode next = new WordNode(normWordSeq[i + 1], 0);
				if(!graph.contains(next))
					throw new IllegalArgumentException();
				next = graph.get(graph.indexOf(next));
				for(WordPair pair : temp1.adjFor){
					// Find the next word in the adjacency list of the current word
					if(pair.targetWord.equals(next))
						// Increase the sequence count
						totalCost = totalCost + Math.log((double)(temp1.count)/(double)(pair.count));
				}
			}
			else
				throw new IllegalArgumentException();
		}
		return totalCost;
	}

	public String generatePhrase(String startWord, String endWord, int N){
		computeDijkstra(startWord);
		ArrayList<WordNode> maxPath = maxPath(endWord);
		if(maxPath.size() > N)
			return "";
		else{
			StringBuilder b = new StringBuilder();
			for(int i = 0; i < maxPath.size() - 1; i++){
				b.append(maxPath.get(i).toString());
				b.append(" ");
			}
			b.append(maxPath.get(maxPath.size() - 1).toString());
			return b.toString();
		}
	}
	
	// Dijkstra's
	public void computeDijkstra(String s){
		String w = s;
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		
		// Retrieve the node with the correct String
		WordNode n = new WordNode(w, 0);
		if(!graph.contains(n))
			throw new IllegalArgumentException();
		n = graph.get(graph.indexOf(n));
		n.min = 0.0;
		
		// Priority Queue with max distance as highest priority
		PriorityQueue<WordNode> queue = new PriorityQueue<WordNode>();
		queue.add(n);
		
		while(!queue.isEmpty()){
			WordNode u = queue.poll();
			for(WordPair e : u.adjFor){
				WordNode v = e.targetWord;
				double weight = e.cost;
				double distanceThroughU = u.min + weight;
				if(distanceThroughU < v.min){
					queue.remove(v);
					v.min = distanceThroughU;
					v.parent = u;
					queue.add(v);
				}
			}
		}
	}
	
	public ArrayList<WordNode> maxPath(String s){
		String w = s;
		w = w.toLowerCase();
		w = w.replaceAll("\\s", "");
		w = w.replaceAll("\\W", "");
		
		// Retrieve the node with the correct String
		WordNode target = new WordNode(w, 0);
		if(!graph.contains(target))
			throw new IllegalArgumentException();
		target = graph.get(graph.indexOf(target));
		ArrayList<WordNode> path = new ArrayList<WordNode>();
		for(WordNode node = target; node != null; node = node.parent)
			path.add(node);
		Collections.reverse(path);
		return path;
	}
	
	private double computeCost(double wordCount, double pairCount){
		return Math.log(wordCount/pairCount);
	}
}
