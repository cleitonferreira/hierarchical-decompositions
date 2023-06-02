package org.example.orientacao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a module containing nodes Classe que representa um módulo contendo nós
 *
 * @author User
 */
public class Module {

  private @Getter
  String name;
  private @Getter
  @Setter
  Module parent;
  private List<Module> modules;
  private List<Node> nodes;

  /**
   * Initializes the module Inicializa o módulo
   */
  public Module(String name) {
    this.name = name;
    this.parent = null;
    this.modules = new ArrayList<Module>();
    this.nodes = new ArrayList<Node>();
  }

  /**
   * Adds a submodule to the module Adiciona um submódulo ao módulo
   */
  public Module addModule(Module module) {
    modules.add(module);
    module.setParent(this);
    return this;
  }

  /**
   * Returns the number of submodules Retorna o número de submódulos
   */
  public int countModules() {
    return modules.size();
  }

  /**
   * Returns the all numbers of modules Retorna o número de todos de módulos
   */
  public int countAllModules() {
    return (int) (countModules() + StreamSupport.stream(getModules().spliterator(), false).count());
  }

  /**
   * Returns a submodule, given its index Retorna um submódulo, dado seu índice
   */
  public Module getModuleByIndex(int index) {
    return modules.get(index);
  }

  /**
   * Returns a module, given its name Retorna um módulo, dado seu nome
   */
  public Module getModuleByName(String name) {
		if (this.name.compareToIgnoreCase(name) == 0) {
			return this;
		}

    for (Module module : modules) {
      Module submodule = module.getModuleByName(name);

			if (submodule != null) {
				return submodule;
			}
    }

    return null;
  }

  /**
   * Returns all submodules Retorna todos os submódulos
   */
  public Iterable<Module> getModules() {
    return modules;
  }

  /**
   * Adds a node into the module Adiciona um nó ao módulo
   */
  public Module addNode(Node node) {
    nodes.add(node);
    node.setParent(this);
    return this;
  }

  /**
   * Returns the number of node in the module Retorna o número de nós no módulo
   */
  public int countNodes() {
    return nodes.size();
  }

  /**
   * Returns a node, given its index Retorna um nó, dado seu índice
   */
  public Node getNodeByIndex(int index) {
    return nodes.get(index);
  }

  /**
   * Returns a node, given its name Retorna um nó, dado seu nome
   */
  public Node getNodeByName(String name) {
    for (Node node : nodes) {
			if (node.getName().compareToIgnoreCase(name) == 0) {
				return node;
			}
    }

    for (Module module : modules) {
      Node node = module.getNodeByName(name);

			if (node != null) {
				return node;
			}
    }

    return null;
  }

  /**
   * Builds the incoming links of the nodes in the module and its submodules Constrói os links de
   * entrada dos nós no módulo e seus submódulos
   */
  public void buildIncomingLinks() {
    for (Node node : nodes) {
      for (Node outgoingNode : node.getOutgoingLinks()) {
        outgoingNode.addIncomingLink(node);
      }
    }

    for (Module module : modules) {
      module.buildIncomingLinks();
    }
  }

  /**
   *
   */
  public double psi() {
    int unconnectedEntities = countUnconnectedEntities();
    double result = codeLength(unconnectedEntities + 1);

    int connectedEntities = countConnectedEntities();
    result += codeLength(connectedEntities + 1);

    //TODO verficar com o Prof
    int submodules = modules.size();
    result += codeLength(submodules + 1);

    result += calculateFrequencyComponent();
    result += calculateDepthComponent();
    return result;
  }

  /**
   * private double calculateFrequencyComponent()
   */
  private double calculateFrequencyComponent() {
    double result = 0;
    int totalFrequency = calculateTotalFrequency();

    for (Node node : nodes) {
      if (node.isConnected()) {
        double nodeFrequency = node.getInDegree() + 1;
        double logRatio = log2(nodeFrequency / totalFrequency);
        result += codeLength(-logRatio) - nodeFrequency * logRatio;
      }
    }

    for (Module module : modules) {
      double moduleFrequency = module.calculateInDegree() + 1;
      double logRatio = log2(moduleFrequency / totalFrequency);
      result += codeLength(-logRatio) - moduleFrequency * logRatio;
    }

    return result;
  }

  public int calculateTotalFrequency() {
    int result = 0;

    for (Node node : nodes) {
			if (node.isConnected()) {
				result += node.getInDegree() + 1;
			}
    }

    for (Module module : modules) {
      result += module.calculateInDegree() + 1;
    }

    return result;
  }

  private int calculateInDegree() {
    int result = 0;

    for (Node node : nodes) {
      for (Node link : node.getIncomingLinks()) {
				if (!hasNode(link)) {
					result++;
				}
      }
    }

		for (Module module : modules) {
			result += module.calculateInDegree();
		}

    return result;
  }

  private double calculateDepthComponent() {
    double result = 0;

    for (Node node : nodes) {
      for (Node nodeLine : node.getOutgoingLinks()) {
        Module ancestor = leastCommonAncestor(node, nodeLine);
        int relDepth = ancestor.relativeDepth(node);
        result += codeLength(relDepth);
      }
    }

    return result;
  }

  /**
   * Returns the relative depth of a node regarding the current module Retorna a profundidade
   * relativa de um nó em relação ao módulo atual
   */
  public int relativeDepth(Node node) {
		if (nodes.contains(node)) {
			return 0;
		}

    for (Module m : modules) {
			if (m.hasNode(node)) {
				return m.relativeDepth(node) + 1;
			}
    }

    return 0;
  }

  /**
   * Returns the least common ancestor of two nodes Retorna o ancestral menos comum de dois nós
   */
  public Module leastCommonAncestor(Node node1, Node node2) {
    Module parent = node1.getParent();

    if(node2.getParent() != null) {
      while (!parent.hasNode(node2)) {
        parent = parent.getParent();
      }
    }

    return parent;
  }

  private boolean hasNode(Node node) {
		if (nodes.contains(node)) {
			return true;
		}

    for (Module module : modules) {
			if (module.hasNode(node)) {
				return true;
			}
    }

    return false;
  }

  private int countUnconnectedEntities() {
    int count = 0;

    for (Node node : nodes) {
			if (!node.isConnected()) {
				count++;
			}
    }

    return count;
  }

  private int countConnectedEntities() {
    int count = 0;

    for (Node node : nodes) {
			if (node.isConnected()) {
				count++;
			}
    }

    return count;
  }

  public double codeLength(double n) {
    //TODO verificar com o Prof o n = n + 1;
    n = n + 1;
    return log2(n) + 2 * log2(log2(n + 1)) + 1;
  }

  //TODO validar Math.log10(n) com teste unitário, o logaritmo senão utilizar return (Math.log(valor) / Math.log(base));
  public double log2(double n) {
    return Math.log10(n) / Math.log10(2);
  }

  public void getAllModules(List<Module> result) {
    for (Module module : modules) {
      result.add(module);
      module.getAllModules(result);
    }
  }
}