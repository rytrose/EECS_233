import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	public void testWordCount(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		assertEquals("The count should be two for \"this\".", 3, ws.wordCount("the"));
	}
	
	@Test
	public void testWordRank(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		assertEquals("Should be word rank 1", 1, ws.wordRank("the"));
		assertEquals("Should be word rank 1", 1, ws.wordRank("sentence"));
		assertEquals("Should be word rank 3", 3, ws.wordRank("is"));
	}
	
	@Test
	public void testMostCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonWords (2);
		String[] theoretical = {"sentence", "the"};
		assertArrayEquals(common, theoretical);
	}
	
	@Test
	public void testLeastCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.leastCommonWords (3);
		String[] theoretical = {"just", "third", "first"}; 
		assertArrayEquals(common, theoretical);
	}
	@Test
	public void testWordPairCount(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		int count = ws.wordPairCount ("the","sentence");
		assertEquals("This method should return the count of the pair of words", 0, count);
		int count1 = ws.wordPairCount("This", "is");
		assertEquals("This method should return the count of the pair of words", 2, count1);
	}
	
	@Test
	public void testWordPairRank(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		int rank = ws.wordPairRank("the","sentence");
		assertEquals("This method should return the rank of the pair of words", 0, rank);
		int rank1 = ws.wordPairRank("This", "is");
		assertEquals("This method should return the rank of the pair of words", 1, rank1);
		int rank2 = ws.wordPairRank("sentence!", "this");
		assertEquals("This method should return the rank of the pair of words", 3, rank2);
	}
	
	@Test
	public void testMostCommonWordPairs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonWordPairs (2);
		String[] theoretical = {"is the", "this is"}; 
		assertArrayEquals(common, theoretical);
	}
	@Test
	public void testMostCommonCollocs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String[] common = ws.mostCommonCollocs(2,"the",2);
		String[] theoretical = {"sentence", "the"};
		assertArrayEquals(common, theoretical);
	}
	@Test
	public void testMostCommonCollocsExc(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String exc [] = {"the", "is"};
		String[] common = ws.mostCommonCollocsExc(2,"This",4,exc);
		String[] theoretical = {"sentence", "this"};
		assertArrayEquals(common, theoretical);
	}
	@Test
	public void testGenerateWordString(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String common = ws.generateWordString(3,"This");
		assertTrue("returns a string composed of k words of the form startWord",true);
	}
	
	@Test
	public void testExamples() throws FileNotFoundException{
		WordStat ws = new WordStat("hhgttg.txt");
		String[] common = ws.mostCommonWords(100);
		for(int i = 0; i < common.length; i++)
			System.out.println(common[i]);
		System.out.println("");
		String[] least = ws.leastCommonWords(100);
		for(int i = 0; i < least.length; i++)
			System.out.println(least[i]);
		System.out.println("");
		String[] pairs = ws.mostCommonWordPairs(100);
		for(int i = 0; i < pairs.length; i++)
			System.out.println(pairs[i]);
		
		System.out.println(ws.wordRank("the"));
		System.out.println(ws.wordRank("trillian"));
		System.out.println(ws.wordCount("the"));
		System.out.println(ws.wordCount("of"));
		System.out.println(ws.wordCount("trillian"));
		System.out.println(ws.wordCount("could"));
		System.out.println(ws.wordCount("two"));
		System.out.println(ws.wordRank("could"));
		System.out.println(ws.wordRank("two"));
		System.out.println(ws.wordCount("my"));
		System.out.println(ws.wordRank("my"));
		System.out.println(ws.wordCount("throttled"));
		System.out.println(ws.wordRank("throttled"));
		
		System.out.println("");
		String[] collocs = ws.mostCommonCollocs(10, "arthur", -1);
		for(int i = 0; i < collocs.length; i++)
			System.out.println(collocs[i]);
		System.out.println("");
		String[] collocs1 = ws.mostCommonCollocs(10, "arthur", 1);
		for(int i = 0; i < collocs1.length; i++)
			System.out.println(collocs1[i]);
		System.out.println("");
		String[] exc = ws.mostCommonWords(70);
		String[] collocs2 = ws.mostCommonCollocsExc(10, "arthur", -1, exc);
		for(int i = 0; i < collocs2.length; i++)
			System.out.println(collocs2[i]);
		System.out.println();
		String[] collocs3 = ws.mostCommonCollocsExc(10, "arthur", 1, exc);
		for(int i = 0; i < collocs3.length; i++)
			System.out.println(collocs3[i]);
	}
}
