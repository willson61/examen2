package gt.edu.url.examen2.problema3;

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
