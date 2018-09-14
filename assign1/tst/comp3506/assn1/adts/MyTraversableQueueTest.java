package comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * 
 * @author anthony Carrick
 *
 */
public class MyTraversableQueueTest {

	@Test(timeout=500)
	public void testTwoElementQueueSize() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		testQueue.enqueue(new Object());
		testQueue.enqueue(new Object());
		assertThat("A queue with two elements does not have a size of 2.", testQueue.size(), is(equalTo(2)));
	}
	
	@Test(timeout=500)
	public void testTwoElementQueue() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		testQueue.enqueue(element0);
		testQueue.enqueue(element1);
		assertThat("Enqueing and Dequeing one element does not return that element.", 
				   testQueue.dequeue(), is(equalTo(element0)));
		assertThat("Enqueing and Dequeing one element does not return that element.", 
				   testQueue.dequeue(), is(equalTo(element1)));
	}
	
	@Test(timeout=500)
	public void testThreeElementQueueDequeue() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		Integer element2 = new Integer(2);
		testQueue.enqueue(element0);
		
		assertThat("Enqueing and Dequeing one element does not return that element.", 
				   testQueue.dequeue(), is(equalTo(element0)));
		testQueue.enqueue(element1);
		
		assertThat("Enqueing and Dequeing one element does not return that element.", 
				   testQueue.dequeue(), is(equalTo(element1)));
		
		testQueue.enqueue(element2);
		assertThat("Enqueing and Dequeing one element does not return that element.", 
				   testQueue.dequeue(), is(equalTo(element2)));
	}
	
	@Test()
	public void testIteratorThree() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		Integer element2 = new Integer(2);
		testQueue.enqueue(element0);
		testQueue.enqueue(element1);
		testQueue.enqueue(element2);
		Iterator<Object> it = testQueue.iterator();
		assertThat("Wrong Next 0", it.next(), is(equalTo(element0)));
		assertThat("Wrong Next 1", it.next(), is(equalTo(element1)));
		assertThat("Wrong Next 2", it.next(), is(equalTo(element2)));
	}

	@Test()
	public void testIteratorThreeRemove() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		Integer element2 = new Integer(2);
		testQueue.enqueue(element0);
		testQueue.enqueue(element1);
		testQueue.enqueue(element2);
		Iterator<Object> it = testQueue.iterator();
		assertThat("Wrong Next 0", it.next(), is(equalTo(element0)));
		testQueue.dequeue();
		assertThat("Wrong Next 1", it.next(), is(equalTo(element1)));
	}

	@Test()
	public void testLoop() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		for (int i = 0; i < 40000; i++) {
			testQueue.enqueue(element0);
			testQueue.enqueue(element1);
			testQueue.dequeue();
			testQueue.dequeue();
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testEmpty() {
		IterableQueue<Object> testQueue = new TraversableQueue<>();
		testQueue.dequeue();
	}
	
}
