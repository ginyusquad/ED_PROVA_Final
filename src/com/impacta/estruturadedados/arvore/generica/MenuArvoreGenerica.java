package com.impacta.estruturadedados.arvore.generica;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.arvore.generica.sources.LinkedTree;
import com.impacta.estruturadedados.arvore.generica.sources.NodePositionList;
import com.impacta.estruturadedados.arvore.generica.sources.Position;
import com.impacta.estruturadedados.arvore.generica.sources.PositionList;
import com.impacta.estruturadedados.arvore.generica.sources.TreeNode;
import com.impacta.estruturadedados.arvore.generica.sources.TreePosition;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.Arvore;


public class MenuArvoreGenerica implements ISubMenu{

	
	private IMenu menuPrincipal;
	private Scanner entrada;

	private String titulo = "Arvore Generica";
	
    public MenuArvoreGenerica(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		

		
		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			
		}
		
		LinkedTree<String> sistemas_operacionais = criarArvorePadrao();
		// Montando pilha padrao
		Arvore arvore =  new Arvore(sistemas_operacionais);
		out.println("    Por padrão será usado a arvore:");
		out.println(arvore.toString);
	
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar um elemento na Arvore ");
	        out.println("     2. Alterar um elemento da Arvore");
	        out.println("     3. Remover um elemento da Arvore");
	        out.println("     4. Mostrar a Arvore");
	        out.println("     5. Mostrar Tamanho da Arvore");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..6) }>");
	        opcao = entrada.nextInt();
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na Arvore selecione um Ramo:");
	        		TreeNode<String> selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		out.print("\tDigite o que será adicionado }>");
	        		String elemento = entrada.next();
	        		
	        		criarFilho( selecionado, elemento);
	        		arvore =  new Arvore(sistemas_operacionais);
	        		
	        		out.println(arvore.toString );
	        		break;
	        	case 2:
	        		
	        		break;
	        	case 3:
	        		
	        		break;
	        	case 4:
	        		
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
	        
        }while( opcao != 6);
        
		
		
		out.println( menuPrincipal.getTitulo() + ":");
	}
	
	private Position<String> selecionarNaArvore(Arvore arvore) {
		
		out.println("\tArvore atual:");
		out.println(arvore.toString);
		out.print("    Selecione um ramo (1.1, 1.2 e etc) }>");
        String opcao = entrada.next();
        
		String elemento = arvore.map.get(opcao).element();
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
