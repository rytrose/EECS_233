import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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
	 * Array that stores the single-word HashEntrys, sorted by increasing rank in respect to value. (i.e. decreasing order of count)
	 */
	private HashEntry[] entries;
	
	/**
	 * Stores the hash table of words and their counts in the file or String array of words.
	 */
	private HashTable table;
	
	/**
	 * Stores the hash table of word pairs and their count.
	 */
	private HashTable pairTable;
	
	/**
	 * Array that stores the word pair HashEntrys, sorted by increasing rank in respect to value. (i.e. decreasing order of count)
	 */
	private HashEntry[] pairEntries;
	
	/**
	 * Computes statistics from a file name.
	 * @param file String representation of the file name
	 * @throws FileNotFoundException
	 */
	public WordStat(String file) throws FileNotFoundException{
		// Creates a list of all the words in the list
		list = new Tokenizer(file).wordList();
		
		// Creates a hash table of the words and their counts
		table = new HashTable(list.size() * 2);
		for(String s : list){
			int occurrence = table.get(s);
			if(occurrence == -1)
				table.update(s, 1);
			else
				table.update(s, occurrence + 1);
		}
		
		// Creates an array of all the single-word hash table entries
		ArrayList<HashEntry> arrayListOfEntries = new ArrayList<HashEntry>((int)(table.getNumItems()));
		ArrayList<LinkedList<HashEntry>> hashTable = table.getTable();
		for(LinkedList<HashEntry> l: hashTable){
			for(HashEntry e : l){
				arrayListOfEntries.add(e);
			}
		}
		entries = arrayListOfEntries.toArray(new HashEntry[(int)(table.getNumItems())]);
		// Sorts the array into ascending order, meaning lowest rank is at index 0
		Arrays.sort(entries);
		// Flips the array
		arrayListOfEntries = new ArrayList<HashEntry>();
		for(int i = 0; i < entries.length; i++)
			arrayListOfEntries.add(entries[i]);
		Collections.reverse(arrayListOfEntries);
		entries = arrayListOfEntries.toArray(entries);
		
		// Builds a hash table of word pairs.
		pairTable = new HashTable((list.size() - 1) * 2);
		int i = 0;
		int j = 1;
		int occurrence;
		while(j < list.size()){
			occurrence = table.get(list.get(i) + " " + list.get(j));
			if(occurrence == -1)
				pairTable.update(list.get(i) + " " + list.get(j), 1);
			else
				pairTable.update(list.get(i) + " " + list.get(j), occurrence + 1);
			i++;
			j++;
		}
		
		// Creates an array of all the word pair hash table entries
		ArrayList<HashEntry> arrayListOfPairEntries = new ArrayList<HashEntry>((int)(pairTable.getNumItems()));
		ArrayList<LinkedList<HashEntry>> pairHashTable = pairTable.getTable();
		for(LinkedList<HashEntry> l: pairHashTable){
			for(HashEntry e : l){
				arrayListOfPairEntries.add(e);
			}
		}
		pairEntries = arrayListOfPairEntries.toArray(new HashEntry[(int)(pairTable.getNumItems())]);
		// Sorts the array into ascending order, meaning lowest rank is at index 0
		Arrays.sort(pairEntries);
		// Flips the array
		arrayListOfPairEntries = new ArrayList<HashEntry>();
		for(int k = 0; k < pairEntries.length; k++)
			arrayListOfPairEntries.add(pairEntries[k]);
		Collections.reverse(arrayListOfPairEntries);
		pairEntries = arrayListOfPairEntries.toArray(pairEntries);
	}
	
	/**
	 * Computes statistics from words in a given String array.
	 * @param array String array from which the words are to be given statistics
	 */
	public WordStat(String[] array){
		// Creates a list of all the words in the list
		list = new Tokenizer(array).wordList();
		
		// Creates a hash table of all the single words and their counts
		table = new HashTable(list.size() * 2);
		for(String s : list){
			int occurrence = table.get(s);
			if(occurrence == -1)
				table.update(s, 1);
			else
				table.update(s, occurrence + 1);
		}
		
		// Creates an array of all the single-word hash table entries
		ArrayList<HashEntry> arrayListOfEntries = new ArrayList<HashEntry>((int)(table.getNumItems()));
		ArrayList<LinkedList<HashEntry>> hashTable = table.getTable();
		for(LinkedList<HashEntry> l: hashTable){
			for(HashEntry e : l){
				arrayListOfEntries.add(e);
			}
		}
		entries = arrayListOfEntries.toArray(new HashEntry[(int)(table.getNumItems())]);
		// Sorts the array into ascending order, meaning lowest rank is at index 0
		Arrays.sort(entries);
		// Flips the array
		arrayListOfEntries = new ArrayList<HashEntry>();
		for(int i = 0; i < entries.length; i++)
			arrayListOfEntries.add(entries[i]);
		Collections.reverse(arrayListOfEntries);
		entries = arrayListOfEntries.toArray(entries);	
		
		// Builds a hash table of word pairs.
		pairTable = new HashTable((list.size() - 1) * 2);
		int i = 0;
		int j = 1;
		int occurrence;
		while(j < list.size()){
			occurrence = table.get(list.get(i) + " " + list.get(j));
			if(occurrence == -1)
				pairTable.update(list.get(i) + " " + list.get(j), 1);
			else
				pairTable.update(list.get(i) + " " + list.get(j), occurrence + 1);
			i++;
			j++;
		}
		
		// Creates an array of all the word pair hash table entries
		ArrayList<HashEntry> arrayListOfPairEntries = new ArrayList<HashEntry>((int)(pairTable.getNumItems()));
		ArrayList<LinkedList<HashEntry>> pairHashTable = pairTable.getTable();
		for(LinkedList<HashEntry> l: pairHashTable){
			for(HashEntry e : l){
				arrayListOfPairEntries.add(e);
			}
		}
		pairEntries = arrayListOfPairEntries.toArray(new HashEntry[(int)(pairTable.getNumItems())]);
		// Sorts the array into ascending order, meaning lowest rank is at index 0
		Arrays.sort(pairEntries);
		// Flips the array
		arrayListOfPairEntries = new ArrayList<HashEntry>();
		for(int k = 0; k < pairEntries.length; k++)
			arrayListOfPairEntries.add(pairEntries[k]);
		Collections.reverse(arrayListOfPairEntries);
		pairEntries = arrayListOfPairEntries.toArray(pairEntries);
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
	 * Returns the rank of the word, where rank 1 is the most common word.
	 * @param word the word for which the rank is desired
	 */
	public int wordRank(String word){
		int rank = 0;
		while(word.equals(entries[rank].getKey()) == false)
			rank++;
		if(rank == 0)
			return rank + 1;
		else{
			if(entries[rank].getValue() != entries[rank - 1].getValue())
				return rank + 1;
			else{
				while(rank > 0 && entries[rank].getValue() == entries[rank - 1].getValue())
					rank = rank - 1;
				return rank + 1;
			}
		}
	}
	
	/**
	 * Returns the k most common words, in descending order of their count, in alphabetical order.
	 * @param k how many of the most common words are desired
	 */
	public String[] mostCommonWords(int k){
		String[] s = new String[k];
		for(int i = 0; i < k; i++)
			s[i] = entries[i].getKey();
		return s;
	}
	
	/**
	 * Returns the k least common words, in increasing order of their count.
	 * @param k how many of the least common words are desired
	 */
	public String[] leastCommonWords(int k){
		String[] s = new String[k];
		for(int i = 0; i < k; i++)
			s[i] = entries[(entries.length - 1) - i].getKey();
		return s;
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

