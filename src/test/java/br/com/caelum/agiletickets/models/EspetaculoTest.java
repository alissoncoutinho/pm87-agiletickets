package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import antlr.collections.List;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	@Test
	public void deveCriarApenasUmaSessaoCasoADataInicioSejaIgualADataFim() throws Exception 
	{
		Espetaculo circoSolei = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		Periodicidade semanal= Periodicidade.SEMANAL;
		java.util.List<Sessao> sessoesDiarias = circoSolei.criaSessoes(inicio, fim, horario, diaria);
		Assert.assertEquals(1,sessoesDiarias.size());
		java.util.List<Sessao> sessoesSemanais = circoSolei.criaSessoes(inicio, fim, horario, semanal);
		Assert.assertEquals(1,sessoesSemanais.size());
	}
	@Test
	public void deveCriar7SessoesCasoADataInicoSeja01012014EaDataFinalSeja07012014EAPeriodicidadeDiaria() throws Exception 
	{
		Espetaculo circoSolei = new Espetaculo();
		LocalDate inicio = new LocalDate(2014,01,01);
		LocalDate fim = new LocalDate(2014,01,07);
		LocalTime horario= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		java.util.List<Sessao> sessoesDiarias = circoSolei.criaSessoes(inicio, fim, horario, diaria);
		Assert.assertEquals(7,sessoesDiarias.size());
		
	}
	
	@Test(expected=Exception.class)
	public void deveRetornarMensagemDeAvisoCasoADataFinalSejaMenorDataInicial() throws Exception 
	{
		Espetaculo circoSolei = new Espetaculo();
		LocalDate inicio = new LocalDate(2014,1,10);
		LocalDate fim = new LocalDate(2014,1,8);
		LocalTime horario= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		java.util.List<Sessao> sessoesDiarias = circoSolei.criaSessoes(inicio, fim, horario, diaria);
		
		
	}
	
}
