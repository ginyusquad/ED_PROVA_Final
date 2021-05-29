package com.impacta.estruturadedados;

import static java.lang.System.out;
import java.util.Scanner;


import com.impacta.estruturadedados.interfaces.*;
import com.impacta.estruturadedados.listaDeNodos.MenuListaDeNodos;
import com.impacta.estruturadedados.mapa.MenuMapa;
import com.impacta.estruturadedados.mapa.ABB.MenuMapaOrdenadoABB;
import com.impacta.estruturadedados.mapa.AVL.MenuMapaOrdenadoAVL;
import com.impacta.estruturadedados.pilha.MenuPilha;
import com.impacta.estruturadedados.arranjo.MenuArranjo;
import com.impacta.estruturadedados.arvore.binaria.MenuArvoreBinaria;
import com.impacta.estruturadedados.arvore.generica.MenuArvoreGenerica;
import com.impacta.estruturadedados.dicionario.MenuDicionario;
import com.impacta.estruturadedados.fila.MenuFila;
import com.impacta.estruturadedados.fila.prioridades.MenuFilaPrioridades;
import com.impacta.estruturadedados.grafo.MenuGrafos;


public class Principal implements IMenu{
	
	private String titulo = " Menu Principal";
	
	public static void main(String[] args) {
		Principal menuPrincipal = new Principal();

		try {

			// Inicia o menu principal
			menuPrincipal.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Mostra e inicia o menu
    public void show(){
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		out.println("Bem Vindo!!");
		out.println("Este é o projeto final do grupo 6 - Ginyu\n");
		
		out.println(getTitulo()+":");
		int opcao;
		do {
			out.println("\t1  - Lista Arranjo");
			out.println("\t2  - Pilha");
			out.println("\t3  - Fila");
			out.println("\t4  - Lista de Nodos");
			out.println("\t5  - TAD-Árvore Genérica");
			out.println("\t6  - TAD-Árvore Binária");
			out.println("\t7  - TAD-Fila de Prioridade");
			out.println("\t8  - TAD-Mapa");
			out.println("\t9  - TAD-Dicionário");
			out.println("\t10 - TAD-Mapa Ordenado – ABB");
			out.println("\t11 - TAD-Mapa Ordenado – AVL");
			out.println("\t12 - TAD-Grafos");
			out.println("\t13 - Sair\n");
			
			out.print("Escolha uma dessas opções(1..13) }>");

			opcao = entrada.nextInt();
			out.println();
			
			// Valida a entrada
			while(opcao <= 0 || opcao > 13){

				out.print("Opção invalida!! \nDigite novamente: ");
				opcao = entrada.nextInt();
				out.println();

			}
			

			ISubMenu subMenu =  null;

			switch(opcao) {
				case 1:
					subMenu = new MenuArranjo(this);
					break;
				case 2:
					subMenu = new MenuPilha(this);
					break;
				case 3:
					subMenu = new MenuFila(this);
					break;
				case 4:
					subMenu = new MenuListaDeNodos(this);
					break;
				case 5:
					subMenu = new MenuArvoreGenerica(this);
					break;
				case 6:
					subMenu = new MenuArvoreBinaria(this);
					break;
				case 7:
					subMenu = new MenuFilaPrioridades(this);
					break;
				case 8:
					subMenu = new MenuMapa(this);
					break;
				case 9:
					subMenu = new MenuDicionario(this); 
					break;
				case 10:
					subMenu = new MenuMapaOrdenadoABB(this);
					break;
				case 11:
					subMenu = new MenuMapaOrdenadoAVL(this);
					break;
				case 12:
					subMenu = new MenuGrafos(this);
					break;
				case 13:
					entrada.close();
					close();
					return;
				default:
					out.println("Opção invalida!! Reveja o Menu");
					continue;
			}
			// Inicia o Sub Menu correspondente
			subMenu.show();
			
		}while(opcao < 13 );
		
		entrada.close();
	};
    // fechar o menu
    public void close(){
    	out.println("Ate mais!! :)\n");
    	
    	try {
    		System.exit(0);
		}catch (Exception e) {
			return;
		}
	}

	@Override
	public String getTitulo() {
		return this.titulo ;
	};

}
