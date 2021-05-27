package com.impacta.estruturadedados.listaDeNodos;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.Principal;
import com.impacta.estruturadedados.fila.source.FilaNode;
import com.impacta.estruturadedados.fila.source.Queue;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.listaDeNodos.source.NodePositionList;
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
		
		PositionList<Integer> listPosition = new NodePositionList<Integer>();
		// Montando pilha padrao
		listPosition.addFirst(1);
		listPosition.addFirst(2);
		listPosition.addFirst(3);
		listPosition.addFirst(4);
		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			
		}
		
		out.println("    Por padrão será usado a Lista de Nodos:");
	    out.println("      " + listPosition.toString() );
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento no começo da lista");
	        out.println("     2. Remover elemento no final da lista");
	        out.println("     3. Adicionar ou Remover um elemento a frente ou atras\n      de um outro elemento");
	        out.println("     4. Substituir uma elemento ");
	        out.println("     5. Mostrar a Lista");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na Lista "+ listPosition.toString());
	        		out.print("\tDigite o inteiro a ser adicionado:");
	        		int elemento = entrada.nextInt();
	        		listPosition.addFirst(elemento);
	        		out.println("\t  " + listPosition.toString() );
	        		break;
	        	case 2:
	        		out.println("\tPara a fila "+ listPosition.toString());
	        		Integer elementoRemovido = listPosition.last().element();
	        		listPosition.remove(listPosition.last());
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + listPosition.toString() );
	        		break;
	        	case 3:
	        		out.println("\tLista:");
	        		out.println("\t  " + listPosition.toString() );
	        		break;
	        	case 4:
	        		out.println("\tLista Substituir");
	        		break;
	        	case 5:
	        		out.println("\tLista:");
	        		out.println("\t  " + listPosition.toString() );
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
	        
        }while(opcao > 0 && opcao < 5);
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
