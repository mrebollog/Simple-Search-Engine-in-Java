package search;

import java.util.*;

public class AllStrategy implements SearchStrategy {
    @Override
    public Set<Integer> search(String[] consulta, Map<String, Set<Integer>> indiceInvertido, List<String> lineas) {
        Set<Integer> resultado = new LinkedHashSet<>();
        for (String palabra : consulta) {
            Set<Integer> lineasPalabra = indiceInvertido.get(palabra);
            if (lineasPalabra == null) {
                return Collections.emptySet();
            }
            if (resultado.isEmpty()) {
                resultado.addAll(lineasPalabra);
            } else {
                resultado.retainAll(lineasPalabra);
                if (resultado.isEmpty()) {
                    return resultado;
                }
            }
        }
        return resultado;
    }
}
