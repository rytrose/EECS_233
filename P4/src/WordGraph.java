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
		
		public String word;
		public int count;
		public LinkedList<WordPair> adjFor = new LinkedList<WordPair>();
		public LinkedList<WordPair> adjBack = new LinkedList<WordPair>();
		public int maxDistance = 0;
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
		
		@Override
		public int compareTo(WordNode other){
			return Integer.compare(maxDistance, other.maxDistance);
		}
		
		public String toString(){
			return word;
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
	

	private ArrayList<String> list;
	private ArrayList<WordNode> graph;

	public WordGraph(String file) throws FileNotFoundException{
		Tokenizer t = new Tokenizer(file);
		list = t.wordList();
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
	}
	
	public int numNodes(){
		return graph.size();
	}
	
	public int numEdges(){
		int edges = 0;
		for(WordNode n : graph){
			edges = edges + n.adjBack.size() + n.adjFor.size();
		}
		return edges;
	}
	
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
	
	public double wordSeqCount(String[] wordSeq){
		int count = 0;
		// For every word in the sequence
		for(int i = 0; i < wordSeq.length - 1; i++){
			WordNode temp = new WordNode(wordSeq[i], 0);
			if(graph.contains(temp)){
				// Find the word in the graph
				temp = graph.get(graph.indexOf(temp));
				WordNode next = new WordNode(wordSeq[i + 1], 0);
				for(WordPair w : temp.adjFor){
					// Find the next word in the adjacency list of the current word
					if(w.targetWord.equals(next))
						// Increase the sequence count
						count = count + w.count;
				}
			}
		}
		return (double) count;
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
		// Retrieve the node with the correct String
		WordNode n = new WordNode(s, 0);
		if(!graph.contains(n))
			System.out.println("No node with that word.");
		n = graph.get(graph.indexOf(n));
		
		// Priority Queue with max distance as highest priority
		PriorityQueue<WordNode> queue = new PriorityQueue<WordNode>();
		queue.add(n);
		
		while(!queue.isEmpty()){
			WordNode u = queue.poll();
			for(WordPair e : u.adjFor){
				WordNode v = e.targetWord;
				int weight = e.count;
				int distanceThroughU = u.maxDistance + weight;
				if(distanceThroughU > v.maxDistance){
					queue.remove(v);
					v.maxDistance = distanceThroughU;
					v.parent = u;
					queue.add(v);
				}
			}
		}
	}
	
	public ArrayList<WordNode> maxPath(String s){
		// Retrieve the node with the correct String
		WordNode target = new WordNode(s, 0);
		if(!graph.contains(target))
			System.out.println("No node with that word.");
		target = graph.get(graph.indexOf(target));
		
		ArrayList<WordNode> path = new ArrayList<WordNode>();
		for(WordNode node = target; node != null; node = node.parent)
			path.add(node);
		Collections.reverse(path);
		return path;
	}
}
