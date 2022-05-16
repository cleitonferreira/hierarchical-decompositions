package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

/*
A0

Links

0 1 2 3 4
1 2 3 4 5
2 3 4 6
3 4 7
4 7
5 6 7 8
6 7 8
7 8
8

*/
public class Figura18 {

    public static HMD hmd() {

        List<Entidade> listaEntidadesA0 = new ArrayList<>();
        listaEntidadesA0.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"), new Entidade("3"), new Entidade("4"))));
        listaEntidadesA0.add(new Entidade("1", Arrays.asList(new Entidade("2"), new Entidade("3"), new Entidade("4"), new Entidade("5"))));
        listaEntidadesA0.add(new Entidade("2", Arrays.asList(new Entidade("3"), new Entidade("4"), new Entidade("6"))));
        listaEntidadesA0.add(new Entidade("3", Arrays.asList(new Entidade("4"), new Entidade("7"))));
        listaEntidadesA0.add(new Entidade("4", Arrays.asList(new Entidade("7"))));
        listaEntidadesA0.add(new Entidade("5", Arrays.asList(new Entidade("6"), new Entidade("7"), new Entidade("8"))));
        listaEntidadesA0.add(new Entidade("6", Arrays.asList(new Entidade("7"), new Entidade("8"))));
        listaEntidadesA0.add(new Entidade("7", Arrays.asList(new Entidade("8"))));
        listaEntidadesA0.add(new Entidade("8", null));


        Modulo moduloA0 = new Modulo(listaEntidadesA0, "A0", null);

        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.add(moduloA0);

        HMD hmdFigura18 = new HMD(modulos);

        return hmdFigura18;

    }

}
