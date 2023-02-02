package org.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Modulo {

    private List<Entidade> listaEntidades;
    private String nome;
    private Collection<Modulo> submodulos;
    //private Module parent;


    public void preparandoSubmodulos() {
        this.submodulos = new ArrayList<>();
    }
}
