package comp3506.assn2.application;

import java.util.Iterator;

public class LinkedList<T> implements Iterator<Object> {
	private ListNode<T> head;
	private ListNode<T> tail;
	int size;
	private ListNode<T> current;
	
	public LinkedList() {
		size = 0;
	}
	
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

	public ListNode<T> getHead() {
		return head;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public ListNode<T> next() {
		ListNode<T> temp = current;
		current = current.next;
		return temp;
	}
}
