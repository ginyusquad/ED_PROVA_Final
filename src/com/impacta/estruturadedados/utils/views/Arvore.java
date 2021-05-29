package com.impacta.estruturadedados.utils.views;

import java.util.HashMap;

import com.impacta.estruturadedados.arvore.binaria.sources.LinkedBinaryTree;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tree.Tree;

public class Arvore<T> {
	
	public String toString;
	public Tree<T> arvore;  
	public HashMap<String, Position<T> > map;
	
	public Arvore() {}
	
	public Arvore(LinkedBinaryTree<T> raiz) {
		
		map = new HashMap<String, Position<T>>();
		
		map.put("1", raiz.root());
		
		toString = " 1 => "+build(raiz, raiz.root(), "1");
		arvore = raiz;
		
	}
	
	public Arvore(Tree<T> raiz) {
		
		map = new HashMap<String, Position<T>>();
		map.put("1", raiz.root());
		toString = " 1 "+build(raiz, raiz.root(), "1");
		arvore = raiz;
		
	}
	
	public String build( LinkedBinaryTree<T> raiz, Position<T> v, String prefix) {
		
		String s =  v.element().toString() +"\n";
		s += "   ";
		if (raiz.isInternal(v)) {
			Boolean firstTime = true;
			int contador = 1;
			for (Position<T> w : raiz.children(v)) {
				
				map.put(prefix.trim()+"."+ contador, w);
				
				if (firstTime) {
					s +=  prefix +"."+ contador + " => " + build(raiz, w,"  "+ prefix + "." + contador  );
					firstTime = false; 
				} else {
					s +=  prefix +"."+ contador + " => " + build(raiz, w, "  "+ prefix + "." + contador );
					
				}
				contador++;
			}
			s += "";
		}
		return s;
	}
	
	public String build( Tree<T> raiz, Position<T> v, String prefix) {
		
		String s =  v.element().toString() +"\n";
		s += "   ";
		if (raiz.isInternal(v)) {
			Boolean firstTime = true;
			int contador = 1;
			for (Position<T> w : raiz.children(v)) {
				
				map.put(prefix.trim()+"."+ contador, w);
				
				if (firstTime) {
					s +=  prefix +"."+ contador + " " + build(raiz, w,"  "+ prefix + "." + contador  );
					firstTime = false; 
				} else {
					s +=  prefix +"."+ contador + " " + build(raiz, w,"  "+ prefix + "." + contador );
					
				}
				contador++;
			}
			s += "";
		}
		return s;
	}
	/* 
	public Arvore(Tree<String> raiz) {
		
		toString = build(raiz, raiz.root(), "└──");

	}
	
	public String build( Tree<String> raiz, Position<String> v, String prefix) {
		
		String s =  v.element().toString() +"\n";
		s += "   ";
		if (raiz.isInternal(v)) {
			Boolean firstTime = true;
			int contador = 1;
			for (Position<String> w : raiz.children(v)) {
				if (firstTime) {
					s += ""+ prefix + contador + build(raiz, w,"   "+ prefix  );
					firstTime = false; 
				} else {
					s += ""+ prefix + contador + build(raiz, w,"   "+ prefix );
				}
				contador++;
			}
			s += "";
		}
		return s;
	}/**/
	
}
