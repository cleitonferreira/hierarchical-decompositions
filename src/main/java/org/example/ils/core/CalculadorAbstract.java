package org.example.ils.core;

public abstract class CalculadorAbstract {

	protected Problema problema = null;

	/**
	 * Inicializa o calculator com os dados do problema
	 */
	public CalculadorAbstract(Problema problema) {
		this.problema = problema;
	}

	/**
	 * Avalia o fitness de uma solu��o
	 */
	public abstract double evaluate(SolucaoAbstract solucaoAbstract);
	
	/**
	 * Avalia a solu��o
	 */
	public abstract double evaluate(SolucaoAbstract solucaoAbstract, int[] valores);
	
	public Problema getProblema() {
		return problema;
	}
	
}