package com.impacta.estruturadedados.arvore.generica.sources;

import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;

public class TreeNode<E> implements TreePosition<E> {
	
	private TreePosition<E> parent;
	private E element;
	private PositionList<Position<E>> children;
	
	public TreeNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		setElement(element);
		setParent(parent);
		setChildren(children);
	}
	public String toString(){
		String retorno = "";
		
		if(element != null)retorno = element.toString();
		
		return retorno; 
	}
	public TreeNode() {}
	public E element() { return element; }
	public void setElement(E elm) { element = elm; }
	public E getElement() { return element; }
	public PositionList<Position<E>> getChildren() { return children; }
	public void setChildren(PositionList<Position<E>> elm) { children = elm; }
	public TreePosition<E> getParent() { return parent; }
	public void setParent(TreePosition<E> elm) { parent = elm; }
	
}