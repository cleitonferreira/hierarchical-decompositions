package org.example.ils.core;

import java.util.List;

public abstract class ExperimentoModel {

	public abstract List<Parametro> getParametros();
	
	public abstract String getIdExperimento();
	
	// abre os arquivos de saída para todos os parâmetros do experimento
	public Exibicao[] iniciaExibicoes()
	{
		List<String> arquivos = Diretorios.getNomesArquivosSaida(getParametros());
		Exibicao[] exibicoes = new Exibicao[arquivos.size()];
		for (int i = 0; i < arquivos.size(); i++) {
			String nomeArquivo = arquivos.get(i);
			exibicoes[i] = new Exibicao(Diretorios.diretorioResultados + nomeArquivo);
			exibicoes[i].addListener(System.out, "system.out");
		}
		return exibicoes;
	}
}
