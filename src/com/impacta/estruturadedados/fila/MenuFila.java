package com.impacta.estruturadedados.fila;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.impacta.estruturadedados.fila.source.FilaNode;
import com.impacta.estruturadedados.fila.source.Queue;
import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;


public class MenuFila implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Fila";
	
    public MenuFila(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

	@Override
	public void show() {
		
		out.println( getTitulo() + ":");
		out.println("\n"
		  	  	  + "\t┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
		   	  	  + "\t│                                                               DESCRICAO                                                               │");
		out.println("\t├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
		out.println("\t│    Uma Fila é uma estrutura de dados que armazena elementos seguindo o padrão                                                         │");
        out.println("\t│   FIFO, ao seja, o primeiro elemento a ser adicionado e o primeiro a ser removido.                                                    │");
        out.println("\t│   Por exemplo em uma mainframe de um Banco possue uma fila de operaçoes como essa:                                                    │");
        out.println("\t│                                                                                                                                       │");
        out.println("\t│    1° Criar Conta A com R$ 300,0 de saldo        [Usuario: Gilberto (1° titular da conta A)]                                          │");
        out.println("\t│    2° Sacar R$ 300,0 da Conta A                  [Usuario: Rodigo   (2° titular da conta A)]                                          │");
        out.println("\t│    3° Tranferir R$ 300,0 da Conta A para Conta B [Usuario: Takai    (3° titular da conta A)]                                          │");
        out.println("\t│                                                                                                                                       │");
        out.println("\t│    Nesse exemplo o computador vai criar a conta A e depois vai sacar 300 reais dela                                                   │");
        out.println("\t│   em seguinda  no 3° elemento da fila o usuario takai vai tentar transferir e dará saldo insuficiente                                 │");
        out.println("\t│    Se não tivessemos a fila de operação poderia ocorrer em qualquer ordem e não teria um controle sobre oque executa primeiro         │");
        out.println("\t│   poderiamos ter também o caso da operação 2° e 3° ocorrerem ao mesmo tempo, oque faria o banco perder 300 reais.                     │");
        out.println("\t└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
		
		Queue<Integer> fila = new FilaNode<Integer>();
		// Montando pilha padrao
		fila.enqueue(1);;
		fila.enqueue(2);
		fila.enqueue(3);
		fila.enqueue(4);

		
		out.println("    Por padrão será usado a Fila:");
	    out.println("      " + fila.toString() + "\n");
    
        int opcao = -1;
		// Loop das operações
        do {
	        
        	out.println("    Opções: ");
	        out.println("     1. Adicionar elemento");
	        out.println("\t enqueue(x): Insere o elemento x na fila");
	        out.println("     2. Remover elemento ");
	        out.println("\t dequeue(): Remove o elemento de inicio da fila");
	        out.println("     3. Ver o primeiro elemento da fila ");
	        out.println("\t front(): Retorna o primeiro elemento da fila");
	        out.println("     4. Mostrar a Fila");
	        out.println("     5. Sair para o menu principal ");
	        out.print("    Selecione uma opção (1..5) }>");
	        
	        opcao = entrada.nextInt();
	        out.println();
	        switch(opcao) {
	        	case 1:
	        		out.println("\tPara adicionar na fila "+ fila.toString());
	        		out.print("\tDigite o inteiro a ser adicionado:");
	        		int elemento = entrada.nextInt();
	        		fila.enqueue(elemento);
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 2:
	        		out.println("\tPara a fila "+ fila.toString());
	        		Integer elementoRemovido = fila.dequeue();
	        		out.println("\tElemento removido [ "+ elementoRemovido.toString()+" ]");
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 3:
	        		out.println("\tPara a fila "+ fila.toString());
	        		out.println("\tO elemento primeiro da fila é "+ fila.front().toString() + "\n");
	        		break;
	        	case 4:
	        		out.println("\tFila:");
	        		out.println("\t  " + fila.toString() + "\n");
	        		break;
	        	case 5:
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
	        
        }while(opcao > 0 && opcao < 5);
        
        
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
