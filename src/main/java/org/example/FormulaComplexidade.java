package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.*;

public class FormulaComplexidade {

    private static HMD hmdSolucao;
    private static double formulaComplexidade;
    private static double valorRelativaProfundidade;
    private static Modulo moduloEntidade1;
    private static Modulo moduloEntidade2;

    public static void setHmdSolucao(HMD hmdSolucao) {
        FormulaComplexidade.hmdSolucao = hmdSolucao;
    }

    public static double executa(HMD hmd) {

        hmdSolucao = hmd;
        valorRelativaProfundidade = 0;

        if (hmdSolucao.getModulos() != null) {
            for (Modulo modulo : hmdSolucao.getModulos()) {
                System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null) {
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        System.out.println("Entidade: " + entidade.getNome());
                        formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));
                    }
                }
                if (modulo.getSubmodulos() != null) {
                    for (Modulo submodulo : modulo.getSubmodulos()) {
                        System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                        if (submodulo.getListaEntidades() != null) {
                            for (Entidade entidade : submodulo.getListaEntidades()) {
                                System.out.println("Entidade: " + entidade.getNome());
                                formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));
                            }
                        }
                        if (submodulo.getSubmodulos() != null) {
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
                                if (submoduloII.getListaEntidades() != null) {
                                    for (Entidade entidade : submoduloII.getListaEntidades()) {
                                        System.out.println("Entidade: " + entidade.getNome());
                                        formulanPertenceCmEnPertenteOutNodes(getOutNodes(entidade));
                                    }
                                }

                                formula(0, calculaCm(submoduloII), calculaMm(submoduloII), submoduloII);
                            }
                        }
                        formula(0, calculaCm(submodulo), calculaMm(submodulo), submodulo);
                    }
                }
                formula(0, calculaCm(modulo), calculaMm(modulo), modulo);
            }
        }

        return formulaComplexidade;
    }

    /*Formula da complexidade*/
    private static void formula(int um, int cm, int mm, Modulo modulo) {

        double formula = l(um + 1) + l(cm + 1) + l(mm + 1) +
                nPertenceCmUniaoMm(modulo) +
                valorRelativaProfundidade;
        formulaComplexidade = formulaComplexidade + formula;
    }

    /* n pertence Cm uniao Mm
     * Log sem estar a base escrita é base igual a 10. Chamado também de logaritmo decimal.
     * BigDecimal.valueOf(int);*/
    private static double nPertenceCmUniaoMm(Modulo modulo) {
        double valor = 0;

        if (modulo.getListaEntidades() != null) {
            for (Entidade entidade : modulo.getListaEntidades()) {
                //System.out.println("log: " + (-log(2,frequenciaN(entidade) / frequenciaM(modulo))));
                double a = frequenciaN(entidade);
                double b = frequenciaM(modulo);
                double c = a / b;
                double d = -log(2, c);
                double e = l(d);
                double f = -a * log(2, c);
                double g = valor + e + f;

                valor = valor + l(-log(2, (frequenciaN(entidade) / frequenciaM(modulo)))) - frequenciaN(entidade) * log(2, (frequenciaN(entidade) / frequenciaM(modulo)));
                System.out.println("nPertenceCmUniaoMm modulo: " + modulo.getNome() + " - " + entidade.getNome() + " - " + valor);
            }
        }

        if (modulo.getSubmodulos() != null) {
            for (Modulo submodulo : modulo.getSubmodulos()) {
                for (Entidade entidade : submodulo.getListaEntidades()) {
                    valor = valor + l(-log(2, (frequenciaN(entidade) / frequenciaM(submodulo)))) - frequenciaN(entidade) * log(2, (frequenciaN(entidade) / frequenciaM(submodulo)));
                    System.out.println("nPertenceCmUniaoMm submodulo: " + submodulo.getNome() + " - " + valor);
                }
                if (submodulo.getSubmodulos() != null) {
                    for (Modulo submoduloII : submodulo.getSubmodulos()) {
                        for (Entidade entidade : submoduloII.getListaEntidades()) {
                            valor = valor + l(-log(2, (frequenciaN(entidade) / frequenciaM(submoduloII)))) - frequenciaN(entidade) * log(2, (frequenciaN(entidade) / frequenciaM(submoduloII)));
                            System.out.println("nPertenceCmUniaoMm submoduloII: " + submoduloII.getNome() + " - " + valor);
                        }
                    }
                }
            }
        }

        System.out.println("nPertenceCmUniaoMm VALOR: " + valor);
        System.out.println();

        return valor;
    }

    private static void formulanPertenceCmEnPertenteOutNodes(HashMap<Entidade, Entidade> entradas) {
        for (Map.Entry<Entidade, Entidade> entrada : entradas.entrySet()) {

            Modulo m = lca(entrada.getKey(), entrada.getValue());
            int r = relativaProfundidade(entrada.getKey(), m);
            valorRelativaProfundidade = valorRelativaProfundidade + l(r);
        }
    }

    public static HashMap<Entidade, Entidade> getOutNodes(Entidade nEntidade) {

        HashMap<Entidade, Entidade> map = new HashMap<>();
        // Links de saída da entidade
        if (nEntidade.getLinks() != null) {
            for (Entidade link : nEntidade.getLinks()) {
                //System.out.println("getOutNodes: " + link);
                map.put(nEntidade, link);
            }
        }

        return map;
    }

    /*C(m) é o conjunto de entidades básicas conectadas no módulo m*/
    public static int calculaCm(Modulo modulo) {
        return modulo.getListaEntidades().size();
    }

    /*M(m) é o conjunto de submódulos do modulo m*/
    public static int calculaMm(Modulo modulo) {
        int valor = 0;

        if (modulo.getSubmodulos() != null) {
            for (Modulo submodulo : modulo.getSubmodulos()) {
                 valor++;
                if (submodulo.getSubmodulos() != null) {
                    for (Modulo submoduloII : submodulo.getSubmodulos()) {
                        valor++;
                    }
                }
            }
        }

        return valor;
    }

    static Modulo lca(Entidade entidade1, Entidade entidade2) {

        Modulo moduloLCA = null;

        for (Modulo modulo : hmdSolucao.getModulos()) {
            moduloLCA = encontrarModulo(modulo, entidade1, entidade2);
            if (modulo.getSubmodulos() != null) {
                for (Modulo submodulo : modulo.getSubmodulos()) {
                    moduloLCA = encontrarModulo(submodulo, entidade1, entidade2);
                    if (submodulo.getSubmodulos() != null) {
                        for (Modulo submoduloII : submodulo.getSubmodulos()) {
                            moduloLCA = encontrarModulo(submoduloII, entidade1, entidade2);
                        }
                    }
                }
            }
        }

        return moduloLCA;
    }

    private static Modulo encontrarModulo(Modulo modulo, Entidade entidade1, Entidade entidade2) {

        Modulo moduloLCA = null;


        for (Entidade entidade : modulo.getListaEntidades()) {
            if (entidade.getNome().equals(entidade1.getNome())) {
                moduloEntidade1 = modulo;
            }

            if (entidade.getNome().equals(entidade2.getNome())) {
                moduloEntidade2 = modulo;
            }
        }

        if (moduloEntidade1 != null && moduloEntidade2 != null) {

            if (moduloEntidade1.getNome().equals(moduloEntidade2.getNome())) {
                moduloLCA = modulo;
            } else {

                /*valida pai e filho*/
                if (moduloEntidade1.getSubmodulos() != null) {
                    moduloLCA = moduloEntidade1;
                } else if (moduloEntidade2.getSubmodulos() != null) {
                    moduloLCA = moduloEntidade2;
                } else {
                    moduloLCA = hmdSolucao.getModulos().get(0);
                }
            }
        }
        return moduloLCA;
    }


    /* n é uma Entidade Básica, M é um nó módulo */
    static int relativaProfundidade(Entidade entidade, Modulo modulo) {
        int valor = 0;

        if (modulo.getListaEntidades() != null) {
            if (modulo.getListaEntidades().contains(entidade)) {
                valor = 0;
            }
        }

        if (modulo.getSubmodulos() != null) {
            for (Modulo submodulo : modulo.getSubmodulos()) {
                if (submodulo.getListaEntidades().contains(entidade)) {
                    ++valor;
                }
            }
        }

        return valor;
    }

    /* A.4.1 Code lengths for integers
    l(n) = log(n) + 2log * log(n + 1) + 1 */
    static double l(double n) {
        double resultadofn = 0;

        if(n != resultadofn){
            resultadofn = log(2, n) + 2 * log(2, log(2, (n + 1))) + 1;
        } else {
            resultadofn = 0.0;
        }

        /*resultadofn = (n + 1);
        resultadofn = log(2, resultadofn) + 1;
        resultadofn = log(2, n) + 2 * log(2, resultadofn);
        */

        return resultadofn;
    }


    /*logaritmo de valor 0 e base qualquer é igual a indefinido*/
    static double log(double base, double valor) {
        /*if(valor == 0){
            return 0;
        } else{
            return (Math.log(valor) / Math.log(base));
        }*/

        return (Math.log(valor) / Math.log(base));
    }

    /* n é uma Entidade Básica
     * f(n) = indegree(n) + 1 */
    public static double frequenciaN(Entidade nEntidade) {
        double valor = 0;

        if (hmdSolucao.getModulos() != null) {
            for (Modulo modulo : hmdSolucao.getModulos()) {
                if (modulo.getListaEntidades() != null) {
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        if (entidade.getLinks() != null) {
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if ((link.getNome().equals(nEntidade.getNome()))) {
                                        valor++;
                                    }
                                }
                            }
                        }
                    }

                }
                if (modulo.getSubmodulos() != null) {
                    for (Modulo submodulo : modulo.getSubmodulos()) {
                        if (submodulo.getListaEntidades() != null) {
                            for (Entidade entidade : submodulo.getListaEntidades()) {
                                if (entidade.getLinks() != null) {
                                    for (Entidade link : entidade.getLinks()) {
                                        if (link != null) {
                                            if ((link.getNome().equals(nEntidade.getNome()))) {
                                                valor++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (submodulo.getSubmodulos() != null) {
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                if (submoduloII.getListaEntidades() != null) {
                                    for (Entidade entidade : submoduloII.getListaEntidades()) {
                                        if (entidade.getLinks() != null) {
                                            for (Entidade link : entidade.getLinks()) {
                                                if (link != null) {
                                                    if ((link.getNome().equals(nEntidade.getNome()))) {
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


    /*f(m) = indegree(m) + 1
     * m † (m a módulo) é definido informalmente como o número de arestas cujo destino é algum nó interno de m, e cuja fonte é um nó fora de m.
     * */
   static double frequenciaM(Modulo moduloEntidade) {
        double valor = 0;

        if (hmdSolucao.getModulos() != null) {
            for (Modulo modulo : hmdSolucao.getModulos()) {
                if (modulo.getListaEntidades() != null) {
                    for (Entidade entidade : modulo.getListaEntidades()) {
                        if (entidade.getLinks() != null) {
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if (moduloEntidade.getListaEntidades() != null) {
                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()) {
                                            if ((link.getNome().equals(nEntidade.getNome()))) {
                                                valor++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (modulo.getSubmodulos() != null) {
                    for (Modulo submodulo : modulo.getSubmodulos()) {
                        if (submodulo.getListaEntidades() != null) {
                            for (Entidade entidade : submodulo.getListaEntidades()) {
                                if (entidade.getLinks() != null) {
                                    for (Entidade link : entidade.getLinks()) {
                                        if (link != null) {
                                            if (moduloEntidade.getListaEntidades() != null) {
                                                for (Entidade nEntidade : moduloEntidade.getListaEntidades()) {
                                                    if ((link.getNome().equals(nEntidade.getNome()))) {
                                                        valor++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (submodulo.getSubmodulos() != null) {
                            for (Modulo submoduloII : submodulo.getSubmodulos()) {
                                if (submoduloII.getListaEntidades() != null) {
                                    for (Entidade entidade : submoduloII.getListaEntidades()) {
                                        if (entidade.getLinks() != null) {
                                            for (Entidade link : entidade.getLinks()) {
                                                if (link != null) {
                                                    if (moduloEntidade.getListaEntidades() != null) {
                                                        for (Entidade nEntidade : moduloEntidade.getListaEntidades()) {
                                                            if ((link.getNome().equals(nEntidade.getNome()))) {
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
