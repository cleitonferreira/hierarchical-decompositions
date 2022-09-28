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
*/
public class Figura24 {

  public static HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("7", Arrays.asList(new Entidade("8"), new Entidade("9"), new Entidade("10"))));
    listaEntidadesA0.add(new Entidade("8", Arrays.asList(new Entidade("10"), new Entidade("12"))));

    List<Entidade> listaEntidadesA22046 = new ArrayList<>();
    listaEntidadesA22046.add(new Entidade("9", Arrays.asList(new Entidade("10"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA22046.add(new Entidade("10", Arrays.asList(new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA22046.add(new Entidade("11", Arrays.asList(new Entidade("12"), new Entidade("13"))));
    listaEntidadesA22046.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
    listaEntidadesA22046.add(new Entidade("13", null));

    List<Entidade> listaEntidadesA33818 = new ArrayList<>();
    listaEntidadesA33818.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA33818.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA33818.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
    listaEntidadesA33818.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA33818.add(new Entidade("4", Arrays.asList(new Entidade("7"))));

    Modulo moduloA33818 = new Modulo(listaEntidadesA33818, "A33818", null);
    Modulo moduloA22046 = new Modulo(listaEntidadesA22046, "A22046", null);
    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", Arrays.asList(moduloA22046, moduloA33818));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA22046, moduloA33818));

    HMD hmdFigura24 = new HMD(modulos);

    return hmdFigura24;

  }
}
