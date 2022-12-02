package org.example.ils.core;

import org.example.ils.metaheuristica.SolucaoILS;

import java.util.TreeMap;
import java.util.TreeSet;

public class Verificador {

	public static boolean validaSolucao(AlgoritmoAbstract algoritmo, SolucaoAbstract solucao, String opcoes)
	throws Exception
	{
		String nomeAlgoritmo = "";
		if (algoritmo != null) {
			nomeAlgoritmo = algoritmo.getNomeAlgoritmo();
			CalculadorAbstract calculador = algoritmo.getCalculador();
			Problema problema = calculador.getProblema();
			
			if (opcoes.indexOf("sem_fitness")<0) {		
				double fitnessOk = calculador.evaluate(solucao, solucao.getValores());
				if (Math.abs(solucao.getFitness() - fitnessOk) > 0.0000000001) {
					throw new Exception("ERRO: Fitness da solu��o est� incorreto. Obtido=" + solucao.getFitness() + ". Esperado=" + fitnessOk);
				}
			}
			int totalItensOk = problema.getTamanho();
			if (solucao.getTotalItens() != totalItensOk) {
				throw new Exception("ERRO: Quantidade de m�dulos da solu��o est� incorreto.");
			}
			if (solucao instanceof SolucaoILS) {
				if (((SolucaoILS)solucao).getTotalGrupos() > totalItensOk || ((SolucaoILS)solucao).getTotalGrupos() < 1) {
					throw new Exception("ERRO: Quantidade de clusters da solu��o est� incorreto.");
				}
			}
		}
		
		int[] valores = solucao.getValores();
		
		int totalItens = solucao.getTotalItens();
		TreeSet<Integer> clusters = new TreeSet<Integer>();
		TreeMap<Integer,Integer> qtdModulos = new TreeMap<Integer,Integer>();
		for (int i = 0; i < totalItens; i++) {
			int cluster = valores[i];
			clusters.add(cluster);
			Integer qtd = qtdModulos.get(cluster);
			if (qtd==null) qtd = 0;
			qtdModulos.put(cluster, qtd+1);
			if (valores[i] > valores.length) {
				throw new Exception("ERRO: Solu��o cont�m cluster maior do que " + valores.length);
			}
		}
		
		int[] qtdItens;
		int totalGrupos;
		if (solucao instanceof SolucaoILS) {
			qtdItens = ((SolucaoILS)solucao).getQtdItens();
			totalGrupos = ((SolucaoILS)solucao).getTotalGrupos();
		} else {
			qtdItens = null;
			totalGrupos = 0;
		}
		if (qtdItens != null) {
			for (Integer cluster : clusters) {
				int qtd = qtdModulos.get(cluster);
				if (qtd != qtdItens[cluster]) {
					throw new Exception("ERRO: Solu��o cont�m n�mero de m�dulos diferente da vari�vel qtdItens[" + cluster + "]=" + qtdItens[cluster] + " <> " + qtd);
				}
			}
		
			if (clusters.size() != totalGrupos) {
				throw new Exception("ERRO: " + nomeAlgoritmo + " - Solu��o cont�m n�mero de clusters diferente da vari�vel totalGrupos");
			}
		}
		
		return true;
	}

	public static boolean validaSolucao(AlgoritmoAbstract algoritmo, SolucaoAbstract solucao)
	throws Exception
	{
		return validaSolucao(algoritmo, solucao, "");
	}

}
