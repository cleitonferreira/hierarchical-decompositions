package org.example;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class ConverterHMDs {

  private static HMD hmdSolucao;

  private static List<Modulo> lista;

  public static void main(String[] args) {

    lista = new ArrayList<>();

    //Figura1
    Figura figura = new Figura1();
    hmdSolucao = figura.hmd();

    List<Modulo> modulos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(hmdSolucao.getModulos());
    System.out.println("Módulos Nao Hierrarquico: " + modulos);
  }

  private static void listarModulos(List<Modulo> modulos) {
    if (modulos != null) {

      for (Modulo modulo : modulos) {
        System.out.println("Módulo: " + modulo.getNome() + " - submódulo: " + modulo.getSubmodulos());
        listarEntidade(modulo);
        if (modulo.getSubmodulos() != null) {
          System.out.println();
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
