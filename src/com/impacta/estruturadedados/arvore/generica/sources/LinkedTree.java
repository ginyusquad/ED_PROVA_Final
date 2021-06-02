package com.impacta.estruturadedados.arvore.generica.sources;


import java.util.Iterator;

import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyTreeException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.exception.NonEmptyTreeException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.NodePositionList;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;
import com.impacta.estruturadedados.utils.tree.Tree;


public class LinkedTree<E> implements  Tree<E> {
	
	protected TreePosition<E> raiz; 
	protected int size; 
	
	public LinkedTree() {
		raiz = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return (vv.getChildren() == null) || vv.getChildren().isEmpty();
	}
	
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}
	
	public TreePosition<E> root() throws EmptyTreeException {
		if (raiz == null) throw new EmptyTreeException("The tree is empty");
		return raiz;
	}
	
	public TreePosition<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> parentPos = vv.getParent();
		if (parentPos == null) throw new BoundaryViolationException("No parent");
		return parentPos;
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return vv.getChildren();
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (size != 0) preorderPositions(root(), positions);
		return positions;
	}

	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = new NodePositionList<E>();
		for (Position<E> pos : positions) elements.addLast(pos.element());
		return elements.iterator();
	}
	
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}

	public TreePosition<E> addRoot(E e) throws NonEmptyTreeException {
		if (!isEmpty()) throw new NonEmptyTreeException("Tree already has a root");
		size = 1;
		raiz = createNode(e, null, null);
		return raiz;
	}

	public void swapElements(Position<E> v, Position<E> w) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> ww = checkPosition(w);
		E temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}

	protected TreePosition<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof TreePosition)) throw new InvalidPositionException("The position is invalid");
		return (TreePosition<E>) v;
	}

	protected TreePosition<E> createNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		return new TreeNode<E>(element, parent, children);
	}

	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		pos.addLast(v);
		for (Position<E> w : children(v)) preorderPositions(w, pos);
	}
	
	public String toString() { 
		
		String s = "";

		for (E i : this) { s += ", " + i; }
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}
	


	public String parentheticRepresentation (Tree<E> T, Position<E> v) {
		
		String s = v.element().toString();
		if (T.isInternal(v)) {
			Boolean firstTime = true;
			for (Position<E> w : T.children(v)) {
				if (firstTime) {
					s += "(" + parentheticRepresentation(T, w);
					firstTime = false; 
				} else {
					s += "," + parentheticRepresentation(T, w);
				}
			}
			s += ")";
		}
		return s;
	}
	

	public String toStringPostorder(LinkedTree<E> T, Position<E> v) {
		
		String s = "";			
		for (Position<E> w : children(v)) {
			if (isInternal(w)) {
				s += toStringPostorder(T, w);
				s += w.element().toString() + " - ";
			} else
				s += w.element().toString() + " - ";
		}
		if (v == raiz)
			return s + raiz.element();
		else
			return s;
	}
	/* 3_ c) diskSpace conforme o algoritmo do slide 80. (Ultilizei o tamanho das strings como 
	 	como base para o calculo Exemplo: 
	 		Ultramar => 8 bytes,
	 		�frica => 6 bytes,
	 		Europa=> 6 bytes,
	 		�sia=> 4 bytes,
	 		Austr�lia => 9 bytes  e etc) */
	public int diskSpace(LinkedTree<E> T, Position<E> v) {
		int retorno = v.element().toString().getBytes().length;
		// Inteirando sobre o positions
		for (Position<E> w : T.positions()) {
			
			retorno += w.toString().getBytes().length;
		}
		
		return retorno;
	}
	// 3_ d) depth conforme o algoritmo do slide 84.
	public int depth(LinkedTree<E> T, Position<E> v) {
		
		if (v == raiz)
			return 0;
		else 
			return 1 + depth (T, parent(v));
	}

	// 3_ e) height1 conforme o algoritmo do slide 90.
	public int height1(LinkedTree<E> T) {
		
		int h = 0;
		for (Position<E> w : positions())
			if (isExternal(w))
				h = Math.max(h, depth(T, w));
		return h;
	}

	// 3_ f) height2 conforme o algoritmo do slide 103.
	public int height2(LinkedTree<E> T, Position<E> v) {
		
		int h;
		
		if (isExternal(v)) return 0;
		else {
			h = 0;
			for (Position<E> w : children(v))
				h = Math.max(h, height2(T, w));
			return 1 + h;
		}
	}
	
	public boolean isEmpty() { return (size == 0); }
	public boolean isInternal(Position<E> v) throws InvalidPositionException { return !isExternal(v); }
}
