import org.example.Figura1;
import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EncontrarGetOutNodes {

    private static HMD hmdSolucao;
    private static double valorRelativaProfundidade;
    //private static Entidade nEntidade;

    public static void main(String[] args) {

        Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();

        //Entidade entidade = new Entidade("8",null);

        Entidade entidade = new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8")));


        System.out.println("Entidade: "+entidade.getNome());
        HashMap<Entidade, Entidade> map = getOutNodes(entidade);
    }

    private static HashMap<Entidade, Entidade> getOutNodes(Entidade nEntidade) {

        HashMap<Entidade, Entidade> map = new HashMap<>();
        if (nEntidade.getLinks() != null) {
            for (Entidade link : nEntidade.getLinks()) {
                System.out.println("getOutNodes: " + link);
                map.put(nEntidade, link);
            }
        }
/*
        if(hmdSolucao.getModulos() != null){
            for (Modulo modulo : hmdSolucao.getModulos()){
                //System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                if (modulo.getListaEntidades() != null){
                    System.out.println("Modulo: " + modulo.getNome());
                    for (Entidade entidade : modulo.getListaEntidades()){
                        if(entidade.getLinks() != null){
                            for (Entidade link : entidade.getLinks()) {
                                if (link != null) {
                                    if (link.getNome().equals(nEntidade.getNome())) {
                                        System.out.println("Link de saída: "+ entidade.getNome());
                                        map.put(nEntidade, entidade);
                                        formulanPertenceCmEnPertenteOutNodes(map);
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
                                            if (link.getNome().equals(nEntidade.getNome())) {
                                                System.out.println("Link de saída: "+ link.getNome());
                                                map.put(nEntidade, entidade);
                                                formulanPertenceCmEnPertenteOutNodes(map);
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
                                                    if (link.getNome().equals(nEntidade.getNome())) {
                                                        System.out.println("Link de saída: "+ link.getNome());
                                                        map.put(nEntidade, entidade);
                                                        formulanPertenceCmEnPertenteOutNodes(map);
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
*/
        return map;
    }

    private static void formulanPertenceCmEnPertenteOutNodes(HashMap<Entidade, Entidade> entradas) {
        for (Map.Entry<Entidade, Entidade> entrada : entradas.entrySet()) {
            System.out.println("Key: "+ entrada.getKey() + " - Value: "+ entrada.getValue());
            //valorRelativaProfundidade = valorRelativaProfundidade + l(relativaProfundidade(entrada.getKey(), lca(entrada.getKey(), entrada.getValue())));
        }
    }

}
