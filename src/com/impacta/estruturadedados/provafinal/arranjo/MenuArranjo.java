package com.impacta.estruturadedados.provafinal.arranjo;
import java.util.Scanner;

import com.impacta.estruturadedados.provafinal.interfaces.IMenu;
import com.impacta.estruturadedados.provafinal.interfaces.ISubMenu;

public class MenuArranjo implements ISubMenu{
    
    private IMenu menuPrincipal;
    private Scanner entrada;

    public MenuArranjo(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }

    //Mostra e inicia o menu
    public void show(){
        System.out.println("\t Menu Arranjo:");
        Arranjo arranjo = new Arranjo();
		//arranjo.explicacaoArranjo();
        System.out.println("\t\t Por padrão será usado o vetor:");
        System.out.println("\t\t  " + arranjo.toString() );
        System.out.println("\t\t Deseja definir outro vetor? (s/n) }>");
        String resposta = entrada.next();
		
        
    }
    // fechar o menu
    public void close(){
    	
    	menuPrincipal.show();

    }

    public void setMenu(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
    }

	
}