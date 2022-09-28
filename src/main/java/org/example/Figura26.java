package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
0 - 1 2 3 4
1 - 2 3 4 5
2 - 3 4 6
3 - 4 7
4 - 7
5 - 6 7 8
6 - 7 8
7 - 8 9 10
8 - 10 12
9 - 10 11 12 13
10 - 11 12 13
11 - 12 13
12 - 13
13
14 - 2 4 15 16
15 - 17
16 - 15 18
17 - 14
18 - 19
19
*/
public class Figura26 {

  public static HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA0.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
    listaEntidadesA0.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA0.add(new Entidade("4", Arrays.asList(new Entidade("7"))));
    listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("7", Arrays.asList(new Entidade("8"), new Entidade("9"), new Entidade("10"))));
    listaEntidadesA0.add(new Entidade("8", Arrays.asList(new Entidade("10"), new Entidade("12"))));
    listaEntidadesA0.add(new Entidade("9", Arrays.asList(new Entidade("10"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA0.add(new Entidade("10", Arrays.asList(new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA0.add(new Entidade("11", Arrays.asList(new Entidade("12"), new Entidade("13"))));
    listaEntidadesA0.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
    listaEntidadesA0.add(new Entidade("13", null));
    listaEntidadesA0.add(new Entidade("14", Arrays.asList(new Entidade("2"), new Entidade("4"), new Entidade("15"), new Entidade("16"))));
    listaEntidadesA0.add(new Entidade("15", Arrays.asList(new Entidade("17"))));
    listaEntidadesA0.add(new Entidade("16", Arrays.asList(new Entidade("15"), new Entidade("18"))));
    listaEntidadesA0.add(new Entidade("17", Arrays.asList(new Entidade("14"))));
    listaEntidadesA0.add(new Entidade("18", Arrays.asList(new Entidade("19"))));
    listaEntidadesA0.add(new Entidade("19", null));


    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", null);

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.add(moduloA0);

    HMD hmdFigura26 = new HMD(modulos);

    return hmdFigura26;

  }
}
