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

	protected static class AVLNode<K, V> extends BTNode<Entry<K, V>> {

		protected int height;
	
		AVLNode() { super(); }
	
		@SuppressWarnings("unchecked")
		public AVLNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent, BTPosition<Entry<K, V>> left,
	
			BTPosition<Entry<K, V>> right) {
		
				super(element, parent, left, right);
			
				height = 0;
			
				if (left != null) height = Math.max(height, 1 + ((AVLNode<K, V>) left).getHeight());
			
				if (right != null) height = Math.max(height, 1 + ((AVLNode<K, V>) right).getHeight());
		
			}
		
			public void setHeight(int h) { height = h; }
		
			public int getHeight() { return height; }
	
		}
	
		protected BTPosition<Entry<K, V>> createNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent,
	
			BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
	
			return new AVLNode<K, V>(element, parent, left, right); // agora usa nodos AVL
	
		}
	
		@SuppressWarnings("unchecked")
		protected int height(Position<Entry<K, V>> p) { return ((AVLNode<K, V>) p).getHeight(); }
	
	
		@SuppressWarnings("unchecked")
		protected void setHeight(Position<Entry<K, V>> p) {
	
			((AVLNode<K, V>) p).setHeight(1 + Math.max(height(left(p)), height(right(p))));
	
		}
	
		protected boolean isBalanced(Position<Entry<K, V>> p) {
	
			int bf = height(left(p)) - height(right(p));
		
			return ((-1 <= bf) && (bf <= 1));
	
		}
	
		protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
	
			if (height(left(p)) > height(right(p))) return left(p);
		
			else if (height(left(p)) < height(right(p))) return right(p);
		
			if (isRoot(p)) return left(p); 
		
			if (p == left(parent(p))) return left(p); 
		
			else return right(p);
		
		
		}
		protected void rebalance(Position<Entry<K, V>> zPos) {
		
			if (isInternal(zPos)) setHeight(zPos);
		
			while (!isRoot(zPos)) {
		
				zPos = parent(zPos);
			
				setHeight(zPos);
			
				if (!isBalanced(zPos)) {
			
					Position<Entry<K, V>> xPos = tallerChild(tallerChild(zPos));
				
					zPos = restructure(xPos);
				
					
					setHeight(left(zPos));
				
					setHeight(right(zPos));
				
					setHeight(zPos);
		
				}

			}

	}

	
	public V put(K k, V v) throws InvalidKeyException {

		V toReturn = super.put(k, v);
	
		rebalance(actionPos);
	
		return toReturn;
	}

	public V remove(K k) throws InvalidKeyException {

		V toReturn = super.remove(k);

		if (toReturn != null)

			rebalance(actionPos);

		return toReturn;
	}

}
