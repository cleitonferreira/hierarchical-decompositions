package org.example.ils;

import org.example.Figura18;
import org.example.ils.constructive.ConstrutiveAbstract;
import org.example.ils.constructive.ConstrutiveAglomerativeMQ;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

import java.text.DecimalFormat;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MainILS {

    private static HMD hmdSolucao;

    public static void main(String[] args) throws Exception {

        //Figura1
        /*Figura1 figura = new Figura1();
        hmdSolucao = figura.hmd();*/

        //Figura18
        Figura18 figura = new Figura18();
        hmdSolucao = figura.hmd();

        DecimalFormat df4 = new DecimalFormat("0.0000");
        ConstrutiveAbstract constructor = new ConstrutiveAglomerativeMQ();
        //ConstrutiveAbstract constructor = new ConstrutiveRandom();

        long startTimestamp = System.currentTimeMillis();

        IteratedLocalSearch ils = new IteratedLocalSearch(constructor, hmdSolucao, 100_000);
        ils.execute();

        long finishTimestamp = System.currentTimeMillis();
        long seconds = (finishTimestamp - startTimestamp);

        long memory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        System.out.println(padLeft(figura.getClass().getSimpleName(), 20) + " " + padRight("" + hmdSolucao.getModulos().size(), 10) + " " + padRight(df4.format(ils.getBestFitness()), 10) + " " + padRight("" + seconds, 10) + " ms " + padRight("" + memory, 10) + " MB");

    }

    private static void listarModulos(List<Modulo> modulos) {

        if (modulos != null) {

            for (Modulo modulo : modulos) {
                System.out.println(
                        "Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
                listarEntidade(modulo);
                if (modulo.getSubmodulos() != null) {
                    listarModulos(modulo.getSubmodulos().stream().collect(toList()));
                }
            }
        }
    }

    private static void listarEntidade(Modulo modulo) {
        if (modulo.getListaEntidades() != null) {
            for (Entidade entidade : modulo.getListaEntidades()) {
                System.out.println("Entidade: " + entidade.getNome());
                if (entidade.getLinks() != null) {
                    for (Entidade links : entidade.getLinks()) {
                        System.out.println("Links: " + links.getNome());
                    }
                }
            }
        }
    }

    public static String padLeft(String s, int length)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        while (sb.length() < length)
            sb.append(' ');

        return sb.toString();
    }

    public static String padRight(String s, int length)
    {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length - s.length())
            sb.append(' ');

        sb.append(s);
        return sb.toString();
    }

}
