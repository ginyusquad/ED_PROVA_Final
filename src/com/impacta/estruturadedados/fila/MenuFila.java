package com.impacta.estruturadedados.fila;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.fila.source.FilaNode;
import com.impacta.estruturadedados.fila.source.Queue;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;


public class MenuFila implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Fila";
	
    public MenuFila(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

	@Override
	public void show() {
		
		out.println( getTitulo() + ":");
		
		Queue<Integer> fila = new FilaNode<Integer>();
		// Montando pilha padrao
		fila.enqueue(1);;
		fila.enqueue(2);
		fila.enqueue(3);
		fila.enqueue(4);

		
		out.println("    Por padrão será usado a Fila:");
	    out.println("      " + fila.toString() + "\n");
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t enqueue(): ");
	        out.println("     2. Remover elemento ");
	        out.println("\t dequeue(): ");
	        out.println("     3. Ver o primeiro elemento da fila ");
	        out.println("\t front(): ");
	        out.println("     4. Mostrar a Fila");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na fila "+ fila.toString());
	        		out.print("\tDigite o inteiro a ser adicionado:");
	        		int elemento = entrada.nextInt();
	        		fila.enqueue(elemento);
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 2:
	        		out.println("\tPara a fila "+ fila.toString());
	        		Integer elementoRemovido = fila.dequeue();
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 3:
	        		out.println("\tPara a fila "+ fila.toString());
	        		out.println("\tO elemento primeiro da fila é "+ fila.front().toString() + "\n");
	        		break;
	        	case 4:
	        		out.println("\tFila:");
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 5:
	        		close();
	        		return;
	        	default:
	        		out.println("    Não entendi!\n");
	        		continue;
	        }
	        try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				
			}
	        
        }while(opcao > 0 && opcao < 5);
        
        
		out.println( menuPrincipal.getTitulo() + ":");
	}

	@Override
	public void close() {
		out.println("    Saindo do menu "+ this.titulo);
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
