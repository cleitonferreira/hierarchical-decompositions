package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entidade {

    /*Construtor com o nome da entidade*/
    public Entidade(String nome) {
        this.nome = nome;
    }

    private String nome;
    private Collection<Entidade> links;

}
