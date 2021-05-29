package com.impacta.estruturadedados.utils.tree;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;

public interface BinaryTree<T> extends Tree<T> {

	// Retorna o filho da esquerda do nodo.

	public Position<T> left(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	// Retorna o filho da direita do nodo.

	public Position<T> right(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	// Retorna se o nodo tem filho da esquerda.

	public boolean hasLeft(Position<T> v) throws InvalidPositionException;

	// Retorna se o nodo tem filho da direita.

	public boolean hasRight(Position<T> v) throws InvalidPositionException;

}