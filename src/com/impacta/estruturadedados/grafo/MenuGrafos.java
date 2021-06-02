package com.impacta.estruturadedados.grafo;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.grafo.source.Aresta;
import com.impacta.estruturadedados.grafo.source.Grafo;
import com.impacta.estruturadedados.grafo.source.Vertice;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.mapa.source.HashTableMap;
import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuGrafos implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Grafos";
	
    public MenuGrafos(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		Grafo<String> grafo =  criarGrafoPadrao();
		
		Tabela tbl_grafo = new Tabela(grafo);
		
		out.println("    Por padrão será usado o Grafo:");
	    out.println("\n" + tbl_grafo.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar Vertice");
	        out.println("     2. Adicionar Aresta");
	        out.println("     3. Remover Vertice");
	        out.println("     4. Remover Aresta");
	        out.println("     5. Mostrar o Grafo");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        out.println();
	        String chave = "";
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.println("\tDigite o Vertice:");
	        		entrada.nextLine();
	        		chave = entrada.nextLine();
	        		
	        		grafo.addVertice(chave);
	        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\tVertice adicionado!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 2:
	        		out.println("\tPara adicionar no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.print("\t Selecione a posicao da origem }>");
	        		int origemIndex = entrada.nextInt();
	        		Vertice<String> verticOrigem  = null;
	        		
	        		try { verticOrigem =  tbl_grafo.mapVertices.get(origemIndex); }
	        		catch (Exception e) {
	        			out.println("\t Posicao não encontrada!!");
					}
	        		
	        		out.print("\t Selecione a posicao de destino }>");
	        		int destinoIndex = entrada.nextInt();
	        		Vertice<String> verticDestino = null;
	        		
	        		try { verticDestino =  tbl_grafo.mapVertices.get(destinoIndex);}
	        		catch (Exception e) {
	        			out.println("\t Posicao não encontrada!!");
					}
	        		grafo.addAresta(verticOrigem, verticDestino);
	        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\tAresta adicionado!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 3:
	        		out.println("\tPara Remover no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		int index = 0;
	        		out.print("\t Selecione a posicao Removida }>");
	        		index = entrada.nextInt();
	        		Vertice<String> verticeRemovido = grafo.vertices.get(index - 1);
	        		grafo.vertices.remove(verticeRemovido);
	        		
	        		for(Vertice<String> vert : grafo.vertices) {
	        			for(int i = 0; i < vert.aresta.size(); i++) {
	        				
	        				Aresta<String> arest =  vert.aresta.get(i);
	        				if(arest.destino.equals(verticeRemovido)) {
	        					vert.aresta.remove(i);
	        				}
	        				
	        				
	        			}
	        		}
	        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\t Vertice Removido!!!");
	        		out.println(tbl_grafo.toString);
	        		
	        		break;
	        	case 4:
	        		out.println("\tPara Remover no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.print("\t Selecione a posicao do vertice }>");
	        		int verticeIndex = entrada.nextInt();
	        		Vertice<String> vertic  = null;
	        		
	        		try { vertic =  tbl_grafo.mapVertices.get(verticeIndex); }
	        		catch (Exception e) {
	        			out.println("\t Posicao não encontrada!!");
					}
	        		Tabela arestas = new Tabela(vertic.aresta);
	        		
	        		out.print("\t" + arestas.toString);
	        		
	        		out.print("\t Digite a posicao da Aresta }>");
	        		int indexAresta = entrada.nextInt()-1;
	        		
	        		vertic.aresta.remove(indexAresta);
	        			        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\t Aresta Removida!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 5:
	        		out.println(tbl_grafo.toString);
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
	        
        }while( opcao != 6 );
		
		out.println( menuPrincipal.getTitulo() + ":");
	}

	private Grafo<String> criarGrafoPadrao() {
		
		Grafo<String> grafo = new Grafo<String>();
		
		Vertice<String> rudge = grafo.addVertice("Av. Rudge");
		Vertice<String> bosque = grafo.addVertice("R. do Bosque");
		Vertice<String> norma = grafo.addVertice("R. Norma Pieruccini Giannotti");
		
		grafo.addAresta(rudge, bosque);
		grafo.addAresta(rudge, norma);
		grafo.addAresta(bosque, rudge);
		grafo.addAresta(norma, bosque);
	
		
		return grafo;
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
