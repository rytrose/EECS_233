import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class uses Tokenizer and HashTable to compute word statistics.
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class WordStat {
	/**
	 * Stores the tokenized list of words from the file or String array of words.
	 */
	private ArrayList<String> list;
	
	/**
	 * Stores the hash table of words and their counts in the file or String array of words.
	 */
	private HashTable table;
	
	/**
	 * Computes statistics from a file name.
	 * @param file String representation of the file name
	 * @throws FileNotFoundException
	 */
	public WordStat(String file) throws FileNotFoundException{
		list = new Tokenizer(file).wordList();
		table = new HashTable(list.size() * 2);
		for(String s : list){
			int occurrence = table.get(s);
			if(occurrence == -1)
				table.update(s, 1);
			else
				table.update(s, occurrence + 1);
		}
	}
	
	/**
	 * Computes statistics from words in a given String array.
	 * @param array String array from which the words are to be given statistics
	 */
	public WordStat(String[] array){
		list = new Tokenizer(array).wordList();
		table = new HashTable(list.size() * 2);
		for(String s : list){
			int occurrence = table.get(s);
			if(occurrence == -1)
				table.update(s, 1);
			else
				table.update(s, occurrence + 1);
		}
	}
	
	/**
	 * Returns the count of a word argument. Returns 0 if the word is not in the table.
	 * @param word the word for which the count is being searched
	 */
	public int wordCount(String word){
		word = word.toLowerCase();
		word = word.replaceAll("\\s", "");
		word = word.replaceAll("\\W", "");
		if(table.get(word) == -1)
			return 0;
		else
			return table.get(word);
	}
	
	/**
	 * Returns the rank of the word, where rank1 1 is the most common word.
	 * @param word the word for which the rank is desired
	 */
	public int wordRank(){
		return -1;
	}
	
	/**
	 * Returns the k most common words, in descending order of their count, in alphabetical order.
	 * @param k how many of the most common words are desired
	 */
	public String[] mostCommonWords(int k){
		return null;
	}
	
	/**
	 * Returns the k most common words, in increasing order of their count.
	 * @param k how many of the least common words are desired
	 */
	public String[] leastCommonWords(int k){
		return null;
	}
	
	/**
	 * Returns the count of a word pair.
	 * @param w1 the first word
	 * @param w2 the second word
	 */
	public int wordPairCount(String w1, String w2){
		return -1;
	}
	
	/**
	 * Returns the rank of a word pair.
	 * @param w1 the first word
	 * @param w2 the second word
	 */
	public int wordPairRank(String w1, String w2){
		return -1;
	}
	
	/**
	 * Returns the k most common word pairs.
	 * @param k number of most common pairs to return
	 */
	public String[] mostCommonWordPairs(int k){
		return null;
	}
	
	/**
	 * Returns the k most common words at a relative position i to the baseword.
	 * @param k number of collocations desired
	 * @param baseWord base word when deciding where to find collocations
	 * @param i gives the relative position in order to determine whether to look before or after the base word
	 */
	public String[] mostCommonCollocs(int k, String baseWord, int i){
		return null;
	}
	
	/**
	 * Returns the k most common collocations, with exclusions of any word in a given String array.
	 * @param k number of collocations desired
	 * @param baseWord base word when deciding where to find collocations
	 * @param i gives the relative position in order to determine whether to look before or after the base word
	 * @param exclusions String array containing the words to be excluded for most common collocations consideration
	 */
	public String[] mostCommonCollocsExc(int k, String baseWord, int i, ArrayList<String> exclusions){
		return null;
	}
	
	/**
	 * Returns a word String composed of k words starting from a given word, the next word being the previous word's most common collocation.
	 * @param k number of words desired
	 * @param startWord starting word from which to start the string
	 */
	public String generateWordString(int k, String startWord){
		return null;
	}
	
}

