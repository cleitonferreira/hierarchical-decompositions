package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class GeradorSolucaoTest {

  public static void main(String[] args) {

// declare & initialize numbers collection, random generator
    int length = 2;
    int[] numbers = new int[length];
    Random rand = new Random();

// Define range of values
    int min = 0;
    int max = 2;
    int soma = 0;
// Generate random number for each element in array
    for (int i = 0; i < length; i++) {
      if (soma < length && i > 0) {
        numbers[i] = rand.nextInt(max - min) + min;
      }

      soma += numbers[i];
    }
    System.out.println("A soma: " + soma);
// Print array of random numbers to console as string
    System.out.println(Arrays.toString(numbers));
  }
}
