import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AprentarHMD {

    private static HMD hmdSolucao;

    public static void main(String[] args) {

        List<Entidade> listaEntidadesA0 = new ArrayList<>();
        listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("3"), new Entidade("2"), new Entidade("4"), new Entidade("5"))));
        listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
        listaEntidadesA0.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
        listaEntidadesA0.add(new Entidade("13", null));

        List<Entidade> listaEntidadesA1 = new ArrayList<>();
        listaEntidadesA1.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("3"), new Entidade("4"), new Entidade("2"))));

        List<Entidade> listaEntidadesA2 = new ArrayList<>();
        listaEntidadesA2.add(new Entidade("2", Arrays.asList(new Entidade("4"), new Entidade("6"))));
        listaEntidadesA2.add(new Entidade("3", Arrays.asList(new Entidade("7"), new Entidade("4"))));
        listaEntidadesA2.add(new Entidade("4", Arrays.asList(new Entidade("7"))));

        List<Entidade> listaEntidadesA3 = new ArrayList<>();
        listaEntidadesA3.add(new Entidade("8", Arrays.asList(new Entidade("10"), new Entidade("12"))));
        listaEntidadesA3.add(new Entidade("9", Arrays.asList(new Entidade("10"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
        listaEntidadesA3.add(new Entidade("10", Arrays.asList(new Entidade("11"), new Entidade("13"), new Entidade("12"))));
        listaEntidadesA3.add(new Entidade("11", Arrays.asList(new Entidade("12"), new Entidade("13"))));

        List<Entidade> listaEntidadesA4 = new ArrayList<>();
        listaEntidadesA4.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
        listaEntidadesA4.add(new Entidade("7", Arrays.asList(new Entidade("8"), new Entidade("9"), new Entidade("10"))));



        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.add(new Modulo(listaEntidadesA0, "A0",
                Arrays.asList(
                        new Modulo(listaEntidadesA1,"A1",
                                Arrays.asList(new Modulo(listaEntidadesA2,"A2", null))),
                        new Modulo(listaEntidadesA3,"A3",
                                Arrays.asList(new Modulo(listaEntidadesA4,"A4", null))))));

        hmdSolucao = new HMD(modulos);

        System.out.println("ListaModulos: " + hmdSolucao.getModulos().size());
        System.out.println("ListaSubmodulos: " + hmdSolucao.getModulos().get(0).getSubmodulos().size());
        System.out.println("ListaEntidades: " + hmdSolucao.getModulos().get(0).getListaEntidades().size());

        for (Modulo modulo : hmdSolucao.getModulos()){
            System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
            entidades(modulo.getListaEntidades());
            for (Modulo submodulo : modulo.getSubmodulos()){
                System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                entidades(submodulo.getListaEntidades());
                for (Modulo submoduloII : submodulo.getSubmodulos()) {
                    System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
                    entidades(submoduloII.getListaEntidades());
                }
            }
        }

    }

    private static void entidades(List<Entidade> listaEntidades){
        for (Entidade entidade : listaEntidades) {
            System.out.println("Entidade: " + entidade.getNome());
            if(entidade.getLinks() != null){
                for(Entidade links : entidade.getLinks()) {
                    System.out.println("Links: " + links.getNome());
                }
            }
        }
    }

}
