package com.impacta.estruturadedados.utils.tree;

import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;


public interface CompleteBinaryTree<T> extends BinaryTree<T> {
	public Position<T> add(T elem);
	public T remove();
}
