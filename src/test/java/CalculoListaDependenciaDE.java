import java.util.ArrayList;
import java.util.List;
import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoListaDependenciaDE {

  private static HMD hmdSolucao;

  private static Class classe;

  public static void main(String[] args) {

    Figura18 figura18 = new Figura18();
    classe = figura18.getClass();
    HMD hmdFigura = figura18.hmd();

    hmdSolucao = hmdFigura;

    /*Entidade entidade = new Entidade("4", null);*/

    Entidade entidade = new Entidade("8", null);

    String name = classe.getSimpleName();
    String filename = classe.getPackageName();

    System.out.println("name: " + name);
    System.out.println("filename: " + filename);

    System.out.println("dependenciaDE: " + listaDependenciaDE(entidade));
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

}
