package gt.edu.url.examen2.problema5;

/**
 * 
 * @author Will
 *
 * @param <E>
 */
public class DynamicStack<E> implements Stack<E> {
	/**
	 * 
	 * @author Will
	 *
	 * @param <E>
	 */
	private static class Node<E>{
		private E element;
		private Node<E> next;
		/**
		 * 
		 * @param element elemento del nodo
         * @param prev referencia al nodo previo al actual
		 */
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
	}
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param e Elemento a agregar a la pila
	 */
	public void push(E e) {
		Node<E> temp = new Node<>(e, head);
		if(size() == 0) {
			head = temp;
			tail = temp;
			size++;
		}
		else {
			head = temp;
			size++;
		}
	}

	/**
	 * @return elemento en el tope de la pila
	 */
	public E top() {
		if(isEmpty()) {
			return null;
		}
		return head.getElement();
	}

	/**
	 * @return elemento eliminado del tope de la pila
	 */
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		E out = head.getElement();
		head = head.next;
		size--;
		if(size == 0) {
			tail = null;
		}
		return out;
	}

}
