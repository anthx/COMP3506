package comp3506.assn1.adts;

import java.util.Iterator;

/**
 * Traversable Queue. Queue implementation based on lecture slides.
 * @author anthony Carrick
 * 
 * @param <T>
 * 
 * 
 */

public class TraversableQueue<T> implements IterableQueue<T> {

	private T[] queue;
	private int frontOfQueue = 0;
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public TraversableQueue() {
		queue = (T[]) new Object[20000];
	}
	
	/**
	 * Returns an iterator of the queue so other things can iterate it.
	 * @return Iterator object for the current queue.
	 */
	@Override
	public Iterator<T> iterator() {
		
		Iterator<T> iterator = (QueueIterator<T>) new QueueIterator<T>(queue, this, frontOfQueue);
		return iterator;
	}

	/**
	 * Puts something onto the queue. If queue is full, throws exception.
	 * Runs in constant time because it's backed by a standard array.
	 * Time: O(c)
	 * @param element The element to add to the queue.
	 */
	@Override
	public void enqueue(T element) throws IllegalStateException {
		
		if (size == 20000) {
			throw new IllegalStateException("Queue is full!");
		}
		else {
			int availableSpot = (frontOfQueue + size) % queue.length;
			queue[availableSpot] = element;
			size++;
		}
	}

	/**
	 * Removes and returns the item at the front of the queue.
	 * Returns the element at the head of the queue in constant time because it's a list index
	 * operation.
	 * 
	 * @return the object at the front of the queue.
	 */
	@Override
	public T dequeue() throws IndexOutOfBoundsException {
		
		if (size == 0) {
			throw new IndexOutOfBoundsException("The queue is empty!");
		}
		T frontElement = queue[frontOfQueue];
		queue[frontOfQueue] = null;
		frontOfQueue = (frontOfQueue + 1) % queue.length;
		size--;
		return frontElement;
	}

	/**
	 * Returns the size of the queue in constant time because it tracks it each modification.
	 * @return the number of items in the queue.
	 */
	@Override
	public int size() {
		
		return size;
	}
	/**
	 * Removes an element from the queue
	 * Time: O(n)
	 * @param element
	 * @return
	 */
	boolean remove(T element) {
		for (int i = 0; i < queue.length; i++) {
			if (queue[i] == element) {
				queue[i] = null;
				if (frontOfQueue == i) {
					frontOfQueue++;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Possibly a linked list would be smaller in memory, but this still works.
	 * References:
	 * Thomas, R. (2018). Algorithms and Data Structures.
	 */
}
