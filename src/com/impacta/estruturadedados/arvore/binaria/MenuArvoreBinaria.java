package com.impacta.estruturadedados.arvore.binaria;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.arvore.binaria.sources.LinkedBinaryTree;
import com.impacta.estruturadedados.arvore.generica.sources.LinkedTree;

import com.impacta.estruturadedados.arvore.generica.sources.TreeNode;
import com.impacta.estruturadedados.arvore.generica.sources.TreePosition;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.NodePositionList;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;
import com.impacta.estruturadedados.utils.views.Arvore;

public class MenuArvoreBinaria implements ISubMenu{

	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Arvore Binaria";

    public MenuArvoreBinaria(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		
		out.println( getTitulo() + ":");
				
		LinkedBinaryTree<Integer> arvoreBinaryInteira = criarArvoreBinaryPadrao();
		// Montando pilha padrao
		Arvore<Integer> arvore =  new Arvore<Integer>(arvoreBinaryInteira);
		out.println(" Por Padrao será usado a árvore:");
		out.println(arvore.toString);
		
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar um elemento na Árvore Binaria");
	        out.println("     2. Alterar um elemento da Árvore Binaria");
	        out.println("     3. Remover um elemento da Árvore Binari");
	        out.println("     4. Mostrar a Árvore Binaria");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        opcao = entrada.nextInt();
			out.println();
			Position<Integer> selecionado;
	        Integer elemento;
	        
	        switch(opcao) {
	        	case 1:
	        		out.print("\tDigite o que será adicionado }>");
	        		elemento = entrada.nextInt();
	        		
	        		criarFilho( arvoreBinaryInteira, elemento);
	        		arvore =  new Arvore<Integer>(arvoreBinaryInteira);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 2:
	        		
	        		out.println("\tPara alterar a Árvore selecione um Ramo:");
	        		selecionado = (Position<Integer>) selecionarNaArvore(arvore);
	        		out.print("\tDigite o que será colocado no lugar }>");
	        		elemento = entrada.nextInt();
	        		arvoreBinaryInteira.replace(selecionado, elemento);

	        		arvore =  new Arvore<Integer>(arvoreBinaryInteira);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 3:
	        		out.println("\tPara remover na Árvore selecione um Ramo:");
	        		selecionado =  selecionarNaArvore(arvore);
	        		
	        		arvoreBinaryInteira.remove(selecionado);
	        		
	        		arvore =  new Arvore<Integer>(arvoreBinaryInteira);
	        		out.println("    Ramo removido:"+selecionado.element());
	        		out.println(arvore.toString + "\n");
	        		break;
	        	case 4:
	        		arvore =  new Arvore<Integer>(arvoreBinaryInteira);
	        		out.println(arvore.toString + "\n");
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
	        
        }while( opcao != 5);
		
		
		out.println( menuPrincipal.getTitulo() + ":");
		
	}
	
	private Position<Integer> selecionarNaArvore(Arvore<Integer> arvore) {
		
		out.println("\tÁrvore atual:");
		out.println(arvore.toString);
		out.print("    Selecione um ramo (1.1, 1.2 e etc) }>");
        String opcao = entrada.next();
        
		String elemento = arvore.map.get(opcao).toString();
		out.println("    Ramo selecionado: "+ opcao +" "+ elemento);
		
		return arvore.map.get(opcao);
	}
	
	private void criarFilho(LinkedBinaryTree<Integer> t, Integer n) {
		
		
		t.add(n, t.root(), t);
		
		

	}
	
	private LinkedBinaryTree<Integer> criarArvoreBinaryPadrao() {
		
		LinkedBinaryTree<Integer> T = new LinkedBinaryTree<Integer>();
		
		Position<Integer> raiz = T.addRoot(10);
		Position<Integer> leftRaiz = T.insertLeft(raiz, 5);
		Position<Integer> rightRaiz = T.insertRight(raiz, 15);
		
		T.insertLeft( leftRaiz, 1);
		T.insertRight( leftRaiz, 9);
		
		T.insertLeft( rightRaiz, 11);
		T.insertRight( rightRaiz, 19);
		
		return T;
	}

	@Override
	public void close() {
		this.menuPrincipal.show();
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
