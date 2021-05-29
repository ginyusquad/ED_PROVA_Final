package com.impacta.estruturadedados.arvore.generica;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.arvore.generica.sources.LinkedTree;


import com.impacta.estruturadedados.arvore.generica.sources.TreeNode;
import com.impacta.estruturadedados.arvore.generica.sources.TreePosition;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.NodePositionList;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.Position;
import com.impacta.estruturadedados.utils.tab_lista_de_nodos.PositionList;
import com.impacta.estruturadedados.utils.views.Arvore;


public class MenuArvoreGenerica implements ISubMenu{

	
	private IMenu menuPrincipal;
	private Scanner entrada;

	private String titulo = "Árvore Genérica";
	
    public MenuArvoreGenerica(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
				
		
	
		LinkedTree<String> sistemas_operacionais = criarArvorePadrao();
		
		// Montando pilha padrao
		Arvore<String> arvore =  new Arvore<String>(sistemas_operacionais);
		out.println(" Por Padrçao será usado a árvore:");
		out.println(arvore.toString);
		
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar um elemento na Árvore ");
	        out.println("     2. Alterar um elemento da Árvore");
	        out.println("     3. Remover um elemento da Árvore");
	        out.println("     4. Mostrar a Árvore");
	        out.println("     5. Mostrar Altura da Árvore");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..6) }>");
	        opcao = entrada.nextInt();
			out.println();
	        TreeNode<String> selecionado;
	        String elemento;
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na Árvore selecione um Ramo:");
	        		selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		out.print("\tDigite o que será adicionado }>");
	        		elemento = entrada.next();
	        		
	        		criarFilho( selecionado, elemento);
	        		arvore =  new Arvore(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 2:
	        		
	        		out.println("\tPara alterar a Árvore selecione um Ramo:");
	        		selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		out.print("\tDigite o que será colocado no lugar }>");
	        		elemento = entrada.next();
	        		sistemas_operacionais.replace(selecionado, elemento);

	        		arvore =  new Arvore(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 3:
	        		out.println("\tPara remover na Árvore selecione um Ramo:");
	        		selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		
	        		if(sistemas_operacionais.isRoot(selecionado)) {
	        			
	        			out.println("    Não é possível remover a raiz Principal!!!\n");
	        			break;
	        		}
	        		
	        		TreePosition<String> pai = selecionado.getParent();
	        		PositionList<Position<String>> list = new NodePositionList<Position<String>>();
	        		
	        		for(Position<String> finho: pai.getChildren()) {
	        			if(!finho.equals(selecionado))list.addLast(finho);
	        		}
	        		
	        		pai.setChildren(list);
	        		
	        		arvore =  new Arvore<String>(sistemas_operacionais);
	        		out.println("    Ramo removido:"+selecionado.element());
	        		out.println(arvore.toString + "\n");
	        		break;
	        	case 4:
	        		arvore =  new Arvore<String>(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		break;
	        	case 5:
	        		out.println("\tAltura da Árvore: "+sistemas_operacionais.height1(sistemas_operacionais) + "\n");
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
	        
        }while( opcao != 6);
		
		out.println( menuPrincipal.getTitulo() + ":");
	}
	private Position<String> selecionarNaArvore(Arvore<String> arvore) {
		
		out.println("\tÁrvore atual:");
		out.println(arvore.toString);
		out.print("    Selecione um ramo (1.1, 1.2 e etc) }>");
        String opcao = entrada.next();
        
		String elemento = arvore.map.get(opcao).toString();
		out.println("    Ramo selecionado: "+ opcao +" "+ elemento);
		
		return arvore.map.get(opcao);
	}
	public LinkedTree<String> criarArvorePadrao() {
		
		LinkedTree<String> T = new LinkedTree<String>();
		TreeNode<String> raiz, windows, linux, unix, debian;
		
		T.addRoot("Sistemas Operacionais");
		raiz = (TreeNode<String>) T.root();
		raiz.setChildren(new NodePositionList<Position<String>>());
		
		windows = criarFilho(raiz, "Windows");
		linux = criarFilho(raiz, "Linux");
		unix = criarFilho(raiz, "Unix");
		
		criarFilho(windows, "Windows NT");
		criarFilho(windows, "Windows XP");
		criarFilho(windows, "Windows 7");
		
		criarFilho(linux, "Slackware");
		criarFilho(linux, "SUSE Linux");
		debian = criarFilho(linux, "Debian");
		criarFilho(debian, "Ubuntu");
		
		criarFilho(unix, "FreeBSD");
		criarFilho(unix, "OpenBSD");
		criarFilho(unix, "NetBSD");
		
		return T;
	}
	
	private TreeNode<String> criarFilho(TreeNode<String> p, String n) {
		
		PositionList<Position<String>> filho;
		TreeNode<String> aux;
		filho = p.getChildren();
		aux = new TreeNode<String>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<String>>());
		filho.addLast(aux);
		
		return aux;
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
