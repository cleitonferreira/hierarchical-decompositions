package org.example;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class ConverterModuloIIIArquivo {

  private static HMD hmdSolucao;

  public static void main(String[] args) {
    /*Problema problema = getProblema6Classes();
    hmdSolucao = problema.getHMD();*/

    ExperimentoModel experimento = new ExperimentoFinalParte1();
    List<Problema> problemas = experimento.getProblemas();
    Problema problema = problemas.get(0);
    //hmdSolucao = problema.getHMD();

    /*Problema problema = getProblemaExemplo();*/


    /*int[] originalPackages = {0, 0, 1, 1, 2, 2};*/

    //int[] valores = {0, 1, 1, 2, 3, 4, 5, 6, 0};
    //int[] valores = {0, 0, 0, 2, 1, 1, 1, 1, 2};
    //int[] valores = {0, 0, 0, 2, 0, 0, 3, 2, 0};
    int[] valores = {2,3,2,1,0,0,2,1,0,3};

    //int[] submodulos = {0, 0, 0, 1, 1, 2, 4};
    //int[] submodulos = {0, 0, 1};
    //int[] submodulos = {0, 0, 1};
    //int[] submodulos = {0, 0, 0, 0, 2, 0, 3};
    int[] submodulos = {0,0,0,0};

    //int[] submodulos = geradorSubmodulos(valores);

    System.out.println("Valores: "+Arrays.toString(valores));
    System.out.println("Submódulos: "+Arrays.toString(submodulos));
    System.out.println();

    hmdSolucao = converterProblemaParaHMD(problema, valores, submodulos);
    //System.out.println(hmd);

    listarModulos(hmdSolucao.getModulos());

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);
    double valorFormulaComplexidade = formulaComplexidade.executa();
    System.out.println("FormulaComplexidade: " + valorFormulaComplexidade);
  }

  private static HMD converterProblemaParaHMD(Problema problema, int[] valores, int[] submodulos) {
    Modulo modulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();
    hmdSolucao = new HMD();

    // Modulo A0
    modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "modulo-0", null);
    modulos.add(modulo);

    /*Remover valores repetidos*/
    int[] resultado = Arrays.stream(valores).distinct().toArray();
    /*Ordenar*/
    Arrays.sort(resultado);

    for (int a = 0; a < resultado.length; a++) {
      for (int b = 0; b < submodulos.length; b++) {

        int verificaModulo = getVerificaModulo(modulos, resultado[a]);

        if (verificaModulo == -1 && a == b) {

          Modulo submodulo = new Modulo(
              getListaEntidadesPorModulo(problema, valores, resultado[a]),
              String.valueOf("modulo-" + b), null);

          List<Modulo> listaModulosModulosNaoHierrarquicos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(modulos);

          Modulo moduloPosicao = buscarPosicaoModulo(listaModulosModulosNaoHierrarquicos, submodulos[b]);
          if (Objects.isNull(moduloPosicao.getSubmodulos())){
            moduloPosicao.preparandoSubmodulos();
          }
          moduloPosicao.getSubmodulos().add(submodulo);
        }
      }
    }
    return new HMD(modulos);
  }

  public static Modulo buscarPosicaoModulo(List<Modulo> modulos, int posicao) {
    String nomeModulo = "modulo-"+posicao;
    for (int i = 0; i < modulos.size(); i++) {
      if (modulos.get(i).getNome().equals(nomeModulo)) {
        return modulos.get(i);
      }
    }
    return null;
  }

  public static int getVerificaModulo(List<Modulo> modulos, int valor) {

    int retorno = -1;

    for (Modulo modulo : modulos) {
      if (Objects.nonNull(modulo)) {
        if (modulo.getNome().equals("modulo-" + valor)) {
          retorno = 0;
        }
      }
      if (Objects.nonNull(modulo.getSubmodulos())) {
        for (Modulo submodulo : modulo.getSubmodulos()) {
          if (submodulo.getNome().equals("modulo-" + valor)) {
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

            Collection<Entidade> links = new ArrayList<>();
            for (int l = 0; l < problema.getListaDependenciasPara()[i].length; l++) {
              if (problema.getQtdDependenciasPara()[i] > l) {
                int value = problema.getListaDependenciasPara()[i][l];
                links.add(new Entidade(String.valueOf(value), null));
              }
            }
            links = links.size() == 0 ? null : links;
            listaEntidades.add(new Entidade(String.valueOf(i), links));
          }
        }
      }
    }
    /*System.out.println(listaEntidades);*/
    return listaEntidades;
  }

  public static Problema getProblemaExemplo() {
    int numClasses = 10;
    int numPackages = 2;
    int[] originalPackages = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
    int[] originalClasses = {6, 4, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] listaDependenciasPara =
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 5, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 3, 4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {6, 7, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasPara =
        {
            0, 0, 1, 0, 2, 3, 0, 1, 2, 0
        };

    int[][] listaDependenciasDe =
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 4, 5, 0, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {7, 8, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasDe =
        {
            0, 0, 1, 3, 1, 1, 2, 1, 0, 0
        };

    Problema problema = new Problema(
        "Exemplo",
        "",
        numClasses,
        numPackages,
        originalClasses,
        originalPackages,
        listaDependenciasPara,
        qtdDependenciasPara,
        listaDependenciasDe,
        qtdDependenciasDe,
        null
    );

    return problema;
  }

  public static Problema getProblema6Classes() {
    int numClasses = 6;
    int numPackages = 3;
    int[] originalPackages = {0, 0, 1, 1, 2, 2};
    int[] originalClasses = {2, 2, 2, 0, 0, 0};

    int[][] listaDependenciasPara =
        {
            {1, 0, 0, 0, 0, 0},
            {2, 3, 4, 0, 0, 0},
            {0, 1, 3, 0, 0, 0},
            {4, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasPara =
        {
            1, 3, 3, 1, 1, 0
        };

    int[][] listaDependenciasDe =
        {
            {2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {1, 2, 0, 0, 0, 0},
            {1, 3, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0}
        };
    int[] qtdDependenciasDe =
        {
            1, 2, 1, 2, 2, 1
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
        null
    );

    return problema;
  }

  public static Problema getProblemaFigura18() {
    int numClasses = 9;
    int numPackages = 1;
    int[] originalPackages = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] originalClasses = {9, 0, 0, 0, 0, 0, 0, 0, 0};

    Figura figura = new Figura18();

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
        figura.hmd()
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

  private static void listarModulos(List<Modulo> modulos) {
    if (modulos != null) {

      for (Modulo modulo : modulos) {
        System.out.println(
            "Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
        listarEntidade(modulo);
        if (modulo.getSubmodulos() != null) {
          System.out.println();
          listarModulos(modulo.getSubmodulos().stream().collect(toList()));
        }
      }
    }
  }

  private static void listarEntidade(Modulo modulo) {
    if (modulo.getListaEntidades() != null) {
      for (Entidade entidade : modulo.getListaEntidades()) {
        System.out.println("Entidade: " + entidade.getNome());
        if (entidade.getLinks() != null) {
          for (Entidade links : entidade.getLinks()) {
            System.out.println("Links: " + links.getNome());
          }
        }
      }
    }
  }

}
