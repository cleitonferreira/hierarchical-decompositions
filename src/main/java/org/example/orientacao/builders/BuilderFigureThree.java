package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Node;

/*
0 - 1 2 3 4
1 - 2 3 4 5
2 - 3 4 6
3 - 4 7
4 - 7
5 - 6 7 8
6 - 7 8
7 - 8 9 10
8 - 10 12
9 - 10 11 12 13
10 - 11 12 13
11 - 12 13
12 - 13
13
 */
public class BuilderFigureThree {

  public Decomposition build() {
    Decomposition decomposition = new Decomposition();

    Node n0 = new Node("n0");
    Node n1 = new Node("n1");
    Node n2 = new Node("n2");
    Node n3 = new Node("n3");
    Node n4 = new Node("n4");
    Node n5 = new Node("n5");
    Node n6 = new Node("n6");
    Node n7 = new Node("n7");
    Node n8 = new Node("n8");
    Node n9 = new Node("n9");
    Node n10 = new Node("n10");
    Node n11 = new Node("n11");
    Node n12 = new Node("n12");
    Node n13 = new Node("n13");

    n0.addOutgoingLink(n1).addOutgoingLink(n2).addOutgoingLink(n3).addOutgoingLink(n4);
    n1.addOutgoingLink(n2).addOutgoingLink(n3).addOutgoingLink(n4).addOutgoingLink(n5);
    n2.addOutgoingLink(n3).addOutgoingLink(n4).addOutgoingLink(n6);
    n3.addOutgoingLink(n4).addOutgoingLink(n7);
    n4.addOutgoingLink(n7);
    n5.addOutgoingLink(n6).addOutgoingLink(n7).addOutgoingLink(n8);
    n6.addOutgoingLink(n7).addOutgoingLink(n8);
    n7.addOutgoingLink(n8).addOutgoingLink(n9).addOutgoingLink(n10);
    n8.addOutgoingLink(n10).addOutgoingLink(n12);
    n9.addOutgoingLink(n10).addOutgoingLink(n11).addOutgoingLink(n12).addOutgoingLink(n13);
    n10.addOutgoingLink(n11).addOutgoingLink(n12).addOutgoingLink(n13);
    n11.addOutgoingLink(n12).addOutgoingLink(n13);
    n12.addOutgoingLink(n13);

    decomposition.getRoot().
        addNode(n0).
        addNode(n1).
        addNode(n2).
        addNode(n3).
        addNode(n4).
        addNode(n5).
        addNode(n6).
        addNode(n7).
        addNode(n8).
        addNode(n9).
        addNode(n10).
        addNode(n11).
        addNode(n12).
        addNode(n13);

    decomposition.prepare();
    return decomposition;
  }
}