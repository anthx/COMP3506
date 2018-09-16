package comp3506.assn2.application;

import java.util.Iterator;

public class LinkedList<T> implements Iterator<Object> {
	private ListNode<T> head;
	private ListNode<T> tail;
	private int size;
	private ListNode<T> current;
	
	public void append(T element) {
		ListNode<T> aNode = new ListNode<>(element);
		add(aNode);
	}

	public void add(ListNode<T> aNode) {
		aNode.previous = this.tail;
		this.tail = aNode;
		if (this.head == null) {
			this.head = aNode;
			current = this.head;
		}
		this.size++;
	}

	public ListNode<T> getHead() {
		return head;
	}

	@Override
	public boolean hasNext() {
		return current.next != null;
	}

	@Override
	public Object next() {
		Object next = current.next;
		current = current.next;
		return next;
	}
}
