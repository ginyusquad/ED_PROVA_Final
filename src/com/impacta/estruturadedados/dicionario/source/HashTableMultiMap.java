package com.impacta.estruturadedados.dicionario.source;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HashTableMultiMap<K, V> {
	
	Map<K, LinkedList<Map.Entry<K, V>>> m; // mapa de chaves para listas de entradas

	int nSize; // tamanho deste MapDictionary

	// Construtor default que usa um HashMap

	public HashTableMultiMap() {

	m = new HashMap<K, LinkedList<Map.Entry<K, V>>>(); // mapa default

	nSize = 0;

	}

	// Retorna a quantidade de entradas no dicionário

	public int size() { return nSize; }

	// Retorna se o dicionário está vazio

	public boolean isEmpty() { return nSize == 0; }

	// Insere um item em um dicionário. Retorna uma entrada recentemente criada

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

	// Retorna uma entrada contendo uma dada chave ou null se não existe a entrada

	public Map.Entry<K, V> get(K key) throws IllegalArgumentException {

	LinkedList<Map.Entry<K, V>> ll;

	if (key == null) throw new IllegalArgumentException();

	if ((ll = m.get(key)) == null) return null; // nada aqui ainda

	return ll.peekFirst(); // o primeiro elemento é tão bom como qualquer um

	}

	// Retorna um iterador contendo todas as entradas contendo uma certa chave ou um iterador

	// vazio se a entrada não existir.

	public Iterable<Map.Entry<K, V>> getAll(K key) throws IllegalArgumentException {

	LinkedList<Map.Entry<K, V>> ll;

	if (key == null) throw new IllegalArgumentException();

	if ((ll = m.get(key)) == null) return null; // nada ainda

	return ll;

	}

	// Remove e retorna uma dada entrada do dicionário.

	public Map.Entry<K, V> remove(Map.Entry<K, V> e) throws IllegalArgumentException {

	LinkedList<Map.Entry<K, V>> ll;

	if (e == null) throw new IllegalArgumentException("Elemnento nao encontrado!!");

	K key = e.getKey();

	ll = m.get(key);

	if (ll == null) throw new IllegalArgumentException("Elemento nao encontrado!!"); // chave não está em m

	if (ll.remove(e)) {

	nSize--;

	if (ll.isEmpty()) m.remove(key); // neste caso, remove a lista vazia

	return e; // e estava em ll, então retorna a entrada removida

	} else throw new IllegalArgumentException(); // e não estava em ll

	}

	// Retorna um iterador contendo todas as entradas do dicionário.

	public Iterable<Map.Entry<K, V>> entrySet() {

	LinkedList<Map.Entry<K, V>> ll = new LinkedList<Map.Entry<K, V>>();

	// todas as entradas desta lista para ll

	for (LinkedList<Map.Entry<K, V>> sub : m.values()) ll.addAll(sub);

	return ll;

	}

}
