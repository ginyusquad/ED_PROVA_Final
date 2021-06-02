package com.impacta.estruturadedados.listaDeNodos;

import static java.lang.System.out;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

import com.impacta.estruturadedados.listaDeNodos.source.NodePositionList;

import com.impacta.estruturadedados.listaDeNodos.source.PositionList;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuListaDeNodos implements ISubMenu{

	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Lista Nodos";
	
    public MenuListaDeNodos(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

	@Override
	public void show() {
		out.println( getTitulo() + ":");

		PositionList<Integer> listNodos = new NodePositionList<Integer>();
		// Montando pilha padrao
		listNodos.addFirst(4);
		listNodos.addFirst(3);
		listNodos.addFirst(2);
		listNodos.addFirst(1);
		out.println("\n"      
		  	  	  + "\t┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
		   	  	  + "\t│                                                         DESCRICAO                                                      │");
		out.println("\t├────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│                                                                                                                        │");
		out.println("\t│      Uma lista de nodos é estrutura de dados que armazena um conjunto de nós, sendo                                    │");
        out.println("\t│     que cada um armazena um elemento e a referencia do nó anterior e posterior da sua posição atual,                   │");
        out.println("\t│     dentro dela e usado uma Abstração chamada de position que nos permite adcionar e remover elementos                 │");
        out.println("\t│     em relação a um elemento que já exista na lista, por exemplo:                                                      │");
        out.println("\t│       [\" Primeiro \", \"Segundo\", \"Terceiro\"]                                                                            │");
        out.println("\t│        .addBefore(\"Segundo\", \"Entre o Primeiro e o Segundo\")                                                           │");
        out.println("\t│        .addAfter(\"Segundo\", \"Entre o Segundo e o Terceiro\")                                                            │");
        out.println("\t│       [\" Primeiro \", \"Entre o Primeiro e o Segundo \", \"Segundo\", \"Entre o Segundo e o Terceiro\", \"Terceiro\"]           │");
        out.println("\t│      Fora isso e possivel usar o addFirst(x) para adicionar no começo da lista.                                        │");
        out.println("\t│     e addLast(x) para adicionar no começo da lista.                                                                    │");
        out.println("\t│                                                                                                                        │");
        out.println("\t└────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
		
        out.println("    Por padrão será usado a Lista de Nodos:");
	    out.println("      " + listNodos.toString() + "\n");
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento no começo da lista");
	        out.println("\t addFirst(x): Insere o elemento x no inicio da lista");
	        out.println("     2. Remover elemento no final da lista");
	        out.println("\t remove(x): Remove o node x e redireciona o node anterior e posterior para ");
	        out.println("\t           fechar a lista que se abriu quando foi retirado o elemento x");
	        out.println("     3. Adicionar ou Remover um elemento à frente ou atrás\n        de um outro elemento");
	        out.println("\t  + menu PositionList:");
	        out.println("\t   + addBefore(), addAfert()");
	        out.println("\t   + remove()");
	        out.println("     4. Substituir um elemento ");
	        out.println("\t set(x,y): Substitui x pelo elemento y na lista");
	        out.println("     5. Mostrar a Lista");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..6) }>");
	        
	        opcao = entrada.nextInt();
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na Lista "+ listNodos.toString());
	        		out.print("\tDigite o inteiro a ser adicionado }>");
	        		int elemento = entrada.nextInt();
	        		listNodos.addFirst(elemento);
	        		out.println("\t  " + listNodos.toString() + "\n");
	        		break;
	        	case 2:
	        		out.println("\tPara a fila "+ listNodos.toString());
	        		Integer elementoRemovido = listNodos.last().element();
	        		listNodos.remove(listNodos.last());
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + listNodos.toString() + "\n");
	        		break;
	        	case 3:
	        		
	        		out.println("\t As PositionLists permitem que você remova e adicione elementos");
	        		out.println("\tbaseados não em um índice específico mais pela posição de ");
	        	    out.println("\tum determinado elemento, escolhendo se vai adicionar ou remover ");
	        	    out.println("\tum elemento dela antes ou depois");
	        		out.println("\t  " + listNodos.toString() + "\n");
	        		ISubMenu subMenuListaPosition = new MenuPositionList(this, listNodos);
	        		
	        		subMenuListaPosition.show();
	        		
	        		break;
	        	case 4:
	        		out.println("\t Tabela com as Posições:");
	        		
	        		// Tabela que monta a lsita de posicoes na tela
	        		Tabela tbl_posicoes = new Tabela(listNodos);
	        		out.print(tbl_posicoes.toString);

	        		// Pegando qual eleemnto o ussuario quer trocar!!!
	        		out.print("\tDigite a posição do elemento (1.."+(listNodos.size() - 1)+") }>");
	        		int posicao = entrada.nextInt() - 1 ;
	        		while(posicao > listNodos.size() || posicao < 0) {
	        			out.print("\tNão Entendi!!!");
	        			out.print("\tDigite a posição do elemento novamente (1.."+(listNodos.size() - 1)+") }>");
	        			posicao = entrada.nextInt() - 1 ;
	        		}
	        		
	        		out.print("\tDigite o elemento que deseja colocar }>");
	        		int element = entrada.nextInt();
	        		
	        		listNodos.set(tbl_posicoes.listPosition[posicao], element);
	        		
	        		out.println("\tElemento Substituído!!");
	        		out.println("\tLista:");
	        		out.println("\t  " + listNodos.toString() + "\n");
	        		break;
	        	case 5:
	        		out.println("\tLista:");
	        		out.println("\t  " + listNodos.toString() + "\n");
	        		break;
	        	case 6:
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
	        
        }while(opcao > 0 && opcao < 6);
        
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
