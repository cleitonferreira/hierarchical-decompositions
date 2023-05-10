package org.example.orientacao.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a node Classe que representa um nó
 *
 * @author User
 */
public class Node {

  private @Getter
  String name;
  private @Getter
  @Setter
  Module parent;
  private List<Node> outgoingLinks;
  private List<Node> incomingLinks;

  /**
   * Initializes the node Inicializa o nó
   */
  public Node(String name) {
    this.name = name;
    this.parent = null;
    this.outgoingLinks = new ArrayList<Node>();
    this.incomingLinks = new ArrayList<Node>();
  }

  /**
   * Adds a link starting in the node Adiciona um link começando no nó
   */
  public Node addOutgoingLink(Node target) {
    outgoingLinks.add(target);
    return this;
  }

  /**
   * Returns all links starting in the node Retorna todos os links começando no nó
   */
  public Iterable<Node> getOutgoingLinks() {
    return outgoingLinks;
  }

  /**
   * Returns the number of links starting in the node Retorna o número de links começando no nó
   */
  public int getOutDegree() {
    return outgoingLinks.size();
  }

  /**
   * Adds a link terminating in the node Adiciona um link que termina no nó
   */
  public void addIncomingLink(Node node) {
    this.incomingLinks.add(node);
  }

  /**
   * Returns all links terminating in the node Retorna todos os links que terminam no nó
   */
  public Iterable<Node> getIncomingLinks() {
    return incomingLinks;
  }

  /**
   * Returns the number of links terminating in the node Retorna o número de links que terminam no
   * nó
   */
  public int getInDegree() {
    return incomingLinks.size();
  }

  /**
   * Determines whether the node is connected Determina se o nó está conectado
   */
  public boolean isConnected() {
    return !incomingLinks.isEmpty() || !outgoingLinks.isEmpty();
  }
}