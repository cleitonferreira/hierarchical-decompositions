import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class CalculoFrequenciaN {

    private static HMD hmdSolucao;
    private static Entidade nEntidade;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        Entidade entidade = new Entidade("4",null);
        nEntidade = entidade;


        System.out.println("Entidade: "+entidade.getNome());
        double valor = frequenciaN(entidade);
        System.out.println("frequenciaN: "+ valor);
    }

    /* n é uma Entidade Básica
     * f(n) = indegree(n) + 1 */
    private static double frequenciaN(Entidade nEntidade) {
        double valor = 0;
        Modulo moduloEntidade = null;

        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                //System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if ((link.getNome().equals(nEntidade.getNome())) && (modulo != moduloEntidade)) {
                                        valor++;
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
                                            if ((link.getNome().equals(nEntidade.getNome())) && (submodulo != moduloEntidade)) {
                                                valor++;
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
                                    System.out.println("SubModuloII: " + submoduloII.getNome());
                                    for (Entidade entidade : submoduloII.getListaEntidades()){
                                        if(entidade.getLinks() != null) {
                                            for (Entidade link : entidade.getLinks()) {
                                                if (link != null) {
                                                    if ((link.getNome().equals(nEntidade.getNome())) && (submoduloII != moduloEntidade)) {
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

        return valor + 1;
    }

    private static Modulo encontrarModulo(Modulo modulo, Entidade entidade1){

        Modulo moduloEntidade = null;

        for (Entidade entidade : modulo.getListaEntidades()){
            //System.out.println("entidade:"+ entidade.getNome());
            if (entidade.getNome().equals(entidade1.getNome())){
                moduloEntidade = modulo;
            }
        }

        if(moduloEntidade != null) {
            System.out.println("MODULO Entidade:" + moduloEntidade.getNome());
        }

        return moduloEntidade;
    }

}
