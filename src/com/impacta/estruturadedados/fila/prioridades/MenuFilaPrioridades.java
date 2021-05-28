package com.impacta.estruturadedados.fila.prioridades;

import static java.lang.System.out;

import java.util.Scanner;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

public class MenuFilaPrioridades implements ISubMenu {
	
	
	private IMenu menuPrincipal;
	private Scanner entrada;
	
	private String titulo = "Menu Prioridades";
	
    public MenuFilaPrioridades(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
    
	@Override
	public void show() {
		out.println( getTitulo() + ":");

		// Codigo ...
		
		out.println( menuPrincipal.getTitulo() + ":");
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
