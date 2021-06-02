package com.impacta.estruturadedados.utils.tree;

import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyTreeException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;


public interface Tree<T> extends Iterable<T> {

	public int size();

	public boolean isEmpty();

	public Iterator<T> iterator();

	public Iterable<Position<T>> positions();

	public T replace(Position<T> v, T e) throws InvalidPositionException;

	public Position<T> root() throws EmptyTreeException;

	public Position<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException;

	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException;

	public boolean isInternal(Position<T> v) throws InvalidPositionException;

	public boolean isExternal(Position<T> v) throws InvalidPositionException;

	public boolean isRoot(Position<T> v) throws InvalidPositionException;

}