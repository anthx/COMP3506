package comp3506.assn2.application;
/**
 * LinkedList Node object
 * Space Complexity O(number of bytes for the 3 pointers)
 * Time: O(1) - impossible to construct more than one at once
 * @author anthony
 *
 * @param <T>
 */
class ListNode<T> {
	T element;
	ListNode<T> next;
	ListNode<T> previous;

	/**
	 * Constructor
	 * Time: O(1) - impossible to construct more than one at once
	 * @param element
	 */
	public ListNode(T element) {
		this.element = element;
	}
	
	/**
	 * Getter
	 * Time: O(1) - Data structure too simple for anything else.
	 * @return
	 */
	public T getElement() {
		return element;
	}
}
