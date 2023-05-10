package org.example.ils.core;

public interface SolucaoAbstract {
	
	public abstract double getFitness();
	
	public abstract String getString();

	public abstract String getGruposString();
	
	public abstract int getLocation();
	
	public abstract void setFitness(double fitness);

	public abstract void setGrupos(int[] grupos);
	
	public abstract void setLocation(int location);

	// representação da solução onde cada item do vetor corresponde ao módulo e o conteúdo ao cluster que o modulo faz parte
	public abstract int[] getValores();
	
	public abstract int getTotalItens();

	// grupos e subgrupos
	public abstract int[] getGrupos();

}
