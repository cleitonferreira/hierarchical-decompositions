import java.util.List;
import java.util.Objects;
import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class EncontrarLCA {

    private static HMD hmdSolucao;
    private static Modulo moduloEntidade1;
    private static Modulo moduloEntidade2;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura = figura1.hmd();

        /*Figura21 figura1 = new Figura21();
        HMD hmdFigura = figura1.hmd();*/

        hmdSolucao = hmdFigura;

        //7, 4 = A0
        Entidade entidade1 = new Entidade("7",null);
        Entidade entidade2 = new Entidade("4",null);


        // 7, 10 = A3
        /*Entidade entidade1 = new Entidade("7",null);
        Entidade entidade2 = new Entidade("10",null);*/

        // 7, 6 = A4
        /*Entidade entidade1 = new Entidade("7",null);
        Entidade entidade2 = new Entidade("6",null);*/

        //5, 6 = A0
        /*Entidade entidade1 = new Entidade("5",null);
        Entidade entidade2 = new Entidade("6",null);*/

        System.out.println(entidade1);
        System.out.println(entidade2);
        Modulo m = lca(entidade1, entidade2, hmdSolucao.getModulos());
        System.out.println("LCA: "+ m.getNome());

    }

    private static Modulo lca(Entidade entidade1, Entidade entidade2, List<Modulo> modulos) {

        Modulo moduloLCA = null;

        for (Modulo modulo : modulos){
            System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());

            for (Entidade entidade : modulo.getListaEntidades()){
                System.out.println("for Entidade: "+ entidade.getNome() + " - entidade1: " + entidade1.getNome() + " - entidade2: " + entidade2.getNome() );
                if (entidade.getNome().equals(entidade1.getNome())){
                    moduloEntidade1 = modulo;
                }

                if (entidade.getNome().equals(entidade2.getNome())){
                    moduloEntidade2 = modulo;
                }
            }

            if(Objects.nonNull(moduloEntidade1) && Objects.nonNull(moduloEntidade2)) {

                System.out.println("moduloEntidade1: "+ moduloEntidade1.getNome());
                System.out.println("moduloEntidade2: "+ moduloEntidade2.getNome());

                if (moduloEntidade1.getNome().equals(moduloEntidade2.getNome())){
                    moduloLCA = modulo;
                    System.out.println("if"+moduloLCA.getNome());
                    break;
                } else {

                    /*valida pai e filho*/
                    if(Objects.nonNull(moduloEntidade1.getSubmodulos())){
                        System.out.println("contains moduloEntidade1: "+ moduloEntidade1.getSubmodulos().contains(moduloEntidade2));
                        moduloLCA = moduloEntidade1;
                    }else if(Objects.nonNull(moduloEntidade2.getSubmodulos())) {
                        System.out.println("contains moduloEntidade2: "+ moduloEntidade2.getSubmodulos().contains(moduloEntidade1));
                        moduloLCA = moduloEntidade2;
                    } else {
                        //moduloLCA = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[--i];
                        moduloLCA = hmdSolucao.getModulos().get(0);
                    }
                }
            }
        }

        return moduloLCA;
    }

}
