package comp3506.assn2.application;

class ListNode<T> {
	T element;
	ListNode<T> next;
	ListNode<T> previous;

	public ListNode(T element) {
		this.element = element;
	}
}
