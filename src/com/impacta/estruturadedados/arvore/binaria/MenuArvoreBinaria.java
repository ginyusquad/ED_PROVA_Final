package com.impacta.estruturadedados.arvore.binaria;

import static java.lang.System.out;

import java.util.Scanner;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

public class MenuArvoreBinaria implements ISubMenu{

	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Arvore Binaria";

    public MenuArvoreBinaria(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");
		
		// Codigo
		
		out.println( menuPrincipal.getTitulo() + ":");
		
	}

	@Override
	public void close() {

		
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {

		
	}

	@Override
	public String getTitulo() {
		return menuPrincipal.getTitulo()+" > "+this.titulo ;
	};
    
}
