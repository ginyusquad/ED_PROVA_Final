package com.impacta.estruturadedados.fila.source;

public class Node<E> {
	
	private E dado;

	private Node<E> next;

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

	public boolean hasNext() {
		return next != null;
	}

	public boolean hasPrev() {
		return prev != null;
	}

	public boolean isExist() {
		return dado != null;
	}
	
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