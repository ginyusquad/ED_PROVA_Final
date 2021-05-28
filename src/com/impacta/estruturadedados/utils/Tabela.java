package com.impacta.estruturadedados.utils;

import java.util.Iterator;

import com.impacta.estruturadedados.listaDeNodos.source.DNode;
import com.impacta.estruturadedados.listaDeNodos.source.Position;
import com.impacta.estruturadedados.listaDeNodos.source.PositionList;

public class Tabela{
	
	public String toString;
	public Position<Integer>[] listPosition;
	
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
		
		this.toString = listTexto;
		this.listPosition = listPositions;
	}
}
