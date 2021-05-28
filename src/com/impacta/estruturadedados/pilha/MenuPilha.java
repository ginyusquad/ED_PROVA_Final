package com.impacta.estruturadedados.pilha;

import static java.lang.System.out;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.pilha.source.NodeStack;
import com.impacta.estruturadedados.pilha.source.Stack;

public class MenuPilha implements ISubMenu {

	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Pilha";
	
    public MenuPilha(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		
		out.println( getTitulo() + ":");
		
		Stack<Integer> pilha = new NodeStack<Integer>();
		// Montando pilha padrao
		pilha.push(1);
		pilha.push(2);
		pilha.push(3);
		pilha.push(4);
		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			
		}
		
		out.println("    Por padrão será usado a pilha:");
	    out.println("      " + pilha.toString() );
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("     2. Remover elemento ");
	        out.println("     3. Ver o primeiro elemento da pilha ");
	        out.println("     4. Mostrar a Pilha");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na pilha "+ pilha.toString());
	        		out.print("\tDigite o inteiro a ser adicionado:");
	        		int elemento = entrada.nextInt();
	        		pilha.push(elemento);
	        		out.println("\t  " + pilha.toString() );
	        		break;
	        	case 2:
	        		out.println("\tPara a pilha "+ pilha.toString());
	        		Integer elementoRemovido = pilha.pop();
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + pilha.toString() );
	        		break;
	        	case 3:
	        		out.println("\tPara a pilha "+ pilha.toString());
	        		out.println("\tO elemento do topo da pilha é "+ pilha.top().toString());
	        		break;
	        	case 4:
	        		out.println("\tPilha:");
	        		out.println("\t  " + pilha.toString() );
	        		break;
	        	case 5:
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
        
		out.println( menuPrincipal.getTitulo() + ":");
	}

	@Override
	public void close() {
		menuPrincipal.show();
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	@Override
	public String getTitulo() {
		return menuPrincipal.getTitulo()+" > "+this.titulo ;
	};
	

}
