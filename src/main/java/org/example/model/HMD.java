package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
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
}
