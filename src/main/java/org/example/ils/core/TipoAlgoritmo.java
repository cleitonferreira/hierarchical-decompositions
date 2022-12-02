package org.example.ils.core;

public enum TipoAlgoritmo {
	GULOSO_HASHMAP,
	HILL_CLIMBING,
	ITERATED_LOCAL_SEARCH;
	
	public boolean isIteratedLocalSearch() {
		return this == ITERATED_LOCAL_SEARCH;  
	}
}
