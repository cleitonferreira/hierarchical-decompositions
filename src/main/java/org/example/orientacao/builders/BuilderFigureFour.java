package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class BuilderFigureFour {

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

    n0.addOutgoingLink(n1).addOutgoingLink(n2);
    n2.addOutgoingLink(n3);
    n3.addOutgoingLink(n1);
    n4.addOutgoingLink(n3).addOutgoingLink(n7);
    n5.addOutgoingLink(n7);
    n6.addOutgoingLink(n5);

    Module a3 = new Module("A3").
        addNode(n4).
        addNode(n5).
        addNode(n6).
        addNode(n7);

    Module a2 = new Module("A2").
        addNode(n0).
        addNode(n1).
        addNode(n2).
        addNode(n3);

    decomposition.getRoot().
        addModule(a2).
        addModule(a3);

    decomposition.prepare();
    return decomposition;
  }
}