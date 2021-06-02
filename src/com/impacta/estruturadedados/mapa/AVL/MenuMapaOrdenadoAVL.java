package com.impacta.estruturadedados.mapa.AVL;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.mapa.ABB.source.BinarySearchTree;
import com.impacta.estruturadedados.mapa.AVL.source.AVLTreeMap;
import com.impacta.estruturadedados.utils.Entry;
import com.impacta.estruturadedados.utils.views.Arvore;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuMapaOrdenadoAVL implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;

	private String titulo = "Menu Mapa Ordenado AVL";
	
    public MenuMapaOrdenadoAVL(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
	
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		AVLTreeMap<Integer, String> mapa =  criaArvoreBinariaPadra();
		
		out.println("\n# Descrição:");
		out.println("\t    Um mapa Ordenado AVL é uma estrutura de dados semelhante ao Mapa");
        out.println("\t   no entanto ele usa uma arvore binaria de busca para indexar como o");
        out.println("\t   mapa Ordenado ABB, mas utiliza mecanicas para manter a arvore balanceada\n");
        
		
		Tabela tbl_mapa = new Tabela(mapa);
		Arvore<Entry<Integer, String>> arvore = new Arvore<Entry<Integer,String>>(mapa);
		
		out.println("\t\t\t@Um mapa ordenado ABB utiliza uma arvore binaria de pesquisa!");
		out.println("    Por padrão será usado o Mapa:");
	    out.println("    Tabela:  \n" + tbl_mapa.toString + "\n");
	    out.println("    Arvore binaria:  \n" + arvore.toString + "\n");
	    out.print(mapa.printExpression(mapa.root())+"\n");
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t put(k, v): Insere um elemento no Mapa com chave k e valor v, restructure");
	        out.println("\t           em nosso AVL a posicao da insercao, tenta manter a arvore balanceada");
	        out.println("\t           usando o restructure(x) aonde a arvore e rebalanceada depois da operação");
	        out.println("     2. Remover elemento");
	        out.println("\t remove(k): Remove o elemento com a chave k do Mapa");
	        out.println("     3. Mostrar o Mapa");
	        out.println("     4. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..4) }>");
	        
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

	private AVLTreeMap<Integer, String> criaArvoreBinariaPadra() {
		AVLTreeMap<Integer, String> map = new AVLTreeMap<Integer, String>(); 
		
		map.put(10, "root");
		map.put(5, "Filha0");
		map.put(15, "Filha1");
		map.put(1, "Filha2");
		map.put(9, "Filha3");
		
		map.put(11, "Filha4");
		map.put(20, "Filha5");
		
		return map;
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
