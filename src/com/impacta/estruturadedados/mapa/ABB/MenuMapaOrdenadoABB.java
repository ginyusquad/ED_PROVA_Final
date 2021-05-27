package com.impacta.estruturadedados.mapa.ABB;

import java.util.Scanner;

import com.impacta.estruturadedados.interfaces.IMenu;
import com.impacta.estruturadedados.interfaces.ISubMenu;

public class MenuMapaOrdenadoABB implements ISubMenu{
	
	private IMenu menuPrincipal;
	private Scanner entrada;

    public MenuMapaOrdenadoABB(IMenu menuPrincipal){
        this.menuPrincipal = menuPrincipal;
        this.entrada = new Scanner(System.in);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMenu(IMenu menuPrincipal) {
		// TODO Auto-generated method stub
		
	}
    
}
