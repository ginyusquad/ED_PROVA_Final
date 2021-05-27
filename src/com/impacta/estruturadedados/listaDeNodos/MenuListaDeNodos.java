package com.impacta.estruturadedados.listaDeNodos;

import static java.lang.System.out;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.listaDeNodos.source.DNode;
import com.impacta.estruturadedados.listaDeNodos.source.NodePositionList;
import com.impacta.estruturadedados.listaDeNodos.source.Position;
import com.impacta.estruturadedados.listaDeNodos.source.PositionList;

public class MenuListaDeNodos implements ISubMenu{

	private IMenu menuPrincipal;
	private Scanner entrada;

    public MenuListaDeNodos(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

	@Override
	public void show() {
		out.println("  Menu Lista de Nodos:");
		
		PositionList<Integer> listNodos = new NodePositionList<Integer>();
		// Montando pilha padrao
		listNodos.addFirst(4);
		listNodos.addFirst(3);
		listNodos.addFirst(2);
		listNodos.addFirst(1);
		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			
		}
		
		out.println("    Por padrão será usado a Lista de Nodos:");
	    out.println("      " + listNodos.toString() );
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento no começo da lista");
	        out.println("     2. Remover elemento no final da lista");
	        out.println("     3. Adicionar ou Remover um elemento a frente ou atras\n        de um outro elemento");
	        out.println("     4. Substituir um elemento ");
	        out.println("     5. Mostrar a Lista");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na Lista "+ listNodos.toString());
	        		out.print("\tDigite o inteiro a ser adicionado no começo }>");
	        		int elemento = entrada.nextInt();
	        		listNodos.addFirst(elemento);
	        		out.println("\t  " + listNodos.toString() );
	        		break;
	        	case 2:
	        		out.println("\tPara a fila "+ listNodos.toString());
	        		Integer elementoRemovido = listNodos.last().element();
	        		listNodos.remove(listNodos.last());
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + listNodos.toString() );
	        		break;
	        	case 3:
	        		out.println("\tLista:");
	        		out.println("\t  " + listNodos.toString() );
	        		break;
	        	case 4:
	        		out.println("\t Tabela com as Posicoes:");
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
	        		out.print(listTexto);

	        		// Pegando qual eleemnto o ussuario quer trocar!!!
	        		out.print("\tDigite a posicao do elemento (1.."+index+") }>");
	        		int posicao = entrada.nextInt() - 1 ;
	        		while(posicao > listNodos.size() || posicao < 0) {
	        			out.print("\tNão Entendi!!!");
	        			out.print("\tDigite a posicao do elemento novamente (1.."+index+") }>");
	        			posicao = entrada.nextInt() - 1 ;
	        		}
	        		
	        		out.print("\tDigite o elemento que deseja colocar }>");
	        		int element = entrada.nextInt();
	        		
	        		listNodos.set(listPositions[posicao], element);
	        		
	        		out.println("\tElemento Substituido!!");
	        		out.println("\tLista:");
	        		out.println("\t  " + listNodos.toString() );
	        		break;
	        	case 5:
	        		out.println("\tLista:");
	        		out.println("\t  " + listNodos.toString() );
	        		break;
	        	case 6:
	        		close();
	        		return;
	        	default:
	        		out.println("    Não entendi!\n");
	        		continue;
	        }
	        try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				
			}
	        
        }while(opcao > 0 && opcao < 6);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {
		// TODO Auto-generated method stub
		
	}
    
}
