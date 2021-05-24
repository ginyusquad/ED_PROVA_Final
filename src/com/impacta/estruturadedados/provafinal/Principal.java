package com.impacta.estruturadedados.provafinal;

import static java.lang.System.out;
import java.util.Scanner;
import com.impacta.estruturadedados.provafinal.interfaces.*;
import com.impacta.estruturadedados.provafinal.pilha.MenuPilha;
import com.impacta.estruturadedados.provafinal.arranjo.MenuArranjo;


public class Principal implements IMenu{

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
		out.println("Este é o projeto final do grupo 6 - Ginyu");
		int opcao;
		do {
			out.println("Menu: ");
			out.println("\t1 - Lista Arranjo");
			out.println("\t2 - Pilha");
			out.println("\t3 - Fila");
			out.println("\t4 - Lista de Nodos");
			out.println("\t5 - TAD-Árvore Genérica");
			out.println("\t6 -  TAD-Árvore Binária");
			out.println("\t7 - TAD-Fila de Prioridade");
			out.println("\t8 - TAD-Mapa");
			out.println("\t9 - TAD-Dicionário");
			out.println("\t10 - TAD-Mapa Ordenado – ABB");
			out.println("\t11 - TAD-Mapa Ordenado – AVL");
			out.println("\t12 - TAD-Grafos\n");
			out.println("\t13 - Sair\n");
			
			out.print("Escolha uma dessas estruturas: ");

			opcao = entrada.nextInt();
			if(opcao <= 0 || opcao > 12) {
				do {
					out.print("Opção invalida!! \nDigite novamente: ");
					opcao = entrada.nextInt();
					
				}while(opcao <= 0 || opcao > 12);
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
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
				case 13:
					
					break;
			}
			// Inicia o Sub Menu correspondente
			subMenu.show();
			
		}while(opcao != 13);
		
	};
    // fechar o menu
    public void close(){
    	
    	out.println("Ate mais!! :)");
    	
    	try {
    		System.exit(0);
		}catch (Exception e) {
			return;
		}
	};

}
