import java.util.Objects;
import org.example.Figura1;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoMm {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        //Modulo moduloA5 = new Modulo(null, "A5", null);
        //figura1.hmd().getModulos().get(0).getSubmodulos().add(moduloA5);

        //Modulo: A0
        Modulo modulo = (Modulo) hmdSolucao.getModulos().get(0);

        //Modulo: A4
        /*Modulo modulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];*/

        int i = calculaMm(modulo);

        System.out.println(i);

    }

    /*M(m) é o conjunto de submódulos do modulo m*/
    public static int calculaMm(Modulo modulo) {
        int valor = 0;

        if (Objects.nonNull(modulo.getSubmodulos())) {
            for(Modulo submodulo : modulo.getSubmodulos()) {
                valor++;
                if (Objects.nonNull(submodulo.getSubmodulos())) {
                    valor++;
                    calculaMm((Modulo) submodulo.getSubmodulos().toArray()[0]);
                }
            }
        }

        return valor;
    }

}
