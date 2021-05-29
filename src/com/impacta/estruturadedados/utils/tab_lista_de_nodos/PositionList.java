package com.impacta.estruturadedados.utils.tab_lista_de_nodos;

import java.util.Iterator;

public interface PositionList<E> extends Iterable<E> {
	// Retorna o n�mero de elementos desta lista.
	public int size();
	
	// Retorna quando a lista est� vazia.
	public boolean isEmpty();
	
	// Retorna o primeiro nodo da lista.
	public Position<E> first();
	
	// Retorna o �ltimo nodo da lista.
	public Position<E> last();
	
	// Retorna o nodo que segue um dado nodo da lista.
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	// Retorna o nodo que antecede um dado nodo da lista.
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	// Insere um elemento no in�cio da lista, retornando uma posi��o nova.
	public void addFirst(E e);
	
	// Insere um elemento na �ltima posi��o, retornando uma posi��o nova.
	public void addLast(E e);
	
	// Insere um elemento ap�s um dado elemento da lista.
	public void addAfter(Position<E> p, E e) throws InvalidPositionException;
	
	// Insere um elemento antes de um dado elemento da lista.
	public void addBefore(Position<E> p, E e) throws InvalidPositionException;

	// Remove um nodo da lista, retornando o elemento l� armazenado.
	public E remove(Position<E> p) throws InvalidPositionException;
	
	// Substitui o elemento armazenado em um determinado nodo, retornando o elemento que estava l� armazenado.
	public E set(Position<E> p, E e) throws InvalidPositionException;
	
	// Retorna um iterador sobre todos os elementos da lista.
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
