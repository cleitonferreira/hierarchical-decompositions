package org.example;

import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figura4 {

    public static void main(String[] args) {


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


        List<Modulo> modulos = new ArrayList<Modulo>();
        modulos.add(new Modulo(listaEntidadesA2, "A0", "A2"));
        modulos.add(new Modulo(listaEntidadesA3, "A0", "A3"));

        HMD hmdFigura4 = new HMD(modulos);

        FormulaComplexidade.executa(hmdFigura4);

    }

}
