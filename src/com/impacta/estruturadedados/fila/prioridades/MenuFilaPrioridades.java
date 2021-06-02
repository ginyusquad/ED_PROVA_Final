package com.impacta.estruturadedados.fila.prioridades;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.fila.prioridades.source.SortedListPriorityQueue;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.views.Tabela;
/*
 *	Grupo: 6.
 *	Turma: CC3BN.
 *	Github: https://github.com/ginyusquad/ED_PROVA_Final
 *	Integrantes:    Claudia Thifany dos Santos (RA: 1903247);
 *					Gilberto Ramos de Oliveira (RA: 1903991);
 *					Leandro Epifanio Silva Costa (RA: 1902516);
 *					Matheus Luz Galdino (RA: 1903502);
 *			        Rodrigo Monastero (RA: 1904247). 
 *
*/
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
		
		// Montando fila de prioridades padrao
		SortedListPriorityQueue<Integer, String> listBugs =  criaFilaPrioridades();
		Tabela tbl_bugs = new Tabela(listBugs);
		
		out.println("\n"
		  	  	  + "\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
		   	  	  + "\t│                                                   DESCRIÇÃO                                              │");
		out.println("\t├──────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│                                                                                                          │");
		out.println("\t│                                                                                                          │");
	    out.println("\t│    Uma Fila de prioridades é uma estrutura de dados que armazena elementos com base na prioridade        │");
        out.println("\t│   os elementos possuem uma chave que os define sua prioridade e a remoção                                │");
        out.println("\t│   ocorre conforme suas respectivas prioridades.                                                          │");
        out.println("\t│   Por exemplo em uma lista de bugs de uma loga virtual existem os seguintes itens:                       │");
        out.println("\t│                                                                                                          │");
        out.println("\t│     1  [Impeditivo] Login fora do ar                                                                     │");
        out.println("\t│     2  [Bug Grave na Jornada] Carrinho de compras apaga itens comprados                                  │");
        out.println("\t│     3  [Aparência] Página de informações sobre o produto, esta sumindo com o nome do produto             │");
        out.println("\t│                   quando o nome no mesmo supera 256 carateres                                            │");
        out.println("\t│                                                                                                          │");
        out.println("\t│    Nesse exemplo o programador devera se concentrar no elemento com maior priodade                       │");
        out.println("\t│   que é o [Impeditivo] Login fora do ar , pois sem o login não e possível, realizar pedidos ou verificar │");
        out.println("\t│   a situação de pedidos que já tinham sido feitos!                                                       │");
        out.println("\t│                                                                                                          │");
        out.println("\t│                   @Curiosidade: É possivel se contruir uma fila de prioridades usando uma Lista de Nodos │");
        out.println("\t│                                                                                              ou um Heap! │");
        out.println("\t│                                                                                                          │");
        out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
        
		out.println("    Por padrão será usado a Fila de Prioridades:");
	    out.println("      " + tbl_bugs.toString + "\n");
	    
	    
	    // Loop de operaçoes
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t insert(k, v): Insere o v na fila");
	        out.println("     2. Remover elemento com menor Prioridade ");
	        out.println("\t removeMin(): Retorna o elemento com menor chave");
	        out.println("     3. Ver o primeiro elemento da Fila ");
	        out.println("\t min(): Retorna o elemento com menor chave");
	        out.println("     4. Mostrar a Fila de Prioridade");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        String conteudo = "";
	        out.println();
	        switch(opcao) {
	        	case 1:// Adiciona
	        		out.println("\tPara adicionar na fila de Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		out.print("\tDigite a prioridade:");
	        		int chave = entrada.nextInt();
	        		out.println("\tDigite o conteudo:");
	        		
	        		entrada.nextLine();
	        		conteudo = entrada.nextLine();
	        		
	        			
	        		listBugs.insert(chave, conteudo);
	        		
	        		tbl_bugs = new Tabela(listBugs);
	        		
	        		out.println(tbl_bugs.toString + "\n");
	        		
	        		break;
	        	case 2: // Remove
	        		out.println("\tPara remover da fila de Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		
	        		String elementoRemovido = listBugs.removeMin().getValue().toString();
	        		out.println("\t O seguinte elemento foi removido :" + elementoRemovido);
	        		tbl_bugs = new Tabela(listBugs);
	        		break;
	        	case 3: // Exibe o elemento mais prioritario
	        		out.println("\tNa fila Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		out.println("\tO elemento mais prioritário é o "+listBugs.min().getValue());
	        		break;
	        	case 4: // Exibe a fila de prioridades
	        		out.println("\tFila de Prioridades:");
	        		out.println(tbl_bugs.toString);
	        		break;
	        	case 5:// Sai para o menu principal
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
		fila.insert(3, "[Back-End] Sistema indisponível ao realizar compra");
		fila.insert(5, "[Front-End] Gráfico Montado errado");
		
		return fila;
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
