package org.example.orientacao.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Decomposition {

  private @Getter
  Module root;

  public Decomposition() {
    root = new Module("root");
  }

  public void prepare() {
    root.buildIncomingLinks();
  }

  public double psi() {
    List<Module> modules = new ArrayList<Module>();
    modules.add(root);

    // add os modulos
    root.getAllModules(modules);

    double result = 0.0;

		for (Module module : modules) {
			result += module.psi();
		}

    return result;
  }

  public Module getModuleByName(String name) {
    return root.getModuleByName(name);
  }

  public Node getNodeByName(String name) {
    return root.getNodeByName(name);
  }
}