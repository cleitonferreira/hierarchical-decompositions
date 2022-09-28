import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;

public class CalculoFrequenciaM {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        /*Figura1 figura = new Figura1();
        hmdSolucao = figura.hmd();*/

        Figura18 figura = new Figura18();
        hmdSolucao = figura.hmd();

        //Modulo: A0
        Modulo modulo = hmdSolucao.getModulos().get(0);

        //Modulo: A1
        /*Modulo modulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];*/

        //Modulo: A2
        /*Modulo submodulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[0];
        Modulo modulo = (Modulo) submodulo.getSubmodulos().toArray()[0];*/

        //Modulo: A3
        /*Modulo modulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];*/

        //Modulo: A4
        /*Modulo submodulo = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[1];
        Modulo modulo = (Modulo) submodulo.getSubmodulos().toArray()[0];*/

        System.out.println("ModuloEntidade: "+modulo.getNome());

        double valor = frequenciaM(modulo);
        System.out.println("frequenciaM: "+ valor);

    }

    /**
     * f(m) = indegree(m) + 1
     * m † (m a módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
     * @Modulo - este é um parâmetro do método frequenciaM
     * @return Este metodo retorna um valor inteiro
     * @throws Exception - Esta exeption nunca é lançada neste método,
     * mas tá aqui pra exemplificar o comentário Javadoc para Exceptions
     */
    private static double frequenciaM(Modulo moduloEntidade) {

        List<Modulo> listaModulos = hmdSolucao.getModulos();
        double valorFrequenciaM = 0;

        if (listaModulos != null) {

            for (Modulo modulo : listaModulos) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if (moduloEntidade.getListaEntidades() != null){
                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                            if (link.getNome().equals(nEntidade.getNome()) && !modulo.getNome().equals(moduloEntidade.getNome())) {
                                            //if (link.getNome().equals(nEntidade.getNome())) {
                                                System.out.println("Modulo: " + modulo.getNome() + " - " + link.getNome() + " - " + nEntidade.getNome());
                                                valorFrequenciaM++;
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

        return valorFrequenciaM + 1;
    }

}
