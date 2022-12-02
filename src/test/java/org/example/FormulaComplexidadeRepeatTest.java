package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.model.HMD;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FormulaComplexidadeRepeatTest {

  @Mock
  private HMD hmdSolucao;

  @Test
  void calcularFormulaComplexidadeFigura18() {

    Figura18 figura = new Figura18();
    hmdSolucao = figura.hmd();
    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    System.out.println("173.4529015028225");
    System.out.println("1: "+formulaComplexidade.executa());
    System.out.println("2: "+formulaComplexidade.executa());

    FormulaComplexidade formulaComplexidade3 = new FormulaComplexidade(hmdSolucao);

    System.out.println("3: "+formulaComplexidade3.executa());

  }


}
