package com.impacta.estruturadedados.utils.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.impacta.estruturadedados.dicionario.source.HashTableMultiMap;
import com.impacta.estruturadedados.fila.prioridades.source.SortedListPriorityQueue;
import com.impacta.estruturadedados.grafo.source.Aresta;
import com.impacta.estruturadedados.grafo.source.Grafo;
import com.impacta.estruturadedados.grafo.source.Vertice;
import com.impacta.estruturadedados.listaDeNodos.source.DNode;
import com.impacta.estruturadedados.listaDeNodos.source.Position;
import com.impacta.estruturadedados.listaDeNodos.source.PositionList;
import com.impacta.estruturadedados.mapa.ABB.source.BinarySearchTree;
import com.impacta.estruturadedados.mapa.source.HashTableMap;
import com.impacta.estruturadedados.utils.Entry;

public class Tabela{
	
	public String toString;
	public Position<Integer>[] listPosition;
	public HashMap<Integer,java.util.Map.Entry> mapDic;
	public HashMap<Integer,Vertice<String>> mapVertices;
	
	
	public Tabela(PositionList<Integer> listNodos) {
		
		String listTexto = "\t┌───────┬────────┐ \n"
				 		 + "\t│Posicao│Conteudo│ \n"
				 		 + "\t├───────┼────────┤ \n";
		int index = 0;
		Position<Integer>[] listPositions = new DNode[listNodos.size()];
		listPositions[index] = listNodos.first();
		Iterator<Integer> ite = listNodos.iterator();
		ite.next();
		listTexto +=   "\t│   "+(1+index)+"°\t│  "+listPositions[index].element().toString()+"\t │\n";
		
		do {
			
			Integer i = ite.next();
			
			Position<Integer> position = listNodos.next(listPositions[index]);
			listPositions[++index] = position;
					
			listTexto +=   "\t│   "+(1+index)+"°\t│  "+i+"\t │\n";
			
		}while(ite.hasNext());
		
		listTexto +=       "\t└───────┴────────┘ \n";
		
		if(listNodos.isEmpty()) {
			listTexto = "\t┌───────┬────────┐ \n"
			   		  + "\t│Posicao│Conteudo│ \n"
			 		  + "\t├───────┴────────┤ \n"
			 		  + "\t│   Esta Vazio!  │ \n"
			 		  + "\t└────────────────┘ \n";
		}
		
		this.toString = listTexto;
		this.listPosition = listPositions;
	}

	public Tabela(SortedListPriorityQueue<Integer, String> listBugs) {
		
		
		

		String listTexto = "\t┌────────┬──────────┬────────────────────────────────────────────────────────────────────┐ \n"
				 		 + "\t│ Posicao│Prioridade│  Conteudo                                                          │ \n"
				 		 + "\t├────────┼──────────┼────────────────────────────────────────────────────────────────────┤ \n";
		int index = 1;
		for(Entry<Integer, String> entry : listBugs.getEntries()) {
			listTexto += "\t│   "+ index +"°\t │";
			listTexto += "  "+entry.getKey()+"\t    │ ";
			String espaco = "";
			String value = entry.getValue();
			
			if(value.length() > 65)value = value.substring(0, 64)+"..";
			
			espaco = new String(new char[ (67 - value.length()) ]).replace("\0", " ");
			// Valida a aprte visual
    		
    		
			listTexto += value + espaco +"│\n";
			index++;
		}
		listTexto +=       "\t└────────┴──────────┴────────────────────────────────────────────────────────────────────┘ \n";
		
		if(listBugs.isEmpty()) {
			listTexto = "\t┌────────┬──────────┬────────────────────────────────────────────────────────────────────┐ \n"
			 		  + "\t│ Posicao│Prioridade│  Conteudo                                                          │ \n"
			 		  + "\t├────────┴──────────┴────────────────────────────────────────────────────────────────────┤ \n"
			 		  + "\t│                 Lista de Prioridades esta Vazia!                                       │ \n"
					  +	"\t└────────────────────────────────────────────────────────────────────────────────────────┘ \n";
		}
		this.toString = listTexto;
		//this.listPosition = listPositions;
		
		
	}

	public Tabela(HashTableMap<String, String> mapa) {
		String listTexto = "\t┌─────────┬─────────────────────┐ \n"
		 		 		 + "\t│ Chave   │ Valor               │ \n"
						 + "\t├─────────┼─────────────────────┤ \n";
		
		for(Entry<String, String> entry : mapa.entrySet()) {
			
			
			String espaco = "";
			String chave = entry.getKey();
			String value = entry.getValue();
			
			if(chave.length() > 7)chave = chave.substring(0, 5)+"..";
			espaco = new String(new char[ (7 - chave.length()) ]).replace("\0", " ");
			listTexto += "\t│ "+ chave + espaco +" │";
			
			
			if(value.length() > 20)value = value.substring(0, 17)+"..";
			espaco = new String(new char[ (20 - value.length()) ]).replace("\0", " ");
			// Valida a parte visual
    		 		
			listTexto += " "+value + espaco +"│\n";
		}
		listTexto +=       "\t└─────────┴─────────────────────┘ \n";
		if(mapa.isEmpty()) {
			listTexto = "\t┌─────────┬────────────────┐ \n"
	 		 		  + "\t│ Chave   │ Valor          │ \n"
					  + "\t├─────────┴────────────────┤ \n"
					  + "\t│  O Mapa esta Vazio!      │ \n"
					  + "\t└──────────────────────────┘ \n";
		}
		this.toString = listTexto;
		
	}

	public Tabela(BinarySearchTree<Integer, String> mapa) {
		String listTexto = "\t┌─────────┬─────────────────────┐ \n"
		 		 		 + "\t│ Chave   │ Valor               │ \n"
		 		 		 + "\t├─────────┼─────────────────────┤ \n";
		
		for(Entry<Integer, String> entry : mapa.entrySet()) {
			
			
			String espaco = "";
			String chave = entry.getKey()+"";
			String value = entry.getValue();
			
			if(chave.length() > 7)chave = chave.substring(0, 5)+"..";
			espaco = new String(new char[ (7 - chave.length()) ]).replace("\0", " ");
			listTexto += "\t│ "+ chave + espaco +" │";
			
			
			if(value.length() > 20)value = value.substring(0, 17)+"..";
			espaco = new String(new char[ (20 - value.length()) ]).replace("\0", " ");
			// Valida a parte visual
			 		
			listTexto += " "+value + espaco +"│\n";
		}
		listTexto +=       "\t└─────────┴─────────────────────┘ \n";
		if(mapa.isEmpty()) {
			listTexto = "\t┌─────────┬────────────────┐ \n"
			 		  + "\t│ Chave   │ Valor          │ \n"
					  + "\t├─────────┴────────────────┤ \n"
					  + "\t│  O Mapa esta Vazio!      │ \n"
					  + "\t└──────────────────────────┘ \n";
		}
		this.toString = listTexto;
	}

	public Tabela(HashTableMultiMap<String, String> dict) {
				
		
		String listTexto = "\t┌──────────────┬─────────────────────┐ \n"
		 		 		 + "\t│ Chave        │ Valores             │ \n"
		 		 		 + "\t├──────────────┼─────────────────────┤ \n";

		for(java.util.Map.Entry<String,String>  entry: dict.entrySet()) {
			
			
			String espaco = "";
			String chave = entry.getKey();
			
			
			if(chave.length() > 12)chave = chave.substring(0, 10)+"..";
			espaco = new String(new char[ (12 - chave.length()) ]).replace("\0", " ");
			listTexto += "\t│ "+ chave + espaco +" │";
			
			
			String value = entry.getValue();
			if(value.length() > 20)value = value.substring(0, 17)+"..";
			espaco = new String(new char[ (20 - value.length()) ]).replace("\0", " ");
			// Valida a parte visual
			 		
			listTexto += " "+value + espaco +"│\n";
		}
		listTexto +=       "\t└──────────────┴─────────────────────┘ \n";
		if(dict.isEmpty()) {
			listTexto = "\t┌─────────┬────────────────┐ \n"
			 		  + "\t│ Chave   │ Valores        │ \n"
					  + "\t├─────────┴────────────────┤ \n"
					  + "\t│ O Dicionario esta Vazio! │ \n"
					  + "\t└──────────────────────────┘ \n";
		}
		this.toString = listTexto;
				
		
	}

	public Tabela(HashTableMultiMap<String, String> dict, boolean isIndex) {
		
		mapDic = new HashMap<Integer, java.util.Map.Entry>();
		
		String listTexto = "\t┌────────┬──────────────┬─────────────────────┐ \n"
		 		 		 + "\t│Posicao │ Chave        │ Valores             │ \n"
		 		 		 + "\t├────────┼──────────────┼─────────────────────┤ \n";
		int index = 0;

		for(java.util.Map.Entry<String,String>  entry: dict.entrySet()) {
			
			
			String espaco = "";
			String chave = entry.getKey();
			String posicao = ""+ index;
			mapDic.put(index, entry);
			
			if((posicao+"").length() > 6)posicao = posicao.substring(0, 4)+"..";
			espaco = new String(new char[ (6 - posicao.length()) ]).replace("\0", " ");
			listTexto += "\t│ "+ posicao + espaco +" │";
			
			
			
			if(chave.length() > 12)chave = chave.substring(0, 10)+"..";
			espaco = new String(new char[ (12 - chave.length()) ]).replace("\0", " ");
			listTexto += " "+ chave + espaco +" │";
			
			
			String value = entry.getValue();
			if(value.length() > 20)value = value.substring(0, 17)+"..";
			espaco = new String(new char[ (20 - value.length()) ]).replace("\0", " ");
			// Valida a parte visual
			 		
			listTexto += " "+value + espaco +"│\n";
			index++;
		}
		listTexto +=       "\t└────────┴──────────────┴─────────────────────┘ \n";
		if(dict.isEmpty()) {
			listTexto = "\t┌─────────┬────────────────┐ \n"
			 		  + "\t│ Chave   │ Valores        │ \n"
					  + "\t├─────────┴────────────────┤ \n"
					  + "\t│ O Dicionario esta Vazio! │ \n"
					  + "\t└──────────────────────────┘ \n";
		}
		this.toString = listTexto;
		
	}

	public Tabela(Grafo<String> grafo) {
		mapVertices = new HashMap<Integer, Vertice<String>>();
		String listTexto = "\t┌────────┬──────────────────────────┬─────────────────────────────────┐ \n"
		 		 		 + "\t│Posicao │ Vertice                  │ Arestas                         │ \n"
		 		 		 + "\t├────────┼──────────────────────────┼─────────────────────────────────┤ \n";
		int index = 0;

		for(Vertice<String> vertic: grafo.vertices) {
			
			index++;
			
			String espaco = "";
			String chave = vertic.nome;
			String posicao = ""+ index;
			
			mapVertices.put(index, vertic);
			
			if((posicao+"").length() > 6)posicao = posicao.substring(0, 4)+"..";
			espaco = new String(new char[ (6 - posicao.length()) ]).replace("\0", " ");
			listTexto += "\t│ "+ posicao + espaco +" │";
			
			
			
			if(chave.length() > 25)chave = chave.substring(0, 23)+"..";
			espaco = new String(new char[ (25 - chave.length()) ]).replace("\0", " ");
			listTexto += " "+ chave + espaco +"│";
			
			if(vertic.aresta.size() == 1) {
				
				String value = vertic.aresta.get(0).destino.toString();

				if(value.length() > 32)value = value.substring(0, 30)+"..";
				espaco = new String(new char[ (32 - value.length()) ]).replace("\0", " ");
				// Valida a parte visual
				listTexto += " "+value + espaco +"│\n";
				
			}else if(vertic.aresta.isEmpty()){
				
				String value = "";
				if(value.length() > 32)value = value.substring(0, 20)+"..";
				espaco = new String(new char[ (32 - value.length()) ]).replace("\0", " ");
				// Valida a parte visual
				listTexto += " "+value + espaco +"│\n";
				
			} else {
				
				boolean isFirst = true;
				for(Aresta<String> aresta : vertic.aresta) {
					if(isFirst) {
						
						String value = aresta.destino.toString();
						if(value.length() > 32)value = value.substring(0, 20)+"..";
						espaco = new String(new char[ (32 - value.length()) ]).replace("\0", " ");
						// Valida a parte visual
						listTexto += " "+value + espaco +"│\n";
						isFirst = false;
					} else {
						
						listTexto +=  "\t│        │                          │";
						
						String value = aresta.destino.toString();
						if(value.length() > 32)value = value.substring(0, 30)+"..";
						espaco = new String(new char[ (32 - value.length()) ]).replace("\0", " ");
						//listTexto += " "+ chave + espaco +" │";
						// Valida a parte visual
						listTexto += " "+value + espaco +"│\n";
						
					}
				}
			}
			
			
		}
		
		listTexto +=       "\t└────────┴──────────────────────────┴─────────────────────────────────┘ \n";
		
		if(grafo.isEmpty()) {
			listTexto = "\t┌─────────┬────────────────┐ \n"
			 		  + "\t│ Vertice │ Arestas        │ \n"
					  + "\t├─────────┴────────────────┤ \n"
					  + "\t│ O Grafo esta Vazio!      │ \n"
					  + "\t└──────────────────────────┘ \n";
		}
		
		this.toString = listTexto;
	}

	public Tabela(List<Aresta<String>> arestas) {
		String listTexto = "\n\t┌───────┬────────────────────────────────────────┐ \n"
		 		 		   + "\t│Posicao│   Aresta                               │ \n"
		 		 		   + "\t├───────┼────────────────────────────────────────┤ \n";
		int index = 1;
		
		for(Aresta<String> aresta : arestas) {
			
			
			String conteudo = aresta.destino.toString();
			
			if(conteudo.length() > 37)conteudo = conteudo.substring(0, 32)+"..";
			conteudo += new String(new char[ (37 - conteudo.length()) ]).replace("\0", " ");
			
			listTexto +=   "\t│  "+(index)+"°\t│  "+ conteudo +" │\n";
			
			index++;
		}
		
		listTexto +=       "\t└───────┴────────────────────────────────────────┘ \n";
		
		if(arestas.isEmpty()) {
			listTexto = "\t┌───────┬────────┐ \n"
			   		  + "\t│Posicao│Vertice │ \n"
			 		  + "\t├───────┴────────┤ \n"
			 		  + "\t│   Esta Vazio!  │ \n"
			 		  + "\t└────────────────┘ \n";
		}
		
		this.toString = listTexto;

	}
}
