package com.impacta.estruturadedados.listaDeNodos;

import static java.lang.System.out;

import java.util.Scanner;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.listaDeNodos.source.Position;
import com.impacta.estruturadedados.listaDeNodos.source.PositionList;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuPositionList implements ISubMenu {
	
	private IMenu menuPrincipal;
	private PositionList<Integer> listNodos;
	private Scanner entrada;
	
	private String titulo = "Menu Lista de Posicoes";
	
	public MenuPositionList(IMenu menuPrincipal,PositionList<Integer> listNodos) {
		this.menuPrincipal = menuPrincipal;
		this.listNodos = listNodos;
		this.entrada = new Scanner(System.in);
	}
	
	@Override
	public void show() {
		
		out.println( getTitulo() + ":");

		Tabela tbl_posicoes = new Tabela(listNodos);
		out.print(tbl_posicoes.toString);
		// Pegando qual elemento o usuario quer trocar!!!
		out.print("\tDigite a posição do elemento (1.."+(listNodos.size() )+") }>");
		int posicao = entrada.nextInt() - 1 ;
		out.println();
		while(posicao > listNodos.size() - 1  || posicao < 0) {
			out.print("\tNão Entendi!!!\n");
			out.print("\tDigite a posição do elemento novamente (1.."+(listNodos.size())+") }>");
			posicao = entrada.nextInt() - 1 ;
		}
		Position<Integer> selecionado = tbl_posicoes.listPosition[posicao];
		out.println("\tElemento Selecionado Salvo na memória!!!\n");
		int opcao = -1;
		 // Loop das operações
		do{
			 
			out.println("    Opções: ");
			out.println("     1. Adicionar um elemento Antes ou Depois do Selecionado");
			out.println("\t addBefore(x,y):Adiciona o elemento y antes do nodo x");
			out.println("\t addAfter(x,y): Adiciona o elemento y depois do nodo x");
			out.println("     2. Remover um elemento Antes ou Depois do Selecionado");
			out.println("\t remove(x): Remove o elemento x");
			out.println("     3. Selecionar outro elemento");
			out.println("     4. Mostrar Tabela de Posições");
			out.println("     5. Sair para o menu superior ");
			out.print("    Selecione uma opção (1.."+ (listNodos.size()) +") }>");
			opcao = entrada.nextInt();
			out.println();
			int sub_opcao = -1;
			switch(opcao) {
				case 1:// Sub Menu Adicionar Position
					do {
						out.println("     Opções: ");
						out.println("      1. Adicionar um elemento Antes do Selecionado");
						out.println("      2. Adicionar um elemento Depois do Selecionado");
						out.println("      3. Sair para menu superior");
						out.print("    Selecione uma opção (1..3) }>");
						sub_opcao = entrada.nextInt();
						int elemento = 0;
						switch(sub_opcao) {
							case 1://Adicionar
								out.print("\tDigite o inteiro a ser adicionado antes do Selecionado }>");
				        		elemento = entrada.nextInt();
				        		listNodos.addBefore(selecionado, elemento);
				        		out.println("\t"+listNodos.toString()+ "\n");
				        		tbl_posicoes = new Tabela(listNodos);
								break;
							case 2:
								out.print("\tDigite o inteiro a ser adicionado Depois do Selecionado }>");
				        		elemento = entrada.nextInt();
				        		listNodos.addAfter(selecionado, elemento);
				        		out.println("\t"+listNodos.toString()+ "\n");
				        		tbl_posicoes = new Tabela(listNodos);
								break;
							case 3:
								break;
							default:
								out.println("    Não entendi!!!\n");
						}
						
					}while(sub_opcao != 3  );
					
					break;
				case 2:// Sub Menu Remover Position
					sub_opcao = -1;
					do {
						out.println("     Opções: ");
						out.println("      1. Remover um elemento Antes do Selecionado");
						out.println("      2. Remover um elemento Depois do Selecionado");
						out.println("      3. Sair para menu superior");
						out.print("    Selecione uma opção (1..3) }>");
						sub_opcao = entrada.nextInt();
						out.println();
						
						switch(sub_opcao) {
							case 1://remover anterior
								Integer elementoRemovido = null;
								try{ elementoRemovido = listNodos.remove(listNodos.prev(selecionado)); }
								catch (Exception e) {
									out.print("\t Não foi possivel remover o item anterior!");
									break;
								}
				        		
				        		out.print("\t Elemento removido: "+elementoRemovido);
				        		out.print("\t"+listNodos.toString()+ "\n");
				        		tbl_posicoes = new Tabela(listNodos);
								break;
							case 2:// remover posterior
								
				        		Integer inteiro = null;
				        		try{ inteiro = listNodos.remove(listNodos.next(selecionado)); }
								catch (Exception e) {
									out.print("\t Não foi possivel remover o item posterior!");
									break;
								}
				        		
				        		out.print("\t Elemento removido: "+inteiro);
				        		out.print("\t"+listNodos.toString()+ "\n");
				        		tbl_posicoes = new Tabela(listNodos);
								break;
							case 3:
								break;
							default:
								out.println("    Não entendi!!!\n");
						}
						
					}while(sub_opcao != 3  );
					
					break;
				case 3:// Altera o um elemento
					out.print(tbl_posicoes.toString);
					// Pegando qual eleemnto o ussuario quer trocar!!!
					out.print("\tDigite a posição do elemento (1.."+(listNodos.size() - 1)+") }>");
					posicao = entrada.nextInt() - 1 ;
					while(posicao > listNodos.size() - 1 || posicao < 0) {
						out.print("\tNão Entendi!!!\n");
						out.print("\tDigite a posição do elemento novamente (1.."+(listNodos.size() - 1)+") }>");
						posicao = entrada.nextInt() - 1 ;
					}
					selecionado = tbl_posicoes.listPosition[posicao];
					out.print("\t Elemento Salvo na memória!!! }>");
					
					break;
				case 4:// Exibe a tablea de posicoes
					out.print(tbl_posicoes.toString);
					break;
				case 5:// Sai para o menu superior
					this.close();
					return;
				default:
					out.println("    Não entendi!!!\n");
			}
			
			
		 }while(opcao != 5);
		 
		out.println( menuPrincipal.getTitulo() + ":");
		
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
