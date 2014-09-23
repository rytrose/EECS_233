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
	
	@Test
	public void testIntersect(){
		double[] array = {1, 2, 3, 4, 5, 6};
		NumSet set = new NumSet(array);
		double[] array1 = {3, 4, 6, 7, 9};
		NumSet set1 = new NumSet(array1);
		NumSet result = NumSet.intersect(set, set1);
		assertEquals("Incorrectly had too many or too few set elements.", 3, result.size());
		assertTrue(result.contains(3));
		assertTrue(result.contains(4));
		assertTrue(result.contains(6));
	}

}
