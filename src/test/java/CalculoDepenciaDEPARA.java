import java.util.List;
import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoDepenciaDEPARA {

  private static HMD hmdSolucao;

  public static void main(String[] args) {

    Figura1 figura1 = new Figura1();
    HMD hmdFigura1 = figura1.hmd();

    hmdSolucao = hmdFigura1;

    /*Entidade entidade = new Entidade("4", null);*/

    Entidade entidade = new Entidade("0",null);

    System.out.println("dependenciaDE: " + dependenciaDE(entidade));
    System.out.println("dependenciaPara: " + dependenciaPara(entidade));
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

}
