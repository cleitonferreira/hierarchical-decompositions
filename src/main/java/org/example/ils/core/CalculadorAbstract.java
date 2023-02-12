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
   * Avalia o fitness de uma solução
   */
  public abstract double evaluate(SolucaoAbstract s);

  /**
   * Avalia a solução
   */
  public abstract RetornoSolucao evaluate(SolucaoAbstract s, int[] valores);

  public Problema getProblema() {
    return problema;
  }

}