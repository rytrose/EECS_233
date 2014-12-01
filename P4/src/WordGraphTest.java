import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;


public class WordGraphTest {

	@Test
	public void test() throws FileNotFoundException {
		WordGraph g = new WordGraph("file.txt");
	}
}
