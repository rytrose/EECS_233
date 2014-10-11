import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class  ByteCounterFundTest {

    /* JUnit tests of fundamental functionality
     *   
     * Use these JUnit tests to ensure that your code correctly 
     * implements the fundamental functionality.
     */

    @Test
    public void testArrayArgumentConstructorAndToString() {
    	byte test [] = {(byte)'a', (byte)'b', (byte)'c',(byte)'a'};
    	ByteCounter byteCount = new ByteCounter(test);     
        assertEquals("Your constructors should store these bytes with their occurrences values " + 
            "It also can be the problem in method TOSTRING.", 
            (byte)'a'+":2 "+ (byte)'b'+":1 "+(byte)'c'+":1", byteCount.toString());
    }
    
    @Test
    public void testStringArgumentConstructorAndGetCount()throws IOException {
		ByteCounter byteCount = new ByteCounter("file.txt"); 
    	byte b = (byte)'l';
        assertEquals("Your constructors should read the file and store these bytes with their occurrences " + 
            "It also can be the problem in method TOSTRING.", 
             3, byteCount.getCount(b));
    }
    
    @Test
    public void testByteArgumentGetCount() {
    	byte test [] = {(byte)'e', (byte)'e', (byte)'c', (byte)'s'};
    	byte b = (byte)'e';
    	ByteCounter byteCount = new ByteCounter(test);     
        assertEquals("getCount method should take a byte value and return the " + 
            " number of occurrences.", 
            2, byteCount.getCount(b));
    }
    
    @Test
    public void testArrayArgumentGetCount() {
    	byte test [] = {(byte)'h',(byte)'e', (byte)'l', (byte)'l',(byte)'o'};
    	byte b [] = {(byte)'h',(byte)'e',(byte)'l',(byte)'o'};
    	ByteCounter byteCount = new ByteCounter(test); 
    	int count [] = byteCount.getCount(b);
        assertArrayEquals("getCount method should take a byte array and return an " + 
            " array of the number of occurrences of each byte.", 
            new int[]{1,1,2,1},count);
    }
    
    @Test
    public void testGetElements() {
    	byte test [] = {(byte)'h',(byte)'e', (byte)'l', (byte)'l',(byte)'o'};
       	ByteCounter byteCount = new ByteCounter(test); 
    	byte elements [] = byteCount.getElements();
        assertArrayEquals("get Elements method should return an array of byte " + 
            " that has a non-zero count", new byte[]{(byte)'h', (byte)'e', (byte)'l',(byte)'o'},elements);
        assertEquals("get Elements method should return an array of byte " + 
            " that has a non-zero count", 4,elements.length);
    }
    
    /* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     * 
     * Your draft code should contain a complete set of methods as specified in the assignment.
     * Any methods not yet implemented should be written as skeleton methods with an empty body. 
     * 
	 * The JUnit tests below help to ensure that your methods compile with our test suite and have 
	 * correctly typed arguments. You can replace them with more meaningful tests to test their
	 * functionalities.
     */
    
    @Test
	public void testSetOrder() {
    	byte test [] = {(byte)'a', (byte)'b'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.setOrder("byte");
    	assertTrue("Method setOrder define the order of the current object",
			true);
	}
    
    @Test
	public void testFormatToString() {
    	byte test [] = {(byte)'a', (byte)'b'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.toString("byte");
    	assertTrue("Method toString returns the bytes and their counts based on the format provided",
			true);
	}
}