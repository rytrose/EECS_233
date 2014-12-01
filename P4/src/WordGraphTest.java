import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;


public class WordGraphTest {

	@Test
	public void test() throws FileNotFoundException {
		WordGraph g = new WordGraph("test.txt");
		System.out.println(g.numNodes());
		System.out.println(g.numEdges());
		System.out.println(g.inDegree("hello"));
		System.out.println(g.outDegree("hello"));
		System.out.println(g.inDegree("world"));
		System.out.println(g.outDegree("world"));
		System.out.println(Arrays.toString(g.prevWords("world")));
		System.out.println(Arrays.toString(g.nextWords("world")));
		
	}
}
