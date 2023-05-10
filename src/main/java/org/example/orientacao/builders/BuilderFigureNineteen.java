package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class BuilderFigureNineteen {

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

    n0.addOutgoingLink(n1).addOutgoingLink(n2).addOutgoingLink(n3).addOutgoingLink(n4);
    n1.addOutgoingLink(n2).addOutgoingLink(n3).addOutgoingLink(n4).addOutgoingLink(n5);
    n2.addOutgoingLink(n3).addOutgoingLink(n4).addOutgoingLink(n6);
    n3.addOutgoingLink(n4).addOutgoingLink(n7);
    n4.addOutgoingLink(n7);
    n5.addOutgoingLink(n6).addOutgoingLink(n7).addOutgoingLink(n8);
    n6.addOutgoingLink(n7).addOutgoingLink(n8);
    n7.addOutgoingLink(n8);

    Module a7 = new Module("A7").
        addNode(n0).
        addNode(n2);

    Module a8 = new Module("A8").
        addNode(n4).
        addNode(n6).
        addNode(n7);

    Module a197 = new Module("A197").
        addNode(n5).
        addNode(n8);

    Module a652 = new Module("A652").
        addNode(n1).
        addNode(n3);

    decomposition.getRoot().
        addModule(a7).
        addModule(a8).
        addModule(a197).
        addModule(a652);

    decomposition.prepare();
    return decomposition;
  }
}