package org.example;

// Java Program to add an element in an Array

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

class MainCollection {

  public static void main(String[] args)
  {
// creating an empty LinkedList
    Collection<String> list = new ArrayList<String>();
    Collection<Integer> listNumer = new ArrayList<Integer>();
    Random rand = new Random();

    // use add() method to add elements in the list
    list.add("Geeks");
    list.add("for");
    list.add("Geeks");

    for (int i = 1; i < 10; i++) {
      listNumer.add(rand.nextInt(i));
    }

    // Output the present list
    System.out.println("The list is: " + list);
    System.out.println("The listNumer is: " + listNumer);

    // Adding new elements to the end
    list.add("Last");
    list.add("Element");

    // printing the new list
    System.out.println("The new List is: " + list);
  }
}

