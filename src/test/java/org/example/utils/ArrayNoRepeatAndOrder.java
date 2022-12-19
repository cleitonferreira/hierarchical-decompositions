package org.example.utils;

import java.util.Arrays;

public class ArrayNoRepeatAndOrder {

  public static void main(String[] args) {

    int[] ja = {9, 2, 1, 2, 3, 3, 4, 5, 6, 6, 8, 9};
    /*Remover valores repetidos*/
    int[] resultado = Arrays.stream(ja).distinct().toArray();

    /*Ordenar*/
    Arrays.sort(resultado);

    for (int i = 0; i < resultado.length; i++) {
      System.out.println(resultado[i]);
    }

  }

}
