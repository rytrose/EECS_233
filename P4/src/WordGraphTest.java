import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;


public class WordGraphTest {
	
	@Test
	public void testSingleWord() throws FileNotFoundException{
		WordGraph g = new WordGraph("single_word.txt");
		System.out.println(g.numNodes());
		System.out.println(g.numEdges());
		System.out.println();
	}
		
	@Test
	public void testTest() throws FileNotFoundException {
		WordGraph g = new WordGraph("test.txt");
		System.out.println(g.numNodes());
		System.out.println(g.numEdges());
		System.out.println(g.inDegree("hello"));
		System.out.println(g.outDegree("hello"));
		System.out.println(g.inDegree("world"));
		System.out.println(g.outDegree("world"));
		System.out.println(Arrays.toString(g.prevWords("world")));
		System.out.println(Arrays.toString(g.nextWords("world")));	
		System.out.println();
	}
	
	@Test
	public void testFile() throws FileNotFoundException{
		WordGraph g = new WordGraph("file.txt");
		System.out.println(g.numNodes());
		System.out.println(g.numEdges());
		System.out.println(g.wordCount("a"));
		System.out.println(g.inDegree("!a"));
		System.out.println(g.outDegree("a*"));
		System.out.println(Arrays.toString(g.prevWords("A")));
		System.out.println(Arrays.toString(g.nextWords("A@")));
		System.out.println();
	}
	
	@Test
	public void testAlphabet() throws FileNotFoundException{
		WordGraph g = new WordGraph("alphabet.txt");
		System.out.println(g.numNodes());
		System.out.println(g.wordCount("a"));
		System.out.println(g.numEdges());
		System.out.println();
	}
}
