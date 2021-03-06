package org.example;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class Main {

  private static HMD hmdSolucao;

  public static void main(String[] args) {

    //Figura1
    Figura1 figura1 = new Figura1();
    hmdSolucao = figura1.hmd();

    //Figura18
        /*Figura18 figura18 = new Figura18();
        hmdSolucao = figura18.hmd();*/

    //System.out.println(hmdFigura);

    FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmdSolucao);

    double valorFormulaComplexidade = formulaComplexidade.executa();

    System.out.println("Fórmula da Complexidade: " + valorFormulaComplexidade);
    System.out.println("Esperado figura1 Prof: " + 362.92532577980467);
    System.out.println("Esperado figura1: " + 398.5);
    System.out.println("Esperado figura18: " + 208.37);
    System.out.println();

    /*listarModulos(hmdFigura.getModulos());*/

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

}
