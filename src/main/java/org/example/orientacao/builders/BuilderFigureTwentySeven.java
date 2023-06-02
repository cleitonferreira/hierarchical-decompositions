package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class BuilderFigureTwentySeven {

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
    Node n14 = new Node("n14");
    Node n15 = new Node("n15");
    Node n16 = new Node("n16");
    Node n17 = new Node("n17");
    Node n18 = new Node("n18");
    Node n19 = new Node("n19");

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
    n14.addOutgoingLink(n15).addOutgoingLink(n16);
    n15.addOutgoingLink(n17);
    n16.addOutgoingLink(n15).addOutgoingLink(n18);
    n17.addOutgoingLink(n14);
    n18.addOutgoingLink(n19);

    Module a2114 = new Module("A2114").
        addNode(n1).
        addNode(n3).
        addNode(n8).
        addNode(n9);

    Module a61997 = new Module("A61997").
        addNode(n0).
        addNode(n1).
        addNode(n2).
        addNode(n3).
        addNode(n4).
        addModule(a2114);

    Module a21198 = new Module("A21198").
        addNode(n9).
        addNode(n10).
        addNode(n11).
        addNode(n12).
        addNode(n13);

    decomposition.getRoot().
        addNode(n5).
        addNode(n6).
        addNode(n7).
        addNode(n8).
        addModule(a21198).
        addModule(a61997).
        addModule(a2114);

    decomposition.prepare();
    return decomposition;
  }
}