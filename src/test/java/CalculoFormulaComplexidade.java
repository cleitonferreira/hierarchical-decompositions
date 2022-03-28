import org.example.Figura1;
import org.example.Figura18;
import org.example.FormulaComplexidade;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.Collection;
import java.util.List;

public class CalculoFormulaComplexidade {

    private static HMD hmdSolucao;
    private static double valorRelativaProfundidade = 0;
    private static double valorFormulaComplexidade = 0;

    private static FormulaComplexidade formulaComplexidade;

    public static void main(String[] args) {

        //Figura1
        /*Figura1 figura1 = new Figura1();
        hmdSolucao = figura1.hmd();*/

        //Figura18
        Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();

        formulaComplexidade = new FormulaComplexidade(hmdSolucao);

        double formulaComplexidade = executa();

        //double formulaComplexidade = executaAntigo();


        //34.801142504825165

        System.out.println("Fórmula da Complexidade: " + formulaComplexidade);
        System.out.println("Cleiton: "+156.90334591994235);
        System.out.println("Prof. Marcio: "+173.45290150282247);
        System.out.println("Esperado: "+208.37);
        System.out.println();

    }

    public static double executa() {

        List<Modulo> listaModulos = hmdSolucao.getModulos();

        if (listaModulos != null) {

            for (Modulo modulo : listaModulos) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    for (Entidade entidade : modulo.getListaEntidades()){
                        System.out.println("Entidade: " + modulo.getNome() + " - " + entidade.getNome());
                        formulanPertenceCmEnPertenteOutNodes(entidade, formulaComplexidade.getOutNodes(entidade));
                    }
                }
                formula(0, formulaComplexidade.calculaCm(modulo), formulaComplexidade.calculaMm(modulo));
            }
        }

        return valorRelativaProfundidade + formulaComplexidade.nPertenceCmUniaoMm(hmdSolucao.getModulos());
    }

    /*Formula da complexidade*/
    private static void formula(int um, int cm, int mm) {

        double formula = formulaComplexidade.l(um + 1) + formulaComplexidade.l(cm + 1) + formulaComplexidade.l(mm + 1) +
                valorRelativaProfundidade;
        valorFormulaComplexidade = valorFormulaComplexidade + formula;
    }

    public static void formulanPertenceCmEnPertenteOutNodes(Entidade nEntidade, Collection<Entidade> links) {
        for (Entidade link : links) {

            Modulo m = formulaComplexidade.lca(nEntidade, link, hmdSolucao.getModulos());
            int r = formulaComplexidade.relativaProfundidade(nEntidade, m);
            valorRelativaProfundidade = valorRelativaProfundidade + formulaComplexidade.l(r);
        }
    }


}
