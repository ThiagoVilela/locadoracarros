package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.Custo;

/**
 * 
 * Classe de Testes relativo a classe Custo
 * 
 * @author Thiago Vilela
 *
 */
class CustoTest {

	/**
	 * Classe que testa os métodos Get e Set NomeLocadora
	 */
	@Test
	public void testeGetSetNomeLocadora() {
		Custo custo = new Custo();
		custo.setNomeLocadora("Nome1");
		assertEquals(custo.getNomeLocadora(),"Nome1");
	}
	
	/**
	 * Classe que testa os métodos Get e Set TipoCarro
	 */
	@Test
	public void testeGetSetTipoCarro() {
		Custo custo = new Custo();
		custo.setTipoCarro("Tipo1");
		assertEquals(custo.getTipoCarro(),"Tipo1");
	}
	
	/**
	 * Classe que testa os métodos Get e Set Custo
	 */
	@Test
	public void testeGetSetCusto() {
		Custo custo = new Custo();
		custo.setCusto(2000);
		assertEquals(custo.getCusto(),2000);
	}
	
	/**
	 * Classe que testa o método ToString
	 */
	@Test
	public void testeToString() {
		Custo custo = new Custo("Nome","Tipo",2000);
		System.out.println(custo);
		assertEquals(custo.toString(),"Nome:2000.0");
	}


}
