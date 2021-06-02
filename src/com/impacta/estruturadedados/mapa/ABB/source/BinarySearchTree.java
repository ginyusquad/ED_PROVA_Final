package com.impacta.estruturadedados.mapa.ABB.source;

import java.util.Comparator;

import com.impacta.estruturadedados.arvore.binaria.sources.LinkedBinaryTree;
import com.impacta.estruturadedados.arvore.binaria.sources.interfaces.BTPosition;
import com.impacta.estruturadedados.exception.InvalidEntryException;
import com.impacta.estruturadedados.exception.InvalidKeyException;
import com.impacta.estruturadedados.exception.InvalidPositionException;
import com.impacta.estruturadedados.mapa.source.Map;
import com.impacta.estruturadedados.utils.Entry;
import com.impacta.estruturadedados.utils.comparators.DefaultComparator;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.NodePositionList;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;

	
public class BinarySearchTree<K, V> extends LinkedBinaryTree<Entry<K, V>> implements Map<K, V> {
	

		protected Comparator<K> C;
		protected Position<Entry<K, V>> actionPos;
		protected int numEntries = 0;

		public BinarySearchTree() {

			C = new DefaultComparator<K>();
	
			addRoot(null);
		}
		
		public BinarySearchTree(Comparator<K> comp ) {

			C = comp;

			addRoot(null);
		}
		
		public BinarySearchTree(K chave, V valor) {
			addRoot(new BSTEntry<K, V>(chave, valor));
		}

		protected static class BSTEntry<K, V> implements Entry<K, V> {
			
			protected K key;
			protected V value;
			protected Position<Entry<K, V>> pos;
			
			BSTEntry() { }
			BSTEntry(K k, V v, Position<Entry<K, V>> p) { key = k; value = v; pos = p; }
	
			public BSTEntry(K chave, V valor) {
				this.key = chave;
				this.value = valor;
			}
			public K getKey() { return key; }
	
			public V getValue() { return value; }
	
			public Position<Entry<K, V>> position() { return pos; }
			
			public String toString() {
				return "("+key+":"+value+")";
			}
	
		}

		protected K key(Position<Entry<K, V>> position) { return position.element().getKey(); }

		protected V value(Position<Entry<K, V>> position) { return position.element().getValue(); }

		protected Entry<K, V> entry(Position<Entry<K, V>> position) { return position.element(); }

		protected V replaceEntry(Position<Entry<K, V>> pos, Entry<K, V> ent) {

			((BSTEntry<K, V>) ent).pos = pos;
	
			return replace(pos, ent).getValue();

		}

		protected void checkKey(K key) throws InvalidKeyException {

			if (key == null) 
	
			throw new InvalidKeyException("chave nula");
	
		}

		protected void checkEntry(Entry<K, V> ent) throws InvalidEntryException {

			if (ent == null || !(ent instanceof BSTEntry)) throw new InvalidEntryException("elemento inválido");

		}

		protected Entry<K, V> insertAtExternal(Position<Entry<K, V>> v, Entry<K, V> e) {

			expandExternal(v, null, null);
	
			replace(v, e);
	
			numEntries++;
	
			return e;
	
		}

		protected void removeExternal(Position<Entry<K, V>> v) {

			removeAboveExternal(v);
	
			numEntries--;

		}

		protected Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> pos) {

			if (isExternal(pos)) return pos;
	
			else {
	
				K curKey = key(pos);
		
				int comp = C.compare(key, curKey);
		
				if (comp < 0) return treeSearch(key, left(pos));
		
				else if (comp > 0) return treeSearch(key, right(pos));
		
				return pos;
			}

		}

		public int size() { return numEntries; }

		public boolean isEmpty() { return size() == 0; }

		public V get(K key) throws InvalidKeyException {

			checkKey(key);
	
			Position<Entry<K, V>> curPos = treeSearch(key, root());
	
			actionPos = curPos;
	
			if (isInternal(curPos)) return value(curPos);
	
			return null;

		}

		public V put(K k, V x) throws InvalidKeyException {

			checkKey(k);
	
			Position<Entry<K, V>> insPos = treeSearch(k, root());
			
			BSTEntry<K, V> e = new BSTEntry<K, V>(k, x, insPos);
			
			
			actionPos = insPos;
	
			if (isExternal(insPos)) {
	
				insertAtExternal(insPos, e);
		
				return null;
	
			}

			return replaceEntry(insPos, e);

		}

		public V remove(K k) throws InvalidEntryException {

			checkKey(k);
	
			Position<Entry<K, V>> remPos = treeSearch(k, root());

			if (isExternal(remPos)) return null;
	
			Entry<K, V> toReturn = entry(remPos);
	
			if (isExternal(left(remPos))) remPos = left(remPos);
	
			else if (isExternal(right(remPos))) remPos = right(remPos);
	
			else {

				Position<Entry<K, V>> swapPos = remPos;
		
				remPos = right(swapPos);
		
				do remPos = left(remPos); while (isInternal(remPos));
		
					replaceEntry(swapPos, (Entry<K, V>) parent(remPos).element());
	
			}
	
			actionPos = sibling(remPos);
	
			removeExternal(remPos);
	
			return toReturn.getValue();

		}

		public void expandExternal(Position<Entry<K, V>> v, Entry<K, V> l, Entry<K, V> r) throws InvalidPositionException {

			if (!isExternal(v)) throw new InvalidPositionException("Node is not external");
	
			insertLeft(v, l);
	
			insertRight(v, r);

		}

		public void removeAboveExternal(Position<Entry<K, V>> v) throws InvalidPositionException {

			if (!isExternal(v)) throw new InvalidPositionException("Node is not external");

			if (isRoot(v)) remove(v);
	
			else {
	
				Position<Entry<K, V>> u = parent(v);
		
				remove(v);
		
				remove(u);
	
			}

		}

		public Iterable<K> keySet() {
	
			PositionList<K> keys = new NodePositionList<K>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) keys.addLast(key(cur));
	
			return keys;

		}

		public Iterable<V> values() {

			PositionList<V> vals = new NodePositionList<V>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) vals.addLast(value(cur));
	
			return vals;

		}

		public Iterable<Entry<K, V>> entrySet() {

			PositionList<Entry<K, V>> entries = new NodePositionList<Entry<K, V>>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) entries.addLast(cur.element());
	
			return entries;

		}

		public String printExpression(Position<Entry<K, V>> v) {

			String s = "";
	
			if (isInternal(v)) s += "(";
	
			if (hasLeft(v)) s += printExpression(left(v));
	
			if (v.element()!=null) s += v.element().getKey().toString();
	
			if (hasRight(v)) s += printExpression(right(v));
	
			if (isInternal(v)) s += ")";
	
			return s;


		}
		
		protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {

			BTPosition<Entry<K, V>> a, b, c, t0, t1, t2, t3;

			Position<Entry<K, V>> y = parent(x); 

			Position<Entry<K, V>> z = parent(y); 

			BTPosition<Entry<K, V>> xx = (BTPosition<Entry<K, V>>) x;

			BTPosition<Entry<K, V>> yy = (BTPosition<Entry<K, V>>) y;

			BTPosition<Entry<K, V>> zz = (BTPosition<Entry<K, V>>) z;

			boolean xLeft = (x == left(y));

			boolean yLeft = (y == left(z));

			if (xLeft && yLeft) {

				a = xx;
				b = yy;
				c = zz;

				t0 = a.getLeft();
				t1 = a.getRight();
				t2 = b.getRight();
				t3 = c.getRight();

			} else if (!xLeft && yLeft) { 

				a = yy;
				b = xx;
				c = zz;
				t0 = a.getLeft();

				t1 = b.getLeft();
				t2 = b.getRight();
				t3 = c.getRight();

			} else if (xLeft && !yLeft) { 

				a = zz;
				b = xx;
				c = yy;

				t0 = a.getLeft();
				t1 = b.getLeft();
				t2 = b.getRight();
				t3 = c.getRight();

			} else { 

				a = zz;
				b = yy;
				c = xx;

				t0 = a.getLeft();
				t1 = b.getLeft();
				t2 = c.getLeft();
				t3 = c.getRight();

			}

			if (isRoot(z)) {

				root = b;

				b.setParent(null);

			} else {

				BTPosition<Entry<K, V>> zParent = (BTPosition<Entry<K, V>>) parent(z);

				if (z == left(zParent)) { 

					b.setParent(zParent);

					zParent.setLeft(b);

				} else { 

					b.setParent(zParent);

					zParent.setRight(b);

				}

			}


			b.setLeft(a);
			a.setParent(b);
			a.setLeft(t0);
			t0.setParent(a);
			a.setRight(t1);
			t1.setParent(a);

			b.setRight(c);
			c.setParent(b);
			c.setLeft(t2);
			t2.setParent(c);
			c.setRight(t3);
			t3.setParent(c);

			((BSTEntry<K, V>) a.element()).pos = a;

			((BSTEntry<K, V>) b.element()).pos = b;

			((BSTEntry<K, V>) c.element()).pos = c;

			return b;
		}
}