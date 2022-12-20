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
7 - 8
8
*/
public class Figura19 implements Figura {

  public HMD hmd() {

    List<Entidade> listaEntidadesA7 = new ArrayList<>();
    listaEntidadesA7.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"),new Entidade("4"))));
    listaEntidadesA7.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));

    List<Entidade> listaEntidadesA8 = new ArrayList<>();
    listaEntidadesA8.add(new Entidade("4", Arrays.asList(new Entidade("7"))));
    listaEntidadesA8.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA8.add(new Entidade("7", Arrays.asList(new Entidade("8"))));

    List<Entidade> listaEntidadesA197 = new ArrayList<>();
    listaEntidadesA197.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA197.add(new Entidade("8", null));

    List<Entidade> listaEntidadesA652 = new ArrayList<>();
    listaEntidadesA652.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA652.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));

    Modulo moduloA7 = new Modulo(listaEntidadesA7, "A7", null);
    Modulo moduloA8 = new Modulo(listaEntidadesA8, "A8", null);
    Modulo moduloA197 = new Modulo(listaEntidadesA197, "A197", null);
    Modulo moduloA652 = new Modulo(listaEntidadesA652, "A652", null);
    Modulo moduloA0 = new Modulo(null, "A0", Arrays.asList(moduloA7, moduloA8, moduloA197, moduloA652));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA7, moduloA8, moduloA197, moduloA652));

    HMD hmdFigura19 = new HMD(modulos);

    return hmdFigura19;


  }
}
