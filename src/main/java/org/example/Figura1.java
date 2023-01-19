package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

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
public class Figura1 implements Figura {

    public HMD hmd() {

        List<Entidade> listaEntidadesA0 = new ArrayList<>();
        listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
        listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
        listaEntidadesA0.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
        listaEntidadesA0.add(new Entidade("13", null));

        List<Entidade> listaEntidadesA1 = new ArrayList<>();
        listaEntidadesA1.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));

        List<Entidade> listaEntidadesA2 = new ArrayList<>();
        listaEntidadesA2.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
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

        Modulo moduloA4 = new Modulo(listaEntidadesA4, "A4", null);
        Modulo moduloA3 = new Modulo(listaEntidadesA3, "A3", Arrays.asList(moduloA4));
        Modulo moduloA2 = new Modulo(listaEntidadesA2, "A2", null);
        Modulo moduloA1 = new Modulo(listaEntidadesA1, "A1", Arrays.asList(moduloA2));
        Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", Arrays.asList(moduloA1, moduloA3));

        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.addAll(Arrays.asList(moduloA0));

        HMD hmdFigura1 = new HMD(modulos);

        return hmdFigura1;

    }

}
