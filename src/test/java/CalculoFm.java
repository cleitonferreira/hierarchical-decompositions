import org.example.Figura18;
import org.example.FormulaComplexidade;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;

public class CalculoFm {

    private static HMD hmdSolucao;

    private static FormulaComplexidade formulaComplexidade;

    public static void main(String[] args) {

        /*Figura1 figura = new Figura1();
        hmdSolucao = figura.hmd();*/

        Figura18 figura = new Figura18();
        hmdSolucao = figura.hmd();

        formulaComplexidade = new FormulaComplexidade(hmdSolucao);

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

        System.out.println("ModuloEntidade: " + modulo.getNome());

        double valor = frequenciaM();
        System.out.println("frequenciaM: " + valor);

    }

    /**
     * f(m) = indegree(m) + 1
     * m † (m a módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
     *
     * @return Este metodo retorna um valor inteiro
     * @throws Exception - Esta exeption nunca é lançada neste método,
     *                   mas tá aqui pra exemplificar o comentário Javadoc para Exceptions
     * @Modulo - este é um parâmetro do método frequenciaM
     */
    private static double frequenciaM() {

        List<Modulo> listaModulos = hmdSolucao.getModulos();
        double valorFrequenciaM = 0;

        if (listaModulos != null) {

            for (Modulo modulo : listaModulos) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());

                /*if(modulo.getSubmodulos() == null) {
                    valorFrequenciaM = formulaComplexidade.frequenciaN(entidade);
                }*/


                if (modulo.getListaEntidades() != null) {
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        if (entidade.getNome() != null) {

                            for (Modulo moduloLink : listaModulos) {
                                if (modulo.getListaEntidades() != null) {
                                    for (Entidade entidadeLink : modulo.getListaEntidades()) {
                                        if (entidadeLink.getLinks() != null) {
                                            for (Entidade link : entidadeLink.getLinks()) {
                                                if (link != null) {
                                                    if (link.getNome().equals(entidade.getNome())) {
                                                        valorFrequenciaM = valorFrequenciaM + 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                        valorFrequenciaM = valorFrequenciaM + 1;
                    }
                }
            }
        }

        return valorFrequenciaM;
    }

}
