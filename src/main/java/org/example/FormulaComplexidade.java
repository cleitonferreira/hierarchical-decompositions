package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;
import java.util.Objects;

public class FormulaComplexidade {

  private static HMD hmdSolucao;
  private static Modulo moduloEntidade1;
  private static Modulo moduloEntidade2;

  public FormulaComplexidade(HMD hmdSolucao) {
    this.hmdSolucao = hmdSolucao;
  }

  public static double executa() {

    List<Modulo> listaModulos = hmdSolucao.getModulos();
    double formulaComplexidade = 0;

    if (listaModulos != null) {

      for (Modulo modulo : listaModulos) {
        double valor = 0;
        /*System.out.println(
            "Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());*/
        if (modulo.getListaEntidades() != null) {
          for (Entidade entidade : modulo.getListaEntidades()) {
            /*System.out.println("Entidade: " + modulo.getNome() + " - " + entidade.getNome());*/
            valor += formulanPertenceCmEnPertenteOutNodes(entidade);
          }
        }
        formulaComplexidade = formula(0, calculaCm(modulo), calculaMm(modulo), valor, formulaComplexidade);
      }
    }

    return formulaComplexidade + nPertenceCmUniaoMm(hmdSolucao.getModulos());
  }

  /*Formula da complexidade*/
  private static double formula(int um, int cm, int mm, double valor, double formulaComplexidade) {

    double formula = l(um + 1) + l(cm + 1) + l(mm + 1) + valor;
    formulaComplexidade += formula;
    return formulaComplexidade;
  }

  /* n pertence Cm uniao Mm
   * Log sem estar a base escrita é base igual a 10. Chamado também de logaritmo decimal.
   * BigDecimal.valueOf(int);*/
  public static double nPertenceCmUniaoMm(List<Modulo> listaModulos) {
    double valor = 0;

    if (listaModulos != null) {

      for (Modulo modulo : listaModulos) {

        double valorFrequenciaM = frequenciaM(modulo);

        if (modulo.getListaEntidades() != null) {
          for (Entidade entidade : modulo.getListaEntidades()) {
            double a = frequenciaN(entidade);
            double b = valorFrequenciaM;
            double c = a / b;
            double d = -log(2, c);
            double e = l(d);
            double f = -a * log(2, c);
            double g = e + f;
            valor = valor + g;

          }
        }
      }
    }

    return valor;
  }

  public static double formulanPertenceCmEnPertenteOutNodes(Entidade nEntidade) {
    double result = 0;
    if (nEntidade.getLinks() != null) {
      for (Entidade link : nEntidade.getLinks()) {

        Modulo m = lca(nEntidade, link, hmdSolucao.getModulos());
        int r = relativaProfundidade(nEntidade, m);
        result += l(r);
      }
    }

    return result;
  }

  /*C(m) é o conjunto de entidades básicas conectadas no módulo m*/
  public static int calculaCm(Modulo modulo) {
    int quantidade = 0;
    if (Objects.nonNull(modulo.getListaEntidades())) {
      quantidade = modulo.getListaEntidades().size();
    }
    return quantidade;
  }

  /*M(m) é o conjunto de submódulos do modulo m*/
  public static int calculaMm(Modulo modulo) {
    int valor = 0;

    if (Objects.nonNull(modulo.getSubmodulos())) {
      for (Modulo submodulo : modulo.getSubmodulos()) {
        valor++;
        if (Objects.nonNull(submodulo.getSubmodulos())) {
          valor++;
          calculaMm((Modulo) submodulo.getSubmodulos().toArray()[0]);
        }
      }
    }

    return valor;
  }

  public static Modulo lca(Entidade entidade1, Entidade entidade2, List<Modulo> modulos) {

    Modulo moduloLCA = null;

    for (Modulo modulo : modulos) {

      if (Objects.nonNull(modulo.getListaEntidades())) {
        for (Entidade entidade : modulo.getListaEntidades()) {
          if (entidade.getNome().equals(entidade1.getNome())) {
            moduloEntidade1 = modulo;
          }

          if (entidade.getNome().equals(entidade2.getNome())) {
            moduloEntidade2 = modulo;
          }
        }
      }

      if (Objects.nonNull(moduloEntidade1) && Objects.nonNull(moduloEntidade2)) {

        if (moduloEntidade1.getNome().equals(moduloEntidade2.getNome())) {
          moduloLCA = modulo;
          break;
        } else {

          /*valida pai e filho*/
          if (Objects.nonNull(moduloEntidade1.getSubmodulos())) {
            moduloLCA = moduloEntidade1;
          } else if (Objects.nonNull(moduloEntidade2.getSubmodulos())) {
            moduloLCA = moduloEntidade2;
          } else {
            moduloLCA = hmdSolucao.getModulos().get(0);
          }
        }
      }
    }

    return moduloLCA;
  }


  /* n é uma Entidade Básica, M é um nó módulo */
  public static int relativaProfundidade(Entidade entidade, Modulo modulo) {
    int valor = 0;
    boolean flag = false;

    if (Objects.nonNull(modulo.getListaEntidades())) {
      if (containsNomeEntidade(modulo.getListaEntidades(), entidade.getNome())) {
        valor = 0;
        flag = true;
      }
    }

    if (flag == false && Objects.nonNull(modulo.getSubmodulos())) {
      for (Modulo submodulo : modulo.getSubmodulos()) {
        ++valor;
        if (containsNomeEntidade(submodulo.getListaEntidades(), entidade.getNome())) {
          break;
        }
      }
    }

    return valor;
  }

  private static boolean containsNomeEntidade(final List<Entidade> listaEntidades,
      final String name) {
    return listaEntidades.stream().anyMatch(o -> o.getNome().equals(name));
  }

  /* A.4.1 Code lengths for integers
  l(n) = log(n) + 2log * log(n + 1) + 1 */
  public static double l(double n) {
    double resultadofn = 0;

    n = n + 1;
    //resultadofn = log(2, n) + 2 * log(2, log(2, (n + 1))) + 1;
    resultadofn = log(2, n) + 2 * log(2, log(2, n + 1)) + 1;

    return resultadofn;
  }


  /*logaritmo de valor 0 e base qualquer é igual a indefinido*/
  static double log(double base, double valor) {
    return (Math.log(valor) / Math.log(base));
  }

  /* n é uma Entidade Básica
   * f(n) = indegree(n) + 1 */
  /* n é uma Entidade Básica
   * f(n) = indegree(n) + 1 */
  public static double frequenciaN(Entidade nEntidade) {

    List<Modulo> listaModulos = hmdSolucao.getModulos();
    double valorFrequenciaN = 0;

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

    return valorFrequenciaN + 1;
  }


  /*f(m) = indegree(m) + 1
   * m † (m é o módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
   * */
  public static double frequenciaM(Modulo modulo) {

    double valorFrequenciaM = 0;

    if (modulo != null) {

      if (modulo.getListaEntidades() != null) {
        for (Entidade entidade : modulo.getListaEntidades()) {
          if (entidade.getNome() != null) {

            for (Modulo m : hmdSolucao.getModulos()) {
              if (m.getListaEntidades() != null) {
                for (Entidade entidadeLink : modulo.getListaEntidades()) {
                  if (entidadeLink.getLinks() != null) {
                    for (Entidade link : entidadeLink.getLinks()) {
                      if (link != null) {
                        if (link.getNome().equals(entidade.getNome())) {
                          valorFrequenciaM = valorFrequenciaM + 1;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          valorFrequenciaM = valorFrequenciaM + 1;

          if (modulo.getSubmodulos() != null) {
            valorFrequenciaM += frequenciaN(entidade);
          }
        }
      }
    }

    return valorFrequenciaM;
  }

}
