package search;

import java.util.*;

public class AnyStrategy implements SearchStrategy {
    @Override
    public Set<Integer> search(String[] consulta, Map<String, Set<Integer>> indiceInvertido, List<String> lineas) {
        Set<Integer> resultado = new LinkedHashSet<>();
        for (String palabra : consulta) {
            Set<Integer> lineasPalabra = indiceInvertido.get(palabra);
            if (lineasPalabra != null) {
                resultado.addAll(lineasPalabra);
            }
        }
        return resultado;
    }
}