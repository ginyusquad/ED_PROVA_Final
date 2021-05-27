package com.impacta.estruturadedados.listaDeNodos.source;

import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.InvalidPositionException;

public class NodePositionListIsContain<E> extends NodePositionList<E> {
	
	public NodePositionListIsContain() {
		super();
	}
	
	// Verifica se a posi��o � v�lida para esta lista e a converte para DNode se for v�lida
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null) throw new InvalidPositionException("Null position passed to NodeList");
		if (p == header) throw new InvalidPositionException("The header node is not a valid position");
		if (p == trailer) throw new InvalidPositionException("The trailer node is not a valid position");
		try {
			DNode<E> temp = (DNode<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null) || (!isContain(p)))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}
	
	public boolean isContain(Position<E> p){
		DNode<E> temp = (DNode<E>) p;
		return ( !((temp.getPrev() == null) || (temp.getNext() == null))
				&& p.getListNodes().equals(this));
	}
	
	/*public boolean equals(NodePositionListIsContain<E> lista){
		return ( lista.size()  == this.size() &&
				 lista.header  == this.header &&
				 lista.trailer == this.trailer); 
	}*/
	
	// metrodos rescritos para adicioanr a referencia da lista nos nodes
	
	public void makeFirst(Position<E> p) throws InvalidPositionException {
		p.setListNodes(this);
		super.makeFirst(p);
	}
	// Retorna a posi��o que antecede a fornecida
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		p.setListNodes(this);
		return super.prev(p);
	}
	// Insere o elemento antes da posi��o fornecida, retornando a nova posi��o
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
		newNode.setListNodes(this);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}
	// Insere o elemento dado no in�cio da lista, retornando a nova posi��o
	public void addFirst(E element) {
		numElts++;
		DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
		newNode.setListNodes(this);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}
	
	// Insere um elemento na �ltima posi��o, retornando uma posi��o nova.
	public void addLast(E e) {
		numElts++;
		DNode<E> newNode = new DNode<E>(trailer.getPrev(), trailer, e);
		newNode.setListNodes(this);
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
	}
	// Insere um elemento ap�s um dado elemento da lista.
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
		newNode.setListNodes(this);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}
	
	
	
}

	
