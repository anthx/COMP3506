package comp3506.assn2.application;

import java.util.Iterator;

public class LinkedList<T> implements Iterator<Object> {
	private ListNode<T> head;
	private ListNode<T> tail;
	int size;
	private ListNode<T> current;
	
	/**
	 * Constructor
	 */
	public LinkedList() {
		size = 0;
	}
	
	/**
	 * Adds a new element to LinkedList
	 * O(1) - just creating a new object and re-reference everything
	 * Space: O(1) 3 pointers then the method is done.
	 * @param element
	 */
	public void add(T element) {
		ListNode<T> aNode = new ListNode<>(element);
		aNode.previous = tail;
		
		if (head == null) {
			head = aNode;
			current = head;
		}
		else {
			tail.next = aNode;
		}
		tail = aNode;
		
		size++;
	}
	
	/**
	 * Appends an existing LinkedList to this one.
	 * O(1) - just referencing and adding size int together. 
	 * @param list
	 */
	public void append(LinkedList<T> list) {
		if (tail != null) {
			tail.next = list.head;
		}
		
 		tail = list.tail;
		size += list.size;
		if (head == null) {
			head = list.head;
		}
		if (current == null) {
			current = list.current;
		}
		
	}

	/**
	 * Getter for head element
	 * O(1) referencing only
	 * @return the head
	 */
	public ListNode<T> getHead() {
		return head;
	}

	/**
	 * Getter for has next boolean.
	 * O(1) referencing only
	 */
	@Override
	public boolean hasNext() {
		return current != null;
	}

	/**
	 * Getter for next element
	 * O(1) referencing only
	 */
	@Override
	public ListNode<T> next() {
		ListNode<T> temp = current;
		current = current.next;
		return temp;
	}
	
	/**
	 * Resets the iterator
	 * O(1) referencing only
	 */
	public void reset() {
		if (current == null) {
			current = head;
		}
	}
}
