package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Node;

/**
 * Class that builds a decomposition representing Figure 18
 *
 * @author User
 */
public class BuilderFigureEighteen {

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

    decomposition.getRoot().
        addNode(n0).
        addNode(n1).
        addNode(n2).
        addNode(n3).
        addNode(n4).
        addNode(n5).
        addNode(n6).
        addNode(n7).
        addNode(n8);

    decomposition.prepare();
    return decomposition;
  }
}