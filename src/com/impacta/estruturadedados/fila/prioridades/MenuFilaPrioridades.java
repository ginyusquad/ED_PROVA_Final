package com.impacta.estruturadedados.fila.prioridades;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.fila.prioridades.source.SortedListPriorityQueue;
import com.impacta.estruturadedados.fila.source.FilaNode;
import com.impacta.estruturadedados.fila.source.Queue;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuFilaPrioridades implements ISubMenu {
	
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Prioridades";
	
    public MenuFilaPrioridades(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");

		SortedListPriorityQueue<Integer, String> listBugs =  criaFilaPrioridades();
		Tabela tbl_bugs = new Tabela(listBugs);
		
		out.println("    Por padrão será usado a Fila de Prioridades:");
	    out.println("      " + tbl_bugs.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("     2. Remover elemento com menor Prioridade ");
	        out.println("     3. Ver o primeiro elemento da Fila ");
	        out.println("     4. Mostrar a Fila");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        String conteudo = "";
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na fila de Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		out.print("\tDigite a prioridade:");
	        		int chave = entrada.nextInt();
	        		out.println("\tDigite o conteudo:");
	        		
	        		entrada.nextLine();
	        		conteudo = entrada.nextLine();
	        		
	        			
	        		listBugs.insert(chave, conteudo);
	        		
	        		tbl_bugs = new Tabela(listBugs);
	        		
	        		out.println(tbl_bugs.toString);
	        		
	        		break;
	        	case 2:
	        		out.println("\tPara remover da fila de Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		
	        		String elementoRemovido = listBugs.removeMin().getValue().toString();
	        		out.println("\t O seguinte elemento foi removido :" + elementoRemovido);
	        		tbl_bugs = new Tabela(listBugs);
	        		break;
	        	case 3:
	        		out.println("\tNa fila Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		out.println("\tO elemento mais prioritario é o "+listBugs.min().getValue());
	        		break;
	        	case 4:
	        		out.println("\tFila de Prioridades:");
	        		out.println(tbl_bugs.toString + "\n");
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
	        
        }while( opcao != 5 );
		
		out.println( menuPrincipal.getTitulo() + ":");
	}

	private SortedListPriorityQueue<Integer, String> criaFilaPrioridades() {
		SortedListPriorityQueue<Integer, String> fila = new SortedListPriorityQueue<Integer, String>();
		
		fila.insert(3, "[Impeditivo] Login fora!");
		fila.insert(4, "[Front-End] Seleção de Produtos não se mantém");
		fila.insert(3, "[Back-End] Sistema indisponivel ao realizar compra");
		fila.insert(5, "[Front-End] Grafico Montado errado");
		
		return fila;
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
