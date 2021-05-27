package com.impacta.estruturadedados.arranjo;

import static java.lang.System.out;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

public class MenuArranjo implements ISubMenu{
    
    private IMenu menuPrincipal;
    private Scanner entrada;

    public MenuArranjo(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

    //Mostra e inicia o menu
    public void show(){
    	
        out.println("  Menu Arranjo:");
        Arranjo arranjo = new Arranjo();
        
		arranjo.explicacaoArranjo();

		//Fazendo o Programa pausar para o usuario ;er as informações na tela
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			
		}

		out.println("    Por padrão será usado o vetor:");
	    out.println("      " + arranjo.toString() );
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Somar todos os elementos ");
	        out.println("     2. Pegar o maior elemento ");
	        out.println("     3. Pegar o menor elemento ");
	        out.println("     4. Ver a repetição dos elementos iguais ");
	        out.println("     5. Alterar o vetor");
	        out.println("     6. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..6) }>");
	        
	        opcao = entrada.nextInt();
	        
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tA soma dos itens é: " + arranjo.soma() + "\n");
	        		break;
	        	case 2:
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO maior item é: " + arranjo.maior() + "\n");
	        		break;
	        	case 3:
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO menor item é: " + arranjo.menor() + "\n");
	        		break;
	        	case 4:
	        		out.println("\tDigite o valor que deseja ver as repeticoes }>");
	        		int numeroRepetido = entrada.nextInt();
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO valor "+numeroRepetido+" se repete " +
	        					arranjo.repeticoes(numeroRepetido) +" vezes\n");
	        		break;
	        	case 5:
	        		arranjo = lerArranjo();
	        		break;
	        	case 6:
	        		this.menuPrincipal.show();
	        		return;
	        	default:
	        		out.println("    Não entendi!\n");
	        		continue;
	        }
	        try {
				TimeUnit.SECONDS.sleep(3);
			} catch (Exception e) {
				
			}
        }while(opcao > 0 && opcao < 6);
	        
        
    }
    
    public Arranjo lerArranjo() {
    	
    	Integer[] vetor = null;
    	Arranjo arranjo = null;
    	
    	out.println("    Defina o tamanho }> ");
    	int tamanho = entrada.nextInt();
    	vetor = new Integer[tamanho];
    	
    	for(int index = 0; index < tamanho; index++) {
    		
    		out.println("    Defina o "+ (index+1) +"° elemento }> ");
    		vetor[index] = entrada.nextInt();
    		
    	}
    	
    	arranjo = new Arranjo(vetor);
    	
    	out.println("    O Vetor foi alterado para: ");
    	out.println("      " + arranjo.toString() + "\n");
    	
    	return arranjo;
    }
    // fechar o menu
    public void close(){
    	
    	menuPrincipal.show();

    }

    public void setMenu(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
    }

	
}