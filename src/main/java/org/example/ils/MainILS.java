package org.example.ils;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.example.Figura18;
import org.example.FormulaComplexidade;
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

  public static void main(String[] args) throws Exception {

    //Figura1
        /*Figura1 figura = new Figura1();
        hmdSolucao = figura.hmd();*/

    //Figura18
    Figura18 figura = new Figura18();
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
   * A partir do objeto modelo Project, carrega os dados do problema
   * Adaptado do projeto de Marcio Barros
   */
  private static Problema loadHMD(HMD hmd) throws Exception
  {
    int classCount = hmd.getCountEntidades();
    int packageCount = hmd.getModulos().size();
    int[][] listaDependenciasPara = new int[classCount][classCount];
    int[] qtdDependenciasPara = new int[classCount];
    int[][] listaDependenciasDe = new int[classCount][classCount];
    int[] qtdDependenciasDe = new int[classCount];

    int[] originalPackage = new int[classCount];
    int[] originalClasses = new int[classCount];

    for (int i = 0; i < packageCount; i++) {
      for (int j = 0; j < classCount; j++) {
        //ProjectClass _class = modelo.getClassIndex(i);
        //int sourcePackageIndex = modelo.getIndexForPackage(_class.getPackage());
        int sourcePackageIndex = dependenciaDE(hmd.getModulos().get(i).getListaEntidades().get(j));

        originalPackage[i] = sourcePackageIndex;
        originalClasses[sourcePackageIndex]++;

        int classIndex = dependenciaPara(hmd.getModulos().get(i).getListaEntidades().get(j));

        listaDependenciasPara[i][j] = classIndex;
        qtdDependenciasPara[i]++;

        listaDependenciasDe[classIndex][qtdDependenciasDe[classIndex]++] = i;
      }
    }

    String name = Figura18.class.getClass().getSimpleName();
    String filename = Figura18.class.getClass().getPackageName();

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
    int valorFrequenciaN = 1;

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
    int valorFrequenciaN = 1;

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

  private static void listarModulos(List<Modulo> modulos) {

    if (modulos != null) {

      for (Modulo modulo : modulos) {
        System.out.println(
            "Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
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
}
