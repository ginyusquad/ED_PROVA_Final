package com.impacta.estruturadedados.grafo.source;

//Este é um exemplo simples de implementação de grafo representado por lista
//de adjacências

import java.util.List;
import java.util.ArrayList;

public class Grafo<T>{
	
	 public List<Vertice<T>> vertices;
	 public List<Aresta<T>> arestas;
	
	 public Grafo() {
	     vertices = new ArrayList<Vertice<T>>();
	     arestas = new ArrayList<Aresta<T>>();
	 }
	 
	 public boolean isEmpty() {
		 return vertices.isEmpty();
	 }
	
	 public Vertice<T> addVertice(T nome) {
	     Vertice<T> v = new Vertice<T>(nome);
	     vertices.add(v);
	     return v;
	 }
	
	 public Aresta<T> addAresta(Vertice<T> origem, Vertice<T> destino) {
	     Aresta<T> e = new Aresta<T>(origem, destino);
	     origem.addAdj(e);
	     arestas.add(e);
	     return e;
	 }
	
	 public String toString() {
	     String r = "";
	     for (Vertice<T> u : vertices) {
	         r += u.nome + " -> ";
	         for (Aresta<T> e : u.aresta) {
	             Vertice<T> v = e.destino;
	             r += v.nome + ", ";
	         }
	         r += "\n";
	     }
	     return r;
	 }

}
