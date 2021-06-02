package com.impacta.estruturadedados.grafo.source;

public class Aresta<T> {
	
    public Vertice<T> fonte;
    public Vertice<T> destino;

    public Aresta(Vertice<T> origem, Vertice<T> destino) {
        this.fonte = origem;
        this.destino = destino;
    }
}
