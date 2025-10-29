package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatosIndexados {
    private final Map<String, Set<Integer>> indiceInvertido;
    private final List<String> lineasOriginales;

    public DatosIndexados(Map<String, Set<Integer>> indiceInvertido, List<String> lineasOriginales) {
        this.indiceInvertido = indiceInvertido;
        this.lineasOriginales = lineasOriginales;
    }

    public Map<String, Set<Integer>> getIndiceInvertido() {
        return indiceInvertido;
    }

    public List<String> getLineasOriginales() {
        return lineasOriginales;
    }
}
