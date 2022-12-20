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
public class Figura23 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));

    List<Entidade> listaEntidadesA1007 = new ArrayList<>();
    listaEntidadesA1007.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA1007.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
    listaEntidadesA1007.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA1007.add(new Entidade("4", Arrays.asList(new Entidade("7"))));

    List<Entidade> listaEntidadesA1398 = new ArrayList<>();
    listaEntidadesA1398.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA1398.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA1398.add(new Entidade("7", Arrays.asList(new Entidade("8"), new Entidade("9"), new Entidade("10"))));
    listaEntidadesA1398.add(new Entidade("8", Arrays.asList(new Entidade("10"), new Entidade("12"))));

    List<Entidade> listaEntidadesA13 = new ArrayList<>();
    listaEntidadesA13.add(new Entidade("9", Arrays.asList(new Entidade("10"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA13.add(new Entidade("10", Arrays.asList(new Entidade("11"), new Entidade("12"), new Entidade("13"))));
    listaEntidadesA13.add(new Entidade("11", Arrays.asList(new Entidade("12"), new Entidade("13"))));
    listaEntidadesA13.add(new Entidade("12", Arrays.asList(new Entidade("13"))));
    listaEntidadesA13.add(new Entidade("13", null));

    Modulo moduloA13 = new Modulo(listaEntidadesA13, "A13", null);
    Modulo moduloA1398 = new Modulo(listaEntidadesA1398, "A1398", Arrays.asList(moduloA13));
    Modulo moduloA1007 = new Modulo(listaEntidadesA1007, "A1007", Arrays.asList(moduloA1398));
    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", Arrays.asList(moduloA1007));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA1007, moduloA1398, moduloA13));

    HMD hmdFigura23 = new HMD(modulos);

    return hmdFigura23;

  }
}
