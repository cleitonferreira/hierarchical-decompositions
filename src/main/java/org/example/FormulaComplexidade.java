package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.*;

public class FormulaComplexidade {

    public static void executa(HMD hmd){

        //hmd.getModulo().getListaEntidades();
        int n = 0;
        List<Integer> FN = new ArrayList<>();



        for (Modulo modulo : hmd.getModulos()){
            System.out.println("Módulo: " + modulo.getNome() + " - submódulo" + modulo.getSubmodulo());

            for (Entidade entidade : modulo.getListaEntidades()) {

                n = Integer.parseInt(entidade.getNome());
                System.out.println("n: " + n);
                if(n > 0) {
                    FN.add(logN(n));
                }
                System.out.println("F(n): " + FN);

                if(entidade.getLinks() != null){
                    for(Entidade links : entidade.getLinks()) {

                    }
                }


            }

            int formula = formula(somarmPertenceM(hmd.getModulos()), 0, calculaCm(modulo.getListaEntidades()), calculaMm(modulo.getNome(), hmd.getModulos()), n);
            System.out.println("Fórmula: " + formula);
            System.out.println();
        }

    }

    /*Formula da complexidade*/
    private static int formula(int mPertenceM, int um, int cm,int submodulo, int n){

        int formula = mPertenceM + (logN(um + 1) + logN(cm + 1) + logN(submodulo + 1));
        return formula;
    }

    /*C(m) é o conjunto de entidades básicas conectadas no módulo m*/
    private static int calculaCm(List<Entidade> listaEntidades) {
        return listaEntidades.size();
    }

    /*M(m) é o conjunto de submódulos do modulo m*/
    private static int calculaMm(String chave,List<Modulo> modulos) {
        Map<String,String> submodulos = new HashMap<String,String>();

        for (Modulo modulo : modulos) {
            submodulos.put(modulo.getNome(), modulo.getSubmodulo());
        }

        if(submodulos.containsKey(chave)){
            return submodulos.size();
        } else {
            return 0;
        }
    }

    /*  m é o módulo
        M é o conjunto de todos os módulos no HMD x */
    private static int somarmPertenceM(List<Modulo> modulos) {
        return modulos.size();
    }

    /* A.4.1 Code lengths for integers
    l(n) = log(n) + 2log * log(n + 1) + 1 */
    private static int logN(int n) {
        int resultadofn;

        resultadofn = log(2, n) + 2 * log(2, log(2, n + 1)) + 1;

        return resultadofn;
    }


    private static int log(double base, double valor) {
        return (int) (Math.log(valor) / Math.log(base));
    }


    /*f(n) = indegree(n) + 1 */
    private static int frequenciaN(Entidade entidade) {
        int fn = 0;
        for (Entidade link : entidade.getLinks()) {
            fn = fn + Integer.parseInt(link.getNome()) + 1;
        }
        return fn;//indegree - realizar um loop na entidade e pegar os links
    }

    /*f(m) = indegree(m) + 1
    * m † ( m a módulo ) é definido informalmente como o número de arestas cujo destinoção é algum nó interno de m , e cuja fonte é um nó fora de m .
    * */
    private static int frequenciaM(Modulo modulo) {
        int fm = 0;
        for (Entidade entidade : modulo.getListaEntidades()) {
            if(!modulo.getNome().equals(modulo.getSubmodulo())) {
                for (Entidade link : entidade.getLinks()) {
                    fm = fm + Integer.parseInt(link.getNome()) + 1;
                }
            }
        }
        return fm;
    }

}
