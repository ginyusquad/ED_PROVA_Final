package com.impacta.estruturadedados.arvore.generica.sources;

import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyListException;
import com.impacta.estruturadedados.exception.InvalidPositionException;


public class NodePositionList<E> implements PositionList<E> {
	
	protected int sizeElts; 
	protected DNode<E> head, trail; 
	
	public NodePositionList() {
		
		sizeElts = 0;
		head = new DNode<E>(null, null, null); 
		trail = new DNode<E>(head, null, null);
		head.setNext(trail);
	}
	
	public Position<E> first() throws EmptyListException {
		
		if (isEmpty()) throw new EmptyListException("List is empty");
		return head.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<E> v = checkPosition(p);
		DNode<E> prev = v.getPrev();
		if (prev == head) throw new BoundaryViolationException("Cannot advance past the beginning of the list");
		return prev;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		DNode<E> v = checkPosition(p);
		sizeElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}

	public void addFirst(E element) {
		
		sizeElts++;
		DNode<E> newNode = new DNode<E>(head, head.getNext(), element);
		head.getNext().setPrev(newNode);
		head.setNext(newNode);
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		
		DNode<E> v = checkPosition(p);
		sizeElts--;
		DNode<E> vPrev = v.getPrev();
		DNode<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem = v.element();
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		DNode<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}

	public Position<E> last() {
		
		if (isEmpty()) throw new EmptyListException("List is empty");
		return trail.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<E> v = checkPosition(p);
		DNode<E> next = v.getNext();
		if (next == trail) throw new BoundaryViolationException("Cannot advance past the finaling of the list");
		return next;
	}

	public void addLast(E e) {
		
		sizeElts++;
		DNode<E> newNode = new DNode<E>(trail.getPrev(), trail, e);
		trail.getPrev().setNext(newNode);
		trail.setPrev(newNode);
	}

	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		
		DNode<E> v = checkPosition(p);
		sizeElts++;
		DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}

	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		
		if (p == null) throw new InvalidPositionException("Null position passed to NodeList");
		if (p == head) throw new InvalidPositionException("The header node is not a valid position");
		if (p == trail) throw new InvalidPositionException("The trailer node is not a valid position");
		try {
			DNode<E> tmp = (DNode<E>) p;
			if ((tmp.getPrev() == null) || (tmp.getNext() == null))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			return tmp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}
	
	public Iterator<E> iterator() { return new ElementIterator<E>(this); }
	
	public String toString() {
		
		String s = "";
		// interando sobre min mesmo
		for (E i: this) { s += ", " + i; }
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
		
	}
	
	public int size() {return sizeElts;}

	public boolean isEmpty() { return (sizeElts == 0); }
}

