package com.impacta.estruturadedados.utils.tab_lista_de_nodos;

import java.util.Iterator;

public class NodePositionList<E> implements PositionList<E> {
	protected int numElts;

	protected DNode<E> header, trailer;

	public NodePositionList() {
		numElts = 0;
		header = new DNode<E>(null, null, null); 
		trailer = new DNode<E>(header, null, null);
		header.setNext(trailer);
	}
	
	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null) throw new InvalidPositionException("Null position passed to NodeList");
		if (p == header) throw new InvalidPositionException("The header node is not a valid position");
		if (p == trailer) throw new InvalidPositionException("The trailer node is not a valid position");
		try {
			DNode<E> temp = (DNode<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}

	public int size() {return numElts;}

	public boolean isEmpty() { return (numElts == 0); }

	public Position<E> first() throws EmptyListException {
		if (isEmpty()) throw new EmptyListException("List is empty");
		return header.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> prev = v.getPrev();
		if (prev == header) throw new BoundaryViolationException("Cannot advance past the beginning of the list");
		return prev;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}

	public void addFirst(E element) {
		numElts++;
		DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts--;
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
		return trailer.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> next = v.getNext();
		if (next == trailer) throw new BoundaryViolationException("Cannot advance past the finaling of the list");
		return next;
	}

	public void addLast(E e) {
		numElts++;
		DNode<E> newNode = new DNode<E>(trailer.getPrev(), trailer, e);
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
	}

	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}

	public static <E> String toString(PositionList<E> l) {
		String s = "";
		for (E i: l) { s += ", " + i; }
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}

	public Iterator<E> iterator() { return new ElementIterator<E>(this); }
	public String toString() { return toString(this); }
}

