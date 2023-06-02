package org.example.ils.core;

import java.util.Arrays;
import java.util.Objects;
import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;

public class ConverterProblemaOrientacao {

  private static Decomposition solucao;

  public static Decomposition converterProblemaParaDecomposition(Problema problema, int[] valores, int[] submodulos) {
    Module modulo = null;
    //List<Modulo> modulos = new ArrayList<Modulo>();
    solucao = new Decomposition();

    /*Remover valores repetidos*/
    int[] resultado = Arrays.stream(valores).distinct().toArray();
    /*Ordenar*/
    Arrays.sort(resultado);

    for (int a = 0; a < resultado.length; a++) {
      for (int b = 0; b < submodulos.length; b++) {

        //int verificaModulo = getVerificaModulo(modulos, resultado[a]);

        if (a == b) {

          Module submodulo = criaSubmodulos(problema, valores, b);

          if(b != 0) {
            solucao.getRoot().addModule(submodulo);
          }
        }
      }
    }
    solucao.prepare();
    return solucao;
  }

  private static Module criaSubmodulos(Problema problema, int[] valores,
      int modulo) {
    Module submodulos = new Module("modulo-" + modulo);

    for (int a = 0; a < valores.length; a++) {
      if (Objects.nonNull(problema.getClassCount())) {
        for (int i = 0; i < problema.getClassCount(); i++) {
          if ((valores[i] == modulo) && modulo == a) {

            Node node = new Node(String.valueOf(i));

            for (int l = 0; l < problema.getListaDependenciasPara()[i].length; l++) {
              if (problema.getQtdDependenciasPara()[i] > l) {
                int value = problema.getListaDependenciasPara()[i][l];
                node.addOutgoingLink(new Node(String.valueOf(value)));
              }
            }
            if(modulo == 0) {
              solucao.getRoot().addNode(node);
            } else {
              submodulos.addNode(node);
            }


          }
        }
      }
    }
    /*System.out.println(listaEntidades);*/
    return submodulos;
  }
}
