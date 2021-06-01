package com.impacta.estruturadedados.utils.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.impacta.estruturadedados.dicionario.source.HashTableMultiMap;
import com.impacta.estruturadedados.fila.prioridades.source.SortedListPriorityQueue;
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
}
