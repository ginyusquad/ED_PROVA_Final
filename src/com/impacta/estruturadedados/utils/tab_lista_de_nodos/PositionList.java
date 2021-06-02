package com.impacta.estruturadedados.utils.tab_lista_de_nodos;

import java.util.Iterator;

public interface PositionList<E> extends Iterable<E> {

	public int size();

	public boolean isEmpty();

	public Position<E> first();

	public Position<E> last();

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

	public void addFirst(E e);

	public void addLast(E e);

	public void addAfter(Position<E> p, E e) throws InvalidPositionException;

	public void addBefore(Position<E> p, E e) throws InvalidPositionException;

	public E remove(Position<E> p) throws InvalidPositionException;

	public E set(Position<E> p, E e) throws InvalidPositionException;

	public Iterator<E> iterator();
	
	@SuppressWarnings("serial")
	public class BoundaryViolationException extends RuntimeException {
	public BoundaryViolationException(String err) { super(err); }
	}
	
	@SuppressWarnings("serial")
	public class EmptyListException extends RuntimeException {
	public EmptyListException(String err) { super(err); }
	}
	
	@SuppressWarnings("serial")
	public class InvalidPositionException extends RuntimeException {
	public InvalidPositionException(String err) { super(err); }
	}

}
