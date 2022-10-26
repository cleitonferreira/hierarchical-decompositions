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
public class Figura33 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("0", null));
    listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("0"))));
    listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("1"))));
    listaEntidadesA0.add(new Entidade("10", Arrays.asList(new Entidade("1"), new Entidade("6"))));

    List<Entidade> listaEntidadesA280269 = new ArrayList<>();
    listaEntidadesA280269.add(new Entidade("2", Arrays.asList(new Entidade("1"))));
    listaEntidadesA280269.add(new Entidade("3", Arrays.asList(new Entidade("1"))));
    listaEntidadesA280269.add(new Entidade("8", Arrays.asList(new Entidade("1"), new Entidade("2"))));
    listaEntidadesA280269.add(new Entidade("9", Arrays.asList(new Entidade("2"))));
    listaEntidadesA280269.add(new Entidade("14", Arrays.asList(new Entidade("2"))));
    listaEntidadesA280269.add(new Entidade("15", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("7"), new Entidade("9"))));
    listaEntidadesA280269.add(new Entidade("18", Arrays.asList(new Entidade("1"), new Entidade("14"))));

    List<Entidade> listaEntidadesA280267 = new ArrayList<>();
    listaEntidadesA280267.add(new Entidade("4", Arrays.asList(new Entidade("1"))));
    listaEntidadesA280267.add(new Entidade("5", Arrays.asList(new Entidade("1"))));
    listaEntidadesA280267.add(new Entidade("7", Arrays.asList(new Entidade("1"))));
    listaEntidadesA280267.add(new Entidade("11", Arrays.asList(new Entidade("7"))));
    listaEntidadesA280267.add(new Entidade("12", Arrays.asList(new Entidade("7"))));
    listaEntidadesA280267.add(new Entidade("13", Arrays.asList(new Entidade("7"))));
    listaEntidadesA280267.add(new Entidade("16", Arrays.asList(new Entidade("4"), new Entidade("5"), new Entidade("7"), new Entidade("11"))));
    listaEntidadesA280267.add(new Entidade("17", Arrays.asList(new Entidade("7"), new Entidade("11"), new Entidade("12"), new Entidade("13"))));

    Modulo moduloA280267 = new Modulo(listaEntidadesA280267, "A280267", null);
    Modulo moduloA280269 = new Modulo(listaEntidadesA280269, "A280269", null);
    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", Arrays.asList(moduloA280267, moduloA280269));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA280267, moduloA280269));

    HMD hmdFigura33 = new HMD(modulos);

    return hmdFigura33;

  }
}
