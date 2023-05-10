package org.example.ils.busca;

import java.util.Arrays;
import org.example.ils.construtivo.SolucaoCNM;
import org.example.ils.core.SolucaoAbstract;
import org.example.ils.metaheuristica.SolucaoILS;

/**
 * Representação de uma solução com inteiros
 */
public class SolucaoHC implements SolucaoAbstract {

	// representa o número da geração em que a solução foi encontrada
	private int location;

	// valor de fitness da solução
	private double fitness = 0.00;

	// parte básica da representação da solução
	private int[] valores;

	private int[] grupos;

	// quantidade dos grupos
	private int totalGrupos;

	// quantidade de cada item nos grupos
	private int[] qtdItens;
	
	public SolucaoHC(SolucaoHC s) {
		int[] valores = s.getValores();
		this.valores = Arrays.copyOf(valores, valores.length);

		int[] qtdItens = s.getQtdItens();
		this.qtdItens = Arrays.copyOf(qtdItens, qtdItens.length);
		this.totalGrupos = s.getTotalGrupos();
		this.fitness = s.getFitness();
		this.location = s.getLocation();
		this.grupos = Arrays.copyOf(s.getGrupos(), s.getGrupos().length);
	}

	public SolucaoHC(SolucaoCNM s) {
		int[] valores = s.getValores();
		this.valores = Arrays.copyOf(valores, valores.length);

		int[] qtdItens = s.getQtdItens();
		this.qtdItens = Arrays.copyOf(qtdItens, qtdItens.length);
		this.totalGrupos = s.getTotalGrupos();
		this.fitness = s.getFitness();
		this.location = s.getLocation();
		this.grupos = Arrays.copyOf(s.getGrupos(), s.getGrupos().length);
	}

 	public SolucaoHC(SolucaoILS s) {
		int[] valores = s.getValores();
		this.valores = Arrays.copyOf(valores, valores.length);

		int[] qtdItens = s.getQtdItens();
		this.qtdItens = Arrays.copyOf(qtdItens, qtdItens.length);
		this.totalGrupos = s.getTotalGrupos();
		this.fitness = s.getFitness();
		this.location = s.getLocation();
		this.grupos = Arrays.copyOf(s.getGrupos(), s.getGrupos().length);
	}

	public SolucaoHC(int[] valores, int[] qtdItens, int totalGrupos, int[] grupos) {
		this.valores = valores;
		this.qtdItens = qtdItens;
		this.totalGrupos = totalGrupos;
		this.grupos = grupos;
	}

	public SolucaoHC(int[] valores, int[] qtdItens, int totalGrupos, double fitness, int location, int[] grupos) {
		this.valores = valores;
		this.qtdItens = qtdItens;
		this.totalGrupos = totalGrupos;
		this.fitness = fitness;
		this.location = location;
		this.grupos = grupos;
	}

	public SolucaoHC(int tamanho) {
		this.valores = new int[tamanho];
		this.qtdItens = new int[2 * tamanho];
		this.totalGrupos = 0;
		this.grupos = new int[tamanho];
	}
	
	public void setSolucao(int[] valores, int[] qtdItens, int totalGrupos, double fitness, int location) {
		this.valores = valores;
		this.qtdItens = qtdItens;
		this.totalGrupos = totalGrupos;
		this.fitness = fitness;
		this.location = location;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getLocation() {
		return location;
	}

	public int[] getValores() {
		return this.valores;
	}

	public int getTotalItens() {
		return this.valores.length;
	}

	public void setValores(int[] valores) {
		this.valores = valores;
	}

	public void setTotalGrupos(int totalGrupos) {
		this.totalGrupos = totalGrupos;
	}

	public int getTotalGrupos() {
		return totalGrupos;
	}

	public void setQtdItens(int[] qtdItens, int totalGrupos) {
		this.totalGrupos = totalGrupos;
		this.qtdItens = qtdItens;
	}

	public int[] getQtdItens() {
		return qtdItens;
	}

	public int[] getGrupos() {
		return grupos;
	}

	public void setGrupos(int[] grupos) {
		this.grupos = grupos;
	}
	
	/**
	 * Utilizado para exibição dos resultados
	 */
	public String getString() {
		StringBuilder sb = new StringBuilder("");
		if (this.valores.length > 0) {
			sb.append(String.valueOf(this.valores[0]));
		}
		for (int i = 1; i < valores.length; i++) {
			sb.append(",");
			sb.append(String.valueOf(this.valores[i]));
		}
		return sb.toString();
	}

	public String getGruposString() {
		StringBuilder sb = new StringBuilder("");
		if (this.grupos.length > 0) {
			sb.append(String.valueOf(this.grupos[0]));
		}
		for (int i = 1; i < grupos.length; i++) {
			sb.append(",");
			sb.append(String.valueOf(this.grupos[i]));
		}
		return sb.toString();
	}
}
