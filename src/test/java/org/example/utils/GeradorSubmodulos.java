package org.example.utils;

import java.util.Arrays;
import org.example.ils.core.GeradorGrupos;

public class GeradorSubmodulos {

  public static void main(String[] args) {
    int[] valores = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1};

    int[] submodulos = GeradorGrupos.geradorSubmodulos(valores);
    System.out.println("Submódulos: "+Arrays.toString(submodulos));
  }

}
