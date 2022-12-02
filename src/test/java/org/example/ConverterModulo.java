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

public class ConverterModulo {

  private static HMD hmdSolucao;

  public static void main(String[] args) {
    Problema problema = getProblemaFigura18();

    int[] originalPackages = {0, 0, 1, 1, 2, 2};


    int[] valores = {0, 0, 0, 0, 0, 1, 1, 1, 1};

    //{0, 0}; modulo 0 tem submodulo
    //{0, 1}; modulo 1 tem submodulo

    //int[] submodulos = {0, 1};
    int[] submodulos = {0, 0};

    HMD hmd = converterProblemaParaHMD(problema, valores, submodulos);
    System.out.println(hmd);
  }

  private static HMD converterProblemaParaHMD(Problema problema, int[] valores, int[] submodulos) {
    Modulo modulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();
    boolean flag = false;
    int somaModulos = 0;

    // Modulo A0
    modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "A0", null);
    modulos.add(modulo);

    for (int i = 0; i < submodulos.length; i++) {
      somaModulos += submodulos[i];
    }

    for (int a = 0; a < valores.length; a++) {

        for (int b = 0; b < submodulos.length; b++) {

          //modulos.get(b).getSubmodulos().contains(modulo)
          //!modulos.contains(modulo)

          if (submodulos[b] == 0 && valores[a] == 1 && flag == false && somaModulos == 0) {
            Modulo submodulo = new Modulo(getListaEntidadesPorModulo(problema, valores, valores[a]),
                String.valueOf("B" + a), null);
            modulo.setSubmodulos(Arrays.asList(submodulo));
            //modulos.add(modulo);
            flag = !flag;
          } else if (submodulos[b] == 0 && valores[a] == 1 && flag == false && somaModulos > 0) {
            modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, valores[a]),
                String.valueOf("A" + a), null);
            modulos.add(modulo);
            flag = !flag;
          }
        }
    }
    return new HMD(modulos);
  }

  private static List<Entidade> getListaEntidadesPorModulo(Problema problema, int[] valores,
      int modulo) {
    List<Entidade> listaEntidades = new ArrayList<>();
    for (int a = 0; a < valores.length; a++) {
      if (Objects.nonNull(problema.getClassCount())) {
        for (int i = 0; i < problema.getClassCount(); i++) {
          if (Objects.nonNull(problema.getListaDependenciasPara())) {
            Collection<Entidade> links = new ArrayList<>();
            for (int j = 0; j < problema.getListaDependenciasPara().length; j++) {
              int value = problema.getListaDependenciasPara()[i][j];
              if (value > 0) {
                links.add(new Entidade(String.valueOf(value), null));
              }
            }
            if ((valores[i] == modulo) && modulo == a) {
              listaEntidades.add(
                  new Entidade(String.valueOf(i), (links.size() > 0 ? links : null)));
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

}
