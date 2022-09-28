package org.example.ils.constructive;

import org.example.ils.utils.PseudoRandom;
import org.example.model.HMD;

/**
 * Constructive random method
 *
 * @author User
 */
public class ConstrutiveRandom extends ConstrutiveAbstract {

  @Override
  public int[] createSolution(HMD hmd) {
    int countEntidades = hmd.getCountEntidades();
    int[] solution = new int[countEntidades];

		for (int i = 0; i < countEntidades; i++) {
			solution[i] = PseudoRandom.randInt(0, countEntidades - 1);
		}

    return solution;
  }
}