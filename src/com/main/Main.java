package com.main;

import com.utils.Logic;

/**
 * 
 * Classe principal que d� in�cio ao programa
 * 
 * @author Thiago Vilela
 *
 */
public class Main {
	/**
	 * 
	 * Fun��o principal que d� in�cio a execu��o do programa
	 * 
	 * @param args
	 * 		Poss�vel par�metro inserido (ou n�o) durante a execu��o do programa
	 */
	public static void main(String[] args) {
		 Logic logic = new Logic();
		 
		 if(logic.lerArquivo("entrada.txt")) {
			 System.out.println("Leitura completa, poss�vel prosseguir");
			 logic.start();
		 }else {
			System.err.println("Erro na leitura do arquivo, imposs�vel prosseguir");
		 }  
	}
}

