package org.example.ils.metaheuristica;

import java.util.Arrays;
import java.util.Random;

public class GeradorSolucao extends GeradorSolucaoAbstract {

	/**
	 * Faz com que os grupos tenham numeração sequencial
	 * Complexidade n*n
	 */
	public static void normalizar(SolucaoILS s) 
	{
		int[] valores = s.getValores();
		int[] qtdItens = s.getQtdItens();


		// cópia do vetor que será normalizada
		int[] valoresOriginal = Arrays.copyOf(valores, valores.length);
		int[] qtdItensOriginal = Arrays.copyOf(qtdItens, qtdItens.length);
		
		// percorre o vetor com os grupos marcados como setados no vetor de valores
		for (int i = 0; i < qtdItensOriginal.length; i++) {
		// for (int i = 0; i < qtdGrupos; i++) {
			// se não existir ocorrencia do grupo i na expressão
			if (qtdItensOriginal[i] == 0) {
				// percorre os valores diminuindo em 1 os grupos para preencher a ausência do grupo
				for (int j = 0; j < valores.length; j++) {
					if (valoresOriginal[j] > i) {
						qtdItens[valores[j]]--;
						valores[j]--;
						qtdItens[valores[j]]++;
					}
				}
			}
		}

		/*Remover valores repetidos*/
		int totalGrupos = Arrays.stream(valores).distinct().toArray().length;

		Random rand = new Random();
		int[] subgrupos = new int[totalGrupos];

		for (int i = 0; i < totalGrupos; i++) {
			if (i > 1) {
				subgrupos[i] = rand.nextInt(i);
			}
		}
		s.setGrupos(subgrupos);
		
		// libera a memoria
		valoresOriginal = null;
		qtdItensOriginal = null;
	}
	
	public static SolucaoILS getSolucaoILSRandom() 
	{
		int[] valores = new int[tamanhoSolucao];
		int[] qtdItens = new int[2 * tamanhoSolucao];
		int totalGrupos = 0;
		
		for (int i = 0; i < tamanhoSolucao; i++) {
			valores[i] = randInt();
			if (qtdItens[valores[i]] == 0) {
				totalGrupos++;
			}
			qtdItens[valores[i]]++;
		}
	
		SolucaoILS solucao = new SolucaoILS(valores, qtdItens, totalGrupos);
	
		normalizar(solucao);
	
		return solucao;
	}

}
