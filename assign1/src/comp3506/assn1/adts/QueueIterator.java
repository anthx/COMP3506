package comp3506.assn1.adts;

import java.util.Iterator;

/**
 * 
 * @author anthony Carrick
 *
 * @param <T>
 */
public class QueueIterator<T> implements Iterator<T> {
	
	private int current;
	private T[] queue;
	private TraversableQueue<T> traversableQueue;
	private int frontOfQueue;
	
	public QueueIterator(T[] queue, TraversableQueue<T> traversableQueue, int frontOfQueue) {
		current = frontOfQueue;
		this.queue = queue;
		this.traversableQueue = traversableQueue;
	}

	@Override
	public boolean hasNext() {
		if (traversableQueue.size() > current) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public T next() {
		int next;
		if (frontOfQueue <= traversableQueue.size()) {
			next = current;
		}
		else {
			next = (current + traversableQueue.size()) % queue.length;
		}
		
		T result = queue[next];
		this.current++;
		return result;
	}

}
