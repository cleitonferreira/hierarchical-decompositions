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
7 - 8
8
*/
public class Figura21 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA0 = new ArrayList<>();
    listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("7", Arrays.asList(new Entidade("8"))));
    listaEntidadesA0.add(new Entidade("8", null));

    List<Entidade> listaEntidadesA4118 = new ArrayList<>();
    listaEntidadesA4118.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA4118.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA4118.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
    listaEntidadesA4118.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA4118.add(new Entidade("4", Arrays.asList(new Entidade("7"))));

    Modulo moduloA4118 = new Modulo(listaEntidadesA4118, "A4118", null);
    Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", Arrays.asList(moduloA4118));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA4118));

    HMD hmdFigura21 = new HMD(modulos);

    return hmdFigura21;

  }
}
