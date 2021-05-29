package com.impacta.estruturadedados.arvore.generica.sources;

import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;

public interface TreePosition<E> extends Position<E> {

	public void setElement(E o);
	public E getElement();
	public PositionList<Position<E>> getChildren();
	public void setChildren(PositionList<Position<E>> c);
	public TreePosition<E> getParent();
	public void setParent(TreePosition<E> v);
	
}