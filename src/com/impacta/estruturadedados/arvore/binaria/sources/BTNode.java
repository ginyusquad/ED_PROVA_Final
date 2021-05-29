package com.impacta.estruturadedados.arvore.binaria.sources;

import com.impacta.estruturadedados.arvore.binaria.sources.interfaces.BTPosition;

public class BTNode<E> implements BTPosition<E>{
	
	private E element; // elemento armazenado neste nodo
	
	private BTPosition<E> left, right, parent; // nodos adjacentes
	
	// Construtor principal
	
	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		
		setElement(element);
		
		setParent(parent);
		
		setLeft(left);
		
		setRight(right);
	
	}
	
	// Retorna o elemento armazenado nesta posi��o
	
	public BTNode() {
		setElement(null);

		setParent(null);

		setLeft(null);

		setRight(null);
	}

	public E element() { return element; }
	
	// Define o elemento armazenado nesta posi��o
	
	public void setElement(E o) { element = o; }
	
	// Retorna o filho da esquerda desta posi��o
	
	public BTPosition<E> getLeft() { return left; }
	
	// Define o filho da esquerda desta posi��o
	
	public void setLeft(BTPosition<E> v) { left = v; }
	
	// Retorna o filho da direita desta posi��o
	
	public BTPosition<E> getRight() { return right; }
	
	// Define o filho da direita desta posi��o
	
	public void setRight(BTPosition<E> v) { right = v; }
	
	// Retorna o pai desta posi��o
	
	public BTPosition<E> getParent() { return parent; }
	
	// Define o pai desta posi��o
	
	public void setParent(BTPosition<E> v) { parent = v; }
	
	public String toString() {
		if(element == null)return "";
		return element.toString();
	}

}