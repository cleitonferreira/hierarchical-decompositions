package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

/**
 * Class that builds a decomposition representing the last example in the paper
 *
 * @author User
 */
public class BuilderExample {

  public Decomposition build() {
    Decomposition decomposition = new Decomposition();

    Node n0 = new Node("n0");
    Node n1 = new Node("n1");
    Node n2 = new Node("n2");
    Node n3 = new Node("n3");
    Node n4 = new Node("n4");
    Node n5 = new Node("n5");
    Node n8 = new Node("n8");
    Node n9 = new Node("n9");
    Node n10 = new Node("n10");

    n0.addOutgoingLink(n3);
    n2.addOutgoingLink(n1);
    n1.addOutgoingLink(n4);
    n1.addOutgoingLink(n9);

    Module b1 = new Module("B1").
        addNode(n1).
        addNode(n3).
        addNode(n8).
        addNode(n9);

    Module b2 = new Module("B2").
        addNode(n4).
        addNode(n5).
        addNode(n10);

    decomposition.getRoot().
        addNode(n0).
        addNode(n2).
        addModule(b1).
        addModule(b2);

    decomposition.prepare();
    return decomposition;
  }
}