import org.example.Figura1;
import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CountEntidadeModulo {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        //Figura1
        Figura1 figura1 = new Figura1();
        hmdSolucao = figura1.hmd();

        //Figura18
        /*Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();*/

        listarModulos(hmdSolucao.getModulos());

    }

    private static void listarModulos(List<Modulo> modulos) {

        if (modulos != null) {

            for (Modulo modulo : modulos) {
                System.out.println(
                        "Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null) {
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        System.out.println("Entidade: " + entidade.getNome());
                    }
                } else if (modulo.getSubmodulos() != null) {
                    listarModulos(modulo.getSubmodulos().stream().collect(toList()));
                }
            }
        }
    }

    private static void listarEntidade(Modulo modulo) {

    }

}
