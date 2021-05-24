package com.impacta.estruturadedados.provafinal.pilha;

import static java.lang.System.out;
import java.util.Scanner;

import com.impacta.estruturadedados.provafinal.interfaces.IMenu;
import com.impacta.estruturadedados.provafinal.interfaces.ISubMenu;

public class MenuPilha implements ISubMenu {

	private IMenu menuPrincipal;
	private Scanner entrada;

    public MenuPilha(IMenu menuPrincipal){
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
		this.menuPrincipal = menuPrincipal;
	}

}
