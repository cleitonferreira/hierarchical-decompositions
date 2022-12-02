package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
0
1 - 0
2 - 1
3 - 1
4 - 1
5 - 1
6 - 1
7 - 1
8 - 1 2
9 - 2
10 - 1 6
11 - 7
12 - 7
13 - 7
14 - 2
15 - 1 2 3 7 9
16 - 4 5 7 11
17 - 7 11 12 13
18 - 1 14
*/
public class Figura32 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("0", null));
    listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("0"))));
    listaEntidadesA0.add(new Entidade("2", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("3", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("4", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("7", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("8", Arrays.asList(new Entidade("1"), new Entidade("2"))));
    listaEntidadesA0.add(new Entidade("9", Arrays.asList(new Entidade("2"))));
    listaEntidadesA0.add(new Entidade("10", Arrays.asList(new Entidade("1"), new Entidade("6"))));
    listaEntidadesA0.add(new Entidade("11", Arrays.asList(new Entidade("7"))));
    listaEntidadesA0.add(new Entidade("12", Arrays.asList(new Entidade("7"))));
    listaEntidadesA0.add(new Entidade("13", Arrays.asList(new Entidade("7"))));
    listaEntidadesA0.add(new Entidade("14", Arrays.asList(new Entidade("2"))));
    listaEntidadesA0.add(new Entidade("15", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("7"), new Entidade("9"))));
    listaEntidadesA0.add(new Entidade("16", Arrays.asList(new Entidade("4"), new Entidade("5"), new Entidade("7"), new Entidade("11"))));
    listaEntidadesA0.add(new Entidade("17", Arrays.asList(new Entidade("7"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA0.add(new Entidade("18", Arrays.asList(new Entidade("1"), new Entidade("14"))));

    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", null);

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.add(moduloA0);

    HMD hmdFigura32 = new HMD(modulos);

    return hmdFigura32;

  }
}
