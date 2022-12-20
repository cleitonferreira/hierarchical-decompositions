package org.example.ils.busca;

import java.util.Arrays;
import java.util.Random;
import org.example.ils.metaheuristica.GeradorSolucaoAbstract;

public class GeradorSolucao extends GeradorSolucaoAbstract {

	/**
	 * Faz com que os grupos tenham numeração sequencial
	 * Complexidade n*n
	 */
	public static void normalizar(SolucaoHC s) 
	{
		int[] valores = s.getValores();
		int[] qtdItens = s.getQtdItens();
		// int qtdGrupos = s.getTotalGrupos();

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

		int min = 0;
		int max = 2;
		int soma = 0;
		Random rand = new Random();
		int[] subgrupos = new int[s.getTotalGrupos()];

		for (int i = 0; i < s.getTotalGrupos(); i++) {
			if (soma < s.getTotalGrupos() && i > 0) {
				subgrupos[i] = rand.nextInt(max - min) + min;
			}
			soma += subgrupos[i];
		}
		s.setGrupos(subgrupos);
		
		// libera a memoria
		valoresOriginal = null;
		qtdItensOriginal = null;
	}
	
		
	public static SolucaoHC getSolucaoHCRandom() 
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

		SolucaoHC solucao = new SolucaoHC(valores, qtdItens, totalGrupos);

		normalizar(solucao);

		return solucao;
	}
}
