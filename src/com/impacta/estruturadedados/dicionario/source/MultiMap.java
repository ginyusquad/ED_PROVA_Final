package com.impacta.estruturadedados.dicionario.source;

import java.util.Map;

public interface MultiMap<K, V> {
	
	public int size();

	public boolean isEmpty();

	public Map.Entry<K,V> get(K k) throws IllegalArgumentException;

	public Iterable<Map.Entry<K,V>> getAll(K k) throws IllegalArgumentException;

	public Map.Entry<K,V> put(K k, V v) throws IllegalArgumentException;

	public Map.Entry<K,V> remove(Map.Entry<K,V> e) throws IllegalArgumentException;

	public Iterable<Map.Entry<K,V>> entrySet();

}
