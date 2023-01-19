package org.example.model;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HMD {

  private List<Modulo> modulos;

  public int getCountModulos() {
    int count = 0;
    if (modulos != null) {
      for (Modulo modulo : modulos) {
        count++;
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            count++;
          }
        }
      }
    }
    return count;
  }

  public int getCountEntidades() {
    int count = 0;
    if (modulos != null) {
      for (Modulo modulo : modulos) {
        if (modulo.getListaEntidades() != null) {
          count = count + modulo.getListaEntidades().size();
        }
        if (modulo.getSubmodulos() != null) {
          for (Modulo submodulo : modulo.getSubmodulos()) {
            if (submodulo.getListaEntidades() != null) {
              count = count + submodulo.getListaEntidades().size();
            }
          }
        }
      }
    }
    return count;
  }

  public List<Modulo> converterHMDParaModulosNaoHierrarquicos(final List<Modulo> listaModulos) {

    final List<Modulo> copyList = List.copyOf(listaModulos);

    return copyList.stream().flatMap(i -> {
      if (Objects.nonNull(i.getSubmodulos())) {
        return Stream.concat(Stream.of(i),
            converterHMDParaModulosNaoHierrarquicos(i.getSubmodulos().stream().collect(toList())).stream());
      }
      return Stream.of(i);

    //}).peek(v -> v.setSubmodulos(null)).collect(Collectors.toList());
    }).collect(Collectors.toList());
  }
}
