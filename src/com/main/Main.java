package com.main;

import com.utils.Logic;

/**
 * 
 * Classe principal que dá início ao programa
 * 
 * @author Thiago Vilela
 *
 */
public class Main {
	/**
	 * 
	 * Função principal que dá início a execução do programa
	 * 
	 * @param args
	 * 		Possível parâmetro inserido (ou não) durante a execução do programa
	 */
	public static void main(String[] args) {
		 Logic logic = new Logic();
		 
		 if(logic.lerArquivo("entrada.txt")) {
			 System.out.println("Leitura completa, possível prosseguir");
			 logic.start();
		 }else {
			System.err.println("Erro na leitura do arquivo, impossível prosseguir");
		 }  
	}
}

