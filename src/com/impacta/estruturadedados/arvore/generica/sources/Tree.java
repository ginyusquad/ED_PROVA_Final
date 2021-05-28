package com.impacta.estruturadedados.arvore.generica.sources;


import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyTreeException;
import com.impacta.estruturadedados.exception.InvalidPositionException;


public interface Tree<E> extends Iterable<E> {
	
	public int size();
	public boolean isEmpty();
	public Iterator<E> iterator();
	public Iterable<Position<E>> positions();
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	public TreePosition<E> root() throws EmptyTreeException;
	public TreePosition<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	public boolean isRoot(Position<E> v) throws InvalidPositionException;

}