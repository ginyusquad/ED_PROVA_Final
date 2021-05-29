package com.impacta.estruturadedados.utils;

import java.util.HashMap;

import com.impacta.estruturadedados.arvore.generica.sources.Position;
import com.impacta.estruturadedados.arvore.generica.sources.Tree;

public class Arvore {
	public String toString;
	public Tree<String> arvore;  
	public HashMap<String, Position<String> > map;  
	
	public Arvore() {}
	
	public Arvore(Tree<String> raiz) {
		
		map = new HashMap<String, Position<String>>();
		toString = " 1 "+build(raiz, raiz.root(), "1");
		arvore = raiz;
		
	}
	
	
	public String build( Tree<String> raiz, Position<String> v, String prefix) {
		
		String s =  v.element().toString() +"\n";
		s += "   ";
		if (raiz.isInternal(v)) {
			Boolean firstTime = true;
			int contador = 1;
			for (Position<String> w : raiz.children(v)) {
				
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
