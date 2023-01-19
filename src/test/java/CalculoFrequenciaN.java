import static java.util.stream.Collectors.toList;

import java.util.List;
import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoFrequenciaN {

  private static HMD hmdSolucao;

  private static double valorFrequenciaN = 0;

  public static void main(String[] args) {

    Figura1 figura1 = new Figura1();
    HMD hmdFigura1 = figura1.hmd();

    hmdSolucao = hmdFigura1;

    Entidade entidade = new Entidade("4", null);

    /*Entidade entidade = new Entidade("0",null);*/

    listarModulos(hmdSolucao.getModulos());

    valorFrequenciaN = 0;
    double valor = frequenciaN(hmdSolucao.getModulos(), entidade);
    System.out.println("frequenciaN: " + valor);
    valorFrequenciaN = 0;
    System.out.println("frequenciaN: " + frequenciaN(hmdSolucao.getModulos(), entidade));
  }

  /* n é uma Entidade Básica
   * f(n) = indegree(n) + 1 */
  private static double frequenciaN(List<Modulo> listaModulos, Entidade nEntidade) {

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
          frequenciaN(modulo.getSubmodulos().stream().collect(toList()), nEntidade);
        }
      }
    }

    return valorFrequenciaN + 1;
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
