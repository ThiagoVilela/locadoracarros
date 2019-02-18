package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.model.Custo;
import com.model.Locadora;
/**
 * 
 * Classe que contem os m�todos para realizar as
 * leituras, convers�es, e c�lculos que realizam
 * a l�gica geral do programa
 * 
 * @author Thiago Vilela
 *
 */
public class Logic {
	/** Atributos **/
	private int passageiros;
	private List<Locadora> locadoras;
	private List<Calendar> dias;
	private int diasSemana;
	private int diasFimSemana;
	private Custo menorCusto;
	
	/**
	 * Construtor da classe Logic
	 */
	public Logic() {
		locadoras = new ArrayList<Locadora>();
		locadoras.add(new Locadora("SouthCar",4,"Carro Compacto",210,200));
		locadoras.add(new Locadora("WestCar",2,"Carro Esportivo",530,200));
		locadoras.add(new Locadora("NorthCar",7,"SUV",630,600));
		
		dias = new ArrayList<Calendar>();
		menorCusto = new Custo();
		
		diasSemana = 0;
		diasFimSemana = 0;
	}
	
	/**
	 * 
	 * Classe que realiza a l�gica p�s leitura do arquivo,
	 * nela � chamada as fun��es que realizam o c�lculo do 
	 * intervalo das datas, o c�lculo dos custos em fun��o do 
	 * intervalo e escreve o resultado no arquivo
	 * 
	 */
	public void start() {
		this.calculaIntervalo();
		this.calculaCustos();
		this.escreveResultado("saida.txt");
	}
	
	/**
	 * 
	 * Realiza a leitura do arquivo (nome especificado no m�todo),
	 * separa, converte, valida e armazena nos atributos espec�ficos
	 * o conte�do lido
	 * 
	 * @param nomeArquivo Texto que representa o nome do arquivo a ser lido
	 * 
	 * @return Um booleano que identifica se nenhum problema foi identificado
	 * 
	 */
	public boolean lerArquivo(String nomeArquivo) {

		try {
			FileReader arquivo = new FileReader(nomeArquivo);
			BufferedReader lerArquivo = new BufferedReader(arquivo);
		 
			String linha = lerArquivo.readLine();
			
			String[] novaLinha = linha.split(":");
			if (novaLinha.length == 0) {
				System.err.printf("Erro na leitura do arquivo: N�o possui conte�do v�lido (Insira no formato DATA:PASSAGEIROS) \n");
				arquivo.close();
				return false;
			} else if (novaLinha.length > 2) {
				System.err.printf("Erro na leitura do arquivo: Conte�do excede o formato (DATA:PASSAGEIROS) \n");
				arquivo.close();
				return false;
			}
			
			if(!this.converteDatas(novaLinha[0])) {
				arquivo.close();
				return false;
			}
			if(!this.convertePassageiros(novaLinha[1])) {
				arquivo.close();
				return false;
			}
		    
			boolean maisLinhas = false;
			while (linha != null) {
				linha = lerArquivo.readLine();
				
				if(linha != null) {
					System.err.printf("Erro na leitura do arquivo: Conte�do excede o esperado (Apenas uma linha com DATA:PASSAGEIROS) \n");
					arquivo.close();
					maisLinhas = true;
					linha = null;
				}
			}
			
			if (!maisLinhas) {
				if(!this.verificaOrdemData()) {
					System.err.printf("Erro na leitura do arquivo: a data deve estar em ordem crescente (Primeiro a data inicial, depois a final) \n");
					arquivo.close();
					return false;
				}
				
				arquivo.close();
			} else {
				return false;
			}
			
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
	        e.getMessage());
			return false;
	    }
		
		return true;
	}
	
	/**
	 * 
	 * Converte o texto recebido em um inteiro, 
	 * e salva o conte�do no atributo passageiros
	 * 
	 * @param passageiros
	 * 			Texto que identifica o n�mero de passageiros
	 * @return Um booleano que identifica se a convers�o ocorreu corretamente
	 * 			
	 */
	public boolean convertePassageiros(String passageiros) {
		try {
			this.passageiros = Integer.parseInt(passageiros);
		} catch (NumberFormatException e) {
			System.err.printf("Erro: n�mero de passageiros inv�lido \n",
			e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * Converte um texto que representa uma poss�vel dupla de datas,
	 * e cria um processo de convers�o, para que elas sejam armazenadas, 
	 * e valda��o, para a l�gica do programa
	 *
	 * @param datas
	 * 		Um texto que representa uma dupla de datas separadas pelo caractere ,
	 * @return Um booleano que indica se a valida��o foi realizada com sucesso
	 * 
	 */
	public boolean converteDatas(String datas) {
		String[] intervalo = datas.split(",");
		
		if(intervalo.length > 1) {
			if(intervalo[0].length() >= 9 && intervalo[0].length() <= 15 && intervalo[1].length() >= 9 && intervalo[1].length() <= 15) {
				for (String string : intervalo) {
					String novaString = this.separaDia(string);
					
					int dia = this.converteDia(novaString.substring(0,2));
					if(dia == -1) { return false; }
					
					int mes = this.converteMes(novaString.substring(2,5).toLowerCase());
					if(mes == -1) { return false; }
					
					int endIndex = novaString.length();
					int ano = this.converteAno(novaString.substring(5, endIndex));
					if(ano == -1) { return false; }
					
					boolean dataValida = this.validaData(dia, mes, ano);
					if(!dataValida) { return false; }
					
				}
				
				return true;
			}
			
			return false;
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * Divide um texto recebido pelo caractere espa�o
	 * 
	 * @param data
	 * 		Texto que representa uma data
	 * @return O conte�do anterior ao caractere espa�o
	 * 
	 */
	public String separaDia(String data) {
		String[] semEspaco = data.split(" ");

		if(semEspaco.length >= 2) {
			return semEspaco[0];
		}
		return data;
	}
	
	/**
	 * 
	 * Converte um texto em um inteiro 
	 * que representa um poss�vel dia de um m�s
	 * 
	 * @param textoDia
	 * 		Texto que representa um dia de um m�s
	 * @return Um inteiro que representa um poss�vel dia de um m�s
	 * 
	 */
	public int converteDia(String textoDia) {
		int dia = 0;
		try {
			dia = Integer.parseInt(textoDia);
			if(dia <= 0 || dia > 31) {
				System.err.printf("Erro: Dia especificado na data inv�lido (Os dias devem existir no respectivo m�s) \n");
				return -1;
			}
			return dia;
		} catch (NumberFormatException e) {
			System.err.printf("Erro: Dia especificado na data inv�lido \n",
			e.getMessage());
		}
		return -1;
	}
	
	/**
	 * 
	 * Associa um texto a um poss�vel m�s do ano
	 * (Tamb�m aceita um m�s abreviado em ingl�s)
	 * 
	 * @param textoMes
	 * 		Texto que pode representar um m�s do ano
	 * @return Um inteiro que representa um m�s do ano (De 0 a 11)
	 * 
	 */
	public int converteMes(String textoMes) {
		int mes = -1;
		switch (textoMes) {
			case "jan": mes = 0; break;
			case "fev": mes = 1; break;
			case "feb": mes = 1; break; //ingl�s
			case "mar": mes = 2; break;
			case "abr": mes = 3; break;
			case "apr": mes = 3; break; //ingl�s
			case "mai": mes = 4; break;
			case "may": mes = 4; break; //ingl�s
			case "jun": mes = 5; break;
			case "jul": mes = 6; break;
			case "ago": mes = 7; break;
			case "aug": mes = 7; break; //ingl�s
			case "set": mes = 8; break;
			case "sep": mes = 8; break; //ingl�s
			case "out": mes = 9; break;
			case "oct": mes = 9; break; //ingl�s
			case "nov": mes = 10; break;
			case "dez": mes = 11; break;
			case "dec": mes = 11; break; //ingl�s
			default:    mes = -1; break;
		}
		if(mes == -1) {
			System.err.printf("Erro: M�s especificado na data inv�lido (M�s corresponde as tr�s letras iniciais do mesmo) \n");
			return -1;
		}
		
		return mes;
	}
	
	/**
	 * 
	 * Converte um texto para um poss�vel ano,
	 * levando em considera��o que o ano deve ser maior
	 * ou igual ao atual
	 * 
	 * @param textoAno
	 * 		Texto que pode representar um poss�vel ano
	 * @return Um inteiro que representa um ano
	 * 
	 */
	public int converteAno(String textoAno) {
		Calendar dataAtual = Calendar.getInstance();
		int ano = 0;
		try {
			ano = Integer.parseInt(textoAno);
			/** Comparo o ano lido com o ano atual, para evitar que um carro seja alugado no passado **/
			if(ano < dataAtual.get(Calendar.YEAR)) {
				System.err.printf("Erro: Ano especificado na data inv�lido (Ano deve ser maior igual ao atual, n�o � poss�vel alugar um carro no passado) \n");
				return -1;
			}
			return ano;
		} catch (NumberFormatException e) {
			System.err.printf("Erro: Ano especificado na data inv�lido \n",
			e.getMessage());
		}
		return -1;
	}
	
	/**
	 * 
	 * Constr�i um tipo Calendar com os par�metros
	 * e verifica se a data criada � v�lida de acordo
	 * com as restri��es dos dias em fun��o dos meses e dos anos,
	 * e caso seja v�lido ser� adicionado no atributo lista de dias
	 * 
	 * @param dia
	 * 		Um inteiro que representa um dia de um m�s de um ano
	 * @param mes
	 * 		Um inteiro que representa um m�s de um ano
	 * @param ano
	 * 		Um inteiro que representa um ano
	 * @return Um booleano que representa se a data foi v�lida
	 * 
	 */
	public boolean validaData(int dia, int mes, int ano) {
		Calendar dataLida = Calendar.getInstance();
		dataLida.set(ano,mes,dia);
		
		if(dataLida.get(Calendar.DAY_OF_MONTH) == dia && dataLida.get(Calendar.MONTH) == mes &&  dataLida.get(Calendar.YEAR) == ano) {
			
			int anterior = this.dataAnterior(dataLida);
			if(anterior >= 0) {
				this.dias.add(dataLida);
				return true;
			}else {
				System.err.printf("Erro: Data menor que a atual (n�o � poss�vel alugar um carro no passado) \n");
				return false;
			}
			
		}else {
			System.err.printf("Erro: Data inv�lida (Verificar se os dias correspondem ao respectivo m�s do ano) \n");
			return false;
		}
		
	}
	
	/**
	 * 
	 * Compara se a data recebida � menor, igual ou maior
	 * que uma data criada no momento de execu��o do programa
	 * 
	 * @param data
	 * 		Uma data a ser comparada com o momento de execu��o
	 * @return Um inteiro que identifica o resultado da compara��o (Caso menor: -1 ; Caso igual: 0 ; Caso maior: 1)
	 * 
	 */
	public int dataAnterior(Calendar data) {
		Calendar dataAtual = Calendar.getInstance();
		return data.compareTo(dataAtual);
	}
	
	/**
	 * 
	 * Compara se as datas armazenadas no atributo lista de datas
	 * foram em ordem crescente (modo como deveria estar escrito no arquivo)
	 * 
	 * @return Um booleano que indica se as datas est�o em ordem crescente
	 * 
	 */
	public boolean verificaOrdemData() {
		int estado = this.dias.get(0).compareTo(this.dias.get(1));
		if( estado == -1 || estado == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * Identifica o intervalo (fechado) entre as datas armazanedas no atributo lista de datas,
	 * realizando a contagem dos dias em fun��o das restri��es da classe Calendar, e incrementando
	 * os atributos diasFimSemana e diasSemana, caso o dia encontrado seja um dia de semana ou final de semana
	 * 
	 */
	public void calculaIntervalo() {
		Calendar dataInicial = this.dias.get(0);
		Calendar dataFinal = this.dias.get(1);
		
		int diaInicial = dataInicial.get(Calendar.DAY_OF_MONTH);
		int diaFinal = dataFinal.get(Calendar.DAY_OF_MONTH);
		int anoInicial = dataInicial.get(Calendar.YEAR);
		int anoFinal = dataFinal.get(Calendar.YEAR);
		int mesInicial = dataInicial.get(Calendar.MONTH);
		int mesFinal = dataFinal.get(Calendar.MONTH);
		Calendar dataAux = Calendar.getInstance();
		
		int diaAtual = diaInicial;
		int mesAtual = mesInicial;
		Calendar dataCompare = Calendar.getInstance();
		 
		for (int i = anoInicial; i <= anoFinal; i++) {
			for (int j = mesAtual; dataCompare.get(Calendar.MONTH) > 0; j++) {
				for (int k = diaAtual; dataCompare.get(Calendar.DAY_OF_MONTH) > 1; k++) {
					dataAux.set(i,j,k);

					int diaDaSemana = dataAux.get(Calendar.DAY_OF_WEEK);
					if(diaDaSemana == Calendar.SATURDAY  || diaDaSemana == Calendar.SUNDAY) {
						this.diasFimSemana++;
					} else {
						this.diasSemana++;
					}
					
					/** L�gica para controle dos Dias **/
					if(i == anoFinal) {
						if(j == mesFinal) {
							if (k == diaFinal) {
								dataCompare.set(i, j, 1);
							} else {
								dataCompare.set(i, j, k+1);
							}
						}else {
							dataCompare.set(i, j, k+1);
						}
					} else {
						dataCompare.set(i, j, k+1);
					}	
				}
				
				/** L�gica para controle dos Meses **/
				if(i == anoFinal) {
					if(j == mesFinal) {
						diaAtual = 1;
						dataCompare.set(i, 0, 1);
					} else {
						diaAtual = 1;
						dataCompare.set(i, j+1, 2);
					}
				} else {
					diaAtual = 1;
					dataCompare.set(i, j+1, 2);
				}
			 }
			 mesAtual = 0;
			 dataCompare.set(i, 1, 2);
		 }
	}
	
	/**
	 * 
	 * Calcula o menor custo, criando uma lista de poss�veis custos como atributo,
	 * identificando o menor e salvando no atributo menorCusto
	 * 
	 */
	public void calculaCustos() {
		List<Custo> listaCustos = new ArrayList<Custo>();
		
		for (Locadora locadora : this.locadoras) {
			if(this.passageiros <= locadora.getLimitePessoas()) {
				double custoTotal = (this.diasFimSemana * locadora.getTaxaFimSemanaRegular()) + 
								(this.diasSemana * locadora.getTaxaDiaSemanaRegular());
				listaCustos.add(new Custo(locadora.getNomeLocadora(),locadora.getTipoCarro(),custoTotal));
			}
		}
		
		if(listaCustos.size() > 0) {
			Custo menorCusto = listaCustos.get(0);
			for (Custo custo : listaCustos) {
				if(custo.getCusto() < menorCusto.getCusto()) {
					menorCusto = custo;
				}
			}
			
			this.menorCusto = menorCusto;
		} else {
			System.out.println("Nenhuma locadora se encaixa no padr�o pedido");
		}
	}
	
	/**
	 * 
	 * Escreve no arquivo de sa�da (nome especificado no m�todo)
	 * o conte�do do atributo menorCusto, na ordem tipo de carro que a 
	 * locadora fornece, separador identificado pelo caractere :, e
	 * o nome da locadora em quest�o
	 * 
	 * @param nomeArquivo Texto que representa o nome do arquivo
	 * 
	 */
	public void escreveResultado(String nomeArquivo) {
		try {
			File file = new File(nomeArquivo);
			if (!file.exists()) {
                file.createNewFile();
			}
			FileWriter fwriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			
			bwriter.write(this.menorCusto.getTipoCarro()+":"+this.menorCusto.getNomeLocadora());
			bwriter.close();
		} catch (IOException e) {
			System.err.println("Erro ao escrever o arquivo resultado");
		}
	}
	
	/**
	 * Acessa o valor atual do atributo passageiros
	 * @return Inteiro que representa o valor atual do atributo passageiros
	 */
	public int getPassageiros() {
		return passageiros;
	}
	
	/**
	 *  Altera o valor atual do atributo passageiros
	 * @param passageiros Novo valor de passageiros
	 */
	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
	}
	
	/**
	 * Acesssa a lista de locadoras armazenadas
	 * @return A lista de locadoras
	 */
	public List<Locadora> getLocadoras() {
		return locadoras;
	}
	
	/**
	 * Altera a lista atual de locadoras
	 * @param locadoras Nova lista de locadoras
	 */
	public void setLocadoras(List<Locadora> locadoras) {
		this.locadoras = locadoras;
	}
	
	/**
	 * Acessa o atributo lista de dias
	 * @return A lista de dias
	 */
	public List<Calendar> getDias() {
		return dias;
	}
	
	/**
	 * Altera a lista de dias
	 * @param dias Nova lista de dias
	 */
	public void setDias(List<Calendar> dias) {
		this.dias = dias;
	}
	
	/**
	 * Acessa o atributo diasSemana
	 * @return Inteiro que representa os dias da semana
	 */
	public int getDiasSemana() {
		return diasSemana;
	}
	
	/**
	 * Altera o atributo diasSemana
	 * @param diasSemana Novo valor para os dias da semana
	 */
	public void setDiasSemana(int diasSemana) {
		this.diasSemana = diasSemana;
	}
	
	/**
	 * Acessa o atributo diasFimSemana
	 * @return Inteiro que representa o valor diasFimSemana
	 */
	public int getDiasFimSemana() {
		return diasFimSemana;
	}
	
	/**
	 * Altera o atributo diasFimSemana
	 * @param diasFimSemana Novo valor para o atributo diasFimSemana
	 */
	public void setDiasFimSemana(int diasFimSemana) {
		this.diasFimSemana = diasFimSemana;
	}
	
	/**
	 * Acessa o atributo menorCusto
	 * @return Custo que contem o valor de menorCusto
	 */
	public Custo getMenorCusto() {
		return menorCusto;
	}
	
	/**
	 * Altera o atributo menorCusto
	 * @param menorCusto Novo valor para o atributo menorCusto
	 */
	public void setMenorCusto(Custo menorCusto) {
		this.menorCusto = menorCusto;
	}

}
