package com.impacta.estruturadedados.pilha.source;


public interface Stack<E> {
	// Retorna o n�mero de elementos na pilha. 
	public int size(); 
	
	// @return True se a pilha � vazia, False em caso contr�rio. 
	public boolean isEmpty();
	
	// @return o elemento que est� no topo da pilha.
	// @exception EmptyStackException se a pilha estiver vazia.
	public E top() throws EmptyStackException;
	
	 // @param elemento a ser inserido.
	public void push(E elemento); 
	
	 // @return elemento removido. 
	 // @exception EmptyStackException se a pilha estiver vazia. 
	public E pop() throws EmptyStackException; 
}
