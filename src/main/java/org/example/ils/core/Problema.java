package org.example.ils.core;

import org.example.model.HMD;
import org.example.orientacao.model.Decomposition;

public class Problema {
	
	private String name;
	private String fileName;

	private int classCount;
	private int packageCount;

	private int[] originalPackage;
	private int[] originalClasses;
	
	private int[][] listaDependenciasPara;
	private int[] qtdDependenciasPara;
	
	private int[][] listaDependenciasDe;
	private int[] qtdDependenciasDe;

	private HMD hmd;
	private Decomposition decomposition;

	public Problema(
		String filename,
		String name,
		int numClasses,
		int packageCount,
		int[] originalClasses,
		int[] originalPackage, 
		int[][] listaDependenciasPara,
		int[] qtdDependenciasPara,
		int[][] listaDependenciasDe,
		int[] qtdDependenciasDe,
			HMD hmd) {
		
		this.fileName = filename;
		this.name = name;
		
		this.classCount = numClasses;
		this.packageCount = packageCount;

		this.originalPackage = originalPackage;
		this.originalClasses = originalClasses;
		this.listaDependenciasPara = listaDependenciasPara;
		this.qtdDependenciasPara = qtdDependenciasPara;
		this.listaDependenciasDe = listaDependenciasDe;
		this.qtdDependenciasDe = qtdDependenciasDe;
		this.hmd = hmd;
	}

	public int getTamanho() {
		return this.classCount;
	}

	public int[] getOriginalPackage() {
		return originalPackage;
	}

	public int[][] getListaDependenciasPara() {
		return this.listaDependenciasPara;
	}

	public int[][] getListaDependenciasDe() {
		return this.listaDependenciasDe;
	}

	public int[] getQtdDependenciasPara() {
		return this.qtdDependenciasPara;
	}

	public int[] getQtdDependenciasDe() {
		return this.qtdDependenciasDe;
	}

	public int getClassCount() {
		return classCount;
	}

	public int[] getOriginalClasses() {
		return originalClasses;
	}
	
	public int getPackageCount() {
		return packageCount;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getName() {
		return name;
	}

	public HMD getHMD() {
		return hmd;
	}
	public void setHmd(HMD hmd) {
		this.hmd = hmd;
	}

	public Decomposition getDecomposition() {
		return decomposition;
	}
	public void setDecomposition(Decomposition decomposition) {
		this.decomposition = decomposition;
	}

	public int calculaTotalDependencias() {
		int total = 0;
		for (int i = 0; i < this.classCount; i++) {
			total += this.qtdDependenciasPara[i];
		}
		return total;
	}
	
}
