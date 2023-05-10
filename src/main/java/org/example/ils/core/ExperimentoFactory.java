package org.example.ils.core;

import org.example.ils.metaheuristica.AlgoritmoILS;

public class ExperimentoFactory {
	
	private static AlgoritmoAbstract getAlgoritmo(
		Problema problema, 
		Exibicao exibicao,
		Parametro param) 
	throws Exception
	{
		
		if (param.getEvaluationMax()==null)
			throw new Exception("Quantidade de avaliações não definida.");
				
		TipoAlgoritmo tipoAlgoritmo = param.getTipoAlgoritmo();

		AlgoritmoAbstract algoritmo = new AlgoritmoILS(
			problema,
			exibicao,
			param
		);

		if(algoritmo==null) {
			System.out.println("Algoritmo não encontrado=" + tipoAlgoritmo);
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
			throw new Exception("Algoritmo não executado:" + param.getTipoAlgoritmo());
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
