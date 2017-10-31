Documentacion Examen 2
======================
# Solucion Problema 2
<pre><code>
/**
 * 
 * @author Will
 *
 * @param <E> Valor que definido en la lista
 */
public class ListaProblema2<E> implements List<E>{
	public static final int CAPACITY = 1;
	private E[ ] data;
	private int size = 0;
	public ListaProblema2() {
		data = (E[]) new Object[CAPACITY];
	} 
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * @return Devuelve el valor del tamaño del arreglo
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	/**
	 * return Devuelve valor boolean de si la lista esta vacia o no
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	@Override
	/**
	 * @param i indice del valor de la lista que se desea
	 * @return Valor del indice ingresado
	 */
	public E get(int i) {
		return data[i];
	}

	@Override
	/**
	 * @param i indice que se desea editar
	 * @param e valor por el que se desea cambiar
	 * @return valor de la posicion previo a ser cambiado
	 */
	public E set(int i, E e) {
		E temp = data[i];
		data[i] = e;
		return temp;
	}
	/**
	 * @param i indice que se desea editar
	 * @param e valor que se desea agregar a la lista
	 */
	public void add(int i, E e) {
		if(size == data.length) {
			resize(2 * data.length);
		}
		for(int k = size - 1; k >= i; k--) {
			data[k+1]=data[k];
		}
		data[i] = e;
		size++;
	}

	/**
	 * @param i posicion que se desea eliminar de la lista
	 * @return valor eliminado de la lista
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		E temp = data[i];
		for(int k = i; k < size - 1;k++) {
			data[k]=data[k+1];
		}
		data[size-1]=null;
		size--;
		return temp;
	}
	/**
	 * 
	 * @param capacity tamaño del arreglo que desea ser aumentado
	 */
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for(int k = 0; k < size; k++) {
			temp[k] = data[k];
		}
		data = temp;
	}
	/**
	 * @return elementos de la lista
	 */
	public String toString() {
		String elementos="";
		for(int i = 0; i<size;i++) {
			if(data[i]!=null) {
				elementos += ", "+data[i];
			}
		}
		return elementos;
	}
}
</code></pre>
## Descripcion Parte 1
Implementacion de Array lista de capacidad 1 con modificacion de metodo resize y metodo add para que cuando se llene la 
capacidad de la lista esta duplique su tamanño
<pre><code>
/**
 * 
 * @author Will
 *
 */
public class DemostracionLista implements DemoList{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemostracionLista obj = new DemostracionLista();
		List<Integer> miLista = new ListaProblema2<>();
		miLista = obj.crearDemoLista();
		String in = miLista.toString();
		System.out.println(in);
	}

	/**
	 * @return lista de integers con los datos requeridos
	 */
	public List<Integer> crearDemoLista() {
		List<Integer> temp = new ListaProblema2<>();
		temp.add(0, 4);
		temp.add(0, 3);
		temp.add(0, 2);
		temp.add(2, 1);
		temp.add(1, 5);
		temp.add(1, 6);
		temp.add(3, 7);
		temp.add(0, 8);
		return temp;
	}

}
</code></pre>
## Descripcion Parte 2
Main y metodo crearDemoLista hecho con las especificacion del examen
# Solucion Problema 3
<pre><code>
/**
 * 
 * @author Will
 *
 * @param <E> Tipo de dato de la lista
 */
public class LinkedPositionalListProblema3<E> implements PositionalList<E> {
	/**
	 * 
	 * @author Will
	 *
	 * @param <E> Tipo de dato del nodo
	 */
	public static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        /**
         * 
         * @param element elemento del nodo
         * @param prev referencia al nodo previo al actual
         * @param next referencia al nodo siguiente al actual
         */
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        /**
         * @return devuelve el elemento del nodo
         */
        public E getElement() throws IllegalStateException{
            if(next == null){
                throw new IllegalStateException("Posicion no valida");
            }
            return element;
        }
        /**
         * 
         * @param element dato a ser ingresado en el nodo
         */
        public void setElement(E element) {
            this.element = element;
        }
        /**
         * 
         * @return nodo previo al nodo deseado
         */
        public Node<E> getPrev() {
            return prev;
        }
        /**
         * 
         * @param prev nodo a ser puesto como el previo al nodo deseado
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        /**
         * 
         * @return nodo siguiente al nodo deseaddo
         */
        public Node<E> getNext() {
            return next;
        }
        /**
         * 
         * @param next nodo a ser puesto como el siguiente al nodo deseado
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
        
    }
	private Node<E> header = null;
    private Node<E> trailer = null;
    private int size = 0;

    public LinkedPositionalListProblema3() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    /**
     * 
     * @param p posicion a validar en la lista
     * @return nodo de la posicion ingresada
     * @throws IllegalArgumentException
     */
    private Node<E> validar(Position<E> p) throws IllegalArgumentException{
        if (!(p instanceof Node)){
            throw new IllegalArgumentException("P invalido");
        }
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null){
            throw new IllegalArgumentException("p ya no se encuentra en la lista");
        }
        return node;
    }
    /**
     * 
     * @param nodo nodo del que se desea verificar su posicion
     * @return posicion del nodo
     */
    private Position<E> posicion(Node<E> nodo){
        if (nodo == header || nodo == trailer){
            return null;
        }
        return nodo;
    }
    public int size( ){
        return size;
    }
    public boolean isEmpty( ){
        return size == 0;
    }
    /**
     * @return Posicion del header de la lista
     */
    public Position<E> first( ){
        return posicion(header.getNext());
    }
    /**
     * @return Posicion del trailer de la lista
     */
    public Position<E> last( ){
        return posicion(trailer.getPrev());
    }
    /**
     * 
     * @param e dato que se desea agregar
     * @param pred nodo predecesor al nodo que se desea agregar
     * @param succ nodo sucesor al nodo que se desea agregar
     * @return posicion del nuevo nodo
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        succ.setPrev(newest);
        pred.setNext(newest);
	size++;
	return newest;
    }
    /**
     * @return Posicion del nuevo nodo al inicio de la lista
     */
    public Position<E> addFirst(E e){
        return addBetween(e, header, header.getNext());
    }
    /**
     * @return Posicion del nuevo nodo al final de la lista
     */
    public Position<E> addLast(E e){
        return addBetween(e, trailer.getPrev(), trailer);
    }
    /**
     * @param p posicion de la que se desea el anterior
     * @return Posicion del nodo anterior al nodo dado
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        return posicion(node.getPrev());
    }
    /**
     * @param p Posicion de la que se desea el posterior
     * @return Posicion del nodo posterior al nodo dado
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        return posicion(node.getNext());
    }
    /**
     * @param p Posicion de referencia
     * @param e dato del nuevo nodo
     * @return posicion del nuevo nodo
     */
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
         Node<E> node = validar(p);
         return addBetween(e, node.getPrev(), node);
    }
    /**
     * @param p Posicion de referencia
     * @param e dato del nuevo nodo
     * @return posicion del nuevo nodo
     */
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
         Node<E> node = validar(p);
         return addBetween(e, node, node.getNext());
    }
    /**
     * @param p Posicion en la que se desea cambiar dato
     * @param e dato nuevo que se desea cambiar por el viejo
     * @return dato viejo que fue cambiado
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validar(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    /**
     * @param p posicion que se desea eliminar de la lista
     */
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
    }
    /**
     * @param p Posicion que se desea intercambiar
     * @param q Posiicon que se desea intercambiar
     */
    public void swap(Position<E> p, Position<E> q) {
    	E dato;
    	dato = q.getElement();
    	set(q, p.getElement());
    	set(p, dato);
    }
}
</code></pre>
## Descripcion Solucion Problema 3
Metodo de lista posicionalmente enlazada con la midificacion del metodo swap que intercambia los valores de dos posiciones dadas
# Solucion Problema 4
<pre><code>
/**
 * 
 * @author Will
 *
 * @param <E>
 */
public class LinkedPositionalListProblema4<E> implements PositionalList<E>{
	/**
	 * 
	 * @author Will
	 *
	 * @param <E>
	 */
	public static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        public E getElement() throws IllegalStateException{
            if(next == null){
                throw new IllegalStateException("Posicion no valida");
            }
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
        
    }
	private Node<E> header = null;
    private Node<E> trailer = null;
    private int size = 0;
    public LinkedPositionalListProblema4() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    private Node<E> validar(Position<E> p) throws IllegalArgumentException{
        if (!(p instanceof Node)){
            throw new IllegalArgumentException("P invalido");
        }
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null){
            throw new IllegalArgumentException("p ya no se encuentra en la lista");
        }
        return node;
    }
    private Position<E> posicion(Node<E> nodo){
        if (nodo == header || nodo == trailer){
            return null;
        }
        return nodo;
    }
    public int size( ){
        return size;
    }
    public boolean isEmpty( ){
        return size == 0;
    }
    public Position<E> first( ){
        return posicion(header.getNext());
    }
    public Position<E> last( ){
        return posicion(trailer.getPrev());
    }
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        succ.setPrev(newest);
        pred.setNext(newest);
	size++;
	return newest;
    }
    public Position<E> addFirst(E e){
        return addBetween(e, header, header.getNext());
    }
    public Position<E> addLast(E e){
        return addBetween(e, trailer.getPrev(), trailer);
    }
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        return posicion(node.getPrev());
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        return posicion(node.getNext());
    }
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
         Node<E> node = validar(p);
         return addBetween(e, node.getPrev(), node);
    }
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
         Node<E> node = validar(p);
         return addBetween(e, node, node.getNext());
    }
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validar(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validar(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
    }
    /**
     * @param i indice del que se desea la posicion
     * @return posicion del indice
     */
    public Position<E> positionAtIndex(int i){
    	Node<E> temp = header;
    	Position<E> p = null;
    	if(i > size) {
    		throw new IllegalStateException("IndexOutOfBoundsException");
    	}
    	else {
    		for(int j = 0; j < i; j++) {
    			temp = temp.next;
    		}
    		p = posicion(temp);
    	}
    	return p;
    }
}
</code></pre>
## Descripcion de Solucion Problema 4
Implementacion de lista posicionalemnte enlazada con modificacion de meetodo positionAtIndez que en base a un numero devulve la posicion
de ese valor, como si la lista duera manejada por un indice.
# Solucion Problema 5
<pre><code>
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
</code></pre>
## Descripcion Solucion Problema 5
Implementacion dinamica de una pila utilizando como base una implementacion de lista enlazada para que su limite sea la memoria
del ordenador.
