package com.impacta.estruturadedados.utils.tree;

import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyTreeException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;


public interface Tree<T> extends Iterable<T> {

	// Retorna a quantidade de nodos da �rvore.

	public int size();

	// Retorna se a �rvore est� vazia.

	public boolean isEmpty();

	// Retorna um iterador sobre os elementos armazenados na �rvore.

	public Iterator<T> iterator();

	// Retorna uma cole��o iter�vel dos nodos.

	public Iterable<Position<T>> positions();

	// Substitui o elemento armazenado em um dado nodo.

	public T replace(Position<T> v, T e) throws InvalidPositionException;

	// Retorna a raiz da �rvore.

	public Position<T> root() throws EmptyTreeException;

	// Retorna o pai de um dado nodo.

	public Position<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	// Retorna uma cole��o iter�vel dos filhos de um dado nodo.

	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException;

	// Retorna se um dado nodo � interno.

	public boolean isInternal(Position<T> v) throws InvalidPositionException;

	// Retorna se um dado nodo � externo.

	public boolean isExternal(Position<T> v) throws InvalidPositionException;

	// Retorna se um dado nodo � a raiz da �rvore.

	public boolean isRoot(Position<T> v) throws InvalidPositionException;

}