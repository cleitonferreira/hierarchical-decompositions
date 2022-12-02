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

public class ConverterProblema {

  private static HMD hmdSolucao;

  public static void main(String[] args) {
    Problema problema = getProblemaFigura18();
    HMD hmd = converterProblemaParaHMD(problema);
    System.out.println(hmd);
  }

  private static HMD converterProblemaParaHMD(Problema problema) {

    if (Objects.nonNull(problema.getPackageCount())) {
      Modulo modulo = null;
      List<Entidade> listaEntidades = new ArrayList<>();
      for (int a = 0; a < problema.getPackageCount(); a++) {
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
              listaEntidades.add(new Entidade(String.valueOf(i), links.size() > 0 ? links : null));
            }
          }
        }
        modulo = new Modulo(listaEntidades, String.valueOf("A"+a), null);
      }

      List<Modulo> modulos = new ArrayList<Modulo>();
      modulos.add(modulo);
      return new HMD(modulos);
    } else {
      return null;
    }


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
        "teste6",
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
