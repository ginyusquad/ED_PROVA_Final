package com.impacta.estruturadedados.listaDeNodos.source;

import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyListException;
import com.impacta.estruturadedados.exception.InvalidPositionException;

public class NodePositionList<E> implements PositionList<E> {
	protected int numElts; // N�mero de elementos na lista
	protected DNode<E> header, trailer; // Sentinelas especiais
	// Construtor que cria uma lista vazia
	public NodePositionList() {
		numElts = 0;
		header = new DNode<E>(null, null, null); // cria a cabe�a
		trailer = new DNode<E>(header, null, null); // cria a cauda
		header.setNext(trailer); // faz a cabe�a e a cauda apontarem uma para a outra
	}
	

	public void makeFirst(Position<E> p) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		
		// Fechando ferida da remo��o de P na lista
		v.getPrev().setNext(v.getNext());
		v.getNext().setPrev(v.getPrev());
		
		// Adicionado ao come�o da lista
		
		v.setNext(header.getNext());
		v.setPrev(header);
		
		header.getNext().setPrev(v);
		
		header.setNext(v);
		
	}
	
	public NodePositionList<E> toReverse() {
		
		NodePositionList<E> positionReverse = new NodePositionList<E>();

		for(E node : this){
			positionReverse.addFirst(node);
		}
		
		// posso dar um return positionReverse
		// ou pegar os header e a trail do positionReverse
		// e implantar no atual
		
		return positionReverse;
	}
	
	// Verifica se a posi��o � v�lida para esta lista e a converte para DNode se for v�lida
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
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
	// Retorna a quantidade de elementos na lista
	public int size() {return numElts;}
	// Retorna quando a lista esta vazia
	public boolean isEmpty() { return (numElts == 0); }
	
	// Retorna a primeira posi��o da lista
	public Position<E> first() throws EmptyListException {
		if (isEmpty()) throw new EmptyListException("List is empty");
		return header.getNext();
	}
	// Retorna a posi��o que antecede a fornecida
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> prev = v.getPrev();
		if (prev == header) throw new BoundaryViolationException("Cannot advance past the beginning of the list");
		return prev;
	}
	// Insere o elemento antes da posi��o fornecida, retornando a nova posi��o
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}
	// Insere o elemento dado no in�cio da lista, retornando a nova posi��o
	public void addFirst(E element) {
		numElts++;
		DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}
	
	// Remove da lista a posi��o fornecida
	public E remove(Position<E> p) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts--;
		DNode<E> vPrev = v.getPrev();
		DNode<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem = v.element();
		// Desconecta a posi��o da lista e marca-a como inv�lida
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}
	// Substitui o elemento da posi��o fornecida por um novo e retorna o elemento velho
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}
	// Retorna o �ltimo nodo da lista.
	public Position<E> last() {
		if (isEmpty()) throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}
	// Retorna o nodo que segue um dado nodo da lista.
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> next = v.getNext();
		if (next == trailer) throw new BoundaryViolationException("Cannot advance past the finaling of the list");
		return next;
	}
	
	// Insere um elemento na �ltima posi��o, retornando uma posi��o nova.
	public void addLast(E e) {
		numElts++;
		DNode<E> newNode = new DNode<E>(trailer.getPrev(), trailer, e);
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
	}
	// Insere um elemento ap�s um dado elemento da lista.
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}
	// Retorna a representa��o textual de uma lista de nodos
	public static <E> String toString(PositionList<E> l) {
		String s = "";
		for (E i: l) { s += ", " + i; }
		s = (s.length() == 0 ? s : s.substring(2));
		return  "[ " + s + " ]";
	}
	// Retorna o iterator a partir do ElemenIterator.
	public Iterator<E> iterator() { return new ElementIterator<E>(this); }
	public String toString() { return toString(this); }


	
}

	
