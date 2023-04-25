package org.example.ils.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.example.FormulaComplexidade;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

/**
 * DEFINICÕES:
 * <p>
 * - acoplamento = número de dependências que as classes de um pacote possuem com classes de fora do
 * pacote. Deve ser minimizado.
 * <p>
 * - coesão = número de dependências que as classes de um pacote possuem com outras classes do mesmo
 * pacote. Deve ser maximizado (ou seja, minimizamos seu valor com sinal invertido)
 * <p>
 * - spread = partindo de zero e percorrendo cada pacote, acumula o quadrado da diferença entre o
 * número de classes do pacote e o número de classes do menor pacote
 * <p>
 * - diferenca = diferença entre o número máximo de classes em um pacote e o número mínimo de
 * classes em um pacote
 *
 * @author Marcio Barros
 */
public class Calculador extends CalculadorAbstract {

  //Limite do vetor é o limite superior informado para o problema
  protected int[] inboundEdges;
  protected int[] outboundEdges;
  protected int[] intraEdges;
  protected double[] mf;
  protected double mq;

  private static HMD hmdSolucao;

  /**
   * Inicializa o calculator com os dados do problema
   */
  public Calculador(Problema problema) {
    super(problema);
    hmdSolucao = problema.getHMD();
  }

  public double calculateFormulaComplexidadeEgravaEstado(SolucaoAbstract s, int[] valores) {

    HMD hmd = null;

    int[] subgrupos = GeradorGrupos.geradorSubmodulos(valores);
    s.setGrupos(subgrupos);
    hmd = converterProblemaParaHMD(s, problema, valores);

    int classCount = this.problema.getClassCount();
    this.inboundEdges = new int[classCount];
    this.outboundEdges = new int[classCount];
    this.intraEdges = new int[classCount];
    this.mf = new double[classCount];

    int[][] listaDependencias = this.problema.getListaDependenciasPara();
    int[] qtdDependencias = this.problema.getQtdDependenciasPara();

    for (int i = 0; i < classCount; i++) {
      int sourcePackage = valores[i];
      for (int j = 0; j < qtdDependencias[i]; j++) {
        int targetPackage = valores[listaDependencias[i][j]];
        if (targetPackage != sourcePackage) {
          outboundEdges[sourcePackage]++;
          inboundEdges[targetPackage]++;
        } else {
          intraEdges[sourcePackage]++;
        }
      }
    }

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmd);
    double valorFormulaComplexidade = formulaComplexidade.executa();

    this.mq = valorFormulaComplexidade;

    for (int i = 0; i < classCount; i++) {
      int inter = inboundEdges[i] + outboundEdges[i];
      int intra = intraEdges[i];

      if (intra != 0) {
        this.mf[i] = valorFormulaComplexidade;
      }
    }

    return valorFormulaComplexidade;
  }

  public double calculateFormulaComplexidade(SolucaoAbstract s, int[] valores) {

    HMD hmd = null;

    int[] subgrupos = GeradorGrupos.geradorSubmodulos(valores);
    s.setGrupos(subgrupos);
    hmd = converterProblemaParaHMD(s, problema, valores);

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmd);

    double valorFormulaComplexidade = formulaComplexidade.executa();

    //System.out.println("Valores: "+Arrays.toString(valores) + " - " + "Submódulos: "+Arrays.toString(s.getGrupos()) + " - " + valorFormulaComplexidade);

    return valorFormulaComplexidade;
  }

  private static HMD converterProblemaParaHMD(SolucaoAbstract s, Problema problema, int[] valores) {

    Modulo modulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();

    // Modulo A0
    modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "modulo-0", null);
    modulos.add(modulo);

    /*Remover valores repetidos*/
    int[] resultado = Arrays.stream(valores).distinct().toArray();
    /*Ordenar*/
    Arrays.sort(resultado);

    for (int a = 0; a < resultado.length; a++) {
      for (int b = 0; b < s.getGrupos().length; b++) {

        int verificaModulo = getVerificaModulo(modulos, resultado[a]);

        if (verificaModulo == -1 && a == b) {

          Modulo submodulo = new Modulo(
              getListaEntidadesPorModulo(problema, valores, resultado[a]),
              String.valueOf("modulo-" + b), null);

          List<Modulo> listaModulosModulosNaoHierrarquicos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(
              modulos);

          Modulo moduloPosicao = buscarPosicaoModulo(listaModulosModulosNaoHierrarquicos,
              s.getGrupos()[b]);
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
    return listaEntidades;
  }


  /**
   * Calcula o fitness
   */
  public double evaluate(SolucaoAbstract s) {
    return evaluate(s, s.getValores());
  }

  /**
   * Avalia a solução
   */
  public double evaluate(SolucaoAbstract s, int[] valores) {
    return calculateFormulaComplexidade(s, valores);
  }

  /**
   * Avalia a solução
   */
  public double evaluateEGravaEstado(SolucaoAbstract s, int[] valores) {
    return calculateFormulaComplexidadeEgravaEstado(s, valores);
  }

  /**
   * Calcula o coeficiente de modularidade do projeto Considera somente a diferença nos vetores
   * intraEdges, inboundEdges, outboundEdges calculado na última iteração
   */
  public double evaluateMove(int[] valores, int item, int grupo1, int grupo2) {

    int[][] listaDependenciasPara = this.problema.getListaDependenciasPara();
    int[] qtdDependenciasPara = this.problema.getQtdDependenciasPara();
    int[][] listaDependenciasDe = this.problema.getListaDependenciasDe();
    int[] qtdDependenciasDe = this.problema.getQtdDependenciasDe();

    for (int i = 0; i < qtdDependenciasPara[item]; i++) {
      int filho = listaDependenciasPara[item][i];
      // se o item era do grupo1 reduz as arestas intra de suas dependencias
      if (valores[filho] == grupo1) {
        this.intraEdges[grupo1]--;
        this.inboundEdges[grupo1]++;
        this.outboundEdges[grupo2]++;
      }
      // se o item passa para o grupo2 aumenta as arestas intra de suas dependencias
      else if (valores[filho] == grupo2) {
        this.inboundEdges[grupo2]--;
        this.outboundEdges[grupo1]--;
        this.intraEdges[grupo2]++;
      }
      // se o dependente não for nenhum dos dois grupos
      else {
        this.outboundEdges[grupo1]--;
        this.outboundEdges[grupo2]++;
      }
    }

    for (int i = 0; i < qtdDependenciasDe[item]; i++) {
      int pai = listaDependenciasDe[item][i];
      // se o item era do grupo1 reduz as arestas intra de suas dependencias
      if (valores[pai] == grupo1) {
        this.intraEdges[grupo1]--;
        this.outboundEdges[grupo1]++;
        this.inboundEdges[grupo2]++;
      }
      // se o item passa para o grupo2 aumenta as arestas intra de suas dependencias
      else if (valores[pai] == grupo2) {
        this.inboundEdges[grupo1]--;
        this.outboundEdges[grupo2]--;
        this.intraEdges[grupo2]++;
      }
      // se o dependente não for nenhum dos dois grupos
      else {
        this.inboundEdges[grupo1]--;
        this.inboundEdges[grupo2]++;
      }
    }

    // recalculando o MF do grupo 1 e grupo 2
    this.mq -= this.mf[grupo1];
    this.mq -= this.mf[grupo2];

    this.mf[grupo1] = 0.0;
    int interG1 = inboundEdges[grupo1] + outboundEdges[grupo1];
    int intraG1 = intraEdges[grupo1];
    //if (intraG1 != 0 && interG1 != 0) {
    if (intraG1 != 0) {
      this.mf[grupo1] = intraG1 / (intraG1 + 0.5 * interG1);
    }

    this.mf[grupo2] = 0.0;
    int interG2 = inboundEdges[grupo2] + outboundEdges[grupo2];
    int intraG2 = intraEdges[grupo2];
    //if (intraG2 != 0 && interG2 != 0) {
    if (intraG2 != 0) {
      this.mf[grupo2] = intraG2 / (intraG2 + 0.5 * interG2);
    }

    this.mq += this.mf[grupo1];
    this.mq += this.mf[grupo2];

    return -this.mq;
  }

}