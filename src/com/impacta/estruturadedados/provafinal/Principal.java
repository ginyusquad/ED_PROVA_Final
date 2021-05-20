package com.impacta.estruturadedados.provafinal;

import java.util.Scanner;
import com.impacta.estruturadedados.provafinal.interfaces.*;

import com.impacta.estruturadedados.provafinal.arranjo.Arranjo;
import com.impacta.estruturadedados.provafinal.arranjo.MenuArranjo;

public class Principal implements IMenu{

	public static void main(String[] args) {
		Principal menuPrincipal = new Principal();
		// Inicia o menu principal
		menuPrincipal.show();
	}
	//Mostra e inicia o menu
    public void show(){
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Bem Vindo!!");
		System.out.println("Este é o projeto final do grupo Ginyu");
		System.out.println("Menu: ");
		System.out.println("\t1 - Lista Arranjo");
		System.out.println("\t2 - Pilha");
		System.out.println("\t3 - Fila");
		System.out.println("\t4 - Lista de Nodos");
		System.out.println("\t5 - TAD-Árvore Genérica");
		System.out.println("\t6 -  TAD-Árvore Binária");
		System.out.println("\t7 - TAD-Fila de Prioridade");
		System.out.println("\t8 - TAD-Mapa");
		System.out.println("\t9 - TAD-Dicionário");
		System.out.println("\t10 - TAD-Mapa Ordenado – ABB");
		System.out.println("\t11 - TAD-Mapa Ordenado – AVL");
		System.out.println("\t12 - TAD-Grafos\n");
		
		System.out.print("Escolha uma dessas opções: ");
		int opcao;
		opcao = entrada.nextInt();
		if(opcao <= 0 || opcao > 12) {
			do {
				System.out.print("Opção invalida!! \nDigite novamente: ");
				opcao = entrada.nextInt();
				
			}while(opcao <= 0 || opcao > 12);
		}
		
		ISubMenu subMenu =  null;
		switch(opcao) {
			case 1:
				subMenu = new MenuArranjo(this);
				break;
			case 2:
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
		}
		// Inicia o Sub Menu correspondente
		subMenu.show();
		
	};
    // fechar o menu
    public void close(){

	};

}
