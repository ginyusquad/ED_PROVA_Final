package com.impacta.estruturadedados.mapa;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
import com.impacta.estruturadedados.mapa.source.HashTableMap;
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
public class MenuMapa  implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Mapa";
	
    public MenuMapa(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		HashTableMap<String, String> mapa =  new HashTableMap<String, String>();
		
		Tabela tbl_mapa = new Tabela(mapa);
		
		out.println("\n"
		  	  	  + "\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
		   	  	  + "\t│                                                   DESCRICAO                                              │");
		out.println("\t├──────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│                                                                                                          │");
		out.println("\t│    Um mapa é uma estrutura de dados que armazena elementos indexados por chave-valor                     │");
        out.println("\t│   o mapa permite apenas chaves diferentes e utiliza uma função hash para mapear a                        │");
        out.println("\t│   chave para uma posiçao no vetor.                                                                       │");
        out.println("\t│    * vetor[ Compressao( hash(chave), N) ] = (chave: valor)                                               │");
        out.println("\t│                                                                                                          │");
        out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
		
		out.println("    Por padrão será usado o Mapa:");
	    out.println("      " + tbl_mapa.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t put(k, v): Insere um elemento no Mapa com chave k e valor v");
	        out.println("     2. Remover elemento");
	        out.println("\t remove(k): Remove o elemento com a chave k do Mapa");
	        out.println("     3. Mostrar o Mapa");
	        out.println("     4. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..4) }>");
	        
	        opcao = entrada.nextInt();
	        String chave = "";
	        String conteudo = "";
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar no Mapa:");
	        		out.println(tbl_mapa.toString);
	        		
	        		out.println("\tDigite a Chave:");
	        		entrada.nextLine();
	        		chave = entrada.nextLine();
	        		out.println("\tDigite o conteudo:");
	        		conteudo = entrada.nextLine();
	        		
	        		mapa.put(chave, conteudo);
	        		
	        		tbl_mapa =  new Tabela(mapa);
	        		
	        		out.println(tbl_mapa.toString);
	        		
	        		break;
	        	case 2:
	        		if(mapa.isEmpty()) {
	        			out.println("\tNão é possível remover de um Mapa Vazio!\n");
	        			break;
	        		}
	        		out.println("\tPara remover no Mapa:");
	        		out.println(tbl_mapa.toString);
	        		out.println("\tDigite a Chave do elemento que deseja remover:");
	        		entrada.nextLine();
	        		chave = entrada.nextLine();
	        		
	        		mapa.remove(chave);
	        		out.println("\tElemento removido!!");
	        		
	        		tbl_mapa =  new Tabela(mapa);
	        		
	        		out.println(tbl_mapa.toString);
	        		
	        		break;
	        	case 3:
	        		out.println("\tMapa:");
	        		out.println(tbl_mapa.toString);
	        		
	        		break;
	        	case 4:
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
	        
        }while( opcao != 4 );
		
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
