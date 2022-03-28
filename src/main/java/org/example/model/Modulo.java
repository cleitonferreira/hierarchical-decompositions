package org.example.model;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Modulo {

    private List<Entidade> listaEntidades;
    private String nome;
    private Collection<Modulo> submodulos;
    //private Module parent;

}
