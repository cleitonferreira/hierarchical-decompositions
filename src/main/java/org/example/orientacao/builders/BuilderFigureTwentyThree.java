package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class BuilderFigureTwentyThree {

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

    Module a13 = new Module("A13").
        addNode(n9).
        addNode(n10).
        addNode(n11).
        addNode(n12).
        addNode(n13);

    Module a1398 = new Module("A1398").
        addNode(n5).
        addNode(n6).
        addNode(n7).
        addNode(n8).
        addModule(a13);

    Module a1007 = new Module("A1007").
        addNode(n0).
        addNode(n1).
        addNode(n2).
        addNode(n3).
        addNode(n4)
        .addModule(a1398);

    decomposition.getRoot().
        addNode(n5).
        addNode(n6).
        addNode(n7).
        addNode(n8).
        addModule(a1007);

    decomposition.prepare();
    return decomposition;
  }
}
