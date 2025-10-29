package search;

public class StrategyFactory {
    public static SearchStrategy getStrategy(String name) {
        return switch (name.toUpperCase()) {
            case "ALL" -> new AllStrategy();
            case "ANY" -> new AnyStrategy();
            case "NONE" -> new NoneStrategy();
            default -> throw new IllegalArgumentException("Invalid strategy");
        };
    }
}
