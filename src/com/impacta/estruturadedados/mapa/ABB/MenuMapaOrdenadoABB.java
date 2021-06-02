package com.impacta.estruturadedados.mapa.ABB;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.mapa.ABB.source.BinarySearchTree;
import com.impacta.estruturadedados.mapa.source.HashTableMap;
import com.impacta.estruturadedados.utils.Entry;
import com.impacta.estruturadedados.utils.views.Arvore;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuMapaOrdenadoABB implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Mapa Ordenado ABB";
	
    public MenuMapaOrdenadoABB(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
	
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		BinarySearchTree<Integer, String> mapa =  criaArvoreBinariaPadrao();
		
		Tabela tbl_mapa = new Tabela(mapa);
		Arvore<Entry<Integer, String>> arvore = new Arvore<Entry<Integer,String>>(mapa);
		
		out.println("\t\t\t@Um mapa ordenado ABB utiliza uma arvore binaria de pesquisa!");
		out.println("    Por padrão será usado o Mapa:");
	    out.println("    Tabela:  \n" + tbl_mapa.toString + "\n");
	    out.println("    Arvore binaria:  \n" + arvore.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("     2. Remover elemento");
	        out.println("     3. Mostrar o Mapa");
	        out.println("     4. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        int chave = 0;
	        String conteudo = "";
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar no Mapa:");
	        		
	        		out.println(tbl_mapa.toString);
	        		
	        		out.print("\tDigite a Chave }>");
	        		chave = entrada.nextInt();
	        		
	        		out.println("\tDigite o conteudo:");
	        		entrada.nextLine();
	        		conteudo = entrada.nextLine();
	        		
	        		mapa.put(chave, conteudo);
	        		
	        		tbl_mapa =  new Tabela(mapa);
	        		
	        		out.println(tbl_mapa.toString);
	        		
	        		break;
	        	case 2:
	        		if(mapa.isEmpty()) {
	        			out.println("\tNão é possivel remover de um Mapa Vazio!\n");
	        			break;
	        		}
	        		
	        		out.println("\tPara remover no Mapa:");
	        		out.println(tbl_mapa.toString);
	        		out.println("\tDigite a Chave do elemento que deseja remover:");
	        		chave = entrada.nextInt();
	        		
	        		mapa.remove(chave);
	        		
	        		out.println("\tElemento removido!!");
	        		
	        		tbl_mapa =  new Tabela(mapa);
	        			        		
	        		out.println(tbl_mapa.toString);
	        		
	        		break;
	        	case 3:
	        		
	        		out.println("\tMapa:");
	        		out.println(tbl_mapa.toString);
	        		        		
	        		
	        		break;
	        	case 4:
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
	        
        }while( opcao != 4 );
		
		out.println( menuPrincipal.getTitulo() + ":");
	}

	private BinarySearchTree<Integer, String> criaArvoreBinariaPadrao() {
		BinarySearchTree<Integer, String> tree = null;
		tree = new BinarySearchTree<Integer, String>();
		
		
		tree.put(1,"chamado/");
		tree.put(2,"chamado/ticket");
		tree.put(3,"chamado/atendimento");
		
		
		return tree;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitulo() {
		return menuPrincipal.getTitulo()+" > "+this.titulo ;
	};
    
}
