package e2;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BaseChessPieceImpl implements ChessPiece {

    private Optional<Pair<Integer, Integer>> position;
    //private BiFunction<Pair<Integer, Integer>, Integer, Collection<Pair<Integer, Integer>>> movementFunction;

    public BaseChessPieceImpl(/*BiFunction<Pair<Integer, Integer>, Integer, Collection<Pair<Integer, Integer>>> movementFunction*/) {
        this.position = Optional.empty();
        /*this.movementFunction = movementFunction;*/
    }

    public Pair<Integer, Integer> getPosition() {
        if (this.position.isEmpty())
            throw new IllegalStateException("Position is not set");
        return this.position.get();
    }

    public Collection<Pair<Integer, Integer>> getPossibleMovements(int sizeOfTheBoard) {
        /*if (this.position.isEmpty())
            throw new IllegalStateException("Position is not set");
        return movementFunction.apply(this.position.get(), sizeOfTheBoard);
    */
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Pair<Integer, Integer>> getPossibleHits(int sizeOfTheBoard) {
        throw new UnsupportedOperationException();
    }

    public void setNewPosition(Pair<Integer, Integer> newPosition) {
        this.position = Optional.of(newPosition);
    }
}
