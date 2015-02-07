
public class Node<T> {

	Node<T> next;
	T data;
	
	public Node (T o, Node<T> next) {
		this.data = o;
		this.next = next;
	}
	
	public T getData() {
		return this.data;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
}
