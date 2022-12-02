import org.example.Figura18;
import org.example.model.Entidade;
import org.example.model.HMD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class EncontrarGetOutNodes {

    private static HMD hmdSolucao;
    private static double valorRelativaProfundidade;
    //private static Entidade nEntidade;

    public static void main(String[] args) {

        Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();

        //Entidade entidade = new Entidade("8",null);

        Entidade entidade = new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8")));


        System.out.println("Entidade: "+entidade);
        Collection<Entidade> links = getOutNodes(entidade);

        formulanPertenceCmEnPertenteOutNodes(entidade, links);
    }

    private static Collection<Entidade> getOutNodes(Entidade nEntidade) {

        Collection<Entidade> links = new ArrayList<>();
        // Links de saída da entidade
        if (nEntidade.getLinks() != null) {
            for (Entidade link : nEntidade.getLinks()) {
                System.out.println("getOutNodes: " + link);
                links.add(link);
            }
        }

        return links;
    }

    private static void formulanPertenceCmEnPertenteOutNodes(Entidade nEntidade, Collection<Entidade> links) {
        for (Entidade link : links) {
            System.out.println("Key: "+ nEntidade.getNome());
            System.out.println("Value: "+ link.getNome());
            //valorRelativaProfundidade = valorRelativaProfundidade + l(relativaProfundidade(entrada.getKey(), lca(entrada.getKey(), entrada.getValue())));
        }
    }

}
