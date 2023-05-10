package org.example.orientacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.orientacao.builders.ABuilderFigureTest;
import org.example.orientacao.builders.BuilderFigureEighteen;
import org.example.orientacao.builders.BuilderFigureNineteen;
import org.example.orientacao.builders.BuilderFigureOne;
import org.example.orientacao.builders.BuilderFigureThirtyFour;
import org.example.orientacao.builders.BuilderFigureThirtyOne;
import org.example.orientacao.builders.BuilderFigureThirtyThree;
import org.example.orientacao.builders.BuilderFigureThirtyTwo;
import org.example.orientacao.builders.BuilderFigureThree;
import org.example.orientacao.builders.BuilderFigureTwenty;
import org.example.orientacao.builders.BuilderFigureTwentyFour;
import org.example.orientacao.builders.BuilderFigureTwentyOne;
import org.example.orientacao.builders.BuilderFigureTwentySeven;
import org.example.orientacao.builders.BuilderFigureTwentySix;
import org.example.orientacao.builders.BuilderFigureTwentyThree;
import org.example.orientacao.builders.BuilderFigureTwo;
import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;
import org.junit.jupiter.api.Test;

public class TestModule {

  @Test
  public void testRelativeDepth() {
    Decomposition decomposition = new BuilderFigureEighteen().build();

    Module a3 = decomposition.getModuleByName("a3");
    assertNotNull(a3);

    Module a4 = decomposition.getModuleByName("a4");
    assertNotNull(a4);

    Node n7 = decomposition.getNodeByName("n7");
    assertNotNull(n7);

    assertEquals(1, a3.relativeDepth(n7));
    assertEquals(0, a4.relativeDepth(n7));
    assertEquals(2, decomposition.getRoot().relativeDepth(n7));
  }

  @Test
  public void testLeastCommonAncestor() {
    Decomposition decomposition = new BuilderFigureOne().build();

    Module a0 = decomposition.getModuleByName("root");
    assertNotNull(a0);

    Module a3 = decomposition.getModuleByName("a3");
    assertNotNull(a3);

    Module a4 = decomposition.getModuleByName("a4");
    assertNotNull(a4);

    Node n1 = decomposition.getNodeByName("n1");
    assertNotNull(n1);

    Node n6 = decomposition.getNodeByName("n6");
    assertNotNull(n6);

    Node n7 = decomposition.getNodeByName("n7");
    assertNotNull(n7);

    Node n8 = decomposition.getNodeByName("n8");
    assertNotNull(n8);

    assertEquals(a4, decomposition.getRoot().leastCommonAncestor(n6, n7));
    assertEquals(a3, decomposition.getRoot().leastCommonAncestor(n7, n8));
    assertEquals(a0, decomposition.getRoot().leastCommonAncestor(n1, n6));
  }

  @Test
  public void testCalculateFrequencyComponent() {
    Decomposition decomposition = new BuilderFigureEighteen().build();
    assertEquals(21, decomposition.getRoot().calculateTotalFrequency());
  }

  @Test
  public void testPsiFigure1() {
    Decomposition decomposition = new BuilderFigureOne().build();
    assertEquals(398.5, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure2() {
		Decomposition decomposition = new BuilderFigureTwo().build();
		assertEquals(348.7, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure3() {
    Decomposition decomposition = new BuilderFigureThree().build();
    assertEquals(372.2, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure18() {
    Decomposition decomposition = new BuilderFigureEighteen().build();
    assertEquals(208.37, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure19() {
    Decomposition decomposition = new BuilderFigureNineteen().build();
    assertEquals(249.91, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure20() {
    Decomposition decomposition = new BuilderFigureTwenty().build();
    assertEquals(230.0, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure21() {
    Decomposition decomposition = new BuilderFigureTwentyOne().build();
    assertEquals(202.3, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure23() {
    Decomposition decomposition = new BuilderFigureTwentyThree().build();
    assertEquals(350.7, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure24() {
    Decomposition decomposition = new BuilderFigureTwentyFour().build();
    assertEquals(342.1, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure26() {
    Decomposition decomposition = new BuilderFigureTwentySix().build();
    assertEquals(531.9, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure27() {
    Decomposition decomposition = new BuilderFigureTwentySeven().build();
    assertEquals(468.7, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure31() {
    Decomposition decomposition = new BuilderFigureThirtyOne().build();
    assertEquals(414.6, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure32() {
    Decomposition decomposition = new BuilderFigureThirtyTwo().build();
    assertEquals(426.5, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure33() {
    Decomposition decomposition = new BuilderFigureThirtyThree().build();
    assertEquals(396.6, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigure34() {
    Decomposition decomposition = new BuilderFigureThirtyFour().build();
    assertEquals(408.4, decomposition.psi(), 0.001);
  }

  @Test
  public void testPsiFigureTest() {
    Decomposition decomposition = new ABuilderFigureTest().build();
    assertEquals(00.0, decomposition.psi(), 0.001);
  }
}