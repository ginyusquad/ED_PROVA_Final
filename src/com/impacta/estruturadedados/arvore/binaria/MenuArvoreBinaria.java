package com.impacta.estruturadedados.arvore.binaria;

import java.util.Scanner;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

public class MenuArvoreBinaria implements ISubMenu{

	
	private IMenu menuPrincipal;
	private Scanner entrada;

    public MenuArvoreBinaria(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {

		
	}

	@Override
	public void close() {

		
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {

		
	}
    
}
