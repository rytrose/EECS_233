import static org.junit.Assert.*;

import org.junit.Test;

public class NumArrayListTest {

    /* 
     * JUnit tests of fundamental functionality.
     *   
     * Use these JUnit tests to ensure that your code compiles
     * and correctly implements the fundamental functionality.
     * 
     * Code that does not pass these tests will not be graded.
     * Your draft submission needs to pass these tests in order
     * to receive a non-zero grade on the assignment.
     */

    @Test
    public void testDefaultConstructorAndToString() {
        NumArrayList list = new NumArrayList();     
        assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
            "It also can be the problem in method TOSTRING.", 
            "", list.toString());
    }

    @Test
    public void testAddAndToString() {
        NumArrayList list = new NumArrayList();

        list.add(1.0);
        list.add(3.0);
        list.add(2.0);

        assertEquals("Add method should add element to the end of list each time. " +
            "It's also can be the problem in method TOSTRING.",
            "1.0 3.0 2.0", list.toString());
    }

    @Test
    public void testSize() {
        NumArrayList list = new NumArrayList();

        assertEquals("Method SIZE should return 0, when list is constructed by default constructor.",
            0, list.size());

        list.add(1.0);
        list.add(2.0);
        list.add(3.0);

        assertEquals("Method SIZE should return the size of the list, " + 
            "i.e. the number of elements, in the sequence.",
            3, list.size());
    }

    @Test
    public void testEquals() {
        NumArrayList listA = new NumArrayList();
        NumArrayList listB = new NumArrayList();
        NumArrayList listC = new NumArrayList();

        listA.add(1.0);
        listA.add(3.0);

        assertFalse("EQUALS method should return FALSE, when two lists are not the same.",
            listA.equals(listB));

        listB.add(1.0);
        listB.add(3.0);

        assertTrue("EQUALS method should return TRUE, when two lists are the same.",
            listA.equals(listB));

        listC.add(3.0);
        listC.add(1.0);

        assertFalse("EQUALS method should return FALSE, even if the same " + 
            "numbers are in different order in two lists.",
            listA.equals(listC));
    }

    /* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     */
    
    @Test
	public void testAddAndCapacity() {
		NumArrayList test = new NumArrayList();
		double[] theoretical = new double[10];
		theoretical[0] = 2;
		test.add(2);
		assertArrayEquals(theoretical, test.getArray(), 0.0);
		assertEquals("Capacitiy is reporting an incorrect value.", 10, test.capacity());
		
		double[] theoretical1 = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		for(int i = 3; i < 12; i++)
			test.add(i);
		assertArrayEquals(theoretical1, test.getArray(), 0.0);
		test.add(12);
		double[] theoretical2 = new double[20];
		for(int i = 0; i < 11; i++)
			theoretical2[i] = i + 2;
		assertArrayEquals(theoretical2, test.getArray(), 0.0);
		assertEquals("Capacitiy is reporting an incorrect value.", 20, test.capacity());
	}
    
    @Test
    public void testLookup(){
    	NumArrayList test = new NumArrayList();
    	test.add(2);
    	test.add(3);
    	test.add(4);
    	assertEquals("Reports the incorrect value.", 2, test.lookup(0), 0.0);
    	try{
    		test.lookup(3);
    	}
    	catch(IndexOutOfBoundsException e){}
    }
    
    @Test
    public void testInsert(){
    	NumArrayList test = new NumArrayList();
    	double[] theoretical = new double[10];
    	for(int i = 0; i < 4; i++)	
    		theoretical[i] = i + 1;
    	test.add(1);
    	test.add(2);
    	test.add(4);
    	test.insert(2, 3);
    	assertArrayEquals(theoretical, test.getArray(), 0.0);
    	
    	NumArrayList test1 = new NumArrayList();
    	double[] theoretical1 = new double[10];
    	for(int i = 0; i < 4; i++)
    		theoretical1[i] = i + 1;
    	test1.add(1);
    	test1.add(2);
    	test1.add(3);
    	test1.insert(4, 4);
    	assertArrayEquals(theoretical1, test1.getArray(), 0.0);
    }
    
    @Test
    public void testRemove(){
    	NumArrayList test = new NumArrayList();
    	test.add(1);
    	test.add(2);
    	test.add(4);
    	test.add(3);
    	double[] theoretical = new double[10];
    	for(int i = 0; i < 3; i++)
    		theoretical[i] = i + 1;
    	test.remove(2);
    	assertArrayEquals(theoretical, test.getArray(), 0.0);
    }
    
    @Test
    public void testContainsAndParametrizedConstructor(){
    	NumArrayList test = new NumArrayList(4);
    	test.add(3);
    	test.add(6);
    	test.add(1);
    	test.add(2.1);
    	assertEquals("Incorrectly returning that 2.1 is not in the list", true, test.contains(2.1));
    	assertEquals("Array is the wrong size, i.e. the constructor did not work properly.", 4, test.capacity());
    }
}
