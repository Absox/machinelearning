
public class Queue<T> {

	Node<T> head;
	Node<T> end;
	
	public Queue() {
		this.head = new Node<T>(null, null, null);
		end = null;
	}
	
	public void add(T data) {
		Node<T> newNode = new Node<T>(head, head.getNext(), data);
		Node<T> nextNode = head.getNext();
		if (nextNode != null) nextNode.setLast(newNode);
		head.setNext(newNode);
		
		if (end == null) end = newNode;
	}
	
	public T remove() {
		if (end == null) return null;
		T data = end.getData();
		
		Node<T> lastNode = end.getLast();
		lastNode.setNext(null);
		
		if (lastNode.getLast() != null) {
			end = lastNode;
		} else {
			end = null;
		}
		
		return data;
	}
	
	public void print() {
		Node<T> currentNode = head;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
			System.out.print(currentNode.getData() +  " ");
		}
		System.out.println();
	}
}
