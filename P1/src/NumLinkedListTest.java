import static org.junit.Assert.*;

import org.junit.Test;


public class NumLinkedListTest {

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
        NumLinkedList list = new NumLinkedList();       
        assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
            "It also can be the problem in method TOSTRING.", 
            "", list.toString());
    }

    @Test
    public void testAddAndToString() {
        NumLinkedList list = new NumLinkedList();

        list.add(1.0);
        list.add(3.0);
        list.add(2.0);

        assertEquals("Add method should add element to the end of list each time. " +
            "It's also can be the problem in method TOSTRING.",
            "1.0 3.0 2.0", list.toString());
    }

    @Test
    public void testSize() {
        NumLinkedList list = new NumLinkedList();

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
        NumLinkedList listA = new NumLinkedList();
        NumLinkedList listB = new NumLinkedList();
        NumLinkedList listC = new NumLinkedList();

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
    public void testInsert(){
    	NumLinkedList test = new NumLinkedList();
    	NumLinkedList theoretical = new NumLinkedList();
    	theoretical.add(1.0);
    	theoretical.add(2.0);
    	theoretical.add(3.0);
    	test.add(1.0);
    	test.add(3.0);
    	test.insert(1, 2.0);
    	assertTrue("After the insert, test should equal theoretical.", test.equals(theoretical));
    
    	NumLinkedList test1 = new NumLinkedList();
    	NumLinkedList theoretical1 = new NumLinkedList();
    	theoretical1.add(1.0);
    	test1.insert(3, 1.0);
    	assertTrue("After the insert, test should equal theoretical.", test1.equals(theoretical1));
    }

}
