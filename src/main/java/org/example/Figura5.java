package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Entradas
B0 B1
B0 B2
B0 0
B0 2
B1 8
B1 3
B1 9
B1 1
B2 4
B2 5
B2 10

    Links
    0 3
    2 1
    1 4 9
    3 8
    8 1 2
    9 3 5
    4 5 9
    5 10
*/
public class Figura5 {

    public static HMD hmd() {


        /*Figura 5*/
        List<Entidade> listaEntidadesA0 = new ArrayList<>();
        listaEntidadesA0.add(new Entidade("0", Arrays.asList(new Entidade("3"))));
        listaEntidadesA0.add(new Entidade("2", Arrays.asList(new Entidade("1"))));

        List<Entidade> listaEntidadesB1 = new ArrayList<>();
        listaEntidadesB1.add(new Entidade("1", Arrays.asList(new Entidade("4"), new Entidade("9"))));
        listaEntidadesB1.add(new Entidade("3", Arrays.asList(new Entidade("8"))));
        listaEntidadesB1.add(new Entidade("8", Arrays.asList(new Entidade("1"), new Entidade("2"))));
        listaEntidadesB1.add(new Entidade("9", Arrays.asList(new Entidade("3"), new Entidade("5"))));

        List<Entidade> listaEntidadesB2 = new ArrayList<>();
        listaEntidadesB2.add(new Entidade("4", Arrays.asList(new Entidade("5"), new Entidade("9"))));
        listaEntidadesB2.add(new Entidade("5", Arrays.asList(new Entidade("10"))));
        listaEntidadesB2.add(new Entidade("10", Arrays.asList(new Entidade("4"))));

        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.add(new Modulo(listaEntidadesA0, "A0",
                Arrays.asList(
                        new Modulo(listaEntidadesB1,"B1",
                                null),
                        new Modulo(listaEntidadesB2,"B2",
                                null))));

        HMD hmdFigura5 = new HMD(modulos);

        return hmdFigura5;


    }

}