package com.impacta.estruturadedados.pilha.source;

public class NodeStack<E> implements Stack<E> {
	
	private Node<E> cabeca =  null;
	private int size = 0;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size < 1;
	}

	@Override
	public E top() throws EmptyStackException {
		if(isEmpty())throw new EmptyStackException(" Stack is empty");
		return cabeca.getDado();
	}

	@Override
	public void push(E elemento){
		
		Node<E> node =  new Node<E>();
		node.setDado(elemento);

		
		if(cabeca != null){
			cabeca.setPrev(node);
			node.setPrev(null);
			node.setNext(cabeca);
		}
		
		cabeca = node;
		size++;
	}

	@Override
	public E pop() throws EmptyStackException {
		
		E elemento =  null;
		
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
		
				
		elemento = cabeca.getDado();
		
		
		if(cabeca.hasNext()){
			cabeca.getNext().setPrev(null);
			cabeca = cabeca.getNext();
			
		}else cabeca = null;
		
		size--;
		
		return elemento;
	}
	
	public String toString() {
		String s;
		Node<E> p = cabeca;

		s = "[ ";
		while (p != null) {
			s += p.getDado();
			s += ", ";
			p = p.getNext();
		}
		if (cabeca != null && s.length() > 1) 
			s = s.substring(0, s.length() - 2);
		return s + " ]";
	}

	
}
