package e2;

import java.util.Collection;

public interface ChessPiece {
    public Pair<Integer, Integer> getPosition();
    public Collection<Pair<Integer, Integer>> getPossibleMovements(int sizeOfTheBoard);
    public Collection<Pair<Integer, Integer>> getPossibleHits(int sizeOfTheBoard);
    public void setNewPosition(Pair<Integer, Integer> newPosition);
}
