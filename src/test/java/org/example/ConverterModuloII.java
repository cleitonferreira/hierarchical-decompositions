package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.example.ils.core.Problema;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class ConverterModuloII {

  private static HMD hmdSolucao;

  public static void main(String[] args) {
    //Problema problema = getProblemaFigura18();
    Problema problema = getProblemaFigura21();

    /*int[] originalPackages = {0, 0, 1, 1, 2, 2};*/

    //int[] valores = {0, 0, 0, 0, 0, 1, 1, 1, 1};
    //int[] valores = {1, 1, 1, 1, 1, 0, 0, 0, 0};
    int[] valores = {0, 1, 1, 2, 3, 0, 3, 3, 0}; //TODO posicao em cada modulo

    //{0, 0}; modulo 0 tem submodulo
    //{0, 1}; modulo 1 tem submodulo

    //int[] submodulos = {0, 1};
    //int[] submodulos = {0, 0};
    //int[] submodulos = {0, 1, 1, 2, 3}; //TODO posicao em cada submodulo
    int[] submodulos = {0, 0, 0, 1};

    HMD hmd = converterProblemaParaHMD(problema, valores, submodulos);
    System.out.println(hmd);

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmd);
    double valorFormulaComplexidade = formulaComplexidade.executa();
    System.out.println("FormulaComplexidade: "+valorFormulaComplexidade);
  }

  private static HMD converterProblemaParaHMD(Problema problema, int[] valores, int[] submodulos) {
    Modulo modulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();

    // Modulo A0
    modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "modulo-0", null);
    modulos.add(modulo);

    for (int a = 0; a < valores.length; a++) {

        for (int b = 0; b < submodulos.length; b++) {

          int verificaModulo = getVerificaModulo(modulos, b);

          if (verificaModulo == -1 && submodulos[b] == 0 && valores[a] == b) {
            modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, valores[a]),
                String.valueOf("modulo-" + b), null);
            modulos.add(modulo);
          } else if (verificaModulo == -1 && submodulos[b] == 1 && valores[a] == b) { //(verificaModulo == 0 && valores[b] != submodulos[b] && somaModulos == 0)
            Modulo submodulo = new Modulo(getListaEntidadesPorModulo(problema, valores, valores[a]),
                String.valueOf("submodulo-" + b), null);
            modulo.setSubmodulos(Arrays.asList(submodulo));
          }

        }
    }
    return new HMD(modulos);
  }

  public static int getVerificaModulo(List<Modulo> modulos, int valor) {

    int retorno = -1;

    for (Modulo modulo : modulos) {
      if (Objects.nonNull(modulo)){
        if (modulo.getNome().equals("modulo-"+valor)) {
          retorno = 0;
        }
      }
      if (Objects.nonNull(modulo.getSubmodulos())){
        for (Modulo submodulo : modulo.getSubmodulos()) {
          if (submodulo.getNome().equals("submodulo-"+valor)) {
            retorno = 1;
          }
        }
      }
    }

    return retorno;
  }

  private static List<Entidade> getListaEntidadesPorModulo(Problema problema, int[] valores,
      int modulo) {
    HMD hmd = problema.getHMD();
    List<Entidade> listaEntidades = new ArrayList<>();
    for (int a = 0; a < valores.length; a++) {
      if (Objects.nonNull(problema.getClassCount())) {
        for (int i = 0; i < problema.getClassCount(); i++) {
            if ((valores[i] == modulo) && modulo == a) {

              if (Objects.nonNull(hmd.getModulos())) {
                for (Modulo m : hmd.getModulos()){
                  if (Objects.nonNull(m.getListaEntidades())) {
                    for (Entidade em : m.getListaEntidades()){
                      if (em.getNome().equals(String.valueOf(i))){
                        listaEntidades.add(em);
                      }
                    }
                  }

                  if (Objects.nonNull(m.getSubmodulos())) {
                    for (Modulo s : m.getSubmodulos()){
                      if (Objects.nonNull(s.getListaEntidades())) {
                        for (Entidade es : s.getListaEntidades()){
                          if (es.getNome().equals(String.valueOf(i))){
                            listaEntidades.add(es);
                          }
                        }
                      }
                    }
                  }
                }
              }

            }
        }
      }
    }
    return listaEntidades;
  }

  public static Problema getProblemaFigura18() {
    int numClasses = 9;
    int numPackages = 1;
    int[] originalPackages = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] originalClasses = {9, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] listaDependenciasPara =
        {
            {1, 2, 3, 4, 0, 0, 0, 0, 0},
            {2, 3, 4, 5, 0, 0, 0, 0, 0},
            {3, 4, 6, 0, 0, 0, 0, 0, 0},
            {4, 7, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0, 0},
            {6, 7, 8, 0, 0, 0, 0, 0, 0},
            {7, 8, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasPara =
        {
            4, 4, 3, 2, 1, 3, 2, 1, 0
        };

    int[][] listaDependenciasDe =
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 5, 0, 0, 0, 0, 0, 0, 0},
            {3, 4, 5, 6, 0, 0, 0, 0, 0},
            {5, 6, 7, 0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasDe =
        {
            0, 1, 2, 3, 4, 1, 2, 4, 3
        };

    Problema problema = new Problema(
        "Figura18",
        "",
        numClasses,
        numPackages,
        originalClasses,
        originalPackages,
        listaDependenciasPara,
        qtdDependenciasPara,
        listaDependenciasDe,
        qtdDependenciasDe,
        hmdSolucao
    );

    return problema;
  }

  public static Problema getProblemaFigura21() {
    int numClasses = 9;
    int numPackages = 2;
    int[] originalPackages = {0, 0, 0, 0, 1, 1, 1, 1, 1};
    int[] originalClasses = {4, 5, 0, 0, 0, 0, 0, 0, 0};

    Figura figura = new Figura21();

    int[][] listaDependenciasPara =
        {
            {6, 7, 8, 0, 0, 0, 0, 0, 0},
            {7, 8, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 2, 3, 4, 0, 0, 0, 0, 0},
            {2, 3, 4, 5, 0, 0, 0, 0, 0},
            {3, 4, 6, 0, 0, 0, 0, 0, 0},
            {4, 7, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasPara =
        {
            3, 2, 1, 0, 4, 4, 3, 2, 1
        };

    int[][] listaDependenciasDe =
        {
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {5, 6, 3, 4, 0, 0, 0, 0, 0},
            {5, 6, 7, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasDe =
        {
            1, 2, 4, 3, 0, 1, 2, 3, 4
        };

    Problema problema = new Problema(
        "Figura21",
        "",
        numClasses,
        numPackages,
        originalClasses,
        originalPackages,
        listaDependenciasPara,
        qtdDependenciasPara,
        listaDependenciasDe,
        qtdDependenciasDe,
        figura.hmd()
    );

    return problema;
  }

}
