package com.tests;

import com.model.Custo;
import com.model.Locadora;
import com.utils.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * Classe de Testes relativo a classe Logic
 * 
 * @author Thiago Vilela
 *
 */
class LogicTest {
	
	/**
	 * Classe que testa o método lerArquivo
	 */
	@Test
	public void testeLerArquivo() {
		Logic logic = new Logic();
		
		assertEquals(logic.lerArquivo("entradateste.txt"),true);
	}
	
	/**
	 * Classe que testa o método convertePassageiros
	 */
	@Test
	public void testeConvertePassageiros() {
		Logic logic = new Logic();
		
		assertEquals(logic.convertePassageiros("1"),true);
		assertEquals(logic.convertePassageiros("ASD"),false);
	}
	
	/**
	 * Classe que testa o método converteDatas 
	 */
	@Test
	public void testeConverteDatas() {
		Logic logic = new Logic();
		
		assertEquals(logic.converteDatas("01Jan2100 (qua),02Jan2100 (qui)"),true);
		assertEquals(logic.converteDatas("-1Jan2100 (qua),02Jan2100 (qui)"),false);
		assertEquals(logic.converteDatas("01Asd2100 (qua),02Jan2100 (qui)"),false);
		assertEquals(logic.converteDatas("01Asd1 (qua),02Jan2100 (qui)"),false);
		assertEquals(logic.converteDatas("01Jan2100 (qua), "),false);
		assertEquals(logic.converteDatas(", "),false);
		assertEquals(logic.converteDatas("01Jan2100 (asddasdasdas)))(((), "),false);
	}
	
	/**
	 * Classe que testa o método separaDia 
	 */
	@Test
	public void testeSeparaDia() {
		Logic logic = new Logic();
		
		assertEquals(logic.separaDia("dia"),"dia");
		assertEquals(logic.separaDia("dia teste"),"dia");
	}
	
	/**
	 * Classe que testa o método converteDia 
	 */
	@Test
	public void testeConverteDia() {
		Logic logic = new Logic();

		assertEquals(logic.converteDia("1"),1);
		assertEquals(logic.converteDia("0"),-1);
		assertEquals(logic.converteDia("ASD"),-1);
	}
	
	/**
	 * Classe que testa o método converteMes 
	 */
	@Test
	public void testeConverteMes() {
		Logic logic = new Logic();
		
		assertEquals(logic.converteMes("jan"),0);
		assertEquals(logic.converteMes("fev"),1);
		assertEquals(logic.converteMes("feb"),1);
		assertEquals(logic.converteMes("mar"),2);
		assertEquals(logic.converteMes("abr"),3);
		assertEquals(logic.converteMes("apr"),3);
		assertEquals(logic.converteMes("mai"),4);
		assertEquals(logic.converteMes("may"),4);
		assertEquals(logic.converteMes("jun"),5);
		assertEquals(logic.converteMes("jul"),6);
		assertEquals(logic.converteMes("ago"),7);
		assertEquals(logic.converteMes("aug"),7);
		assertEquals(logic.converteMes("set"),8);
		assertEquals(logic.converteMes("sep"),8);
		assertEquals(logic.converteMes("out"),9);
		assertEquals(logic.converteMes("oct"),9);
		assertEquals(logic.converteMes("oct"),9);
		assertEquals(logic.converteMes("nov"),10);
		assertEquals(logic.converteMes("dez"),11);
		assertEquals(logic.converteMes("dec"),11);
		assertEquals(logic.converteMes("asdasd"),-1);
	}
	
	/**
	 * Classe que testa o método converteAno 
	 */
	@Test
	public void testeConverteAno() {
		Logic logic = new Logic();

		assertEquals(logic.converteAno("3000"),3000);
		assertEquals(logic.converteAno("2000"),-1);
	}
	
	/**
	 * Classe que testa o método validaData 
	 */
	@Test
	public void testeValidaData() {
		Logic logic = new Logic();

		assertEquals(logic.validaData(28, 1, 2019),true);
		assertEquals(logic.validaData(28, 1, 2018),false);
		assertEquals(logic.validaData(29, 1, 2019),false);
	}
	
	/**
	 * Classe que testa o método testeDataAnterior 
	 */
	@Test
	public void testeDataAnterior() {
		Logic logic = new Logic();
		
		Calendar dataTeste = Calendar.getInstance();
		dataTeste.set(1000, 0, 1);
		assertEquals(logic.dataAnterior(dataTeste),-1);
		
		dataTeste = Calendar.getInstance();
		assertEquals(logic.dataAnterior(dataTeste),0);
		
		dataTeste.set(9999999, 0, 1);
		assertEquals(logic.dataAnterior(dataTeste),1);
	}
	
	/**
	 * Classe que testa o método verificaOrdemData
	 */
	@Test
	public void testeVerificaOrdemData() {
		Logic logic = new Logic();
		
		Calendar data1 = Calendar.getInstance();
		data1.set(1000, 0, 1);
		Calendar data2 = Calendar.getInstance();
		data2.set(1000, 0, 2);
		
		logic.getDias().add(data1);
		logic.getDias().add(data2);
		
		assertEquals(logic.verificaOrdemData(),true);
		
		logic.getDias().remove(0);
		logic.getDias().add(data1);
		
		assertEquals(logic.verificaOrdemData(),false);
	}
	
	/**
	 * Classe que testa o método calculaIntervalo
	 */
	@Test
	public void testeCalculaIntervalo() {
		Logic logic = new Logic();
		
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		
		data1.set(2019, 1, 17);
		data2.set(2019, 1, 18);
		logic.getDias().add(data1);
		logic.getDias().add(data2);
		
		logic.calculaIntervalo();
		assertEquals(logic.getDiasSemana(),1);
		assertEquals(logic.getDiasFimSemana(),1);
		
		logic.setDiasSemana(0);
		logic.setDiasFimSemana(0);
		logic.getDias().remove(1);
		logic.getDias().remove(0);
		
		data1.set(2019, 1, 17);
		data2.set(2019, 2, 2);
		logic.getDias().add(data1);
		logic.getDias().add(data2);
		
		logic.calculaIntervalo();
		assertEquals(logic.getDiasSemana(),10);
		assertEquals(logic.getDiasFimSemana(),4);
		
		logic.setDiasSemana(0);
		logic.setDiasFimSemana(0);
		logic.getDias().remove(1);
		logic.getDias().remove(0);
		
		data1.set(2018, 11, 30);
		data2.set(2019, 1, 3);
		logic.getDias().add(data1);
		logic.getDias().add(data2);
		
		logic.calculaIntervalo();
		assertEquals(logic.getDiasSemana(),25);
		assertEquals(logic.getDiasFimSemana(),11);
	}
	
	/**
	 * Classe que testa o método calculaCustos
	 */
	@Test
	public void testeCalculaCustos() {
		Logic logic = new Logic();
		
		logic.setDiasSemana(1);
		logic.setDiasFimSemana(1);
		logic.setPassageiros(7);
		logic.calculaCustos();
		
		assertEquals(logic.getMenorCusto().getCusto(),1230);
		
		logic.setPassageiros(3);
		logic.calculaCustos();
		assertEquals(logic.getMenorCusto().getCusto(),410);
	}
	
	/**
	 * Classe que testa o método escreveResultado
	 */
	@Test
	public void testeEscreveResultado() {
		Logic logic = new Logic();
		
		String saidaTeste = "saidateste.txt";
		logic.setMenorCusto(new Custo("TesteLocadora","TesteCarro",1000));
		logic.escreveResultado(saidaTeste);
		
		try {
			FileReader arquivo = new FileReader(saidaTeste);
			BufferedReader lerArquivo = new BufferedReader(arquivo);
		 
			String linha = lerArquivo.readLine();
			assertEquals(linha,"TesteCarro:TesteLocadora");

			while (linha != null) {
				linha = lerArquivo.readLine();
			}
			
			assertEquals(linha,null);
			arquivo.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
	        e.getMessage());
	    }
	}
	
	/**
	 * Classe que testa os métodos Get e Set passageiros
	 */
	@Test
	public void testeGetSetPassageiros() {
		Logic logic = new Logic();
		logic.setPassageiros(10);
		assertEquals(logic.getPassageiros(),10);
		assertNotEquals(logic.getPassageiros(),1);
	}
	
	/**
	 * Classe que testa os métodos Get e Set Locadoras
	 */
	@Test
	public void testeGetSetLocadoras() {
		Logic logic = new Logic();
		List<Locadora> listaLocadoras = new ArrayList<Locadora>();
		listaLocadoras.add(new Locadora("Teste1",1,"Carro1",10,20));
		listaLocadoras.add(new Locadora("Teste2",2,"Carro2",20,40));
		
		logic.setLocadoras(listaLocadoras);
		
		assertEquals(logic.getLocadoras().size(),2);
		assertEquals(logic.getLocadoras().get(0).getNomeLocadora(),"Teste1");
		assertEquals(logic.getLocadoras().get(1).getNomeLocadora(),"Teste2");
	}
	
	/**
	 * Classe que testa os métodos Get e Set Dias
	 */
	@Test
	public void testeGetSetDias() {
		Logic logic = new Logic();
		List<Calendar> listaDias = new ArrayList<Calendar>();
		Calendar data1 = Calendar.getInstance();
		data1.set(2000, 1, 1);
		
		Calendar data2 = Calendar.getInstance();
		data2.set(2001, 2, 2);
		
		listaDias.add(data1);
		listaDias.add(data2);
		
		logic.setDias(listaDias);
		
		assertEquals(logic.getDias().size(),2);
		assertEquals(logic.getDias().get(0).get(Calendar.YEAR),2000);
		assertEquals(logic.getDias().get(1).get(Calendar.YEAR),2001);
	}
	
	/**
	 * Classe que testa os métodos Get e Set DiasSemana
	 */
	@Test
	public void testeGetSetDiasSemana() {
		Logic logic = new Logic();
		logic.setDiasSemana(10);
		assertEquals(logic.getDiasSemana(),10);
		assertNotEquals(logic.getDiasSemana(),1);
	}
	
	/**
	 * Classe que testa os métodos Get e Set DiasFimSemana
	 */
	@Test
	public void testeGetSetDiasFimSemana() {
		Logic logic = new Logic();
		logic.setDiasFimSemana(10);
		assertEquals(logic.getDiasFimSemana(),10);
		assertNotEquals(logic.getDiasFimSemana(),1);
	}
	
	/**
	 * Classe que testa os métodos Get e Set MenorCusto
	 */
	@Test
	public void testeGetSetMenorCusto() {
		Logic logic = new Logic();
		logic.setMenorCusto(new Custo("Locadora1","Carro1",100));
		
		assertEquals(logic.getMenorCusto().getCusto(),100);
		assertEquals(logic.getMenorCusto().getNomeLocadora(),"Locadora1");
		assertEquals(logic.getMenorCusto().getTipoCarro(),"Carro1");
	}
}
