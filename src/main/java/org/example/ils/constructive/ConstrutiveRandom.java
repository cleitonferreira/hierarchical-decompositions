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
    int classCount = hmd.getModulos().size();
    int[] solution = new int[classCount];

		for (int i = 0; i < classCount; i++) {
			solution[i] = PseudoRandom.randInt(0, classCount - 1);
		}

    return solution;
  }
}