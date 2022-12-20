import java.util.List;
import org.example.Figura1;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CountEntidadeModulo {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        //Figura1
        Figura1 figura1 = new Figura1();
        hmdSolucao = figura1.hmd();

        //Figura18
        /*Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();*/

        //listarModulos(hmdSolucao.getModulos());

        System.out.println("Count Modulos: " + countModulos(hmdSolucao.getModulos()));

        System.out.println("Count Entidades: " + countEntidades(hmdSolucao.getModulos()));

    }

    private static int countModulos(List<Modulo> modulos) {
        return modulos.size();
    }

    private static int countEntidades(List<Modulo> modulos) {
        int count = 0;

        if (modulos != null) {

            for (Modulo modulo : modulos) {
                if (modulo.getListaEntidades() != null) {
                    count = count + modulo.getListaEntidades().size();
                }
            }
        }
        return count;
    }

}
