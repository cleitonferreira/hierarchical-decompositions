package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;


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
public class Figura2 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA1 = new ArrayList<>();
    listaEntidadesA1.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA1.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA1.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
    listaEntidadesA1.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA1.add(new Entidade("4", Arrays.asList(new Entidade("7"))));

    List<Entidade> listaEntidadesA2 = new ArrayList<>();
    listaEntidadesA2.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA2.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA2.add(new Entidade("7", Arrays.asList(new Entidade("8"), new Entidade("9"), new Entidade("10"))));
    listaEntidadesA2.add(new Entidade("8", Arrays.asList(new Entidade("10"), new Entidade("12"))));

    List<Entidade> listaEntidadesA3 = new ArrayList<>();
    listaEntidadesA3.add(new Entidade("9", Arrays.asList(new Entidade("10"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA3.add(new Entidade("10", Arrays.asList(new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA3.add(new Entidade("11", Arrays.asList(new Entidade("12"), new Entidade("13"))));
    listaEntidadesA3.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
    listaEntidadesA3.add(new Entidade("13", null));

    Modulo moduloA3 = new Modulo(listaEntidadesA3, "A3", null);
    Modulo moduloA2 = new Modulo(listaEntidadesA2, "A2", null);
    Modulo moduloA1 = new Modulo(listaEntidadesA1, "A1", null);
    Modulo moduloA0 = new Modulo(null, "A0", Arrays.asList(moduloA1, moduloA2, moduloA3));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA1, moduloA2, moduloA3));

    HMD hmdFigura2 = new HMD(modulos);

    return hmdFigura2;

  }
}
