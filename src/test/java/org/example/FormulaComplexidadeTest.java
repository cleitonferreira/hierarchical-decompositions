package org.example;


import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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
        formulaComplexidade.setHmdSolucao(hmdSolucao);
    }

    @Test
    void validarFrequenciaN() {

        Entidade entidade = new Entidade("4",null);

        double valor = formulaComplexidade.frequenciaN(entidade);
        Assertions.assertEquals(5.0, valor);
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

        double valorA1 = formulaComplexidade.frequenciaM(moduloA1);
        double valorA3 = formulaComplexidade.frequenciaM(moduloA3);
        double valorA4 = formulaComplexidade.frequenciaM(moduloA4);
        double valorA2 = formulaComplexidade.frequenciaM(moduloA2);

        Assertions.assertEquals(1.0, valorA1);
        Assertions.assertEquals(10.0, valorA3);
        Assertions.assertEquals(7.0, valorA4);
        Assertions.assertEquals(9.0, valorA2);
    }

    @Test
    void validarLCA() {

        //7, 4 = A0
        Modulo modulo1 = formulaComplexidade.lca(new Entidade("7",null), new Entidade("4",null));
        Assertions.assertEquals("A0", modulo1.getNome());

        // 7, 10 = A3
        Modulo modulo2 = formulaComplexidade.lca(new Entidade("7",null), new Entidade("10",null));
        Assertions.assertEquals("A3", modulo2.getNome());

        // 7, 6 = A4
        Modulo modulo3 = formulaComplexidade.lca(new Entidade("7",null), new Entidade("6",null));
        Assertions.assertEquals("A4", modulo3.getNome());

    }

    @Test
    void validarRelDepth() {

        Entidade entidade = new Entidade("7",null);

        //Modulo: A3
        Modulo moduloA3 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
        Assertions.assertEquals(1, formulaComplexidade.relativaProfundidade(entidade, moduloA3));

        //Modulo: A4
        Modulo submoduloA4 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
        Modulo moduloA4 = (Modulo) submoduloA4.getSubmodulos().toArray()[0];
        Assertions.assertEquals(0, formulaComplexidade.relativaProfundidade(entidade, moduloA4));

    }

    @Test
    void validarLogaritmo() {

        Assertions.assertEquals(Double.NEGATIVE_INFINITY, formulaComplexidade.log(2,0));
        Assertions.assertEquals(0, formulaComplexidade.log(2, 1));
        Assertions.assertEquals(7.0, formulaComplexidade.log(2, 128));
        Assertions.assertEquals(4.0, formulaComplexidade.log(5, 625));
        Assertions.assertEquals(1.4999999999999998, formulaComplexidade.log(100, 1000));
        Assertions.assertEquals(2.0, formulaComplexidade.log(7, 49));

    }

    @Test
    void validarLogN() {

        Assertions.assertEquals(Double.NEGATIVE_INFINITY, formulaComplexidade.l(0));
        Assertions.assertEquals(1.0, formulaComplexidade.l(1));
        Assertions.assertEquals(3.328897414907779, formulaComplexidade.l(2));
        Assertions.assertEquals(8.3603559294956, formulaComplexidade.l(12));


    }

    @Test
    void validarGetOutNodes() {

        Entidade entidade = new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8")));

        HashMap<Entidade, Entidade> map = formulaComplexidade.getOutNodes(entidade);

        Assertions.assertEquals(1.0, map.size());

    }

    @Test
    void calcularCm() {

        //Modulo: A0
        Modulo moduloA0 = hmdSolucao.getModulos().get(0);

        //Modulo: A1
        Modulo moduloA1 = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];

        int valor = formulaComplexidade.calculaCm(moduloA0);
        int valor2 = formulaComplexidade.calculaCm(moduloA1);

        Assertions.assertEquals(4, valor);
        Assertions.assertEquals(1, valor2);

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

        Assertions.assertEquals(4, valor);
        Assertions.assertEquals(1, valor2);
        Assertions.assertEquals(0, valor3);

    }
}
