package org.example.ils.constructive;

import org.example.ils.ClusterMetrics;
import org.example.model.HMD;

public class ConstrutiveAglomerativeMQ extends ConstrutiveAbstract {

  @Override
  public int[] createSolution(HMD hmd) {
    return createSolution(hmd, 1)[0];
  }

  private int[][] createSolution(HMD hmd, int quantity) {
    int[] solution = new int[hmd.getCountEntidades()];

    for (int index = 0; index < hmd.getCountEntidades(); index++) {
      solution[index] = index;
    }

    return aglomerateClustering(hmd, solution, quantity);
  }

  private int[][] aglomerateClustering(HMD hmd, int[] solution, int solutionsQuantity) {
    int[][] topSolutions = new int[solutionsQuantity][solution.length];
    Double[] topSolutionsMQ = new Double[solutionsQuantity];

    int n = hmd.getCountEntidades();
    ClusterMetrics cm = new ClusterMetrics(hmd, solution);

    // solucao de entrada e a melhor. Unica conhecida
    topSolutions[0] = solution;
    topSolutionsMQ[0] = cm.calculateMQ();

    int k = 1;
    while (n - k > 1) {
      // selecionar elementos para a aglutinacao
      int aglutinatei = -1;
      int aglutinatej = -1;
      Double currentMaxDelta = null;

      for (int auxi = 0; auxi < cm.getTotalClusteres(); auxi++) {
        for (int auxj = auxi + 1; auxj < cm.getTotalClusteres(); auxj++) {
          int i = cm.convertToClusterNumber(auxi);
          int j = cm.convertToClusterNumber(auxj);

          // verificar o delta da uniao desses dois clusteres
          double currentDelta = cm.calculateMergeClustersDelta(i, j);

          if (currentMaxDelta == null || currentDelta > currentMaxDelta) {
            aglutinatei = i;
            aglutinatej = j;
            currentMaxDelta = currentDelta;
          }
        }
      }

      // algutinar elementos
      cm.makeMergeClusters(aglutinatei, aglutinatej);

      // gravar solucao atual na lista de melhores
      addSolutionOnTopSolutions(cm.cloneSolution(), cm.calculateMQ(), topSolutions, topSolutionsMQ);

      k += 1;
    }
    return topSolutions;
  }

  private void addSolutionOnTopSolutions(
          int[] currentSolution,
          double currentSolutionMQ,
          int[][] topSolutions,
          Double[] topSolutionsMQ) {
    for (int i = 0; i < topSolutionsMQ.length; i++) {
      if (topSolutionsMQ[i] == null) {
        topSolutions[i] = currentSolution;
        topSolutionsMQ[i] = currentSolutionMQ;
        break;
      } else if (topSolutionsMQ[i] < currentSolutionMQ) {
        int[] auxSolution = topSolutions[i];
        double auxSolutionMQ = topSolutionsMQ[i];

        topSolutions[i] = currentSolution;
        topSolutionsMQ[i] = currentSolutionMQ;

        currentSolution = auxSolution;
        currentSolutionMQ = auxSolutionMQ;
      }
    }
  }
}
