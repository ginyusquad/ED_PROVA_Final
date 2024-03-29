package com.impacta.estruturadedados.grafo;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.grafo.source.Aresta;
import com.impacta.estruturadedados.grafo.source.Grafo;
import com.impacta.estruturadedados.grafo.source.Vertice;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.utils.views.Tabela;
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
		out.println("\n"      
		  	  	  + "\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
		   	  	  + "\t│                                                   DESCRIÇÃO                                              │");
		out.println("\t├──────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│                                                                                                          │");
	    out.println("\t│    Um Grafo é uma estrutura de dados que armazena elementos e suas relações                              │");
        out.println("\t│   os elementos são as Vértices e suas ligações são as arestas                                            │");
        out.println("\t│   Por exemplo esse mapa de um bairro :                                                                   │");
        out.println("\t│                                                                                                          │");
        out.println("\t│         Bairro: Jardim Silveira                                                                          │");
        out.println("\t│          (R. São José)                        (Av. Castelo Branco)                                       │");
        out.println("\t│                \\                               /                                                        │");
        out.println("\t│                 \\* Aresta                     / * Aresta                                                │");
        out.println("\t│                  \\                           /                                                          │");
        out.println("\t│                   \\                         /                   @Curiosidade: Existem bancos de dados   │");
        out.println("\t│                  (Av. Santa padroeira do brasil)                          especializados em só armazenar │");
        out.println("\t│                    /             |            \\                          grafos.                        │");
        out.println("\t│                   /* Aresta      |* Aresta     \\ * Aresta                                               │");
        out.println("\t│                  /               |              \\                                                       │");
        out.println("\t│                 /                |               \\                                                      │");
        out.println("\t│        (R. Pilheiros)       (R. Triade)       (Peco do conforto)                                         │");
        out.println("\t│               \\                   \\                /                                                   │");
        out.println("\t│                \\                   \\* Aresta      /* Aresta                                            │");
        out.println("\t│                 \\                   \\            /                                                     │");
        out.println("\t│                  \\                  (R. dos Santos)                                                     │");
        out.println("\t│                   \\* Aresta               /                                                             │");
        out.println("\t│                    \\                     /                                                              │");
        out.println("\t│                     \\                   /* Aresta                                                       │");
        out.println("\t│                      \\                 /                                                                │");
        out.println("\t│                     (Av. Brigadeiro Jordão)                                                              │");
        out.println("\t│                                                                                                          │");
        out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
		
		
		Tabela tbl_grafo = new Tabela(grafo);
		out.println("    Por padrão será usado o Grafo:");
	    out.println("\n" + tbl_grafo.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar Vértice");
	        out.println("\t insertVertex(x): Insere o vértice x e retorna o vértice ");
	        out.println("     2. Adicionar Aresta");
	        out.println("\t insertEdge(v, w, x): Cria uma aresta de v para w");
	        out.println("     3. Remover Vértice");
	        out.println("\t removeVertex(v): Remove o vértice v");
	        out.println("     4. Remover Aresta");
	        out.println("\t removeEdge(e): Remove a aresta e");
	        out.println("     5. Mostrar o Grafo");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        out.println();
	        String chave = "";
	        switch(opcao) {
	        	case 1:// Adicionar Vertice
	        		out.println("\tPara adicionar no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.println("\tDigite o Vértice:");
	        		entrada.nextLine();
	        		chave = entrada.nextLine();
	        		
	        		grafo.addVertice(chave);
	        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\tVértice adicionado!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 2:// Adicioanr Aresta
	        		out.println("\tPara adicionar no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.print("\t Selecione a posição da vértice de origem }>");
	        		int origemIndex = entrada.nextInt();
	        		Vertice<String> verticOrigem  = null;
	        		
	        		try { verticOrigem =  tbl_grafo.mapVertices.get(origemIndex); }
	        		catch (Exception e) {
	        			out.println("\t Posição não encontrada!!");
	        			break;
					}
	        		
	        		out.print("\t Selecione a posição do vértice de destino }>");
	        		int destinoIndex = entrada.nextInt();
	        		Vertice<String> verticDestino = null;
	        		
	        		try { verticDestino =  tbl_grafo.mapVertices.get(destinoIndex);}
	        		catch (Exception e) {
	        			out.println("\t Posição não encontrada!!");
	        			break;
					}
	        		grafo.addAresta(verticOrigem, verticDestino);
	        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\tAresta adicionado!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 3:// Remover Vertice
	        		out.println("\tPara Remover no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		int index = 0;
	        		out.print("\t Selecione a posição Removida }>");
	        		index = entrada.nextInt();
	        		Vertice<String> verticeRemovido = null;
	        		
	        		try { verticeRemovido = grafo.vertices.get(index - 1);}
	        		catch (Exception e) {
	        			out.println("\t Posição não encontrada!!");
	        			break;
					}
	        		
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
	        		out.println("\t Vértice Removido!!!");
	        		out.println(tbl_grafo.toString);
	        		
	        		break;
	        	case 4:// Remover Aresta
	        		out.println("\tPara Remover no Grafo:");
	        		out.println(tbl_grafo.toString);
	        		
	        		out.print("\t Selecione a posição do vértice }>");
	        		int verticeIndex = entrada.nextInt();
	        		Vertice<String> vertic  = null;
	        		
	        		try { vertic =  tbl_grafo.mapVertices.get(verticeIndex); }
	        		catch (Exception e) {
	        			out.println("\t Posição não encontrada!!");
					}
	        		if(vertic ==  null) {
	        			out.println("\t Posição não encontrada!!");
	        			break;
	        		}
	        			
	        		Tabela arestas = new Tabela(vertic.aresta);
	        		
	        		out.print("\t" + arestas.toString);
	        		
	        		out.print("\t Digite a posição da Aresta }>");
	        		int indexAresta = entrada.nextInt()-1;
	        		
	        		vertic.aresta.remove(indexAresta);
	        			        		
	        		tbl_grafo = new Tabela(grafo);
	        		out.println("\t Aresta Removida!!!");
	        		out.println(tbl_grafo.toString);
	        		break;
	        	case 5:// Exibe o grafo
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
		Vertice<String> norma = grafo.addVertice("R. Norma Pieruccini");
		
		grafo.addAresta(rudge, bosque);
		grafo.addAresta(rudge, norma);
		grafo.addAresta(bosque, rudge);
		grafo.addAresta(norma, bosque);
	
		
		return grafo;
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
