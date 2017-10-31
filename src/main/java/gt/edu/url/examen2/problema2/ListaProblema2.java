package gt.edu.url.examen2.problema2;

import java.util.Iterator;

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
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E get(int i) {
		return data[i];
	}

	@Override
	public E set(int i, E e) {
		E temp = data[i];
		data[i] = e;
		return temp;
	}

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

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		E temp = data[i];
		for(int k = i; k < size - 1;k++) {
			data[k]=data[k+1];
		}
		data[size-1]=null;
		size--;
		return temp;
	}
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for(int k = 0; k < size; k++) {
			temp[k] = data[k];
		}
		data = temp;
	}
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
