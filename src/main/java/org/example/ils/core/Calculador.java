package org.example.ils.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.example.FormulaComplexidade;
import org.example.ils.construtivoLista.SolucaoCNMLL;
import org.example.model.Entidade;
import org.example.model.HMD;
import org.example.model.Modulo;

/**
 * DEFINICÕES:
 * 
 * - acoplamento = número de dependências que as classes de um pacote possuem
 * com classes de fora do pacote. Deve ser minimizado.
 * 
 * - coesão = número de dependências que as classes de um pacote possuem com
 * outras classes do mesmo pacote. Deve ser maximizado (ou seja, minimizamos seu
 * valor com sinal invertido)
 * 
 * - spread = partindo de zero e percorrendo cada pacote, acumula o quadrado da
 * diferença entre o número de classes do pacote e o número de classes do menor
 * pacote
 * 
 * - diferenca = diferença entre o número máximo de classes em um pacote e o
 * número mínimo de classes em um pacote
 * 
 * @author Marcio Barros
 */
public class Calculador extends CalculadorAbstract {

	//Limite do vetor é o limite superior informado para o problema
	protected int[] inboundEdges;
	protected int[] outboundEdges;
	protected int[] intraEdges;
	protected double[] mf;
	protected double mq;

	/**
	 * Inicializa o calculator com os dados do problema
	 */
	public Calculador(Problema problema) {
		super(problema);
	}
	
	/**
	 * Calcula o coeficiente de modularidade do projeto
	 */
	public double calculateMQEgravaEstado2(int[] valores) {

		int classCount = this.problema.getClassCount();
		this.inboundEdges = new int[classCount];
		this.outboundEdges = new int[classCount];
		this.intraEdges = new int[classCount];
		this.mf = new double[classCount];
		
		int[][]listaDependencias = this.problema.getListaDependenciasPara();
		int[] qtdDependencias = this.problema.getQtdDependenciasPara();
		
		for (int i=0; i<classCount; i++) {
			int sourcePackage = valores[i];
			for (int j=0; j<qtdDependencias[i]; j++) {
				int targetPackage = valores[listaDependencias[i][j]];
				if (targetPackage != sourcePackage) {
					outboundEdges[sourcePackage]++;
					inboundEdges[targetPackage]++;
				} else
					intraEdges[sourcePackage]++;
			}
		}

		double mq = 0.0;

		//for (int i = 0; i < this.problema.getMaxPackages(); i++) {
		for (int i = 0; i < classCount; i++) {
			int inter = inboundEdges[i] + outboundEdges[i];
			int intra = intraEdges[i];

			//if (intra != 0 && inter != 0) {
			if (intra != 0) {
				this.mf[i] = intra / (intra + 0.5 * inter);
				mq += this.mf[i];
			}
		}
		
		this.mq = mq;
		
		return -mq;
	}

	public double calculateMQEgravaEstado(SolucaoAbstract s, int[] valores) {

		HMD hmd = null;
		//HMD hmd = converterProblemaParaHMD(s, problema, valores);

		if(s instanceof SolucaoCNMLL) {
			hmd = problema.getHMD();
		} else {
			hmd = converterProblemaParaHMD(s, problema, valores);
		}

		int classCount = this.problema.getClassCount();
		this.inboundEdges = new int[classCount];
		this.outboundEdges = new int[classCount];
		this.intraEdges = new int[classCount];
		this.mf = new double[classCount];

		int[][]listaDependencias = this.problema.getListaDependenciasPara();
		int[] qtdDependencias = this.problema.getQtdDependenciasPara();

		for (int i=0; i<classCount; i++) {
			int sourcePackage = valores[i];
			for (int j=0; j<qtdDependencias[i]; j++) {
				int targetPackage = valores[listaDependencias[i][j]];
				if (targetPackage != sourcePackage) {
					outboundEdges[sourcePackage]++;
					inboundEdges[targetPackage]++;
				} else
					intraEdges[sourcePackage]++;
			}
		}

		FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmd);
		double valorFormulaComplexidade = formulaComplexidade.executa();

		this.mq = valorFormulaComplexidade;

		for (int i = 0; i < classCount; i++) {
			int inter = inboundEdges[i] + outboundEdges[i];
			int intra = intraEdges[i];

			if (intra != 0) {
				this.mf[i] = valorFormulaComplexidade;
			}
		}

		return valorFormulaComplexidade;
	}

	/**
	 * Calcula o coeficiente de modularidade do projeto
	 */
	public double calculateMQ2(SolucaoAbstract s, int[] valores) {

		int classCount = this.problema.getClassCount();
		int[] inboundEdges = new int[classCount];
		int[] outboundEdges = new int[classCount];
		int[] intraEdges = new int[classCount];
		
		int[][]listaDependencias = this.problema.getListaDependenciasPara();
		int[] qtdDependencias = this.problema.getQtdDependenciasPara();

		for (int i=0; i<classCount; i++) {
			int sourcePackage = valores[i];
			for (int j=0; j<qtdDependencias[i]; j++) {
				int targetPackage = valores[listaDependencias[i][j]];
				if (targetPackage != sourcePackage) {
					outboundEdges[sourcePackage]++;
					inboundEdges[targetPackage]++;
				} else
					intraEdges[sourcePackage]++;
			}
		}

		double mq = 0.0;

		//for (int i = 0; i < this.problema.getMaxPackages(); i++) {
		for (int i = 0; i < classCount; i++) {
			int inter = inboundEdges[i] + outboundEdges[i];
			int intra = intraEdges[i];

			//if (intra != 0 && inter != 0) {
			if (intra != 0) {
				double mf = intra / (intra + 0.5 * inter);
				mq += mf;
			}
		}
		
		return mq;
	}

	public double calculateMQ(SolucaoAbstract s, int[] valores) {

		HMD hmd = null;

		if(s instanceof SolucaoCNMLL) {
			hmd = problema.getHMD();
		} else {
			hmd = converterProblemaParaHMD(s, problema, valores);
		}

		FormulaComplexidade formulaComplexidade = new FormulaComplexidade(hmd);

		double valorFormulaComplexidade = formulaComplexidade.executa();

		return  valorFormulaComplexidade;
	}

	private static HMD converterProblemaParaHMD(SolucaoAbstract s, Problema problema, int[] valores) {

		Modulo modulo = null;
		Modulo submodulo = null;
		List<Modulo> modulos = new ArrayList<Modulo>();

		// Modulo A0
		modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, 0), "modulo-0", null);
		modulos.add(modulo);

		/*Remover valores repetidos*/
		int[] resultado = Arrays.stream(valores).distinct().toArray();
		/*Ordenar*/
		Arrays.sort(resultado);

		for (int a = 0; a < resultado.length; a++) {
			for (int b = 0; b < s.getGrupos().length; b++) {

				int verificaModulo = getVerificaModulo(modulos, resultado[a]);

				if (verificaModulo == -1 && s.getGrupos()[b] == 0 && a == b) {
					modulo = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
							String.valueOf("modulo-" + resultado[a]), null);
					modulos.add(modulo);
				} else if (verificaModulo == -1 && s.getGrupos()[b] == 1 && a == b) {
					int position = b - 1;
					if ((s.getGrupos()[position] == 1) && (s.getGrupos()[b] == 1)){
						Modulo submoduloII = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
								String.valueOf("submodulo-" + resultado[a]), null);
						submodulo.setSubmodulos(Arrays.asList(submoduloII));
					} else {
						submodulo = new Modulo(getListaEntidadesPorModulo(problema, valores, resultado[a]),
								String.valueOf("submodulo-" + resultado[a]), null);
						modulo.setSubmodulos(Arrays.asList(submodulo));
					}
				}
			}
		}

		return new HMD(modulos);
	}

	public static int getVerificaModulo(List<Modulo> modulos, int valor) {

		int retorno = -1;

		for (Modulo modulo : modulos) {
			if (Objects.nonNull(modulo)){
				if (modulo.getNome().equals("modulo-"+valor)) {
					retorno = 0;
				}
			}
			if (Objects.nonNull(modulo.getSubmodulos())){
				for (Modulo submodulo : modulo.getSubmodulos()) {
					if (submodulo.getNome().equals("submodulo-"+valor)) {
						retorno = 1;
					}
				}
			}
		}

		return retorno;
	}

	private static List<Entidade> getListaEntidadesPorModulo(Problema problema, int[] valores,
			int modulo) {
		HMD hmd = problema.getHMD();
		List<Entidade> listaEntidades = new ArrayList<>();
		for (int a = 0; a < valores.length; a++) {
			if (Objects.nonNull(problema.getClassCount())) {
				for (int i = 0; i < problema.getClassCount(); i++) {
					if ((valores[i] == modulo) && modulo == a) {

						if (Objects.nonNull(hmd.getModulos())) {
							for (Modulo m : hmd.getModulos()){
								if (Objects.nonNull(m.getListaEntidades())) {
									for (Entidade em : m.getListaEntidades()){
										if (em.getNome().equals(String.valueOf(i))){
											listaEntidades.add(em);
										}
									}
								}

								if (Objects.nonNull(m.getSubmodulos())) {
									for (Modulo s : m.getSubmodulos()){
										if (Objects.nonNull(s.getListaEntidades())) {
											for (Entidade es : s.getListaEntidades()){
												if (es.getNome().equals(String.valueOf(i))){
													listaEntidades.add(es);
												}
											}
										}
									}
								}
							}
						}

					}
				}
			}
		}
		return listaEntidades;
	}

	/**
	 * Calcula o fitness
	 */
	public double evaluate(SolucaoAbstract s) {
		return evaluate(s, s.getValores());
	}

	/**
	 * Avalia a solução
	 */
	public double evaluate(SolucaoAbstract s, int[] valores) {
		return calculateMQ(s, valores);
	}

	/**
	 * Avalia a solução
	 */
	public double evaluateEGravaEstado(SolucaoAbstract s, int[] valores) {
		return calculateMQEgravaEstado(s, valores);
	}

	/**
	 * Calcula o coeficiente de modularidade do projeto
	 * Considera somente a diferença nos vetores intraEdges, inboundEdges, outboundEdges calculado na última iteração
	 */
	public double evaluateMove(int[] valores, int item, int grupo1, int grupo2) {
		
		int[][]listaDependenciasPara = this.problema.getListaDependenciasPara();
		int[] qtdDependenciasPara = this.problema.getQtdDependenciasPara();
		int[][]listaDependenciasDe = this.problema.getListaDependenciasDe();
		int[] qtdDependenciasDe = this.problema.getQtdDependenciasDe();
		
		for (int i=0; i < qtdDependenciasPara[item]; i++) {
			int filho = listaDependenciasPara[item][i];
			// se o item era do grupo1 reduz as arestas intra de suas dependencias
			if (valores[filho] == grupo1) {
				this.intraEdges[grupo1]--;
				this.inboundEdges[grupo1]++;
				this.outboundEdges[grupo2]++;
			}
			// se o item passa para o grupo2 aumenta as arestas intra de suas dependencias
			else if (valores[filho] == grupo2) {
				this.inboundEdges[grupo2]--;
				this.outboundEdges[grupo1]--;
				this.intraEdges[grupo2]++;
			}
			// se o dependente não for nenhum dos dois grupos
			else {
				this.outboundEdges[grupo1]--;
				this.outboundEdges[grupo2]++;
			}
		}

		for (int i=0; i < qtdDependenciasDe[item]; i++) {
			int pai = listaDependenciasDe[item][i];
			// se o item era do grupo1 reduz as arestas intra de suas dependencias
			if (valores[pai] == grupo1) {
				this.intraEdges[grupo1]--;
				this.outboundEdges[grupo1]++;
				this.inboundEdges[grupo2]++;
			}
			// se o item passa para o grupo2 aumenta as arestas intra de suas dependencias
			else if (valores[pai] == grupo2) {
				this.inboundEdges[grupo1]--;
				this.outboundEdges[grupo2]--;
				this.intraEdges[grupo2]++;
			}
			// se o dependente não for nenhum dos dois grupos
			else {
				this.inboundEdges[grupo1]--;
				this.inboundEdges[grupo2]++;
			}
		}
		
		// recalculando o MF do grupo 1 e grupo 2
		this.mq -= this.mf[grupo1];
		this.mq -= this.mf[grupo2];
		
		this.mf[grupo1] = 0.0;
		int interG1 = inboundEdges[grupo1] + outboundEdges[grupo1];
		int intraG1 = intraEdges[grupo1];
		//if (intraG1 != 0 && interG1 != 0) {
		if (intraG1 != 0) {
			this.mf[grupo1] = intraG1 / (intraG1 + 0.5 * interG1);
		}

		this.mf[grupo2] = 0.0;
		int interG2 = inboundEdges[grupo2] + outboundEdges[grupo2];
		int intraG2 = intraEdges[grupo2];
		//if (intraG2 != 0 && interG2 != 0) {
		if (intraG2 != 0) {
			this.mf[grupo2] = intraG2 / (intraG2 + 0.5 * interG2);
		}
		
		this.mq += this.mf[grupo1];
		this.mq += this.mf[grupo2];
		 
		return -this.mq;
	}
	
}