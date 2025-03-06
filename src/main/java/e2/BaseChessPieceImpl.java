package e2;

import java.util.Collection;
import java.util.Optional;

public class BaseChessPieceImpl implements ChessPiece {

    private Optional<Pair<Integer, Integer>> position;
    private final ChessPieceFunctions movementFunction;

    public BaseChessPieceImpl(ChessPieceFunctions movementFunction) {
        this.position = Optional.empty();
        this.movementFunction = movementFunction;
    }

    public Pair<Integer, Integer> getPosition() {
        checkPositionIsSet();
        return this.position.get();
    }

    public Collection<Pair<Integer, Integer>> getPossibleMovements(int sizeOfTheBoard) {
        checkPositionIsSet();
        checkValidActualPositionWithSizeOfTheBoard(sizeOfTheBoard);
        return movementFunction.getMovementFunction().apply(this.position.get(), sizeOfTheBoard);
    }

    private void checkValidActualPositionWithSizeOfTheBoard(int sizeOfTheBoard) {
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
