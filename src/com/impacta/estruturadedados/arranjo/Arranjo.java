package com.impacta.estruturadedados.arranjo;

public class Arranjo {
	
	public static final Integer[] LISTA_INICIAL = { 940, 880, 830, 790, 750, 660, 650, 590, 510, 940 };
	private Integer[] vetor;
	
	public Arranjo() {
		this.vetor = LISTA_INICIAL.clone();
	}
	
	public Arranjo(Integer[] vetor) {
		this.vetor = vetor.clone();
	}

	/**
	 * Função que retorna o menor valor do arranjo
	 * 
	 * @return retorna o menor numero dentro da lista
	 */
	public int menor() {

        int menor = this.vetor[0];
        
		for (Integer numero : this.vetor) {

			// Procura o menor
			if (menor > numero)menor = numero;

		}
		return menor;
	}

	/**
	 * Função que retorna o maior valor do arranjo
	 * 
	 * @return retorna o maior numero dentro da lista
	 */
	public int maior() {

		int maior = this.vetor[0];

        for (Integer numero : this.vetor) {

			// Procura o menor
			if (maior < numero)maior = numero;

		}

		return maior;
	}

	/**
	 * Função que retorna a soma dos valores no aranjo
	 * 
	 * @return retorna a soma dos valores dentro do arranjo
	 */
	public int soma() {

		int soma = 0;

		for (Integer numero : this.vetor) {
			soma = numero + soma;
		}

		return soma;
	}

	/**
	 * Função que retorna o numero de vezes que um numero aparece no arranjo
	 * 
	 * @param Int numeroRepetido numero que será buscado no arranjo
	 * @return retorna o numero de vezes que se repete dentro da lista
	 */
	public int repeticoes(int numeroRepetido) {

		int repeticoes = 0;

		for (Integer numero : this.vetor) {

			// Incrementa o contador de repetições para cada achado
			if (numeroRepetido == numero)
				repeticoes++;

		}

		return repeticoes;
	}
	public static final String EXPLICACAO = 	("\t\tCom arranjos podemos fazer operações como:\n"+
			    						 		 "\t\t + Somar todos os elementos;\n"+
			    								 "\t\t + Pegar o maior elemento;\n"+
			    								 "\t\t + Pegar o menor elemento;\n"+
			    								 "\t\t + Ver a repetição dos elementos iguais;");
	public void explicacaoArranjo() {

		System.out.println(EXPLICACAO +"\n");
		
		System.out.print("\t\tNa lista: ");
		System.out.println(toString());

		System.out.println("\t\t A soma dos itens é: " + soma());
		System.out.println("\t\t O maior item é: " + maior());
		System.out.println("\t\t O menor item é: " + menor());
		System.out.println("\t\t O valor 940 se repete " + repeticoes(940) +" vezes\n");
		
	}
	public String toString(){
		String arranjoFormatado = "[ ";
		for( Integer Item : this.vetor) {
			arranjoFormatado += Item+" ";
		}
		return arranjoFormatado + " ]";
	}
}
