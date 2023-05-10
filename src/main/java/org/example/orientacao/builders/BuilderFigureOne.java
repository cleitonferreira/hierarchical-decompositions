package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

/**
 * Class that builds a decomposition representing Figure 1
 *
 * @author User
 */
public class BuilderFigureOne {

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
    n3.addOutgoingLink(n7).addOutgoingLink(n4);
    n4.addOutgoingLink(n7);
    n5.addOutgoingLink(n7).addOutgoingLink(n6).addOutgoingLink(n8);
    n6.addOutgoingLink(n7).addOutgoingLink(n8);
    n7.addOutgoingLink(n8).addOutgoingLink(n9).addOutgoingLink(n10);
    n8.addOutgoingLink(n10).addOutgoingLink(n12);
    n9.addOutgoingLink(n10).addOutgoingLink(n11).addOutgoingLink(n12).addOutgoingLink(n13);
    n10.addOutgoingLink(n11).addOutgoingLink(n12).addOutgoingLink(n13);
    n11.addOutgoingLink(n12).addOutgoingLink(n13);
    n12.addOutgoingLink(n13);

    Module a2 = new Module("A2").
        addNode(n2).
        addNode(n3).
        addNode(n4);

    Module a1 = new Module("A1").
        addNode(n0).
        addModule(a2);

    Module a4 = new Module("A4").
        addNode(n6).
        addNode(n7);

    Module a3 = new Module("A3").
        addNode(n8).
        addNode(n9).
        addNode(n10).
        addNode(n11).
        addModule(a4);

    decomposition.getRoot().
        addNode(n1).
        addNode(n5).
        addNode(n12).
        addNode(n13).
        addModule(a1).
        addModule(a3);

    decomposition.prepare();
    return decomposition;
  }
}