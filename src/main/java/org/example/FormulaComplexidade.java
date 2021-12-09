package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;
import org.example.validacao.Valida;

import java.util.*;

public class FormulaComplexidade {

    private static HMD hmdSolucao;
    private static int formulaComplexidade;
    private static int valorRelativaProfundidade;
    private static Modulo moduloEntidade1;
    private static Modulo moduloEntidade2;

    public static void executa(HMD hmd){

        hmdSolucao = hmd;
        valorRelativaProfundidade = 0;

        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        System.out.println("Entidade: " + entidade.getNome());
                        formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));

                    }
                }
                if(modulo.getSubmodulos() != null){
                    for (Modulo submodulo : modulo.getSubmodulos()){
                        System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                        if (submodulo.getListaEntidades() != null){
                            for (Entidade entidade : submodulo.getListaEntidades()) {
                                System.out.println("Entidade: " + entidade.getNome());
                                formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));
                            }
                        }
                        if(submodulo.getSubmodulos() != null){
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
                                if (submoduloII.getListaEntidades() != null){
                                    for (Entidade entidade : submoduloII.getListaEntidades()) {
                                        System.out.println("Entidade: " + entidade.getNome());
                                        formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));
                                    }
                                }

                                formula(mPertenceM(submoduloII, hmdSolucao.getModulos()), 0, calculaCm(submoduloII.getListaEntidades()), calculaMm(submoduloII), submoduloII);
                            }
                        }
                        formula(mPertenceM(submodulo, hmdSolucao.getModulos()), 0, calculaCm(submodulo.getListaEntidades()), calculaMm(submodulo), submodulo);
                    }
                }
                formula(mPertenceM(modulo, hmdSolucao.getModulos()), 0, calculaCm(modulo.getListaEntidades()), calculaMm(modulo), modulo);
            }
        }

        System.out.println("Fórmula da Complexidade: " + formulaComplexidade);
        System.out.println();

    }

    /*Formula da complexidade*/
    private static void formula(int mPertenceM, int um, int cm, int mm, Modulo modulo){

        int formula = mPertenceM + (logN(um + 1) + logN(cm + 1) + logN(mm + 1) + nPertenceCmUniaoMm(modulo) + logN(valorRelativaProfundidade));
        formulaComplexidade = formulaComplexidade + formula;
    }

    private static void formulanPertenceCmEnPertenteOutNodes(HashMap<Entidade, Entidade> entradas) {
        for (Map.Entry<Entidade, Entidade> entrada : entradas.entrySet()) {
            valorRelativaProfundidade = valorRelativaProfundidade + relativaProfundidade(entrada.getKey(), lca(entrada.getKey(), entrada.getValue()));
        }
    }

    private static HashMap<Entidade, Entidade> getOutNodes(Entidade entidade){

        HashMap<Entidade, Entidade> map = new HashMap<>();
        if(entidade.getLinks() != null){
            for(Entidade links : entidade.getLinks()) {
                map.put(entidade, links);
            }
        }

        return map;
    }

    /*C(m) é o conjunto de entidades básicas conectadas no módulo m*/
    private static int calculaCm(List<Entidade> listaEntidades) {
        return listaEntidades.size();
    }

    /*M(m) é o conjunto de submódulos do modulo m*/
    private static int calculaMm(Modulo modulo) {
        int valor = 0;
        if(modulo.getSubmodulos() != null) {
            valor = modulo.getSubmodulos().size();
        }
        return valor;
    }

    /*  m é o módulo
        M é o conjunto de todos os módulos no HMD x */
    private static int mPertenceM(Modulo modulo, List<Modulo> modulos) {

        int valor = 0;
        for(Modulo m : modulos) {
            if (modulo.equals(m)) {
                valor =  m.getListaEntidades().stream().mapToInt(i -> Integer.parseInt(i.getNome())).sum();
            } else {
                valor = 0;
            }
        }
        return valor;
    }

    /* n pertence Cm uniao Mm
     * Log sem estar a base escrita é base igual a 10. Chamado também de logaritmo decimal. */
    private static int nPertenceCmUniaoMm(Modulo modulo) {
        int valor = 0;
        for (Entidade entidade : modulo.getListaEntidades()) {
            //System.out.println("log: " + (-log(2,frequenciaN(entidade) / frequenciaM(modulo))));
            valor = logN( (-log(2,frequenciaN(entidade) / frequenciaM(modulo)) - frequenciaN(entidade) * log(2,frequenciaN(entidade) / frequenciaM(modulo)) ));
            //System.out.println("nPertenceCmUniaoMm: " + valor);
        }
        return valor;
    }

    private static Modulo lca(Entidade entidade1, Entidade entidade2) {

        Modulo moduloLCA = null;

        for (Modulo modulo : hmdSolucao.getModulos()){
            moduloLCA = encontrarModulo(modulo, entidade1, entidade2);
            if(modulo.getSubmodulos() != null){
                for (Modulo submodulo : modulo.getSubmodulos()){
                    moduloLCA = encontrarModulo(submodulo, entidade1, entidade2);
                    if(submodulo.getSubmodulos() != null){
                        for (Modulo submoduloII : submodulo.getSubmodulos()) {
                            moduloLCA = encontrarModulo(submoduloII, entidade1, entidade2);
                        }
                    }
                }
            }
        }

        return moduloLCA;
    }

    private static Modulo encontrarModulo(Modulo modulo, Entidade entidade1, Entidade entidade2){

        Modulo moduloLCA = null;


        for (Entidade entidade : modulo.getListaEntidades()){
            if (entidade.getNome().equals(entidade1.getNome())){
                moduloEntidade1 = modulo;
            }

            if (entidade.getNome().equals(entidade2.getNome())){
                moduloEntidade2 = modulo;
            }
        }

        if(moduloEntidade1 != null && moduloEntidade2 != null) {

            if (moduloEntidade1.getNome().equals(moduloEntidade2.getNome())){
                moduloLCA = modulo;
            } else {

                /*valida pai e filho*/
                if(moduloEntidade1.getSubmodulos() != null){
                    moduloLCA = moduloEntidade1;
                }else if(moduloEntidade2.getSubmodulos() != null) {
                    moduloLCA = moduloEntidade2;
                } else {
                    moduloLCA = hmdSolucao.getModulos().get(0);
                }
            }
        }
        return moduloLCA;
    }


    /* n é uma Entidade Básica, M é um nó módulo */
    private static int relativaProfundidade(Entidade entidade, Modulo modulo) {
        int valor = 0;

        if (modulo.getListaEntidades() != null) {
            if (modulo.getListaEntidades().contains(entidade)) {
                valor = 0;
            }
        }

        if(modulo.getSubmodulos() != null) {
            for (Modulo submodulo : modulo.getSubmodulos()){
                if (submodulo.getListaEntidades().contains(entidade)) {
                    ++valor;
                }
            }
        }

        return valor;
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

    /* n é uma Entidade Básica
     * f(n) = indegree(n) + 1 */
    private static int frequenciaN(Entidade nEntidade) {
        int valor = 0;
        Modulo moduloEntidade = null;

        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                moduloEntidade = encontrarModulo(modulo, nEntidade);
                if (modulo.getListaEntidades() != null){
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
                        moduloEntidade = encontrarModulo(submodulo, nEntidade);
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
                                moduloEntidade = encontrarModulo(submoduloII, nEntidade);
                                if (submoduloII.getListaEntidades() != null){
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
            if (entidade.getNome().equals(entidade1.getNome())){
                moduloEntidade = modulo;
            }
        }

        return moduloEntidade;
    }


    /*f(m) = indegree(m) + 1
     * m † (m a módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
     * */
    private static int frequenciaM(Modulo moduloEntidade) {
        int valor = 0;

        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                if (modulo.getListaEntidades() != null){
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if (moduloEntidade.getListaEntidades() != null){
                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                            if ((link.getNome().equals(nEntidade.getNome())) && (modulo != moduloEntidade)) {
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
                        if (submodulo.getListaEntidades() != null){
                            for (Entidade entidade : submodulo.getListaEntidades()){
                                if(entidade.getLinks() != null){
                                    for (Entidade link : entidade.getLinks()) {
                                        if (link != null) {
                                            if (moduloEntidade.getListaEntidades() != null){
                                                for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
                                                    if ((link.getNome().equals(nEntidade.getNome())) && (submodulo != moduloEntidade)) {
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
                                if (submoduloII.getListaEntidades() != null){
                                    for (Entidade entidade : submoduloII.getListaEntidades()){
                                        if(entidade.getLinks() != null) {
                                            for (Entidade link : entidade.getLinks()) {
                                                if (link != null) {
                                                    if (moduloEntidade.getListaEntidades() != null){
                                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()){
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
            }
        }

        return valor + 1;

    }

}
