
public class Stack<T> {

	Node<T> head;
	
	public Stack() {
		head = null;
	}
	
	public void push(T data) {
		Node<T> newNode = new Node<T> (data, head);
		this.head = newNode;
	}
	
	public T pop() {
		Node<T> dataNode = this.head;
		if (dataNode == null) return null;
		this.head = dataNode.getNext();
		return dataNode.getData();
	}
	
	public T peek() {
		return this.head.getData();
	}
	
	public int getLength() {
		int counter = 0;
		Node<T> currentNode = head;
		while (currentNode != null) {
			counter++;
			currentNode = currentNode.getNext();
		}
		
		return counter;
	}
	
}
