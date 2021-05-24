package com.impacta.estruturadedados.provafinal.pilha.source;

public class Node<E>{
	
	// Dado mantido pelo Node
	private E dado;
	// Referencia para o Proximo elemento
	private Node<E> prev;
	// Referencia para o elemento Anterior
	private Node<E> next;
	
	public boolean hasNext(){
		return next != null;
	}
	
	public E getDado() {
		return dado;
	}
	public void setDado(E dado) {
		this.dado = dado;
	}
	public Node<E> getPrev() {
		return prev;
	}
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public String toString(){
		return dado.toString();
	}
}
