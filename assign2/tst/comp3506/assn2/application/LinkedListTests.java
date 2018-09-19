package comp3506.assn2.application;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTests {

	@Test
	public void testAddTwo() {
		LinkedList<Object> ll = new LinkedList<Object>();
		ll.append(new Integer(1));
		
		ll.append(new Integer(2));
		Integer expected = 2;
		Integer actual = ll.size;
		assertEquals(expected, actual);
		
		assertEquals(1, ll.getHead().element);
		assertEquals(2, ll.getHead().next.element);
	}

}
