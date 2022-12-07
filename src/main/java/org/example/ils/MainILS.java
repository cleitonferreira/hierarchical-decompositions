package org.example.ils;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.example.Figura;
import org.example.Figura18;
import org.example.Figura21;
import org.example.ils.core.Exibicao;
import org.example.ils.core.ExperimentoFactory;
import org.example.ils.core.ExperimentoModel;
import org.example.ils.core.Parametro;
import org.example.ils.core.Problema;
import org.example.ils.core.TipoExibicao;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class MainILS {

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

    //Configura a exibição conforme algoritmo e problema
    TipoExibicao tipoExibicao = TipoExibicao.DEFAULT;
    tipoExibicao.setDebug(true);

    ExperimentoModel experimento = new ExperimentoFinalParte1();
    executa(tipoExibicao, experimento);
  }

  public static final void executa(TipoExibicao tipoExibicao, ExperimentoModel experimento)
      throws Exception {

    Problema problema = loadHMD(hmdSolucao);
    //Problema problema = getProblema6Classes();
    Exibicao[] exibicoes = experimento.iniciaExibicoes();
    List<Parametro> params = experimento.getParametros();

    // Executa o problema para todas as instâncias
    int indice = 0;
    for (Parametro param : params) {
      int tamanho = problema.getTamanho();

      param.setProbabilidadeMutacao(0.004 * Math.log(tamanho) / Math.log(2));
      param.setTamanhoPopulacao(tamanho * 10);

      // param.setProbabilidadeCrossover( (tamanho < 100) ? 0.8 : 1.0 );
      if (param.getTamanhoPopulacao() < 100) {
        param.setProbabilidadeCrossover(0.8);
      } else {
        param.setProbabilidadeCrossover(1.0);
      }

      param.setEvaluationMax(param.getMultiplicadorEvaluation() * tamanho * tamanho);
      // Barros, 2012
      // param.setEvaluationMax( 200 * tamanho * tamanho );
      // Praditwong, 2011
      // param.setEvaluationMax( 2000 * tamanho * tamanho );

      exibicoes[indice].setTipoExibicaoCiclo(tipoExibicao);
      exibicoes[indice].configuraDebug(indice, problema, param);

      ExperimentoFactory.executa(problema, exibicoes[indice], param);
      indice++;
    }
  }

  /**
   * A partir do objeto modelo Project, carrega os dados do problema Adaptado do projeto de Marcio
   * Barros
   */
  private static Problema loadHMD(HMD hmd) throws Exception {
    int classCount = hmd.getCountEntidades();
    int packageCount = hmd.getCountModulos();
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
      qtdDependenciasDe[moduloDestino] = depDE.size();
      for (int i = 0; i < depDE.size(); i++) {
        //System.out.println("DE[" + moduloDestino + "][" + i + "]=" + depDE.get(i));
        listaDependenciasDe[moduloDestino][i] = depDE.get(i);
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
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            if (submodulo.getListaEntidades() != null) {
              for (Entidade entidade : submodulo.getListaEntidades()) {
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
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            if (submodulo.getListaEntidades() != null) {
              for (Entidade entidade : submodulo.getListaEntidades()) {
                if (entidade.getLinks() != null) {
                  if ((entidade.getNome().equals(nEntidade.getNome()))) {
                    valorFrequenciaN = entidade.getLinks().size();
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

  private static void load(List<Modulo> modulos) {

    if (Objects.nonNull(modulos)) {
      int count = 0;
      for (Modulo modulo : modulos) {
        /*System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());*/
        loadEntidade(modulo);
        originalClasses[count] = modulo.getListaEntidades().size();
        if (modulo.getListaEntidades() != null) {
          for (int a = 0; a < modulo.getListaEntidades().size(); a++) {
            originalPackage[a] = count;
          }
        }
        ++count;
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            loadEntidade(submodulo);
            originalClasses[count] = submodulo.getListaEntidades().size();
            if (submodulo.getListaEntidades() != null) {
              int indiceModulo = modulo.getListaEntidades().size();
              for (int b = 0; b < submodulo.getListaEntidades().size(); b++) {
                originalPackage[indiceModulo + b] = count;
              }
            }
            ++count;
          }
        }
      }
    }
  }

  private static void loadEntidade(Modulo modulo) {

    if (modulo.getListaEntidades() != null) {
      for (Entidade entidade : modulo.getListaEntidades()) {
        int valorDe = dependenciaDE(entidade);
        int chaveDe = dependenciasDe.size();
        List<Integer> depDE = new ArrayList<>();
        for (int i = 0; i < valorDe; i++) {
          depDE = listaDependenciaDE(entidade);
        }
        dependenciasDe.put(chaveDe++, depDE);
        int chavePara = dependenciasPara.size();
        int valorPara = dependenciaPara(entidade);
        List<Integer> depPARA = new ArrayList<>();
        for (int i = 0; i < valorPara; i++) {
          Entidade link = (Entidade) entidade.getLinks().stream().toArray()[i];
          int value = Integer.valueOf(link.getNome());
          depPARA.add(value);
        }
        dependenciasPara.put(chavePara++, depPARA);
      }
    }
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
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            if (submodulo.getListaEntidades() != null) {
              for (Entidade entidade : submodulo.getListaEntidades()) {
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
      }
    }
    return indices;
  }

  private static void listarModulos(List<Modulo> modulos) {
    if (modulos != null) {

      for (Modulo modulo : modulos) {
        System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
        listarEntidade(modulo);
        if (modulo.getSubmodulos() != null) {
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
