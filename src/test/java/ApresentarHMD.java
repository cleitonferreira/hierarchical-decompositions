import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApresentarHMD {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        //Figura1
        /*Figura1 figura1 = new Figura1();
        HMD hmdFigura = figura1.hmd();*/

        //Figura18
        Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();

        //hmdSolucao = new HMD(modulos);

        System.out.println("ListaModulos: " + hmdSolucao.getModulos().size());
        if(hmdSolucao.getModulos().get(0).getSubmodulos() != null) {
            System.out.println("ListaSubmodulos: " + hmdSolucao.getModulos().get(0).getSubmodulos().size());
        }
        System.out.println("ListaEntidades: " + hmdSolucao.getModulos().get(0).getListaEntidades().size());

        if (hmdSolucao.getModulos() != null) {
            for (Modulo modulo : hmdSolucao.getModulos()) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                listarEntidade(modulo);
                if (modulo.getSubmodulos() != null) {
                    for (Modulo submodulo : modulo.getSubmodulos()) {
                        System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                        listarEntidade(submodulo);
                        if (submodulo.getSubmodulos() != null) {
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
                                listarEntidade(submoduloII);
                            }
                        }
                    }
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
