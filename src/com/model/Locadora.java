package com.model;

/**
 * 
 * Classe responsável por moldar as Locadoras
 * 
 * @author Thiago Vilela
 *
 */
public class Locadora {
	/** Atributos **/
	private String nomeLocadora;
	private int limitePessoas;
	private String tipoCarro;
	private double taxaDiaSemanaRegular;
	private double taxaFimSemanaRegular;
	
	/**
	 * Construtor vazio para Locadora
	 */
	public Locadora() {
	}
	
	/**
	 * Construtor para classe Locadora
	 * @param nomeLocadora Texto que representa um nome para a locadora
	 * @param limitePessoas Inteiro que representa o limite de pessoas que ela pode abrangir
	 * @param tipoCarro Texto que representa o tipo de carro que ela fornece
	 * @param semanaRegular Double que representa a taxa cobrada em um dia de semana para um cliente regular
	 * @param fimSemanaRegular Double que representa a taxa cobrada em um dia de final de semana para um cliente regular
	 */
	public Locadora(String nomeLocadora, int limitePessoas, String tipoCarro, double semanaRegular, double fimSemanaRegular){
		this.nomeLocadora = nomeLocadora;
		this.limitePessoas = limitePessoas;
		this.tipoCarro = tipoCarro;
		this.taxaDiaSemanaRegular = semanaRegular;
		this.taxaFimSemanaRegular = fimSemanaRegular;
	}
	
	/**
	 * Retorna em formato textual propriedades que identificam uma locadora
	 */
	@Override
	public String toString() {
		return this.nomeLocadora + " : " + this.limitePessoas + " : " + this.taxaDiaSemanaRegular + " : " + this.taxaFimSemanaRegular;
	}
	
	/**
	 * Acessa atributo nomeLocadora e obtem seu conteudo
	 * @return Texto que representa o nome da locadora
	 */
	public String getNomeLocadora() {
		return this.nomeLocadora;
	}
	
	/**
	 * Altera o atributo nomeLocadora
	 * @param nomeLocadora Novo texto para substituir o atual nome da locadora
	 */
	public void setNomeLocadora(String nomeLocadora) {
		this.nomeLocadora = nomeLocadora;
	}
	
	/**
	 * Acessa atributo limitePessoas e obtem seu conteudo
	 * @return Inteiro que representa o valor atual de limitePessoas
	 */
	public int getLimitePessoas() {
		return limitePessoas;
	}
	
	/**
	 * Altera o valor do atributo limitePessoas
	 * @param limitePessoas Inteiro que representa o novo valor para limitePessoas
	 */
	public void setLimitePessoas(int limitePessoas) {
		this.limitePessoas = limitePessoas;
	}
	
	/**
	 * Acessa atributo tipoCarro e obtem seu conteudo
	 * @return Texto que representa o valor atual de tipoCarro
	 */
	public String getTipoCarro() {
		return tipoCarro;
	}
	
	/**
	 * Altera o valor do atributo tipoCarro
	 * @param tipoCarro Texto que representa no novo valor do tipoCarro
	 */
	public void setTipoCarro(String tipoCarro) {
		this.tipoCarro = tipoCarro;
	}
	
	/**
	 * Acessa atributo tipoCarro e obtem seu conteudo
	 * @return Double que representa o valor atual de taxaDiaSemanaRegular
	 */
	public double getTaxaDiaSemanaRegular() {
		return taxaDiaSemanaRegular;
	}
	
	/**
	 * Altera o valor de taxaDiaSemanaRegular
	 * @param taxaDiaSemanaRegular Double que representa o novo valor de taxaDiaSemanaRegular
	 */
	public void setTaxaDiaSemanaRegular(double taxaDiaSemanaRegular) {
		this.taxaDiaSemanaRegular = taxaDiaSemanaRegular;
	}
	
	/**
	 * Acessa atributo taxaFimSemanaRegular e obtem seu conteudo
	 * @return Double que representa o valor atual de taxaFimSemanaRegular
	 */
	public double getTaxaFimSemanaRegular() {
		return taxaFimSemanaRegular;
	}
	
	/**
	 * Altera o valor de taxaFimSemanaRegular
	 * @param taxaFimSemanaRegular Double que representa o novo valor de taxaFimSemanaRegular
	 */
	public void setTaxaFimSemanaRegular(double taxaFimSemanaRegular) {
		this.taxaFimSemanaRegular = taxaFimSemanaRegular;
	}

}
