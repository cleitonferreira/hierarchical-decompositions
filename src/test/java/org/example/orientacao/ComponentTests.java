package org.example.orientacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.StreamSupport;
import org.example.orientacao.builders.BuilderFigureOne;
import org.example.orientacao.model.Decomposition;
import org.example.orientacao.model.Module;
import org.example.orientacao.model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class ComponentTests {

  @InjectMocks
  Decomposition decomposition;

  @Mock
  Module module;

  @BeforeEach
  public void setUp() {
    decomposition = new BuilderFigureOne().build();
    module = decomposition.getRoot();
  }

  @Test
  void validarLogaritmo() {

    assertEquals(Double.NEGATIVE_INFINITY, module.log2(0));
    assertEquals(0, module.log2(1));
    assertEquals(7.0, module.log2(128));

  }

  @Test
  public void testCodeLength() {

    //Assertions.assertEquals(Double.NEGATIVE_INFINITY, formulaComplexidade.l(0));
    assertEquals(1.0, module.codeLength(1));
    assertEquals(3.328897414907779, module.codeLength(2));
    assertEquals(8.3603559294956, module.codeLength(12));
  }

  @Test
  void validarRelDepth() {

    //Modulo: A3
    Module a3 = decomposition.getModuleByName("a3");
    assertNotNull(a3);

    Node n7 = decomposition.getNodeByName("n7");
    assertNotNull(n7);
    assertEquals(1, a3.relativeDepth(n7));

    //Modulo: A4
    Module a4 = decomposition.getModuleByName("a4");
    assertNotNull(a4);
    assertEquals(0, a4.relativeDepth(n7));

    //Modulo: A0
    assertEquals(2, decomposition.getRoot().relativeDepth(n7));

  }

  @Test
  public void testLeastCommonAncestor() {

    Module a0 = decomposition.getModuleByName("root");
    assertNotNull(a0);

    Module a3 = decomposition.getModuleByName("a3");
    assertNotNull(a3);

    Module a4 = decomposition.getModuleByName("a4");
    assertNotNull(a4);

    Node n1 = decomposition.getNodeByName("n1");
    assertNotNull(n1);

    Node n4 = decomposition.getNodeByName("n4");
    assertNotNull(n4);

    Node n6 = decomposition.getNodeByName("n6");
    assertNotNull(n6);

    Node n7 = decomposition.getNodeByName("n7");
    assertNotNull(n7);

    Node n8 = decomposition.getNodeByName("n8");
    assertNotNull(n8);

    Node n10 = decomposition.getNodeByName("n10");
    assertNotNull(n10);

    assertEquals(a0, decomposition.getRoot().leastCommonAncestor(n7, n4));
    assertEquals(a3, decomposition.getRoot().leastCommonAncestor(n7, n10));

    assertEquals(a4, decomposition.getRoot().leastCommonAncestor(n6, n7));
    assertEquals(a3, decomposition.getRoot().leastCommonAncestor(n7, n8));
    assertEquals(a0, decomposition.getRoot().leastCommonAncestor(n1, n6));
  }

  @Test
  void validarCountModules() {

    assertEquals(2, module.countModules());
    assertEquals(4, module.countAllModules());
    assertNotNull(module.countModules());

  }

  @Test
  void validarGetModuleByIndex() {

    assertEquals("A1", decomposition.getRoot().getModuleByIndex(0).getName());
    assertEquals("A3", decomposition.getRoot().getModuleByIndex(1).getName());
    assertEquals("A2",
        decomposition.getRoot().getModuleByIndex(0).getModuleByIndex(0).getName());
    assertEquals("A4",
        decomposition.getRoot().getModuleByIndex(1).getModuleByIndex(0).getName());

  }

  @Test
  void validarGetModuleByName() {

    assertEquals("A1", decomposition.getModuleByName("A1").getName());
    assertEquals("A3", decomposition.getModuleByName("A3").getName());
    assertEquals("A2", decomposition.getModuleByName("A2").getName());
    assertEquals("A4", decomposition.getModuleByName("A4").getName());

  }

  @Test
  void validarGetModules() {

    assertEquals(2,
        StreamSupport.stream(decomposition.getRoot().getModules().spliterator(), false).count());

  }

  @Test
  void validarAddNode() {

    Node node = new Node("n100");
    assertNotNull(node);

    module.addNode(node);
    assertNotNull(module);

    assertEquals(node, module.getNodeByName("n100"));

  }

  @Test
  void validarCountNodes() {

    assertEquals(4, module.countNodes());

  }

  @Test
  void validarGetNodeByIndex() {

    //Modulo Root A0
    assertEquals("n1", module.getNodeByIndex(0).getName());
    assertEquals("n5", module.getNodeByIndex(1).getName());
    assertEquals("n12", module.getNodeByIndex(2).getName());
    assertEquals("n13", module.getNodeByIndex(3).getName());

    assertEquals("n0", module.getModuleByName("A1").getNodeByIndex(0).getName());

    assertEquals("n2", module.getModuleByName("A2").getNodeByIndex(0).getName());

  }

  @Test
  public void validarPSI() {
    assertEquals(398.5, decomposition.psi(), 0.001);
  }


}
