package com.impacta.estruturadedados.arvore.binaria.sources;

import java.util.Arrays;
import java.util.Iterator;

import com.impacta.estruturadedados.arvore.binaria.sources.interfaces.BTPosition;
import com.impacta.estruturadedados.exception.BoundaryViolationException;
import com.impacta.estruturadedados.exception.EmptyTreeException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.exception.NonEmptyTreeException;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.NodePositionList;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;
import com.impacta.estruturadedados.utils.tree.BinaryTree;
import com.impacta.estruturadedados.utils.tree.Tree;


public class LinkedBinaryTree<E> implements BinaryTree<E> {

	protected BTPosition<E> root; // refer�ncia para a raiz

	protected int size; // n�mero de nodos

// Cria uma �rvore bin�ria vazia.

	public LinkedBinaryTree() {

		root = null; // inicia com uma �rvore vazia

		size = 0;

	}

// Retorna o n�mero de nodos da �rvore.

	public int size() {
		return size;
	}

// Retorna se um nodo � interno.

	public boolean isInternal(Position<E> v) throws InvalidPositionException {

		checkPosition(v); // m�todo auxiliar

		return (hasLeft(v) || hasRight(v));

	}

// Retorna se um nodo � a raiz.

	public boolean isRoot(Position<E> v) throws InvalidPositionException {

		checkPosition(v);

		return (v == root());

	}

// Retorna se um nodo tem o filho da esquerda.

	public boolean hasLeft(Position<E> v) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		return (vv.getLeft() != null);

	}

// Retorna a raiz da �rvore.

	public Position<E> root() throws EmptyTreeException {

		if (root == null)
			throw new EmptyTreeException("The tree is empty");

		return root;

	}

// Retorna o filho da esquerda de um nodo.

	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<E> vv = checkPosition(v);

		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos == null)
			throw new BoundaryViolationException("No left child");

		return leftPos;

	}

// Retorna o pai de um nodo.

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<E> vv = checkPosition(v);

		Position<E> parentPos = (Position<E>) vv.getParent();

		if (parentPos == null)
			throw new BoundaryViolationException("No parent");

		return parentPos;

	}

// Retorna uma cole��o iter�vel contendo os filhos de um nodo.

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {

		PositionList<Position<E>> children = new NodePositionList<Position<E>>();

		if (hasLeft(v))
			children.addLast(left(v));

		if (hasRight(v))
			children.addLast(right(v));

		return children;

	}

//Cria uma lista que armazena os nodos da sub�rvore de um nodo ordenados de acordo com o caminhamento inorder da sub�rvore.

	public void inorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {

		if (hasLeft(v))
			inorderPositions(left(v), pos); // recurs�o sobre o filho da esquerda

		pos.addLast(v);

		if (hasRight(v))
			inorderPositions(right(v), pos); // recurs�o sobre o filho da direita

	}

// Retorna uma cole��o iter�vel (inorder) contendo os nodos da �rvore.

	public Iterable<Position<E>> positionsInorder() {

		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0)
			inorderPositions(root(), positions); // atribui as posi��es usando caminhamento prefixado

		return positions;

	}

// Retorna uma cole��o iter�vel contendo os nodos da �rvore.

	public Iterable<Position<E>> positions() {

		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0)
			preorderPositions(root(), positions); // atribui as posi��es usando caminhamento prefixado

		return positions;

	}

// Retorna um iterador sobre os elementos armazenados nos nodos

	public Iterator<E> iterator() {

		Iterable<Position<E>> positions = positions();

		PositionList<E> elements = new NodePositionList<E>();

		for (Position<E> pos : positions)
			elements.addLast(pos.element());

		return elements.iterator(); // Um iterador sobre os elementos

	}

// Substitui o elemento armazenado no nodo.

	public E replace(Position<E> v, E o) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		E temp = v.element();

		vv.setElement(o);

		return temp;

	}

// Método de acesso adicional

// Retorna o irm�o de um nodo

	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<E> vv = checkPosition(v);

		BTPosition<E> parentPos = vv.getParent();

		if (parentPos != null) {

			BTPosition<E> sibPos;

			BTPosition<E> leftPos = parentPos.getLeft();

			if (leftPos == vv)
				sibPos = parentPos.getRight();

			else
				sibPos = parentPos.getLeft();

			if (sibPos != null)
				return sibPos;

		}

		throw new BoundaryViolationException("No sibling");

	}

// M�todos de acesso adicionais

// Insere a raiz em uma �rvore vazia

	public Position<E> addRoot(E e) throws NonEmptyTreeException {

		if (!isEmpty())
			throw new NonEmptyTreeException("Tree already has a root");

		size = 1;

		root = createNode(e, null, null, null);

		return root;

	}

// Insere o filho da esquerda em um nodo.

	public Position<E> insertLeft(Position<E> v, E e) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos != null)
			throw new InvalidPositionException("Node already has a left child");

		BTPosition<E> ww = createNode(e, vv, null, null);

		vv.setLeft(ww);

		size++;

		return ww;

	}

// Remove um nodo com zero ou um filho.

	public E remove(Position<E> v) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		BTPosition<E> leftPos = vv.getLeft();

		BTPosition<E> rightPos = vv.getRight();

		if (leftPos != null && rightPos != null)
			throw new InvalidPositionException("Não é possível remover um ramo com filhos");

		BTPosition<E> ww; // o �nico filho de v, se houver

		if (leftPos != null)
			ww = leftPos;

		else if (rightPos != null)
			ww = rightPos;

		else // v � folha

			ww = null;

		if (vv == root) { // v � a raiz

			if (ww != null)
				ww.setParent(null);

			root = ww;

		} else { // v n�o � a raiz

			BTPosition<E> uu = vv.getParent();

			if (vv == uu.getLeft())
				uu.setLeft(ww);

			else
				uu.setRight(ww);

			if (ww != null)
				ww.setParent(uu);

		}

		size--;

		return v.element();

	}

// Conecta duas �rvores para serem sub�rvores de um nodo externo.

	public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		if (isInternal(v))
			throw new InvalidPositionException("Cannot attach from internal node");

		if (!T1.isEmpty()) {

			BTPosition<E> r1 = checkPosition(T1.root());

			vv.setLeft(r1);

			r1.setParent(vv); // T1 deve ser invalidada

		}

		if (!T2.isEmpty()) {

			BTPosition<E> r2 = checkPosition(T2.root());

			vv.setRight(r2);

			r2.setParent(vv); // T2 deve ser invalidada

		}

	}

// Se v � um nodo de �rvore bin�ria, converte para BTPosition, caso contr�rio lan�a exce��o

	protected BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException {

		if (v == null || !(v instanceof BTPosition))
			throw new InvalidPositionException("The position is invalid");

		return (BTPosition<E>) v;

	}

// Cria um novo nodo de �rvore bin�ria

	protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {

		return new BTNode<E>(element, parent, left, right);

	}

// Cria uma lista que armazena os nodos da sub�rvore de um nodo ordenados de acordo com o

// caminhamento prefixado da sub�rvore.

	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {

		pos.addLast(v);

		if (hasLeft(v))
			preorderPositions(left(v), pos); // recurs�o sobre o filho da esquerda

		if (hasRight(v))
			preorderPositions(right(v), pos); // recurs�o sobre o filho da direita

	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<E> vv = checkPosition(v);

		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos == null)
			throw new BoundaryViolationException("No right child");

		return rightPos;

	}

	public boolean hasRight(Position<E> v) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		return (vv.getRight() != null);

	}

	public static <E> String toString(LinkedBinaryTree<E> T) {

		String s = "";

		// for (E i : T) { s += ", " + i; }

		// OU

		for (Iterator<E> it = T.iterator(); it.hasNext(); ) {

			s += ", " + it.next().toString();

		}

		s = (s.length() == 0 ? s : s.substring(2));

		return "[" + s + "]";

	}

	public String binaryPostOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {

			f += binaryPostOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPostOrder(sub, sub.right(v));
		}

		f += T.checkPosition(v).element();
		return f;

	}
	public LinkedBinaryTree<E> binaryPostOrderToInterator(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {
			binaryPostOrderToInterator(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			binaryPostOrderToInterator(sub, sub.right(v));
		}
		return sub;

	}

	public String binaryPreOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		f = T.checkPosition(v).element().toString();
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {
			f += binaryPreOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPreOrder(sub, sub.right(v));
		}

		return f;

	}

	public Double evaluateExpression(LinkedBinaryTree<E> T, Position<E> v) {
		Double f = 0.0;
		if (T.isInternal(v)) {
			Double x = evaluateExpression(T, T.left(v));
			Double y = evaluateExpression(T, T.right(v));
			switch (T.checkPosition(v).element().toString()) {
				case "+":
					return f = x + y;

				case "-":
					return f = x - y;

				case "/":
					return f = x / y;

				case "*":
					return f = x * y;
			}

		}
		return f = Double.parseDouble(T.checkPosition(v).element().toString());
	}

	public String inorder(LinkedBinaryTree<E> T, Position<E> v, String divisor) {
		String f = "";
		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v), divisor);
		}

		f += T.checkPosition(v).element().toString() + divisor;
		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v), divisor);
		}

		return f;

	}

	public Position<E> insertRight(Position<E> v, E e) throws InvalidPositionException {

		BTPosition<E> vv = checkPosition(v);

		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos != null)
			throw new InvalidPositionException("Node already has a left child");

		BTPosition<E> ww = createNode(e, vv, null, null);

		vv.setRight(ww);

		size++;

		return ww;

	}

	public String parentheticRepresentation(Tree<E> T, Position<E> v) {

		String s = v.element().toString(); // a��o principal de visita

		if (T.isInternal(v)) {

			Boolean firstTime = true;

			for (Position<E> w : T.children(v)) {

				if (firstTime) {
					// primeiro filho

					s += "(" + parentheticRepresentation(T, w);

					firstTime = false;

				} else {
					// filhos seguintes

					s += "," + parentheticRepresentation(T, w);

				}

				s += ")"; // fecha par�nteses

			}

		}

		return s;

	}

	public String eulerTour(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		f += T.checkPosition(v).element();

		if (T.hasLeft(v)) {
			f += eulerTour(T, T.left(v));
		}
		f += T.checkPosition(v).element();
		if (T.hasRight(v)) {
			f += eulerTour(T, T.right(v));
		}
		f += T.checkPosition(v).element();
		return f;

	}

	public String printExpression(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		if (T.isInternal(v)) {
			f += "(";
		}
		if (T.hasLeft(v)) {
			f += printExpression(T, T.left(v));
		}
		if (T.isInternal(v)) {
			f += (T.checkPosition(v).element());
		} else {
			f += (T.checkPosition(v).element());
		}
		if (T.hasRight(v)) {
			f += printExpression(T, T.right(v));
		}
		if (T.isInternal(v)) {
			f += ")";
		}

		return f;
	}


	public String inorder(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v));
		}
		f += T.checkPosition(v).element().toString();
		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v));
		}

		return f;

	}

	public int depth(LinkedBinaryTree<E> T, Position<E> v) {
		/**
		 * Se v for raiz, profundidade = 0 Se nao, chama depth com o filho do nodo
		 * recursivamente Soma +1 a profundiade maxima dos filhos
		 */
		if (T.isRoot(v)) {
			return 0;
		}
		return 1 + depth(T, T.parent(v));
	}


	public int height1(LinkedBinaryTree<E> T) {
		/**
		 * Percorrer a lista T Verificar se o Position v da lista T um nodo externo se
		 * sim, o inteiro h recebe o maior entre o proprio h e o depth de v na lista T
		 */
		int h = 0;
		for (Position<E> v : T.positions()) {
			if (T.isExternal(v)) {
				h = Math.max(h, T.depth(T, v));
			}
		}
		return h;
	}


	public void add(int valor, Position<Integer> v, LinkedBinaryTree<Integer> arvore) {
		if (!arvore.checkPosition(v).equals(null)) {

			if (valor < arvore.checkPosition(v).element()) {
				if (arvore.hasLeft(v)) {
					add(valor, arvore.left(v), arvore);
				} else {
					arvore.insertLeft(v, valor);
				}

			} else if (valor >= arvore.checkPosition(v).element()) {
				if (arvore.hasRight(v)) {
					add(valor, arvore.right(v), arvore);
				} else {
					arvore.insertRight(v, valor);
				}
			}
		} else if (arvore.checkPosition(v).equals(null)) {
			arvore.checkPosition(v).setElement(valor);
		}
	}

	public Position<Integer> find(int valor, Position<Integer> v, LinkedBinaryTree<Integer> arvore) {
		Position<Integer> finalp = null;

		if (valor == arvore.checkPosition(v).element()) {
			finalp=v;
		}
		else if (valor < arvore.checkPosition(v).element()) {
			finalp=find(valor, arvore.left(v), arvore);
		}

		else if (valor >= arvore.checkPosition(v).element()) {
			if (arvore.hasRight(v)) {
			finalp=find(valor, arvore.right(v), arvore);
			}

		}
		return finalp;
	}



}
