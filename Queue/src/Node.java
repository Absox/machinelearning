
public class Node<T> {

	Node<T> last;
	Node<T> next;
	T data;
	
	public Node(Node<T> last, Node<T> next, T data) {
		this.last = last;
		this.next = next;
		this.data = data;
	}
	
	public Node<T> getLast() {
		return this.last;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setLast(Node<T> last) {
		this.last = last;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
