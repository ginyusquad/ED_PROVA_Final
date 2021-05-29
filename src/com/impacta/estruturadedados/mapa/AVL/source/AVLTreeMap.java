package com.impacta.estruturadedados.mapa.AVL.source;


import java.util.Comparator;

import com.impacta.estruturadedados.arvore.binaria.sources.BTNode;
import com.impacta.estruturadedados.arvore.binaria.sources.interfaces.BTPosition;
import com.impacta.estruturadedados.exception.InvalidKeyException;
import com.impacta.estruturadedados.mapa.ABB.source.BinarySearchTree;
import com.impacta.estruturadedados.mapa.source.Map;
import com.impacta.estruturadedados.utils.Entry;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;



public class AVLTreeMap<K, V> extends BinarySearchTree<K, V> implements Map<K, V> {
	
	public AVLTreeMap(Comparator<K> c) { super(c); }

	public AVLTreeMap() { super(); }

	// Classe aninhada para os nodos da árvore AVL

	protected static class AVLNode<K, V> extends BTNode<Entry<K, V>> {

		protected int height; // campo altura para BTNode
	
		AVLNode() { super(); } // construtor padrão
	
		// Construtor preferido
	
		@SuppressWarnings("unchecked")
	
		AVLNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent, BTPosition<Entry<K, V>> left,
	
			BTPosition<Entry<K, V>> right) {
		
			super(element, parent, left, right);
		
			height = 0;
		
			if (left != null) height = Math.max(height, 1 + ((AVLNode<K, V>) left).getHeight());
		
			if (right != null) height = Math.max(height, 1 + ((AVLNode<K, V>) right).getHeight());
		
			}
		
			// assume que o pai irá revisar a altura se necessário
		
			public void setHeight(int h) { height = h; }
		
			public int getHeight() { return height; }
	
		}
	
		// Cria um novo nodo da árvore binária de busca (sobrescreve a versão herdada)
	
		protected BTPosition<Entry<K, V>> createNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent,
	
			BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
	
			return new AVLNode<K, V>(element, parent, left, right); // agora usa nodos AVL
	
		}
	
		// Retorna a altura de um nodo
	
		@SuppressWarnings("unchecked")
	
		protected int height(Position<Entry<K, V>> p) { return ((AVLNode<K, V>) p).getHeight(); }
	
		// Define a altura de um nodo interno
	
		@SuppressWarnings("unchecked")
	
		protected void setHeight(Position<Entry<K, V>> p) {
	
			((AVLNode<K, V>) p).setHeight(1 + Math.max(height(left(p)), height(right(p))));
	
		}
	
		// Retorna se um nodo tem o fator de balanceamento entre -1 e 1.
	
		protected boolean isBalanced(Position<Entry<K, V>> p) {
	
			int bf = height(left(p)) - height(right(p));
		
			return ((-1 <= bf) && (bf <= 1));
	
		}
	
		// Retorna um filho de p com a altura maior que o outro filho
	
		protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
	
			if (height(left(p)) > height(right(p))) return left(p);
		
			else if (height(left(p)) < height(right(p))) return right(p);
		
			// alturas dos filhos são iguais - retorna o filho conforme o tipo do seu pai
		
			if (isRoot(p)) return left(p); // Se p for raiz, retorna o filho esquerdo.
		
			if (p == left(parent(p))) return left(p); // se p é filho esquerdo , retorna filho esquerdo de p.
		
			else return right(p); // caso contrário, o filho direito.
		
			}
		
			// Chamado para inserir e remover. Percorre de zPos até a raiz.
		
			// Para cada nodo encontrado, recomputamos a sua altura e
		
			// executamos a reestruturação trinode se desbalanceado
		
			protected void rebalance(Position<Entry<K, V>> zPos) {
		
			if (isInternal(zPos)) setHeight(zPos);
		
			while (!isRoot(zPos)) { // percorre a árvore em direção à raizt
		
			zPos = parent(zPos);
		
			setHeight(zPos);
		
			if (!isBalanced(zPos)) {
		
			// realiza a reestruturação trinode no filho mais alto de zPos
		
			Position<Entry<K, V>> xPos = tallerChild(tallerChild(zPos));
		
			zPos = restructure(xPos); // reestrutura (a partir da classe pai
		
			// recomputa alturas
		
			setHeight(left(zPos));
		
			setHeight(right(zPos));
		
			setHeight(zPos);
	
		}

	}

	}

	// Sobrescreve os métodos do TAD Mapa

	// Insere um item no mapa e retorna numa nova entrada criada

	public V put(K k, V v) throws InvalidKeyException {

	V toReturn = super.put(k, v); // Chama nosso método createNode se k é novo

	rebalance(actionPos); // rebalanceia a partir do ponto de inserção

	return toReturn;

	}

	// Remove e returna uma entrada do mapa

	public V remove(K k) throws InvalidKeyException {

	V toReturn = super.remove(k);

	if (toReturn != null) // nós realmente removemos algo

	rebalance(actionPos); // rebalanceia a árvore

	return toReturn;

	}

}
