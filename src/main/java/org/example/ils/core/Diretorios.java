package org.example.ils.core;

import java.util.ArrayList;
import java.util.List;

public class Diretorios {

	public static String diretorioInstancias = System.getProperty( "user.dir" ) + "//instancias//data//";
	public static String diretorioResultados = System.getProperty( "user.dir" ) + "/results/";
	public static String diretorioStatistics = System.getProperty( "user.dir" ) + "/statistics/";
	public static String diretorioInstanciasTXT = System.getProperty( "user.dir" ) + "/instancias/data_txt/";
	
	// um arquivo por parametro (algoritmo)
	public static String PREFIXO_SAIDA = "saida_";
	// um arquivo por parametro + problema
	public static String PREFIXO_DEBUG = "saida_debug_";
	// resultados do experimento condensados em um arquivo csv
	public static String PREFIXO_SAIDA_CSV = "resultados_";
	// resultados do experimento condensados em um arquivo csv
	public static String PREFIXO_DEBUG_CSV = "resultados_debug_";
	
	// coleta os nomes de arquivos de saída, dentro da faixa de parametros, o primeiro parametro é 1
	public static List<String> getNomesArquivosSaida(List<Parametro> params, int parametroInicial,int parametroFinal)
	{
		List<String> arquivos = new ArrayList<String>();
		for (int i = parametroInicial; i <= parametroFinal; i++ ) {
			String nomeArquivo = Diretorios.PREFIXO_SAIDA +
				+ (i)
				+ ".txt";
			arquivos.add(nomeArquivo);
		}
		return arquivos;
	}
	
	// coleta os nomes de arquivos de saída para todos os parâmetros
	public static List<String> getNomesArquivosSaida(List<Parametro> params)
	{
		return getNomesArquivosSaida(params, 1, params.size());
	}

	public static String getNomeArquivoDebug(int indice, String nomeProblema) {
		return Diretorios.PREFIXO_DEBUG  + indice + "_" + nomeProblema + ".txt";
	}
}
