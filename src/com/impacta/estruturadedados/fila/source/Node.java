package com.impacta.estruturadedados.fila.source;

public class Node<E> {
	
	private E dado;
	// link para o proximo elemento da lista
	private Node<E> next;
	// link para o elemento anterior da lista
	// Pode estar nullo dependendo da lista!
	private Node<E> prev;
	
	public Node(E dado) {
		this.dado = dado;
	}
	
	public Node() {}
	
	public E getDado() {
		return dado;
	}
	public void setDado(E dado) {
		this.dado = dado;
	}
	
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public Node<E> getPrev() {
		return prev;
	}
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
	/* Verifica se existe uma proximo elemento do node atual */
	public boolean hasNext() {
		return next != null;
	}
	/* Verifica se existe uma proximo elemento do node atual */
	public boolean hasPrev() {
		return prev != null;
	}

	public boolean isExist() {
		return dado != null;
	}
	
	// Preciso de algo que n�o seja getDado kkk
	public E getValue() {
		return dado;
	}
	public void setValue(E dado) {
		this.dado = dado;
	}
	
	public String toString() {
		return dado.toString();
	}
	
}