import static org.junit.Assert.*;

import org.junit.Test;


public class NumSetTest {

	@Test
	public void testSize() {
		double[] array = {3, 5, 4, 9};
		NumSet set = new NumSet(array);
		assertEquals("Incorrectly reports size.", 4, set.size());
		
		double[] array1 = {};
		NumSet set1 = new NumSet(array1);
		assertEquals("Incorrectly reports size.", 0, set1.size());
	}
	
	@Test
	public void testContains(){
		double[] array = {3, 5, 4, 9};
		NumSet set = new NumSet(array);
		assertTrue(set.contains(5));
		assertFalse(set.contains(11));
	}
	
	

}
