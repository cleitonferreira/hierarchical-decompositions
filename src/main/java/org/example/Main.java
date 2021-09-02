package org.example;


import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

/*
A0 A2
A0 A3
A2 2
A2 3
A2 0
A2 1
A3 6
A3 7
A3 5
A3 4

Links

0 1 2
2 3
3 1
4 3 7
5 7
6 5


*/

        List<Entidade> listaEntidadesA2 = new ArrayList<>();
        listaEntidadesA2.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"))));
        listaEntidadesA2.add(new Entidade("1", null));
        listaEntidadesA2.add(new Entidade("2", Arrays.asList(new Entidade("3"))));
        listaEntidadesA2.add(new Entidade("3", Arrays.asList(new Entidade("1"))));

        List<Entidade> listaEntidadesA3 = new ArrayList<>();
        listaEntidadesA3.add(new Entidade("4", Arrays.asList(new Entidade("3"), new Entidade("7"))));
        listaEntidadesA3.add(new Entidade("5", Arrays.asList(new Entidade("7"))));
        listaEntidadesA3.add(new Entidade("6", Arrays.asList(new Entidade("5"))));
        listaEntidadesA3.add(new Entidade("7", null));


        Modulo moduloA2 = new Modulo(listaEntidadesA2, "A0", "A2");
        Modulo moduloA3 = new Modulo(listaEntidadesA2, "A0", "A3");

        HMD hmdFigura4 = new HMD();
        hmdFigura4.setModulo(moduloA2);
        hmdFigura4.setModulo(moduloA3);



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
        /*Figura 5*/
        List<Entidade> listaEntidadesB1 = new ArrayList<>();
        listaEntidadesB1.add(new Entidade("0", Arrays.asList(new Entidade("3"))));
        listaEntidadesB1.add(new Entidade("2", Arrays.asList(new Entidade("1"))));
        listaEntidadesB1.add(new Entidade("1", Arrays.asList(new Entidade("4"), new Entidade("9"))));
        listaEntidadesB1.add(new Entidade("3", Arrays.asList(new Entidade("8"))));
        listaEntidadesB1.add(new Entidade("8", Arrays.asList(new Entidade("1"), new Entidade("2"))));
        listaEntidadesB1.add(new Entidade("9", Arrays.asList(new Entidade("3"), new Entidade("5"))));

        List<Entidade> listaEntidadesB2 = new ArrayList<>();
        listaEntidadesB2.add(new Entidade("4", Arrays.asList(new Entidade("5"), new Entidade("9"))));
        listaEntidadesB2.add(new Entidade("5", Arrays.asList(new Entidade("10"))));
        listaEntidadesB2.add(new Entidade("10", Arrays.asList(new Entidade("4"))));


        Modulo moduloB1 = new Modulo(listaEntidadesB1, "B0", "B1");
        Modulo moduloB2 = new Modulo(listaEntidadesB2, "B0", "B2");

        HMD hmdFigura5 = new HMD();
        hmdFigura5.setModulo(moduloB1);
        hmdFigura5.setModulo(moduloB2);


     }

}
