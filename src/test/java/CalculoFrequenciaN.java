import java.util.List;
import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoFrequenciaN {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        Entidade entidade = new Entidade("4",null);

        double valor = frequenciaN(entidade);
        System.out.println("frequenciaN: "+ valor);
    }

    /* n é uma Entidade Básica
     * f(n) = indegree(n) + 1 */
    private static double frequenciaN(Entidade nEntidade) {

        List<Modulo> listaModulos = hmdSolucao.getModulos();
        double valorFrequenciaN = 0;

        if (listaModulos != null) {

            for (Modulo modulo : listaModulos) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
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

}
