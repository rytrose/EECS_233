import static org.junit.Assert.*;
import org.junit.Test;


public class HashEntryFundTest {

	@Test
	public void testConstructorAndAccessorMethodsAndCompareTo() {
		HashEntry he = new HashEntry("key", 5);
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,getKey should returns the key value", "key", he.getKey());
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,getValue should returns the value", 5, he.getValue());
		HashEntry he1 = new HashEntry("kay", 5);
		HashEntry he2 = new HashEntry("koy", 5);
		HashEntry he3 = new HashEntry("koo", 6);
		HashEntry he4 = new HashEntry("kow", 4);
		System.out.println(he1.compareTo(he));
		System.out.println(he2.compareTo(he));
		System.out.println(he3.compareTo(he));
		System.out.println(he4.compareTo(he));
	}
	@Test
	public void testSetValue() {
		HashEntry he = new HashEntry("key", 5);
		he.setValue(1);
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,setValue will update the value", 1, he.getValue());
		
	}

}
