package org.example.ils.core;

import java.util.Arrays;
import java.util.Random;

public class GeradorGrupos {

  public static int[] geradorSubmodulos(int[] valores) {
    /*Remover valores repetidos*/
    int totalGrupos = Arrays.stream(valores).distinct().toArray().length;

    Random rand = new Random();
    int[] subgrupos = new int[totalGrupos];

    for (int i = 0; i < totalGrupos; i++) {
      if (i > 1) {
        subgrupos[i] = rand.nextInt(i);
      }
    }
    return subgrupos;
  }
}
