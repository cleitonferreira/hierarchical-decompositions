import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.Objects;

public class EncontrarRelDepth {

    private static HMD hmdSolucao;
    private static Modulo moduloEntidade1;
    private static Modulo moduloEntidade2;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        Entidade entidade = new Entidade("7",null);

        //Modulo: A0
        /*Modulo modulo = hmdSolucao.getModulos().get(0);*/

        //Modulo: A3
        /*Modulo modulo = (Modulo) hmdFigura1.getModulos().get(0).getSubmodulos().toArray()[1];*/

        //Modulo: A4
        Modulo submodulo = (Modulo) hmdFigura1.getModulos().get(0).getSubmodulos().toArray()[1];
        Modulo modulo = (Modulo) submodulo.getSubmodulos().toArray()[0];

        System.out.println("Entidade: "+entidade.getNome());
        System.out.println("Modulo: "+modulo.getNome());
        int valor = relativaProfundidade(entidade, modulo);
        System.out.println("relativaProfundidade: "+ valor);
    }

    /* n é uma Entidade Básica, M é um nó módulo */
    private static int relativaProfundidade(Entidade entidade, Modulo modulo) {
        int valor = 0;

        if (Objects.nonNull(modulo.getListaEntidades())) {
            if (modulo.getListaEntidades().contains(entidade)) {
                valor = 0;
            }
        }

        if(Objects.nonNull(modulo.getSubmodulos())) {
            for (Modulo submodulo : modulo.getSubmodulos()){
                if (submodulo.getListaEntidades().contains(entidade)) {
                    ++valor;
                }
            }
        }

        return valor;
    }

}
