package e2;

import java.util.Collection;
import java.util.function.BiFunction;

public interface ChessPieceFunctions {
    public BiFunction<Pair<Integer, Integer>, Integer, Collection<Pair<Integer, Integer>>> getMovementFunction();
    public BiFunction<Pair<Integer, Integer>, Integer, Collection<Pair<Integer, Integer>>> getHitFunction();
}
