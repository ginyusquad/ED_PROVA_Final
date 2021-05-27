package com.impacta.estruturadedados.listaDeNodos.source;

import com.impacta.estruturadedados.exception.InvalidPositionException;

public class DNode<E> implements Position<E> {

	private DNode<E> prev, next; // Refer�ncia para os nodos anterior e posterior
	private E element; // Elemento armazenado nesta posi��o
	PositionList<E> listNodes = null;
	
	public DNode(DNode<E> newPrev, DNode<E> newNext, E elem) {
		prev = newPrev;
		next = newNext;
		element = elem;
	}
	// M�todo da interface Position
	public E element() throws InvalidPositionException {
		// Verifica se esta ligado a outro elemento na lista
		if ((prev == null) && (next == null))throw new InvalidPositionException("Position is not in a list!");
		
		return element;
	}
	// M�todos de acesso
	public DNode<E> getNext() { return next; }
	public DNode<E> getPrev() { return prev; }
	// M�todos de atualiza��o
	public void setNext(DNode<E> newNext) { next = newNext; }
	public void setPrev(DNode<E> newPrev) { prev = newPrev; }
	public void setElement(E newElement) { element = newElement; }
	@Override
	public void setListNodes(PositionList<E> lista) {
		this.listNodes = lista;
	}
	@Override
	public PositionList<E> getListNodes() {
		return listNodes;
	}
	

}
