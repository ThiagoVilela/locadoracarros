package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.Locadora;

/**
 * 
 * Classe de Testes relativo a classe Locadora
 * 
 * @author Thiago Vilela
 *
 */
class LocadoraTest {

	/**
	 * Classe que testa os métodos Get e Set NomeLocadora
	 */
	@Test
	public void testeGetSetNomeLocadora() {
		Locadora locadora = new Locadora();
		locadora.setNomeLocadora("Nome1");
		assertEquals(locadora.getNomeLocadora(),"Nome1");
	}
	
	/**
	 * Classe que testa os métodos Get e Set LimitePessoas
	 */
	@Test
	public void testeGetSetLimitePessoas() {
		Locadora locadora = new Locadora();
		locadora.setLimitePessoas(10);
		assertEquals(locadora.getLimitePessoas(),10);
	}
	
	/**
	 * Classe que testa os métodos Get e Set TipoCarro
	 */
	@Test
	public void testeGetSetTipoCarro() {
		Locadora locadora = new Locadora();
		locadora.setTipoCarro("Tipo1");
		assertEquals(locadora.getTipoCarro(),"Tipo1");
	}
	
	/**
	 * Classe que testa os métodos Get e Set taxaDiaSemanaRegular
	 */
	@Test
	public void testeGetSetTaxaDiaSemanaRegular() {
		Locadora locadora = new Locadora();
		locadora.setTaxaDiaSemanaRegular(10);
		assertEquals(locadora.getTaxaDiaSemanaRegular(),10);
	}
	
	/**
	 * Classe que testa os métodos Get e Set taxaDiaFimSemanaRegular
	 */
	@Test
	public void testeGetSetTaxaDiaFimSemanaRegular() {
		Locadora locadora = new Locadora();
		locadora.setTaxaFimSemanaRegular(10);
		assertEquals(locadora.getTaxaFimSemanaRegular(),10);
	}
}
