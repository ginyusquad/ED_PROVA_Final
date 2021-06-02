package com.impacta.estruturadedados.fila.source;

public interface Queue<E> {
	
	public int size();
	public boolean isEmpty();
	public E front() throws EmptyQueueException;
	public void enqueue(E element);
	public E dequeue() throws EmptyQueueException;
	public void clear();
	
}
