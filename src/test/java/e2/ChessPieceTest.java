package e2;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {

    @Test
    public void testChessPieceNotInitializedPositionException() {
        ChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece knight = factory.createKnight();
        assertThrows(IllegalStateException.class,
                knight::getPosition);
    }

    @Test
    public void testChessPieceGetPosition() {
        ChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(5, 5));
        assertEquals(new Pair<>(5, 5), knight.getPosition());
    }

    @Test
    public void testKnightGetPossibleMovementsInvalidPosition() {
        ChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(10, 10));
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleMovementsPositionNotSet() {
        ChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece knight = factory.createKnight();
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleMovements() {
        Collection<Pair<Integer, Integer>> resultExpected = new ArrayList<>();
        resultExpected.add(new Pair<>(6, 7));
        resultExpected.add(new Pair<>(7, 6));
        resultExpected.add(new Pair<>(4, 7));
        resultExpected.add(new Pair<>(3, 6));
        resultExpected.add(new Pair<>(4, 3));
        resultExpected.add(new Pair<>(3, 4));
        resultExpected.add(new Pair<>(6, 3));
        resultExpected.add(new Pair<>(7, 4));
        ChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(5, 5));
        Collection<Pair<Integer, Integer>> result = knight.getPossibleMovements(10);
        assertTrue(result.size() == resultExpected.size() && result.containsAll(resultExpected) && resultExpected.containsAll(result));
    }


}
