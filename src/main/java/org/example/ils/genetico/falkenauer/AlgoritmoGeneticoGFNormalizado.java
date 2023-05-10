package org.example.ils.genetico.falkenauer;


import org.example.ils.core.Exibicao;
import org.example.ils.core.Parametro;
import org.example.ils.core.Problema;
import org.example.ils.core.SolucaoAbstract;
import org.example.ils.genetico.AlgoritmoGeneticoAbstract;
import org.example.ils.genetico.GeradorSolucaoGenetico;
import org.example.ils.genetico.Populacao;

public class AlgoritmoGeneticoGFNormalizado extends AlgoritmoGeneticoAbstract {
	
	public AlgoritmoGeneticoGFNormalizado(
		Problema problema,
		Exibicao exibicao,
		Parametro param)
	throws Exception
	{
		super(problema,exibicao, param);
	};

	/**
	 * Inicializa a população randomicamente
	 */
	@Override
	public void iniciaPopulacao(Populacao populacao, int tamPopulacao)
	{
		for (int i = 0; i < tamPopulacao; i++) {
			SolucaoAbstract novaSolucao = GeradorSolucaoGenetico.getSolucaoGeneticoGFRandom();
			double fitness = this.calculador.evaluate(novaSolucao);
			novaSolucao.setLocation(this.evaluation);
			novaSolucao.setFitness(fitness);
			this.evaluation++;
			
			/*
			try {
				Verificador.validaSolucao(this, novaSolucao);
			}catch (Exception e) {
				System.out.println("ERRO na geração da população.");
			}
			*/
			
			populacao.add(novaSolucao);
		}
	}

	/**
	 * Seleciona os elementos da população para reprodução
	 */
	@Override
	protected void selecaoElite(Populacao populacao, Populacao populacaoFilha) 
	{
		populacaoFilha.addMelhores(populacao, this.tamanhoElite);
	}
	
	/**
	 * Substitui os piores elementos da população
	 */
	protected void substituiPioresAleatorio(Populacao populacao, Populacao populacaoFilha) 
	{	
		for (int i = 0; i < this.tamanhoDescarte; i++) {
			SolucaoAbstract novaSolucao = GeradorSolucaoGenetico.getSolucaoGeneticoGFRandom();
			double fitness = this.calculador.evaluate(novaSolucao);
			novaSolucao.setLocation(this.evaluation);
			novaSolucao.setFitness(fitness);
			this.evaluation++;
			populacaoFilha.add(novaSolucao);
		}
	}
	
	/**
	 * Substitui os piores elementos da população
	 */
	@Override
	protected void substituiPioresMutacao(Populacao populacao, Populacao populacaoFilha) 
	{
		int contador = 0;
		while (contador < this.tamanhoDescarte) {
			int posicao = GeradorSolucaoGenetico.randInt(0, this.tamanhoDescarteSelecao);
			SolucaoGeneticoGF solucao = (SolucaoGeneticoGF)populacao.get(posicao);
			this.operadorMutacao.descartePorMovimentacoes(solucao);
			contador++;
		}
	}
	
	@Override
	protected void otimizacao(SolucaoAbstract[] filhos) 
	{
		// Atualizacao do fitness
		double fitness0 = this.calculador.evaluate(filhos[0]);
		double fitness1 = this.calculador.evaluate(filhos[1]);
		filhos[0].setFitness(fitness0);
		filhos[0].setLocation(++this.evaluation);
		filhos[1].setFitness(fitness1);
		filhos[1].setLocation(++this.evaluation);
	}
	

	@Override
	public String getNomeAlgoritmo() 
	{
		return "Algoritmo Genético GF Normalizado";
	}	
	
	/**
	 * Executa o cálculo de fitness
	 */
	protected void novaGeracao(Populacao populacaoFilha, SolucaoAbstract[] filhos) 
	{
		int[] qtdIguaisA = populacaoFilha.addVerificandoFitnessIguais(filhos[0]);
		
		// se existirem uma quantidade excessiva do mesmo fitness introduz diversidade por mutacao
		if (this.tamanhoSolucoesIguaisMaxima>0 && qtdIguaisA[0]>this.tamanhoSolucoesIguaisMaxima) {
			//this.operadorMutacao.diversificaPorMovimentacoes((SolucaoGeneticoGF)filhos[0]);
			this.operadorMutacao.diversificaPorMovimentacoesNormalizado((SolucaoGeneticoGF)filhos[0]);
			double fitness0 = this.calculador.evaluate(filhos[0]);
			filhos[0].setFitness(fitness0);
			filhos[0].setLocation(++this.evaluation);
			populacaoFilha.reposicionaSolucao(qtdIguaisA[1]);
		}
		
		// se existirem uma quantidade excessiva do mesmo fitness introduz diversidade por mutacao
		int[] qtdIguaisB = populacaoFilha.addVerificandoFitnessIguais(filhos[1]);
		if (this.tamanhoSolucoesIguaisMaxima>0 && qtdIguaisB[0]>this.tamanhoSolucoesIguaisMaxima) {
			//this.operadorMutacao.diversificaPorMovimentacoes((SolucaoGeneticoGF)filhos[1]);
			this.operadorMutacao.diversificaPorMovimentacoesNormalizado((SolucaoGeneticoGF)filhos[1]);
			double fitness1 = this.calculador.evaluate(filhos[1]);
			filhos[1].setFitness(fitness1);
			filhos[1].setLocation(++this.evaluation);
			populacaoFilha.reposicionaSolucao(qtdIguaisB[1]);
		}
	}	
	
}
