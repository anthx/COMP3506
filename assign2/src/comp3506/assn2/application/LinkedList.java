package comp3506.assn2.application;

import java.util.Iterator;

import comp3506.assn2.utils.Pair;

public class LinkedList<T> implements Iterator<Object> {
	private ListNode<T> head;
	private ListNode<T> tail;
	int size;
	private ListNode<T> current;
	private ListNode<T> next;
	
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
 		tail = list.tail;
		size += list.size;
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
