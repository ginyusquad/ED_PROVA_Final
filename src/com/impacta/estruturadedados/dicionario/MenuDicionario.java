package com.impacta.estruturadedados.dicionario;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.dicionario.source.HashTableMultiMap;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

import com.impacta.estruturadedados.utils.views.Tabela;

public class MenuDicionario implements ISubMenu{
	
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Dicionario";
	
    public MenuDicionario(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		
		HashTableMultiMap<String, String> dict =  criaDicionarioPadrao();
		out.println("\n# Descrição:");
		out.println("\t    Um dicionario é uma estrutura de dados semelhante aos Mapas no entanto");
        out.println("\t   ele permite armazenar diferentes valores em uma unica chave");
        out.println("\t   como um dicioanrio na vida real aodne uma unica palavra pode");
        out.println("\t   ter inumeros sinonimos e significados simultaneamente.\n");
        
				
		Tabela tbl_dict = new Tabela(dict);
		
		out.println("    Por padrão será usado o Dicionario:");
	    out.println("      " + tbl_dict.toString + "\n");
    
        int opcao = -1;
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t put(k, v): adiciona um elemento com chave k e valor v");
	        out.println("\t           caso já exista uma chave k sera acresentado outro valor");
	        out.println("\t           junto ao existente, diferente do map");
	        out.println("     2. Remover elemento");
	        out.println("\t remove(entry): remove o elemento entry selecionado");
	        out.println("\t               e importante re-saltar que dicionarios");
	        out.println("\t               podem possuir varios valores para uma unica chave!");
	        out.println("     3. Mostrar o dicionario");
	        out.println("     4. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..4) }>");
	        
	        opcao = entrada.nextInt();
	        String chave = "";
	        String conteudo = "";
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar no Dicionario:");
	        		out.println(tbl_dict.toString);
	        		
	        		out.println("\tDigite a Chave:");
	        		entrada.nextLine();
	        		chave = entrada.nextLine();
	        		out.println("\tDigite o conteudo:");
	        		conteudo = entrada.nextLine();
	        		
	        		dict.put(chave, conteudo);
	        		
	        		tbl_dict =  new Tabela(dict);
	        		
	        		out.println(tbl_dict.toString);
	        		
	        		break;
	        	case 2:
	        		if(dict.isEmpty()) {
	        			out.println("\tNão é possível remover de um Dicionario Vazio!\n");
	        			break;
	        		}
	        		out.println("\tPara remover no Dicionario:");
	        		tbl_dict = new Tabela(dict,true);
	        		out.println(tbl_dict.toString);
	        		out.print("\tDigite a posicao do elemento que deseja remover }>");
	        		int posicao = entrada.nextInt();
	        		
	        		try { dict.remove(tbl_dict.mapDic.get(posicao));}
	        		catch (Exception e) {
	        			out.println("\t "+e.getMessage());
	        			break;
					}
	        		out.println("\tElemento removido!!");
	        		
	        		tbl_dict =  new Tabela(dict);
	        		
	        		out.println(tbl_dict.toString);
	        		
	        		break;
	        	case 3:
	        		out.println("\tDicionario:");
	        		out.println(tbl_dict.toString);
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

	private HashTableMultiMap<String, String> criaDicionarioPadrao() {
		HashTableMultiMap<String, String> dicionario = new HashTableMultiMap<String, String>();
		
		dicionario.put("Contente", "Feliz");
		
		dicionario.put("Raiva", "Odio");
		dicionario.put("Raiva", "Aversão");
		
		dicionario.put("Amor", "Afeto");
		dicionario.put("Amor", "Azelo");
		
		return dicionario;
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
