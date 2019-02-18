package com.model;

/**
 * 
 * Classe responsável por moldar os custos
 * 
 * @author Thiago Vilela
 *
 */
public class Custo {
	/** Atributos **/
	private String nomeLocadora;
	private String tipoCarro;
	private double custo;
	
	/**
	 * Construtor sem parâmetros da classe Custo
	 */
	public Custo() {
	}
	
	/**
	 * 
	 * Construtor da classe Custo com parâmetros
	 * 
	 * @param nomeLocadora Texto que corresponde ao nome da Locadora
	 * @param tipoCarro Texto que corresponde ao tipo de carro fornecido pela locadora
	 * @param custo Double que corresponde ao custo calculado
	 * 
	 */
	public Custo(String nomeLocadora, String tipoCarro, double custo) {
		this.nomeLocadora = nomeLocadora;
		this.tipoCarro = tipoCarro;
		this.custo = custo;
	}
	
	/**
	 * Retorna em texto o nome da locadora, caractere :, e o custo
	 */
	@Override
	public String toString() {
		return this.nomeLocadora + ":" + this.custo;
	}
	
	/**
	 * Acessa atributo nomeLocadora e obtem seu conteudo
	 * @return texto que representa o nome da locadora
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
	 * Acessa o atributo tipoCarro e obtem seu conteúdo
	 * @return Texto que representa o tipo do carro
	 */
	public String getTipoCarro() {
		return tipoCarro;
	}
	
	/**
	 * Altera o tipoCarro
	 * @param tipoCarro Novo texto para substrituir o atual tipoCarro
	 */
	public void setTipoCarro(String tipoCarro) {
		this.tipoCarro = tipoCarro;
	}
	
	/**
	 * Acessa o atributo custo e obtem seu conteúdo
	 * @return Double que representa o conteúdo atual do custo
	 */
	public double getCusto() {
		return custo;
	}
	
	/**
	 * Altera o custo
	 * @param custo Novo double para substituir o atual custo
	 */
	public void setCusto(double custo) {
		this.custo = custo;
	}
}
