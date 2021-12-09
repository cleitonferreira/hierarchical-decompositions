import org.example.Figura1;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class EncontrarLCA {

    private static HMD hmdSolucao;
    private static Modulo moduloEntidade1;
    private static Modulo moduloEntidade2;

    public static void main(String[] args) {

        Figura1 figura1 = new Figura1();
        HMD hmdFigura1 = figura1.hmd();

        hmdSolucao = hmdFigura1;

        //7, 4 = A0
        System.out.println(new Entidade("7",null));
        System.out.println(new Entidade("4",null));
        Modulo m = lca(new Entidade("7",null), new Entidade("4",null));

        // 7, 10 = A3
        /*System.out.println(new Entidade("7",null));
        System.out.println(new Entidade("10",null));
        Modulo m = lca(new Entidade("7",null), new Entidade("10",null));*/

        // 7, 6 = A4
        /*System.out.println(listaEntidadesA4.get(0).getNome());
        System.out.println(listaEntidadesA4.get(1).getNome());
        Modulo m = lca(listaEntidadesA4.get(0), listaEntidadesA4.get(1));*/

        System.out.println(m.getNome());

    }

    private static Modulo lca(Entidade entidade1, Entidade entidade2) {

        Modulo moduloLCA = null;

        System.out.println("modulos: "+ hmdSolucao.getModulos().get(0).getSubmodulos().size());

        for (Modulo modulo : hmdSolucao.getModulos()){
            System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
            moduloLCA = encontrarModulo(modulo, entidade1, entidade2);
            if(modulo.getSubmodulos() != null){
                for (Modulo submodulo : modulo.getSubmodulos()){
                    System.out.println("SubmoduloI: " + submodulo.getNome() + " - submódulosI: " + submodulo.getSubmodulos());
                    moduloLCA = encontrarModulo(submodulo, entidade1, entidade2);
                    if(submodulo.getSubmodulos() != null){
                        for (Modulo submoduloII : submodulo.getSubmodulos()) {
                            System.out.println("SubmoduloII: " + submoduloII.getNome() + " - submoduloII: " + submoduloII.getSubmodulos());
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
        System.out.println("MODULO:"+  modulo.getNome());

        for (Entidade entidade : modulo.getListaEntidades()){
            System.out.println("for Entidade: "+ entidade.getNome() + " - entidade1: " + entidade1.getNome() + " - entidade2: " + entidade2.getNome() );
            if (entidade.getNome().equals(entidade1.getNome())){
               moduloEntidade1 = modulo;
            }

            if (entidade.getNome().equals(entidade2.getNome())){
               moduloEntidade2 = modulo;
            }
        }

        if(moduloEntidade1 != null && moduloEntidade2 != null) {

            System.out.println("moduloEntidade1: "+ moduloEntidade1.getNome());
            System.out.println("moduloEntidade2: "+ moduloEntidade2.getNome());

            if (moduloEntidade1.getNome().equals(moduloEntidade2.getNome())){
                moduloLCA = modulo;
                System.out.println("if"+moduloLCA.getNome());
            } else {

                /*valida pai e filho*/
                if(moduloEntidade1.getSubmodulos() != null){
                    System.out.println("contains moduloEntidade1: "+ moduloEntidade1.getSubmodulos().contains(moduloEntidade2));
                    moduloLCA = moduloEntidade1;
                }else if(moduloEntidade2.getSubmodulos() != null) {
                    System.out.println("contains moduloEntidade2: "+ moduloEntidade2.getSubmodulos().contains(moduloEntidade1));
                    moduloLCA = moduloEntidade2;
                } else {
                    //moduloLCA = (Modulo) hmdSolucao.getModulos().get(0).getSubmodulos().toArray()[--i];
                    moduloLCA = hmdSolucao.getModulos().get(0);
                }
            }
        }
        return moduloLCA;
    }

}
