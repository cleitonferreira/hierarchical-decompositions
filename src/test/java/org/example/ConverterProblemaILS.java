package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.example.ils.core.Problema;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class ConverterProblemaILS {

  private static HMD hmdSolucao;
  private static Class classe;
  private static HashMap<Integer, List<Integer>> dependenciasPara;
  private static HashMap<Integer, List<Integer>> dependenciasDe;
  private static int[] originalPackage;
  private static int[] originalClasses;

  public static void main(String[] args) throws Exception {
    //Figura21
    Figura figura = new Figura21();
    //Figura18
    //Figura figura = new Figura18();

    classe = figura.getClass();
    hmdSolucao = figura.hmd();

    Problema problema = loadHMD(hmdSolucao);
  }

  private static Problema loadHMD(HMD hmd) throws Exception {
    int classCount = hmd.getCountEntidades();
    int packageCount = hmd.getModulos().size();
    int[][] listaDependenciasPara = new int[classCount][classCount];
    int[] qtdDependenciasPara = new int[classCount];
    int[][] listaDependenciasDe = new int[classCount][classCount];
    int[] qtdDependenciasDe = new int[classCount];

    originalPackage = new int[classCount];
    originalClasses = new int[classCount];

    dependenciasPara = new HashMap<Integer, List<Integer>>();
    dependenciasDe = new HashMap<Integer, List<Integer>>();
    load(hmd.getModulos());

    for (Integer moduloOrigem : dependenciasPara.keySet()) {
      List<Integer> depPARA = dependenciasPara.get(moduloOrigem);
      qtdDependenciasPara[moduloOrigem] = depPARA.size();
      for (int i = 0; i < depPARA.size(); i++) {
        //System.out.println("PARA[" + moduloOrigem + "][" + i + "]=" + depPARA.get(i));
        listaDependenciasPara[moduloOrigem][i] = depPARA.get(i);
      }
    }
    for (Integer moduloDestino : dependenciasDe.keySet()) {
      List<Integer> depDE = dependenciasDe.get(moduloDestino);
      //TODO remover comentário
      //qtdDependenciasDe[moduloDestino] = depDE.size();
      for (int i = 0; i < depDE.size(); i++) {
        //System.out.println("DE[" + moduloDestino + "][" + i + "]=" + depDE.get(i));
        //TODO remover comentário 2
        //listaDependenciasDe[moduloDestino][i] = depDE.get(i);
      }
    }

    String name = classe.getSimpleName();
    String filename = classe.getPackageName();

    Problema problema = new Problema(
        filename,
        name,
        classCount,
        packageCount,
        originalClasses,
        originalPackage,
        listaDependenciasPara,
        qtdDependenciasPara,
        listaDependenciasDe,
        qtdDependenciasDe,
        hmd);

    return problema;
  }

  private static void load(List<Modulo> modulos) {

    if (Objects.nonNull(modulos)) {
      for (Modulo modulo : modulos) {
        int count = 0;
        /*System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());*/
        loadEntidade(modulo);
        originalClasses[count] = modulo.getListaEntidades().size();
        originalPackage[count] = modulos.indexOf(modulo);
        ++count;
      }
    }
  }

  private static void loadEntidade(Modulo modulo) {

    if (modulo.getListaEntidades() != null) {
      for (Entidade entidade : modulo.getListaEntidades()) {
        int valorDe = dependenciaDE(entidade);
        if (valorDe > 0) {
          List<Integer> depDE = new ArrayList<>();
          for (int i = 0; i < valorDe; i++) {
            depDE = listaDependenciaDE(entidade);
          }
          dependenciasDe.put(Integer.valueOf(entidade.getNome()), depDE);
        }
        int chavePara = dependenciasPara.size();
        int valorPara = dependenciaPara(entidade);
        if (valorPara > 0) {
          List<Integer> depPARA = new ArrayList<>();
          for (int i = 0; i < valorPara; i++) {
            Entidade link = (Entidade) entidade.getLinks().stream().toArray()[i];
            int value = getIndexOf(modulo.getListaEntidades(), link.getNome());
            depPARA.add(value);
          }
          dependenciasPara.put(chavePara++, depPARA);
        }

      }
    }
  }

  public static int dependenciaDE(Entidade nEntidade) {

    List<Modulo> listaModulos = hmdSolucao.getModulos();
    int valorFrequenciaN = 0;

    if (listaModulos != null) {

      for (Modulo modulo : listaModulos) {
        if (modulo.getListaEntidades() != null) {
          for (Entidade entidade : modulo.getListaEntidades()) {
            if (entidade.getLinks() != null) {
              for (Entidade link : entidade.getLinks()) {
                if (link != null) {
                  if ((link.getNome().equals(nEntidade.getNome()))) {
                    valorFrequenciaN++;
                  }
                }
              }
            }
          }
        }
      }
    }

    return valorFrequenciaN;
  }

  public static int dependenciaPara(Entidade nEntidade) {

    List<Modulo> listaModulos = hmdSolucao.getModulos();
    int valorFrequenciaN = 0;

    if (listaModulos != null) {

      for (Modulo modulo : listaModulos) {
        if (modulo.getListaEntidades() != null) {
          for (Entidade entidade : modulo.getListaEntidades()) {
            if (entidade.getLinks() != null) {
              if ((entidade.getNome().equals(nEntidade.getNome()))) {
                valorFrequenciaN = entidade.getLinks().size();
              }
            }
          }
        }
      }
    }

    return valorFrequenciaN;
  }

  private static int getIndexOf(List<Entidade> list, String name) {
    int pos = 0;

    for (Entidade entidade : list) {
      if (name.equalsIgnoreCase(entidade.getNome())) {
        return pos;
      }
      pos++;
    }
    return -1;
  }

  public static List<Integer> listaDependenciaDE(Entidade nEntidade) {

    List<Modulo> listaModulos = hmdSolucao.getModulos();
    List<Integer> indices = new ArrayList<>();

    if (listaModulos != null) {
      for (Modulo modulo : listaModulos) {
        if (modulo.getListaEntidades() != null) {
          for (Entidade entidade : modulo.getListaEntidades()) {
            if (entidade.getLinks() != null) {
              for (Entidade link : entidade.getLinks()) {
                if (link != null) {
                  if ((link.getNome().equals(nEntidade.getNome()))) {
                    indices.add(Integer.parseInt(entidade.getNome()));
                  }
                }
              }
            }
          }
        }
      }
    }
    return indices;
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
        hmdSolucao
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
        hmdSolucao
    );

    return problema;
  }
}
