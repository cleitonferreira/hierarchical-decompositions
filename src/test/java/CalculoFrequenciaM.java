import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;

public class CalculoFrequenciaM {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        //Modulo: A1
        //Modulo modulo = (Modulo) hmdFigura1.getModulos().get(0).getSubmodulos().toArray()[0];

        //Modulo: A3
        //Modulo modulo = (Modulo) hmdFigura1.getModulos().get(0).getSubmodulos().toArray()[1];

        //Modulo: A4
        Modulo submodulo = (Modulo) hmdFigura1.getModulos().get(0).getSubmodulos().toArray()[1];
        Modulo modulo = (Modulo) submodulo.getSubmodulos().toArray()[0];

        System.out.println("ModuloEntidade: "+modulo.getNome());

        int valor = frequenciaM(modulo);
        System.out.println("frequenciaM: "+ valor);
    }

    /*f(m) = indegree(m) + 1
     * m † (m a módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
     * */
    private static int frequenciaM(Modulo moduloEntidade) {
        int valor = 0;

        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                //System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if (moduloEntidade.getListaEntidades() != null){
                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                            if ((link.getNome().equals(nEntidade.getNome())) && (modulo != moduloEntidade)) {
                                                System.out.println("Modulo: " + modulo.getNome());
                                                valor++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(modulo.getSubmodulos() != null){
                    for (Modulo submodulo : modulo.getSubmodulos()){
                        //System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                        if (submodulo.getListaEntidades() != null){
                            for (Entidade entidade : submodulo.getListaEntidades()){
                                if(entidade.getLinks() != null){
                                    for (Entidade link : entidade.getLinks()) {
                                        if (link != null) {
                                            if (moduloEntidade.getListaEntidades() != null){
                                                for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                                    if ((link.getNome().equals(nEntidade.getNome())) && (submodulo != moduloEntidade)) {
                                                        System.out.println("Submodulo: " + submodulo.getNome());
                                                        valor++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(submodulo.getSubmodulos() != null){
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                //System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
                                if (submoduloII.getListaEntidades() != null){
                                    for (Entidade entidade : submoduloII.getListaEntidades()){
                                        if(entidade.getLinks() != null) {
                                            for (Entidade link : entidade.getLinks()) {
                                                if (link != null) {
                                                    if (moduloEntidade.getListaEntidades() != null){
                                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                                            if ((link.getNome().equals(nEntidade.getNome())) && (submoduloII != moduloEntidade)) {
                                                                System.out.println("SubmoduloII: " + submoduloII.getNome());
                                                                valor++;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return valor + 1;

    }

}
