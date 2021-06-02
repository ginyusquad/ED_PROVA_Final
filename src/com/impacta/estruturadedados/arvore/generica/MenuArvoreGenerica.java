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
		
		out.println("\n"
    		  	  + "\t┌─────────────────────────────────────────────────────────────────────────────────────┐\n"
    		   	  + "\t│                                       DESCRIÇÃO                                     │");
		out.println("\t├─────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│    Uma árvore genérica é uma estrutura de dados, que armazena inúmeros elementos    │"); 
        out.println("\t│   montandos eles em nós, com hierarquias, sendo o pai a raiz                        │");
        out.println("\t│   os filhos os nós abaixo do pai e assim por diante hierarquicamente.               │");
        out.println("\t│   Exemplo:                                                                          │");
        out.println("\t│          Adam                                                                       │");
        out.println("\t│         /    \\                                                                     │");
        out.println("\t│       Abel   Cain                                                                   │");
        out.println("\t│                \\                                                                   │");
        out.println("\t│               Enoque                                                                │");
        out.println("\t│   Podendo indexar estruturas gigantescas como sistemas de arquivos.                 │");
        out.println("\t└─────────────────────────────────────────────────────────────────────────────────────┘\n");
		// Montando arvore padrao
		Arvore<String> arvore =  new Arvore<String>(sistemas_operacionais);
		out.println(" Por Padrão será usado a árvore:");
		out.println(arvore.toString);
		
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar um elemento na Árvore ");
	        out.println("\t addLast(x): Adiciona o nó x na última ponta da árvore ou ramo selecionado");
	        out.println("     2. Alterar um elemento da Árvore");
	        out.println("\t replace(x,conteudo): altera o conteudo do elemento selecionado");
	        out.println("     3. Remover um elemento da Árvore");
	        out.println("\t restruturar a árvore para manter a integridade, retirando o ramo e re arrumando a mesma");
	        out.println("     4. Mostrar a Árvore");
	        out.println("     5. Mostrar Altura da Árvore");
	        out.println("\t height1():");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..6) }>");
	        opcao = entrada.nextInt();
			out.println();
	        TreeNode<String> selecionado;
	        String elemento;
	        
	        switch(opcao) {
	        	case 1://Adiciona
	        		out.println("\tPara adicionar na Árvore selecione um Ramo:");
	        		selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		out.println("\tDigite o que será adicionado:");
	        		entrada.nextLine();
	        		elemento = entrada.nextLine();
	        		
	        		criarFilho( selecionado, elemento);
	        		arvore =  new Arvore<String>(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 2:// Altera um ramo
	        		
	        		out.println("\tPara alterar a Árvore selecione um Ramo:");
	        		selecionado = (TreeNode<String>) selecionarNaArvore(arvore);
	        		out.print("\tDigite o que será colocado no lugar }>");
	        		entrada.nextLine();
	        		elemento = entrada.nextLine();
	        		sistemas_operacionais.replace(selecionado, elemento);

	        		arvore =  new Arvore<String>(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		
	        		break;
	        	case 3:// Remove
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
	        	case 4:// Exibe 
	        		arvore =  new Arvore<String>(sistemas_operacionais);
	        		out.println(arvore.toString + "\n");
	        		break;
	        	case 5:// exibe a altura
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
		out.println("    Digite um ramo( Por Exemplo: 1.1, 1.2, 1.3 ");
		out.print("                                   1.1.2 e etc) }>");
        String opcao = entrada.next();
        String elemento = null;
        
        try { elemento = arvore.map.get(opcao).toString(); }
        catch (Exception e) {
        	out.println("    Não consegui identificar esse ramo!!  ");
        	return selecionarNaArvore(arvore);
		}
        
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
