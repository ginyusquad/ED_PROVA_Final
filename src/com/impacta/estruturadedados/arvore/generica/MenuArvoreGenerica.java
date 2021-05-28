package com.impacta.estruturadedados.arvore.generica;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.arvore.generica.sources.LinkedTree;
import com.impacta.estruturadedados.arvore.generica.sources.NodePositionList;
import com.impacta.estruturadedados.arvore.generica.sources.Position;
import com.impacta.estruturadedados.arvore.generica.sources.PositionList;
import com.impacta.estruturadedados.arvore.generica.sources.Tree;
import com.impacta.estruturadedados.arvore.generica.sources.TreeNode;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.Arvore;
import com.impacta.estruturadedados.utils.Tabela;

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
		
		LinkedTree<String> sistemas_operacionais = criarArvorePadrao();
		// Montando pilha padrao
		Arvore arvore =  new Arvore(sistemas_operacionais);
		out.println(" Por Padrao será usado a arvore:");
		out.println(arvore.toString);
		
		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			
		}
		
		
		
		out.println( menuPrincipal.getTitulo() + ":");
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
