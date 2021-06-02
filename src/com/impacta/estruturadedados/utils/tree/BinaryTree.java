package com.impacta.estruturadedados.utils.tree;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;

public interface BinaryTree<T> extends Tree<T> {

	public Position<T> left(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	public Position<T> right(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	public boolean hasLeft(Position<T> v) throws InvalidPositionException;

	public boolean hasRight(Position<T> v) throws InvalidPositionException;

}