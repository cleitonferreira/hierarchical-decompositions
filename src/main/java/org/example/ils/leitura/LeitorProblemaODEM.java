package org.example.ils.leitura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.example.ils.core.GeradorGrupos;
import org.example.ils.core.Problema;
import org.example.ils.leitura.model.Project;
import org.example.ils.leitura.model.ProjectClass;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

public class LeitorProblemaODEM {

  private static HMD hmdSolucao;

  public static ArrayList<Problema> loadInstancesODEM(String diretorio, String[] filenames)
      throws Exception {
    ArrayList<Problema> instances = new ArrayList<Problema>();

		for (String filename : filenames) {
			if (filename.length() > 0) {
				filename = diretorio + filename;
				Problema problema = loadODEM(filename);
				instances.add(problema);
			}
		}

    return instances;
  }

  /**
   * A partir do objeto modelo Project, carrega os dados do problema Adaptado do projeto de Marcio
   * Barros
   */
  public static Problema loadODEM(String filename) throws Exception {
    Project modelo = Reader.load(filename);
    Problema problema = loadProject(modelo);
    //int[] subgrupos = new int[1];
    int[] subgrupos = GeradorGrupos.geradorSubmodulos(problema.getOriginalPackage());
    hmdSolucao = converterProblemaParaHMD(problema, problema.getOriginalPackage(), subgrupos);
    problema.setHmd(hmdSolucao);
    return problema;
  }

  /**
   * A partir do objeto modelo Project, carrega os dados do problema Adaptado do projeto de Marcio
   * Barros
   */
  private static Problema loadProject(Project modelo) throws Exception {
    int classCount = modelo.getClassCount();
    int packageCount = modelo.getPackageCount();
    int[][] listaDependenciasPara = new int[classCount][classCount];
    int[] qtdDependenciasPara = new int[classCount];
    int[][] listaDependenciasDe = new int[classCount][classCount];
    int[] qtdDependenciasDe = new int[classCount];

    int[] originalPackage = new int[classCount];
    int[] originalClasses = new int[classCount];

    for (int i = 0; i < classCount; i++) {
      ProjectClass _class = modelo.getClassIndex(i);
      int sourcePackageIndex = modelo.getIndexForPackage(_class.getPackage());

      originalPackage[i] = sourcePackageIndex;
      originalClasses[sourcePackageIndex]++;

      for (int j = 0; j < _class.getDependencyCount(); j++) {
        String targetName = _class.getDependencyIndex(j).getElementName();
        int classIndex = modelo.getClassIndex(targetName);

        if (classIndex == -1) {
          throw new Exception("Class not registered in project: " + targetName);
        }

        listaDependenciasPara[i][j] = classIndex;
        qtdDependenciasPara[i]++;

        listaDependenciasDe[classIndex][qtdDependenciasDe[classIndex]++] = i;
      }
    }

    String name = modelo.getName();
    String filename = modelo.getFilename();

    Problema problema = new Problema(
        filename,
        name,
        classCount,
        packageCount,
        originalClasses,
        originalPackage,
        listaDependenciasPara,
        qtdDependenciasPara,
        listaDependenciasDe,
        qtdDependenciasDe,
        null);

    return problema;
  }

  private static HMD converterProblemaParaHMD(Problema problema, int[] valores, int[] submodulos) {
    Modulo modulo = null;
    List<Modulo> modulos = new ArrayList<Modulo>();
    hmdSolucao = new HMD();

    // Modulo A0
    modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "modulo-0", null);
    modulos.add(modulo);

    /*Remover valores repetidos*/
    int[] resultado = Arrays.stream(valores).distinct().toArray();
    /*Ordenar*/
    Arrays.sort(resultado);

    for (int a = 0; a < resultado.length; a++) {
      for (int b = 0; b < submodulos.length; b++) {

        int verificaModulo = getVerificaModulo(modulos, resultado[a]);

        if (verificaModulo == -1 && a == b) {

          Modulo submodulo = new Modulo(
              getListaEntidadesPorModulo(problema, valores, resultado[a]),
              String.valueOf("modulo-" + b), null);

          List<Modulo> listaModulosModulosNaoHierrarquicos = hmdSolucao.converterHMDParaModulosNaoHierrarquicos(modulos);

          Modulo moduloPosicao = buscarPosicaoModulo(listaModulosModulosNaoHierrarquicos, submodulos[b]);
          if (Objects.isNull(moduloPosicao.getSubmodulos())){
            moduloPosicao.preparandoSubmodulos();
          }
          moduloPosicao.getSubmodulos().add(submodulo);
        }
      }
    }
    return new HMD(modulos);
  }

  private static int getVerificaModulo(List<Modulo> modulos, int valor) {

    int retorno = -1;

    for (Modulo modulo : modulos) {
      if (Objects.nonNull(modulo)) {
        if (modulo.getNome().equals("modulo-" + valor)) {
          retorno = 0;
        }
      }
      if (Objects.nonNull(modulo.getSubmodulos())) {
        for (Modulo submodulo : modulo.getSubmodulos()) {
          if (submodulo.getNome().equals("modulo-" + valor)) {
            retorno = 1;
          }
        }
      }
    }

    return retorno;
  }

  private static Modulo buscarPosicaoModulo(List<Modulo> modulos, int posicao) {
    String nomeModulo = "modulo-"+posicao;
    for (int i = 0; i < modulos.size(); i++) {
      if (modulos.get(i).getNome().equals(nomeModulo)) {
        return modulos.get(i);
      }
    }
    return null;
  }

  private static List<Entidade> getListaEntidadesPorModulo(Problema problema, int[] valores,
      int modulo) {
    List<Entidade> listaEntidades = new ArrayList<>();

    for (int a = 0; a < valores.length; a++) {
      if (Objects.nonNull(problema.getClassCount())) {
        for (int i = 0; i < problema.getClassCount(); i++) {
          if ((valores[i] == modulo) && modulo == a) {

            Collection<Entidade> links = new ArrayList<>();
            for (int l = 0; l < problema.getListaDependenciasPara()[i].length; l++) {
              if (problema.getQtdDependenciasPara()[i] > l) {
                int value = problema.getListaDependenciasPara()[i][l];
                links.add(new Entidade(String.valueOf(value), null));
              }
            }
            links = links.size() == 0 ? null : links;
            listaEntidades.add(new Entidade(String.valueOf(i), links));
          }
        }
      }
    }
    return listaEntidades;
  }

}
