package com.impacta.estruturadedados.dicionario.source;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HashTableMultiMap<K, V> {
	
	Map<K, LinkedList<Map.Entry<K, V>>> m;
	int nSize;

	public HashTableMultiMap() {

		m = new HashMap<K, LinkedList<Map.Entry<K, V>>>();
	
		nSize = 0;

	}

	public int size() { return nSize; }

	public boolean isEmpty() { return nSize == 0; }

	public Map.Entry<K, V> put(K key, V value) throws IllegalArgumentException {

		LinkedList<Map.Entry<K, V>> ll;
	
		if (key == null) throw new IllegalArgumentException();
	
		if ((ll = m.get(key)) == null) { // ainda nada aqui
	
			ll = new LinkedList<Map.Entry<K, V>>();
			m.put(key, ll);
	
		}
	
		Map.Entry<K, V> e = new AbstractMap.SimpleEntry<K, V>(key, value);
	
		ll.add(e); // acrescenta uma nova entrada na lista desta chave
	
		nSize++;
	
		return e;

	}

	public Map.Entry<K, V> get(K key) throws IllegalArgumentException {

		LinkedList<Map.Entry<K, V>> ll;

		if (key == null) throw new IllegalArgumentException();

		if ((ll = m.get(key)) == null) return null; 

		return ll.peekFirst();
	}

	public Iterable<Map.Entry<K, V>> getAll(K key) throws IllegalArgumentException {

		LinkedList<Map.Entry<K, V>> ll;

		if (key == null) throw new IllegalArgumentException();

		if ((ll = m.get(key)) == null) return null;

		return ll;
	}

	public Map.Entry<K, V> remove(Map.Entry<K, V> e) throws IllegalArgumentException {

		LinkedList<Map.Entry<K, V>> ll;

		if (e == null) throw new IllegalArgumentException("Elemnento nao encontrado!!");

		K key = e.getKey();

		ll = m.get(key);

		if (ll == null) throw new IllegalArgumentException("Elemento nao encontrado!!");

		if (ll.remove(e)) {

			nSize--;

			if (ll.isEmpty()) m.remove(key);

			return e;

		} else throw new IllegalArgumentException();
	}

	public Iterable<Map.Entry<K, V>> entrySet() {

		LinkedList<Map.Entry<K, V>> ll = new LinkedList<Map.Entry<K, V>>();

	
		for (LinkedList<Map.Entry<K, V>> sub : m.values()) ll.addAll(sub);

		return ll;

	}

}
