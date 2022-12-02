import org.example.Figura18;
import org.example.FormulaComplexidade;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.List;

public class PertenceCmUniaoMm {

    private static HMD hmdSolucao;

    private static FormulaComplexidade formulaComplexidade;

    public static void main(String[] args) {

        /*Figura1 figura1 = new Figura1();
        hmdSolucao = figura1.hmd();*/

        Figura18 figura = new Figura18();
        hmdSolucao = figura.hmd();

        formulaComplexidade = new FormulaComplexidade(hmdSolucao);

        double valor = nPertenceCmUniaoMm(hmdSolucao.getModulos());

        System.out.println("nPertenceCmUniaoMm: "+valor);

    }

    /* N pertence Cm uniao Mm
     * Log sem estar a base escrita é base igual a 10. Chamado também de logaritmo decimal.
     * BigDecimal.valueOf(int);*/
    private static double nPertenceCmUniaoMm(List<Modulo> listaModulos) {

        double valor = 0;

        double valorFrequenciaM = 0;

        if (listaModulos != null) {

            for (Modulo modulo : listaModulos) {

                valorFrequenciaM = formulaComplexidade.frequenciaM(modulo);

                if (modulo.getListaEntidades() != null){
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()){
                        /*valor = valor + l(-log(2, (formulaComplexidade.frequenciaN(entidade) / formulaComplexidade.frequenciaM(modulo)))) - formulaComplexidade.frequenciaN(entidade) * log(2, (formulaComplexidade.frequenciaN(entidade) / formulaComplexidade.frequenciaM(modulo)));*/

                        double a = formulaComplexidade.frequenciaN(entidade);
                        double b = valorFrequenciaM;
                        double c = a / b;
                        double d = -log(2, c);
                        double e = l(d);
                        double f = -a * log(2, c);
                        double g = e + f;
                        valor = valor + g;

                        System.out.println("nPertenceCmUniaoMm modulo: " + modulo.getNome() + " | " + entidade.getNome() + " | " + valor);
                    }
                }
            }
        }

        return valor;
    }

    /* A.4.1 Code lengths for integers
    l(n) = log(n) + 2log * log(n + 1) + 1 */
    static double l(double n) {
        double resultadofn;

        n = n + 1;
        resultadofn = log(2, n) + 2 * log(2, log(2, (n + 1))) + 1;

        return resultadofn;
    }


    /*logaritmo de valor 0 e base qualquer é igual a indefinido*/
    static double log(double base, double valor) {
        return (Math.log(valor) / Math.log(base));
    }

}
