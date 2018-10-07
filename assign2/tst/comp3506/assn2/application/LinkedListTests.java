package comp3506.assn2.application;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTests {

	@Test
	public void testAddTwo() {
		LinkedList<Object> ll = new LinkedList<Object>();
		ll.add(new Integer(1));
		
		ll.add(new Integer(2));
		Integer expected = 2;
		Integer actual = ll.size;
		assertEquals(expected, actual);
		
		assertEquals(1, ll.getHead().element);
		assertEquals(2, ll.getHead().next.element);
	}
	
	@Test
	public void appendTests() {
		LinkedList<Object> ll = new LinkedList<Object>();
		ll.add(new Integer(1));
		
		LinkedList<Object> ll2 = new LinkedList<Object>();
		ll2.add(new Integer(102));
		
		ll.append(ll2);
		
		assertEquals(2, ll.size);
		
		assertEquals(1, ll.next().getElement());
		assertEquals(102, ll.next().getElement());
		
	}
	@Test
	public void loop() {
		LinkedList<Object> ll = new LinkedList<Object>();
		ll.add(new Integer(1));
		
		ll.add(new Integer(2));
		ll.add(new Integer(3));
		ll.add(new Integer(4));
		
		assertEquals(4, ll.size);
		Integer currentCount = 1;
		while (ll.hasNext()) {
			Object current = ll.next().getElement();
			System.out.println(current);
			assertEquals(currentCount, current);
			currentCount++;
		}
	}
	
	@Test
	public void loopOne() {
		LinkedList<Object> ll = new LinkedList<Object>();
		ll.add(new Integer(1));
		
		assertEquals(1, ll.size);
		Integer currentCount = 1;
		while (ll.hasNext()) {
			Object current = ll.next().getElement();
			System.out.println(current);
			assertEquals(currentCount, current);
			currentCount++;
		}
	}
}
