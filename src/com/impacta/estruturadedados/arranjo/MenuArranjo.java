package com.impacta.estruturadedados.arranjo;

import static java.lang.System.out;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.arranjo.source.Arranjo;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;
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
public class MenuArranjo implements ISubMenu{
    
    private IMenu menuPrincipal;
    private Scanner entrada;
    
    private String titulo = "Menu Arranjo";
    
    public MenuArranjo(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

    // Mostra  o menu
    public void show(){
    	
        out.println( getTitulo() + ":");
        Arranjo arranjo = new Arranjo();
        out.println("\n"
        		  + "\t┌─────────────────────────────────────────────────────────────────────────────────┐\n"
        		  + "\t│                                   DESCRIÇÃO                                     │");
        out.println("\t├─────────────────────────────────────────────────────────────────────────────────┤");  
        out.println("\t│    Um arranjo é uma coleção de N elementos, sendo eles armazenados              │");
        out.println("\t│   em um vetor que vai de 0 a N - 1, sendo o índice 0 o primeiro elemento e      │");
        out.println("\t│   o índice N - 1 o último elemento!                                             │");
        out.println("\t│    Ele pode ser utilizado para armazenar, por exemplo, notas de uma prova       │");
        out.println("\t│   sendo que você deve saber quantas provas serão aplicadas.                     │");
        out.println("\t└─────────────────────────────────────────────────────────────────────────────────┘\n");
        
        
		out.println("    Por padrão será usado o vetor:");
	    out.println("      " + arranjo.toString()  + "\n");
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Somar todos os elementos ");
	        out.println("\t somar(): Percorre a coleção de elementos somando");
	        out.println("     2. Pegar o maior elemento ");
	        out.println("\t maior(): Percorre a coleção de elementos pegando o maior elemento");
	        out.println("     3. Pegar o menor elemento ");
	        out.println("\t menor(): Percorre a coleção de elementos pegando o menor elemento");
	        out.println("     4. Ver a repetição dos elementos iguais ");
	        out.println("\t repeticoes(x): Percorre a coleção de elementos as ocorrendias de x");
	        out.println("     5. Alterar o vetor");
	        out.println("     6. Sair para o menu principal \n");
	        out.print("    Selecione uma opção (1..6) }>");
	        
	        opcao = entrada.nextInt();
			out.println();
	        
	        switch(opcao) {
	        	case 1:// Mostra a Soma
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tA soma dos itens é: " + arranjo.soma() + "\n");
	        		break;
	        	case 2:// Mostra o maior elemento
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO maior item é: " + arranjo.maior() + "\n");
	        		break;
	        	case 3:// Mostra o menor elemento
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO menor item é: " + arranjo.menor() + "\n");
	        		break;
	        	case 4:// Mostra o numerod e vezes que um eleemento x se repete no conjunto
	        		out.print("\tDigite o elemento que deseja ver as repetições no vetor }>");
	        		int numeroRepetido = entrada.nextInt();
	        		out.println("\tPara o vetor "+ arranjo.toString());
	        		out.println("\tO valor "+numeroRepetido+" se repete " +
	        					arranjo.repeticoes(numeroRepetido) +" vezes\n");
	        		break;
	        	case 5:// Altera o arranjo
	        		arranjo = lerArranjo();
	        		break;
	        	case 6:// Sai para o menu principal
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
        }while(opcao != 6);
	        
        out.println( menuPrincipal.getTitulo() + ":");
    }
    
    public Arranjo lerArranjo() {
    	
    	Integer[] vetor = null;
    	Arranjo arranjo = null;
    	
    	out.print("    Defina o do vetor tamanho }> ");
    	int tamanho = entrada.nextInt();
    	vetor = new Integer[tamanho];
    	
    	for(int index = 0; index < tamanho; index++) {
    		
    		out.print("    Defina o "+ (index+1) +"° elemento }> ");
    		vetor[index] = entrada.nextInt();
    		
    	}
    	
    	arranjo = new Arranjo(vetor);
    	
    	out.println("    O Vetor foi alterado para: ");
    	out.println("      " + arranjo.toString() + "\n");
    	
    	return arranjo;
    }
    // fechar o menu
    public void close(){
    	out.println("    Saindo do menu "+ this.titulo);
    }

    public void setMenu(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
    }

    @Override
	public String getTitulo() {
		return menuPrincipal.getTitulo()+" > "+this.titulo ;
	};

	
}