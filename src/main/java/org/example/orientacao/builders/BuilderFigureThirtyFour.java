package org.example.orientacao.builders;

import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class BuilderFigureThirtyFour {

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

    n1.addOutgoingLink(n0);
    n2.addOutgoingLink(n1);
    n3.addOutgoingLink(n1);
    n4.addOutgoingLink(n1);
    n5.addOutgoingLink(n1);
    n6.addOutgoingLink(n1);
    n7.addOutgoingLink(n1);
    n8.addOutgoingLink(n1).addOutgoingLink(n2);
    n9.addOutgoingLink(n2);
    n10.addOutgoingLink(n1).addOutgoingLink(n6);
    n11.addOutgoingLink(n7);
    n12.addOutgoingLink(n7);
    n13.addOutgoingLink(n7);
    n14.addOutgoingLink(n2);
    n15.addOutgoingLink(n1).addOutgoingLink(n2).addOutgoingLink(n3).addOutgoingLink(n7).addOutgoingLink(n9);
    n16.addOutgoingLink(n4).addOutgoingLink(n5).addOutgoingLink(n7).addOutgoingLink(n11);
    n17.addOutgoingLink(n7).addOutgoingLink(n11).addOutgoingLink(n12).addOutgoingLink(n13);
    n18.addOutgoingLink(n1).addOutgoingLink(n14);


    Module a280270 = new Module("A280270").
        addNode(n14).
        addNode(n18);

    Module a280267 = new Module("A280267").
        addNode(n4).
        addNode(n5).
        addNode(n7).
        addNode(n11).
        addNode(n12).
        addNode(n13).
        addNode(n16).
        addNode(n17);

    Module a280269 = new Module("A280269").
        addNode(n2).
        addNode(n3).
        addNode(n8).
        addNode(n9).
        addNode(n15);

    Module a280271 = new Module("A280271").
        addNode(n0).
        addNode(n1).
        addNode(n6).
        addNode(n10).
        addModule(a280267).
        addModule(a280269);

    decomposition.getRoot().
        addModule(a280267).
        addModule(a280269).
        addModule(a280270).
        addModule(a280271);

    decomposition.prepare();
    return decomposition;
  }
}