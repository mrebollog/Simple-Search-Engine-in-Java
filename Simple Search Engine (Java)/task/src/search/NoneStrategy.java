package search;

import java.util.*;

public class NoneStrategy implements SearchStrategy {
    @Override
    public Set<Integer> search(String[] consulta, Map<String, Set<Integer>> indiceInvertido, List<String> lineas) {
        Set<Integer> excluidas = new HashSet<>();
        for (String palabra : consulta) {
            Set<Integer> lineasPalabra = indiceInvertido.get(palabra);
            if (lineasPalabra != null) {
                excluidas.addAll(lineasPalabra);
            }
        }

        Set<Integer> resultado = new LinkedHashSet<>();
        for (int i = 0; i < lineas.size(); i++) {
            if (!excluidas.contains(i)) {
                resultado.add(i);
            }
        }
        return resultado;
    }
}
