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
public class Figura20 {

  public static HMD hmd() {

    List<Entidade> listaEntidadesA3 = new ArrayList<>();
    listaEntidadesA3.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
    listaEntidadesA3.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));

    List<Entidade> listaEntidadesA6 = new ArrayList<>();
    listaEntidadesA6.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
    listaEntidadesA6.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
    listaEntidadesA6.add(new Entidade("8", null));

    List<Entidade> listaEntidadesA2822 = new ArrayList<>();
    listaEntidadesA2822.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
    listaEntidadesA2822.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
    listaEntidadesA2822.add(new Entidade("4", Arrays.asList(new Entidade("7"))));
    listaEntidadesA2822.add(new Entidade("7", Arrays.asList(new Entidade("8"))));

    Modulo moduloA2822 = new Modulo(listaEntidadesA2822, "A2822", null);
    Modulo moduloA6 = new Modulo(listaEntidadesA6, "A6", null);
    Modulo moduloA3 = new Modulo(listaEntidadesA3, "A3", Arrays.asList(moduloA6));
    Modulo moduloA0 = new Modulo(null, "A0", Arrays.asList(moduloA3, moduloA6, moduloA2822));

    List<Modulo> modulos = new ArrayList<Modulo>();
    modulos.addAll(Arrays.asList(moduloA0, moduloA3, moduloA6, moduloA2822));

    HMD hmdFigura20 = new HMD(modulos);

    return hmdFigura20;

  }

}
