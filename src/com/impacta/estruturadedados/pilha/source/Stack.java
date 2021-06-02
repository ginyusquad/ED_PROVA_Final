package com.impacta.estruturadedados.pilha.source;


public interface Stack<E> {

	public int size(); 
	public boolean isEmpty();
	public E top() throws EmptyStackException;
	public void push(E elemento); 
	public E pop() throws EmptyStackException; 
}
