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
	

		protected Comparator<K> C; // comparador

		protected Position<Entry<K, V>> actionPos; // pai do nodo inserido ou removido

		protected int numEntries = 0; // número de elementos

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

		// Classe aninhada para as entradas conscientes de localização da árvore binária de pesquisa.

		protected static class BSTEntry<K, V> implements Entry<K, V> {
			
			protected K key;
			protected V value;
			protected Position<Entry<K, V>> pos;
			
			BSTEntry() { } /* construtor padrão */
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

		// Retorna a chave do elemento de um dado nodo da árvore.

		protected K key(Position<Entry<K, V>> position) { return position.element().getKey(); }

		// Retorna o valor do elemento de um dado nodo da árvore.

		protected V value(Position<Entry<K, V>> position) { return position.element().getValue(); }

		// Retorna o elemento de um dado nodo da árvore.

		protected Entry<K, V> entry(Position<Entry<K, V>> position) { return position.element(); }

		// Substitui um elemento por um novo elemento (e inicializa a localização do elementos)

		protected V replaceEntry(Position<Entry<K, V>> pos, Entry<K, V> ent) {

			((BSTEntry<K, V>) ent).pos = pos;
	
			return replace(pos, ent).getValue();

		}

		// Verifica se uma determinada chave é válida.

		protected void checkKey(K key) throws InvalidKeyException {

			if (key == null) // um teste simples
	
			throw new InvalidKeyException("chave nula");
	
		}

		// Verifica se um determinado elemento é válido.

		protected void checkEntry(Entry<K, V> ent) throws InvalidEntryException {

			if (ent == null || !(ent instanceof BSTEntry)) throw new InvalidEntryException("elemento inválido");

		}

		// Método auxiliar para inserir um elemento em um nodo externo

		protected Entry<K, V> insertAtExternal(Position<Entry<K, V>> v, Entry<K, V> e) {

			expandExternal(v, null, null);
	
			replace(v, e);
	
			numEntries++;
	
			return e;
	
		}
	
		// Método auxiliar para remover um nodo externo e seu pai

		protected void removeExternal(Position<Entry<K, V>> v) {

			removeAboveExternal(v);
	
			numEntries--;

		}

		// Método auxiliar usado para pesquisar, inserir e remover.

		protected Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> pos) {

			if (isExternal(pos)) return pos; // chave não encontrada; retorna o nodo externo
	
			else {
	
				K curKey = key(pos);
		
				int comp = C.compare(key, curKey);
		
				if (comp < 0) return treeSearch(key, left(pos)); // pesquisa na subárvore à esquerda
		
				else if (comp > 0) return treeSearch(key, right(pos)); // pesquisa na subárvore à direita
		
				return pos; // retorna o nodo interno onde a chave foi encontrada
		
			}

		}

		// métodos do TAD

		public int size() { return numEntries; }

		public boolean isEmpty() { return size() == 0; }

		public V get(K key) throws InvalidKeyException {

			checkKey(key); // pode lançar uma exceção InvalidKeyException
	
			Position<Entry<K, V>> curPos = treeSearch(key, root());
	
			actionPos = curPos; // nodo onde a pesquisa finalizou
	
			if (isInternal(curPos)) return value(curPos);
	
			return null;

		}

		public V put(K k, V x) throws InvalidKeyException {

			checkKey(k); // pode lançar uma exceção InvalidKeyException
	
			Position<Entry<K, V>> insPos = treeSearch(k, root());
			
			BSTEntry<K, V> e = new BSTEntry<K, V>(k, x, insPos);
			
			
			actionPos = insPos; // nodo onde a entrada está sendo inserida
	
			if (isExternal(insPos)) { // necessita de um nodo novo, a chave é nova
	
				insertAtExternal(insPos, e);
		
				return null;
	
			}

			return replaceEntry(insPos, e); // a chave já existe

		}

		public V remove(K k) throws InvalidEntryException {

			checkKey(k); // pode lançar uma InvalidKeyException
	
			Position<Entry<K, V>> remPos = treeSearch(k, root());

			if (isExternal(remPos)) return null; // chave não encontrada
	
			Entry<K, V> toReturn = entry(remPos); // entrada existente
	
			if (isExternal(left(remPos))) remPos = left(remPos); // caso fácil pela esquerda
	
			else if (isExternal(right(remPos))) remPos = right(remPos); // caso fácil pela direita
	
			else { // a entrada está em nodo com filho interno

				Position<Entry<K, V>> swapPos = remPos; // encontra o nodo para a entrada que está sendo movida
		
				remPos = right(swapPos);
		
				do remPos = left(remPos); while (isInternal(remPos));
		
					replaceEntry(swapPos, (Entry<K, V>) parent(remPos).element());
	
			}
	
			actionPos = sibling(remPos); // irmão da folha que está sendo removida
	
			removeExternal(remPos);
	
			return toReturn.getValue();

		}

		// Expande um nodo externo num nodo interno com dois nodos filhos externos

		public void expandExternal(Position<Entry<K, V>> v, Entry<K, V> l, Entry<K, V> r) throws InvalidPositionException {

			if (!isExternal(v)) throw new InvalidPositionException("Node is not external");
	
			insertLeft(v, l);
	
			insertRight(v, r);

		}

		// Remove um nodo externo v e troca seu pai com o irmão de v

		public void removeAboveExternal(Position<Entry<K, V>> v) throws InvalidPositionException {

			if (!isExternal(v)) throw new InvalidPositionException("Node is not external");

			if (isRoot(v)) remove(v);
	
			else {
	
				Position<Entry<K, V>> u = parent(v);
		
				remove(v);
		
				remove(u);
	
			}

		}

		// Retorna uma coleção iterable contendo todas as chaves da árvore

		public Iterable<K> keySet() {
	
			PositionList<K> keys = new NodePositionList<K>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) keys.addLast(key(cur));
	
			return keys;

		}

		// Retorna uma coleção iterable contendo todos os valores da árvore

		public Iterable<V> values() {

			PositionList<V> vals = new NodePositionList<V>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) vals.addLast(value(cur));
	
			return vals;

		}

		// Retorna uma coleção iterável com todas as entradas da árvore

		public Iterable<Entry<K, V>> entrySet() {

			PositionList<Entry<K, V>> entries = new NodePositionList<Entry<K, V>>();
	
			Iterable<Position<Entry<K, V>>> positer = positionsInorder();
	
			for (Position<Entry<K, V>> cur : positer) if (isInternal(cur)) entries.addLast(cur.element());
	
			return entries;

		}

		// Exibe a expressão parentizada da árvore

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

			Position<Entry<K, V>> y = parent(x); // assume que x tem um pai

			Position<Entry<K, V>> z = parent(y); // assume que y tem um pai

			BTPosition<Entry<K, V>> xx = (BTPosition<Entry<K, V>>) x;

			BTPosition<Entry<K, V>> yy = (BTPosition<Entry<K, V>>) y;

			BTPosition<Entry<K, V>> zz = (BTPosition<Entry<K, V>>) z;

			boolean xLeft = (x == left(y));

			boolean yLeft = (y == left(z));

			if (xLeft && yLeft) { // forma (b) de mapeamento

				a = xx;
				b = yy;
				c = zz;

				t0 = a.getLeft();
				t1 = a.getRight();
				t2 = b.getRight();
				t3 = c.getRight();

			} else if (!xLeft && yLeft) { // forma (d) de mapeamento

				a = yy;
				b = xx;
				c = zz;
				t0 = a.getLeft();

				t1 = b.getLeft();
				t2 = b.getRight();
				t3 = c.getRight();

			} else if (xLeft && !yLeft) { // forma (c) de mapeamento

				a = zz;
				b = xx;
				c = yy;

				t0 = a.getLeft();
				t1 = b.getLeft();
				t2 = b.getRight();
				t3 = c.getRight();

			} else { // // forma (a) de mapeamento

				a = zz;
				b = yy;
				c = xx;

				t0 = a.getLeft();
				t1 = b.getLeft();
				t2 = c.getLeft();
				t3 = c.getRight();

			}

			// Passo 2:

			// Substitua a subárvore enraizada em z por uma nova subárvore enraizada em b.

			if (isRoot(z)) {

				root = b;

				b.setParent(null);

			} else {

				BTPosition<Entry<K, V>> zParent = (BTPosition<Entry<K, V>>) parent(z);

				if (z == left(zParent)) { // z é filho esquerdo

					b.setParent(zParent);

					zParent.setLeft(b);

				} else { // z é filho direito

					b.setParent(zParent);

					zParent.setRight(b);

				}

			}

			// Passo 3:

			// Faça a o filho esquerdo de b e T0 e T1 as subárvores esquerda e direita de a,
			// respectivamente.

			b.setLeft(a);
			a.setParent(b);
			a.setLeft(t0);
			t0.setParent(a);
			a.setRight(t1);
			t1.setParent(a);

			// Passo 4:

			// Faça c o filho direito de b e T2 e T3 as subárvores esquerda e direita de c,
			// respectivamente.

			b.setRight(c);
			c.setParent(b);
			c.setLeft(t2);
			t2.setParent(c);
			c.setRight(t3);
			t3.setParent(c);

			// Redefine a entradas conscientes de localização

			((BSTEntry<K, V>) a.element()).pos = a;

			((BSTEntry<K, V>) b.element()).pos = b;

			((BSTEntry<K, V>) c.element()).pos = c;

			return b; // a nova raiz desta subárvore

		}
}


