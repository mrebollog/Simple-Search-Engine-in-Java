package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchStrategy {
    Set<Integer> search(String[] consulta, Map<String, Set<Integer>> indiceInvertido, List<String> lineas);
}