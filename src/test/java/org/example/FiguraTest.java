package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.model.HMD;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FiguraTest {

  @Mock
  private HMD hmdSolucao;

  @Test
  void calcularFormulaComplexidadeFigura1() {

    Figura1 figura = new Figura1();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 398.5;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura2() {

    Figura2 figura = new Figura2();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 348.7;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura3() {

    Figura3 figura = new Figura3();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 372.2;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura18() {

    Figura18 figura = new Figura18();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 208.37;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura19() {

    Figura19 figura = new Figura19();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 249.91;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura20() {

    Figura20 figura = new Figura20();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 230.0;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura21() {

    Figura21 figura = new Figura21();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 202.3;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura23() {

    Figura23 figura = new Figura23();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 350.7;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura24() {

    Figura24 figura = new Figura24();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 342.1;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura26() {

    Figura26 figura = new Figura26();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 531.9;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura27() {

    Figura27 figura = new Figura27();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 468.7;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura31() {

    Figura31 figura = new Figura31();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 414.6;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura32() {

    Figura32 figura = new Figura32();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 426.5;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura33() {

    Figura33 figura = new Figura33();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 396.6;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

  @Test
  void calcularFormulaComplexidadeFigura34() {

    Figura34 figura = new Figura34();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorExpected = 408.4;

    double valorFormulaComplexidade = formulaComplexidade.executa();
    assertEquals(valorExpected, valorFormulaComplexidade);
  }

}
