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
        checkPositionIsSet();
        return this.position.get();
    }

    public Collection<Pair<Integer, Integer>> getPossibleMovements(int sizeOfTheBoard) {
        checkValidActualPositionWithSizeOfTheBoard(sizeOfTheBoard);
        /*if (this.position.isEmpty())
            throw new IllegalStateException("Position is not set");
        return movementFunction.apply(this.position.get(), sizeOfTheBoard);
    */
        throw new UnsupportedOperationException();
    }

    private void checkValidActualPositionWithSizeOfTheBoard(int sizeOfTheBoard) {
        checkPositionIsSet();
        if (this.position.get().getX() < 0
                || this.position.get().getY() < 0
                || this.position.get().getX() >= sizeOfTheBoard
                || this.position.get().getY() >= sizeOfTheBoard)
            throw new IllegalStateException("Actual position is invalid with this size of the board");
    }

    private void checkPositionIsSet() {
        if (this.position.isEmpty())
            throw new IllegalStateException("Position is not set");
    }

    @Override
    public Collection<Pair<Integer, Integer>> getPossibleHits(int sizeOfTheBoard) {
        throw new UnsupportedOperationException();
    }

    public void setNewPosition(Pair<Integer, Integer> newPosition) {
        this.position = Optional.of(newPosition);
    }
}
