package org.example.ils;

import java.util.ArrayList;
import java.util.List;
import org.example.ils.core.ExperimentoModel;
import org.example.ils.core.Parametro;
import org.example.ils.core.TipoAlgoritmo;
import org.example.ils.core.TipoExibicao;
import org.example.ils.metaheuristica.TipoPerturbacao;

public class ExperimentoFinalParte1 extends ExperimentoModel {

  @Override
  public String getIdExperimento() {
    return "Final_Parte1_ILS";
  }

  public String[] getInstancias()
  {
    return new String[] {
        "exemplo.odem"
        //"jstl-1.0.6 18C.odem"
        //"jnanoxml 25C.odem"
        //"joda-money 26C.odem"
        //"jxls-reader 27C.odem"
        //"seemp 31C.odem"
        //"junit-3.8.1 100C.odem"
    };
  }

/*  public String[] getInstancias()
  {
    return new String[] {
        "apache_zip 36C.odem",
        "dom4j-1.5.2 195C.odem",
        "exemplo.odem",
        "forms-1.3.0 68C.odem",
        "gae_plugin_core 140C.odem",
        "jace 340C.odem",
        "javacc 154C.odem",
        "JavaGeom 172C.odem",
        "javaocr 59C.odem",
        "javaws 378C.odem",
        "javaws-7 378C.odem",
        "jconsole-1.7.0 220C.odem",
        "jdendogram 177C.odem",
        "jfluid-1.7.0 82C.odem",
        "jkaryoscope 136C.odem",
        "JMetal 190C.odem",
        "jml 270C.odem",
        "jnanoxml 25C.odem",
        "joda-money 26C.odem",
        "JPassword 96C.odem",
        "jpassword 269C.odem",
        "jscatterplot 74C.odem",
        "jstl-1.0.6 18C.odem",
        "jtreeview 329C.odem",
        "Jung-graph 207C.odem",
        "Jung-visualization 221C.odem",
        "junit-3.8.1 100C.odem",
        "jxls-core 83C.odem",
        "jxls-reader 27C.odem",
        "log4j-1.2.16 308C.odem",
        "notepad-full 299C.odem",
        "notepad-model 46C.odem",
        "pdf_renderer 199C.odem",
        "pfcda_base 67C.odem",
        "pfcda_swing 252C.odem",
        "poormans_2.3 304C.odem",
        "res_cobol 483C.odem",
        "seemp 31C.odem",
        "servletapi-2.3 63C.odem",
        "tinytim 134C.odem",
        "udt-java 56C.odem",
        "xmlapi 184C.odem",
        "xmldom 119C.odem"
    };
  }*/

  public void addParametrosCombinacaoMetodos(List<Parametro> params, Double percentual, int nCiclos,
      int maxIteracoesSemMelhoria, boolean inicialConstrutivo,
      boolean metodosPerturbacaoSimultaneos) {
    Parametro paramILSPadrao = new Parametro(TipoAlgoritmo.ITERATED_LOCAL_SEARCH, nCiclos);
    paramILSPadrao.setILS(null, null, null, null, null, maxIteracoesSemMelhoria, inicialConstrutivo,
        metodosPerturbacaoSimultaneos);

    int n = TipoPerturbacao.values().length;

    for (int perturbacao1 = 0; perturbacao1 < n; perturbacao1++) {
      Double[] percentuais1 = {null, null, null, null, null};
      percentuais1[perturbacao1] = percentual;
      Parametro paramILS1 = new Parametro(paramILSPadrao);
      // somente adiciona os métodos com uma estratégia uma vez, para o flag sequencia = true
      if (metodosPerturbacaoSimultaneos) {
        paramILS1.setILS(percentuais1[0], percentuais1[1], percentuais1[2], percentuais1[3],
            percentuais1[4], maxIteracoesSemMelhoria, inicialConstrutivo,
            metodosPerturbacaoSimultaneos);
        params.add(paramILS1);
      }
      for (int perturbacao2 = perturbacao1; perturbacao2 < n; perturbacao2++) {
        if (perturbacao1 != perturbacao2) {
          Double[] percentuais = {null, null, null, null, null};
          percentuais[perturbacao1] = percentual;
          percentuais[perturbacao2] = percentual;
          Parametro paramILS = new Parametro(paramILSPadrao);
          paramILS.setILS(percentuais[0], percentuais[1], percentuais[2], percentuais[3],
              percentuais[4], maxIteracoesSemMelhoria, inicialConstrutivo,
              metodosPerturbacaoSimultaneos);
          params.add(paramILS);
        } else {
          continue;
        }
        for (int perturbacao3 = perturbacao2; perturbacao3 < n; perturbacao3++) {
          if (perturbacao1 != perturbacao2 && perturbacao1 != perturbacao3
              && perturbacao2 != perturbacao3) {
            Double[] percentuais = {null, null, null, null, null};
            percentuais[perturbacao1] = percentual;
            percentuais[perturbacao2] = percentual;
            percentuais[perturbacao3] = percentual;
            Parametro paramILS = new Parametro(paramILSPadrao);
            paramILS.setILS(percentuais[0], percentuais[1], percentuais[2], percentuais[3],
                percentuais[4], maxIteracoesSemMelhoria, inicialConstrutivo,
                metodosPerturbacaoSimultaneos);
            params.add(paramILS);
          } else {
            continue;
          }
          for (int perturbacao4 = perturbacao3; perturbacao4 < n; perturbacao4++) {
            if (perturbacao1 != perturbacao2 && perturbacao1 != perturbacao3
                && perturbacao1 != perturbacao4 &&
                perturbacao2 != perturbacao3 && perturbacao2 != perturbacao4
                && perturbacao3 != perturbacao4) {
              Double[] percentuais = {null, null, null, null, null};
              percentuais[perturbacao1] = percentual;
              percentuais[perturbacao2] = percentual;
              percentuais[perturbacao3] = percentual;
              percentuais[perturbacao4] = percentual;
              Parametro paramILS = new Parametro(paramILSPadrao);
              paramILS.setILS(percentuais[0], percentuais[1], percentuais[2], percentuais[3],
                  percentuais[4], maxIteracoesSemMelhoria, inicialConstrutivo,
                  metodosPerturbacaoSimultaneos);
              params.add(paramILS);
            } else {
              continue;
            }
          }
        }
      }
    }
    Double[] percentuais = {null, null, null, null, null};
    percentuais[0] = percentual;
    percentuais[1] = percentual;
    percentuais[2] = percentual;
    percentuais[3] = percentual;
    percentuais[4] = percentual;
    Parametro paramILS = new Parametro(paramILSPadrao);
    paramILS.setILS(percentuais[0], percentuais[1], percentuais[2], percentuais[3], percentuais[4],
        maxIteracoesSemMelhoria, inicialConstrutivo, metodosPerturbacaoSimultaneos);
    params.add(paramILS);
  }

  public void addParametros(List<Parametro> params, int nCiclos) {
    Double[] vPercentuaisTestados = {10.00};
    boolean[] vInicialConstrutivo = {true, false};
    boolean[] vMetodosPerturbacaoSimultaneos = {true, false};
    int[] vMaxIteracoesSemMelhoria = {0, 5};

    for (boolean inicialConstrutivo : vInicialConstrutivo) {
      for (boolean metodosPerturbacaoSimultaneos : vMetodosPerturbacaoSimultaneos) {
        for (Double percentual : vPercentuaisTestados) {
          for (int maxIteracoesSemMelhoria : vMaxIteracoesSemMelhoria) {
            addParametrosCombinacaoMetodos(params, percentual, nCiclos, maxIteracoesSemMelhoria,
                inicialConstrutivo, metodosPerturbacaoSimultaneos);
          }
        }
      }
    }
  }

  public List<Parametro> getParametros() {
    List<Parametro> params = new ArrayList<Parametro>();

    //int nCiclos = 30;
    //int nCiclos = 10;
    int nCiclos = 6;

    addParametros(params, nCiclos);

    return params;
  }

  public static void main(String[] args) throws Exception {
    int i = 0;
    List<Parametro> params = new ExperimentoFinalParte1().getParametros();
    System.out.println("Total parametros=" + params.size());
    for (Parametro param : params) {
      System.out.println(++i + "=" + param.getInfoParametros());
    }

    //Configura a exibição conforme algoritmo e problema
    TipoExibicao tipoExibicao = TipoExibicao.DEFAULT;
    tipoExibicao.setDebug(true);

    ExperimentoModel experimento = new ExperimentoFinalParte1(); // acrescentadas quatro instancias
    MainILS.executa(tipoExibicao, experimento);
  }
}
