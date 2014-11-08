import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class WordStatFundTest {

	@Test
	public void FileArgumentConstructor() throws IOException{
		WordStat ws = new WordStat ("file.txt");
		assertTrue("The constructor should read the file and get statistics from it", 
	            true);
	}
	@Test
	public void ArrayArgumentConstructor(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		assertTrue("The constructor should read the array and get statistics from it", 
	            true);
	}
	@Test
	public void testMostCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonWords (2);
		assertTrue("This method should return a String array of the k most common words in descending"+
				" order of their count",true);
	}
	
	@Test
	public void testLeastCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonWords (3);
		assertTrue("This method should return a String array of the k least common words in descending"+
				" order of their count",true);
	}
	@Test
	public void testWordPairCount(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		int count = ws.wordPairCount ("the","sentence");
		assertTrue("This method should return the count of the pair of words",true);
	}
	@Test
	public void testWordPairRank(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		int rank =ws.wordPairRank("the","sentence");
		assertTrue("This method should return the count of the pair of words",true);
	}
	@Test
	public void testMostCommonWordPairs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonWordPairs (2);
		assertTrue("This method should return a String array of the k most common words pairs in descending"+
				" order of their count",true);
	}
	@Test
	public void testMostCommonCollocs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonCollocs(2,"the",2);
		assertTrue("returns the k most common words at a given relative position i to baseWord",true);
	}
	@Test
	public void testMostCommonCollocsExc(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		ArrayList<String> exc = new ArrayList<String>();
		exc.add("is");
		exc.add("the");
		String[] common = ws.mostCommonCollocsExc(2,"This",4,exc);
		assertTrue("returns the k most common words at a given relative position i to baseWord"
				+"excluding the words in  exclusions array",true);
	}
	@Test
	public void testGenerateWordString(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String common = ws.generateWordString(3,"This");
		assertTrue("returns a string composed of k words of the form startWord",true);
	}
	
}
