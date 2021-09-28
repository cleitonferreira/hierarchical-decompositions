package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
A0 A1
A0 A3
A1 A2
A3 A4
A1 0
A2 2
A2 3
A2 4
A0 1
A0 5
A0 12
A0 13
A3 8
A3 9
A3 10
A3 11
A4 6
A4 7

Links

0 1 3 4 2
1 3 2 4 5
3 7 4
2 4 6
4 7
5 6 7 8
6 7 8
7 8 9 10
8 10 12
9 10 11 12 13
10 11 13 12
11 12 13
12 13

*/
public class Figura1 {

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
        modulos.add(new Modulo(listaEntidadesA2, "A0", "A1"));
        modulos.add(new Modulo(listaEntidadesA0, "A0", "A3"));
        modulos.add(new Modulo(listaEntidadesA1, "A1", "A2"));
        modulos.add(new Modulo(listaEntidadesA4, "A3", "A4"));

        HMD hmdFigura1 = new HMD(modulos);

        FormulaComplexidade.executa(hmdFigura1);


/*        System.out.println("ListaEntidades: " + hmdFigura1.getModulos().size());
        System.out.println("ListaEntidades: " + hmdFigura1.getModulos().get(0).getListaEntidades().size());

        for (Modulo modulo : hmdFigura1.getModulos()){
            System.out.println("Módulo: " + modulo.getNome() + " - submódulo" + modulo.getSubmodulo());
            for (Entidade entidade : modulo.getListaEntidades()) {
                System.out.println("Entidade: " + entidade.getNome());
                if(entidade.getLinks() != null){
                    for(Entidade links : entidade.getLinks()) {
                        System.out.println("Links: " + links.getNome());
                    }
                }


            }
        }
*/




    }

}
