package comp3506.assn2.application;

import java.util.Iterator;

import comp3506.assn2.utils.Pair;

public class LinkedList<T> implements Iterator<Object> {
	private ListNode<T> head;
	private ListNode<T> tail;
	int size;
	private ListNode<T> current;
	
	public LinkedList() {
		size = 0;
	}
	
	public void append(T element) {
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
