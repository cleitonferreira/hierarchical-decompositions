package org.example;

import java.util.Arrays;
import java.util.Random;

public class GeradorSolucaoIITest {

  public static void main(String[] args) {

// declare & initialize numbers collection, random generator
    int length = 5;
    int[] numbers = new int[length];
    Random rand = new Random();

// Define range of values
    int soma = 0;
// Generate random number for each element in array
    for (int i = 0; i < length; i++) {
      if (i > 1) {
        //int posicao = i + 1;
        numbers[i] = rand.nextInt(i);
      }
    }
// Print array of random numbers to console as string
    System.out.println(Arrays.toString(numbers));

    /*Exemplo: gerando uma solu��o de 5 posi��es
    * 1 posi��o: 0 possibilidade
    * 2 posi��o: 0 possibilidade
    * 3 posi��o: 0 ou 1 possibilidades
    * 4 posi��o: 0 ou 1 ou 2 possibilidades
    * 5 posi��o: 0 ou 1 ou 2 ou 3 possibilidades
    * */




  }
}
