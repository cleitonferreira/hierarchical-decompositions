package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class Figura4 {

    public static HMD hmd() {

        List<Entidade> listaEntidadesA2 = new ArrayList<>();
        listaEntidadesA2.add(new Entidade("0", Arrays.asList(new Entidade("1"), new Entidade("2"))));
        listaEntidadesA2.add(new Entidade("1", null));
        listaEntidadesA2.add(new Entidade("2", Arrays.asList(new Entidade("3"))));
        listaEntidadesA2.add(new Entidade("3", Arrays.asList(new Entidade("1"))));

        List<Entidade> listaEntidadesA3 = new ArrayList<>();
        listaEntidadesA3.add(new Entidade("4", Arrays.asList(new Entidade("3"), new Entidade("7"))));
        listaEntidadesA3.add(new Entidade("5", Arrays.asList(new Entidade("7"))));
        listaEntidadesA3.add(new Entidade("6", Arrays.asList(new Entidade("5"))));
        listaEntidadesA3.add(new Entidade("7", null));

        Modulo moduloA3 = new Modulo(listaEntidadesA3, "A3", null);
        Modulo moduloA2 = new Modulo(listaEntidadesA2, "A2", null);
        Modulo moduloA0 = new Modulo(null, "A0", Arrays.asList(moduloA2, moduloA3));

        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.addAll(Arrays.asList(moduloA0, moduloA2, moduloA3));

        HMD hmdFigura4 = new HMD(modulos);

        return hmdFigura4;

    }

}
