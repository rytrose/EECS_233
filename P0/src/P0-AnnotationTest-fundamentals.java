import static org.junit.Assert.*;
import org.junit.Test;

public class AnnotationTest {

	/* 
	 * JUnit tests of fundamental functionality.
	 *
	 * Rename this file "AnnotationTest.java" and place with your source code to work with JUnit.
	 * Both Eclipse and DrJava include support for running JUnit tests.
	 *
	 * When you turn in your draft, your code must pass these 
	 * tests in order to receive a non-zero grade on the assignment.
	 */

	@Test
	public void testNullConstructor() {
		Annotation n = new Annotation();
        assertEquals("A constructor with no parameters should assume n is 0.",
        		0, n.getn());
	}
	@Test
	public void testIntegerConstructor() {
		Annotation n = new Annotation(1);
        assertEquals("A constructor with one integer parameter should store the value of that number.",
        		1, n.getn());
	}
	
	/* Other JUnit tests.
	 * 
	 * You should write your own test suite to check the correctness of your implementation.
	 * When you turn in your draft (and final) we will run our own JUnit test suite
	 * on your code and provide you with feedback. 
	 *
	 * For help with writing JUnit tests see junit.org
	 */
}
