package com.impacta.estruturadedados.grafo.source;

import java.util.ArrayList;
import java.util.List;


public class Vertice<T> {
	
    public T nome;
    public List<Aresta<T>> aresta;

    public Vertice(T nome) {
        this.nome = nome;
        this.aresta = new ArrayList<Aresta<T>>();
    }
    
    

    public void addAdj(Aresta<T> e) {
        aresta.add(e);
    }
    public String toString() {
    	return this.nome.toString();
    }
}
