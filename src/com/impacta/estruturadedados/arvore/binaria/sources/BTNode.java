package com.impacta.estruturadedados.arvore.binaria.sources;

import com.impacta.estruturadedados.arvore.binaria.sources.interfaces.BTPosition;

public class BTNode<E> implements BTPosition<E>{
	
	private E element; 
	private BTPosition<E> left, right, parent;

	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}

	public BTNode() {
		setElement(null);
		setParent(null);
		setLeft(null);
		setRight(null);
	}

	public E element() { return element; }
	public void setElement(E o) { element = o; }
	public BTPosition<E> getLeft() { return left; }
	public void setLeft(BTPosition<E> v) { left = v; }
	public BTPosition<E> getRight() { return right; }
	public void setRight(BTPosition<E> v) { right = v; }
	public BTPosition<E> getParent() { return parent; }
	public void setParent(BTPosition<E> v) { parent = v; }
	
	public String toString() {
		if(element == null)return "";
		return element.toString();
	}

}