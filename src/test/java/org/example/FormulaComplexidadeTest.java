package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class) //activate Mockito
class FormulaComplexidadeTest {

  @Mock
  private HMD hmdSolucao;

  @InjectMocks
  private FormulaComplexidade formulaComplexidade;

  @BeforeEach
  public void setUp() {
    Figura1 figura1 = new Figura1();
    hmdSolucao = figura1.hmd();
    formulaComplexidade = new FormulaComplexidade(hmdSolucao);
  }

  @Test
  void validarFrequenciaN() {

    Entidade entidade = new Entidade("4", null);

    double valor = formulaComplexidade.frequenciaN(entidade);
    assertEquals(5.0, valor);

    double valor2 = formulaComplexidade.frequenciaN(entidade);
    assertEquals(5.0, valor2);
  }

  @Test
  void validarFrequenciaM() {

    //Modulo: A1
    Modulo moduloA1 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];

    //Modulo: A3
    Modulo moduloA3 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];

    //Modulo: A4
    Modulo submoduloA4 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
    Modulo moduloA4 = (Modulo) submoduloA4.getSubmodulos().toArray()[0];

    //Modulo: A2
    Modulo submoduloA2 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];
    Modulo moduloA2 = (Modulo) submoduloA2.getSubmodulos().toArray()[0];

        /*double valorA1 = formulaComplexidade.frequenciaM(moduloA1);
        double valorA3 = formulaComplexidade.frequenciaM(moduloA3);
        double valorA4 = formulaComplexidade.frequenciaM(moduloA4);
        double valorA2 = formulaComplexidade.frequenciaM(moduloA2);

        Assertions.assertEquals(1.0, valorA1);
        Assertions.assertEquals(10.0, valorA3);
        Assertions.assertEquals(7.0, valorA4);
        Assertions.assertEquals(9.0, valorA2);*/
  }

  @Test
  void validarLCA() {

    Entidade entidade7 = new Entidade("7", null);

    List<Modulo> modulos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(
        hmdSolucao.getModulos());

    //7, 4 = A0
    Entidade entidade4 = new Entidade("4", null);
    Modulo modulo1 = formulaComplexidade.lca(entidade7, entidade4, modulos);
    assertEquals("A0", modulo1.getNome());

    // 7, 10 = A3
    Entidade entidade10 = new Entidade("10", null);
    Modulo modulo2 = formulaComplexidade.lca(entidade7, entidade10, modulos);
    assertEquals("A3", modulo2.getNome());

    // 7, 6 = A4
    Entidade entidade6 = new Entidade("6", null);
    Modulo modulo3 = formulaComplexidade.lca(entidade7, entidade6, modulos);
    assertEquals("A4", modulo3.getNome());

  }

  @Test
  void validarRelDepth() {

    Entidade entidade = new Entidade("7", null);

    //Modulo: A3
    Modulo moduloA3 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
    assertEquals(1, formulaComplexidade.relativaProfundidade(entidade, moduloA3));

    //Modulo: A4
    Modulo submoduloA4 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
    Modulo moduloA4 = (Modulo) submoduloA4.getSubmodulos().toArray()[0];
    assertEquals(0, formulaComplexidade.relativaProfundidade(entidade, moduloA4));

    //Modulo: A0
    Modulo moduloA0 = hmdSolucao.getModulos().get(0);
    assertEquals(2, formulaComplexidade.relativaProfundidade(entidade, moduloA0));

  }

  @Test
  void validarLogaritmo() {

    assertEquals(Double.NEGATIVE_INFINITY, formulaComplexidade.log(2, 0));
    assertEquals(0, formulaComplexidade.log(2, 1));
    assertEquals(7.0, formulaComplexidade.log(2, 128));
    assertEquals(4.0, formulaComplexidade.log(5, 625));
    assertEquals(1.4999999999999998, formulaComplexidade.log(100, 1000));
    assertEquals(2.0, formulaComplexidade.log(7, 49));

  }

  @Test
  void validarLogN() {

    assertEquals(1.0, formulaComplexidade.l(0));
    assertEquals(3.328897414907779, formulaComplexidade.l(1));
    assertEquals(4.584962500721156, formulaComplexidade.l(2));
    assertEquals(8.558017846869864, formulaComplexidade.l(12));


  }

  @Test
  void calcularCm() {

    //Modulo: A0
    Modulo moduloA0 = hmdSolucao.getModulos().get(0);

    //Modulo: A1
    Modulo moduloA1 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];

    int valor = formulaComplexidade.calculaCm(moduloA0);
    int valor2 = formulaComplexidade.calculaCm(moduloA1);

    assertEquals(4, valor);
    assertEquals(1, valor2);

  }

  @Test
  void calcularMm() {

    //Modulo: A0
    Modulo moduloA0 = (Modulo) hmdSolucao.getModulos().get(0);

    //Modulo: A1
    Modulo moduloA1 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];

    //Modulo: A2
    Modulo submodulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];
    Modulo moduloA2 = (Modulo) submodulo.getSubmodulos().toArray()[0];

    int valor = formulaComplexidade.calculaMm(moduloA0);
    int valor2 = formulaComplexidade.calculaMm(moduloA1);
    int valor3 = formulaComplexidade.calculaMm(moduloA2);

    assertEquals(4, valor);
    assertEquals(1, valor2);
    assertEquals(0, valor3);

  }

  @Test
  void calcularQuantidadeModulos() {

    Integer count = hmdSolucao.getModulos().size();
    assertEquals(5, count);
  }
}
