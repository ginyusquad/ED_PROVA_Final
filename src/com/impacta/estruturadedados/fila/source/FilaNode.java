package com.impacta.estruturadedados.fila.source;

public class FilaNode<E> implements Queue<E>{
	
	private Node<E> head;
	private Node<E> tail;
	private int tamanho = 0;

	// Lista ciscular encadeada
	public FilaNode() {
		this.head = new Node<E>();
		this.tail = new Node<E>();
		// Encadeando a estrutura
		head.setNext(tail);
		head.setPrev(tail);
		tail.setNext(head);
		tail.setPrev(head);
	}
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamanho;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tamanho < 1;
	}

	@Override
	public E front() throws EmptyQueueException {
		
		if(tamanho == 0) throw new EmptyQueueException(" Queue is empty ");
		
		
		return head.getNext().getDado();
	}

	@Override
	public void enqueue(E elemento) {
		
		// Bem mais facil adicioanr um novo elemento!!
			Node<E> nodeEmpilhado = new Node<E>(elemento);
			
			nodeEmpilhado.setPrev(tail.getPrev());
			nodeEmpilhado.setNext(tail);
			tail.getPrev().setNext(nodeEmpilhado);
			tail.setPrev(nodeEmpilhado);

			
			tamanho++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		
		Node<E> nodeDesempinhado = head.getNext();
		
		
		if(tamanho == 0)throw new EmptyQueueException(" Queue is empty.");
		
		
		
		head.setNext( nodeDesempinhado.getNext() );
		nodeDesempinhado.getNext().setPrev(head);
		
		
		tamanho--;
	
		// If se for o ultimo elemento da lsita defazer a head e tail
		
		
		return nodeDesempinhado.getDado();
	}
	
	
	public String toString() {
		
		// Valida se esta zerado a lista
		if(tamanho == 0)return "[]";
		
		String vetor = "["+head.getNext().getDado().toString();
		
		Node<E> nodeAtual = head.getNext();
		
		while(nodeAtual.hasNext() && !nodeAtual.getNext().equals(head) && !nodeAtual.getNext().equals(tail) ){
			
			nodeAtual = nodeAtual.getNext();
			vetor += ", "+nodeAtual.toString();
			
		}
		
		
		return vetor+"]";
	}


	@Override
	public void clear() {
		this.head = new Node<E>();
		this.tail = new Node<E>();
		// Encadeando a estrutura
		head.setNext(tail);
		head.setPrev(tail);
		tail.setNext(head);
		tail.setPrev(head);
		tamanho = 0;
	}

}
