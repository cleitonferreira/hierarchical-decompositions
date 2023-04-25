package org.example;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.example.ils.core.Problema;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class ConverterModuloII {

  private static HMD hmdSolucao;

  public static void main(String[] args) {
    Problema problema = getProblemaFigura18();
    //Problema problema = getProblemaFigura21();

    hmdSolucao = problema.getHMD();

    /*int[] originalPackages = {0, 0, 1, 1, 2, 2};*/

    //int[] valores = {0, 0, 0, 0, 0, 1, 1, 1, 1};
    //int[] valores = {1, 1, 1, 1, 1, 0, 0, 0, 0};
    //int[] valores = {0, 1, 1, 2, 3, 0, 3, 3, 0}; //TODO posicao em cada modulo
    //int[] valores = {0, 0, 2, 3, 3, 5, 2, 7, 5};
    //int[] valores = {0, 0, 1, 2, 2, 3, 1, 4, 3};
    //int[] valores = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    //int[] valores = {1, 1, 1, 1, 1, 1, 1, 1, 0};
    //int[] valores = {2, 2, 2, 2, 2, 2, 2, 0, 1};
    //int[] valores = {2, 2, 2, 2, 2, 1, 0, 2, 3};
    //int[] valores = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    //int[] valores = {1, 3, 0, 1, 2, 2, 1, 3, 0};
    int[] valores = {3, 2, 0, 3, 1, 1, 3, 2, 0};

    //0; add um módulo
    //1; add um submodulo no módulo anterior

    //int[] submodulos = {0, 1};
    //int[] submodulos = {0, 0};
    //int[] submodulos = {0, 1, 1, 1, 0};
    //int[] submodulos = {0, 0, 0, 1};
    //int[] submodulos = {0, 0, 0, 0, 1};
    //int[] submodulos = {0, 1, 1, 0, 0};
    //int[] submodulos = {0};
    //int[] submodulos = {0, 1};
    //int[] submodulos = {0, 1, 1};
    //int[] submodulos = {0, 1, 1, 1};
    //int[] submodulos = {0, 1, 1, 1, 1, 1, 1, 1, 0};
    //int[] submodulos = {0, 0, 0, 0};
    int[] submodulos = {0, 1, 1, 1};

    hmdSolucao = converterProblemaParaHMD(problema, valores, submodulos);
    //System.out.println(hmd);

    listarModulos(hmdSolucao.getModulos());

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);
    double valorFormulaComplexidade = formulaComplexidade.executa();
    System.out.println("FormulaComplexidade: "+valorFormulaComplexidade);
  }

  private static HMD converterProblemaParaHMD(Problema problema, int[] valores, int[] submodulos) {
    Modulo modulo = null;
    Modulo submodulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();

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

        if (verificaModulo == -1 && submodulos[b] == 0 && a == b) {
          modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
              String.valueOf("modulo-" + resultado[a]), null);
          modulos.add(modulo);
        } else if (verificaModulo == -1 && submodulos[b] == 1 && a == b) { //(verificaModulo == 0 && valores[b] != submodulos[b] && somaModulos == 0)
          int position = b - 1;
          if ((submodulos[position] == 1) && (submodulos[b] == 1)){
            Modulo submoduloII = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
                String.valueOf("submodulo-" + resultado[a]), null);
            //submodulo.setSubmodulos(Arrays.asList(submoduloII));

            /*if (submodulo.getSubmodulos() == null) {
              submodulo.setSubmodulos(Arrays.asList(submoduloII));
            } else {*/
              /*Collection<Modulo> collection = new ArrayList<Modulo>();
              collection.add(submoduloII);
              //Collections.addAll(new ArrayList<>((Collection) submoduloII));

              //submodulo.getSubmodulos().stream()
              modulos.stream()
                  .map(Modulo::getSubmodulos)
                  .flatMap(Collection::stream)
                  .filter(Objects::nonNull)
                  .peek(v -> v.setSubmodulos(collection));
                  //.collect(toList());*/

              List<Modulo> listaModulos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(modulos);

              int size = (listaModulos.size() - 1);
              listaModulos.get(size).setSubmodulos(Arrays.asList(submoduloII));
            //}



          } else {
            submodulo = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
                String.valueOf("submodulo-" + resultado[a]), null);
            modulo.setSubmodulos(Arrays.asList(submodulo));
          }
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
        System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
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
