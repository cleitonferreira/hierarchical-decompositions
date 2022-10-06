package org.example.ils.core;

import org.example.ils.metaheuristica.AlgoritmoILS;

public class ExperimentoFactory {
	private static AlgoritmoAbstract getAlgoritmo(
		Problema problema,
		Exibicao exibicao,
		Parametro param) 
	throws Exception
	{
		int tamanho = problema.getTamanho();
		
		if (param.getEvaluationMax()==null)
			throw new Exception("Quantidade de avalia��es n�o definida.");
				
		TipoAlgoritmo tipoAlgoritmo = param.getTipoAlgoritmo();

		AlgoritmoAbstract algoritmo = null;
		switch (tipoAlgoritmo) {
			case ITERATED_LOCAL_SEARCH:
				algoritmo = new AlgoritmoILS(
						problema,
						exibicao,
						param
					);
				break;
			default:
				break;
			
		}
		if(algoritmo==null) {
			System.out.println("Algoritmo n�o encontrado=" + tipoAlgoritmo);
			return null;
		}
		
		return algoritmo;
	}
	
	public static Experimento executa(
		Problema problema,
		Exibicao exibicao,
		Parametro param
	) 
	throws Exception
	{		
		AlgoritmoAbstract algoritmo = getAlgoritmo(problema, exibicao, param);
		if (algoritmo == null) 
			throw new Exception("Algoritmo n�o executado:" + param.getTipoAlgoritmo());
		Experimento experimento = new Experimento(param.getInfoParametros(), algoritmo);
		
		int nCicloInicial = param.getnCicloInicial();
		int nCiclos = param.getnCiclos();
		if (param.temCiclosEspecificos()) {
			String nomeInstancia = problema.getFileName();
			int i = nomeInstancia.indexOf("\\");
			while (i>=0) {
				nomeInstancia = nomeInstancia.substring(i+1);
				i = nomeInstancia.indexOf("\\");
			}
			
			nCicloInicial = param.getCicloDaInstancia(nomeInstancia);
			nCiclos = nCicloInicial + 1;
		}
		
		experimento.runWithMedia(nCicloInicial, nCiclos, param.getComSemente());
		return experimento;
	}
	
}
