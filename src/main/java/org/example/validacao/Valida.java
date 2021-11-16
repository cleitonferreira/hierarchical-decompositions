package org.example.validacao;

import java.util.Collection;
import java.util.Map;

public class Valida {

    public static boolean isNullOrEmpty( final Collection< ? > c ) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotNull( final Collection< ? > c ) {
        return c != null;
    }

    public static boolean isNullOrEmpty( final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }

    public static boolean isNotNull( final Map< ?, ? > m ) {
        return m != null;
    }
}
