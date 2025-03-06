package e2;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {

    private static final int BOARD_DIMENSION = 10;
    private static final int X_Y_MIDDLE_BOARD_POSITION = 5;
    private static final int INVALID_X_Y_POSITION = BOARD_DIMENSION;
    private static final int X_EDGE_POSITION = 9;
    private static final int Y_EDGE_POSITION = 5;

    private ChessPieceFactory factory;

    @BeforeEach
    public void init() {
        factory = new ChessPieceFactory();
    }

    @Test
    public void testChessPieceNotInitializedPositionException() {
        ChessPiece knight = factory.createKnight();
        assertThrows(IllegalStateException.class,
                knight::getPosition);
    }

    @Test
    public void testChessPieceGetPosition() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(X_Y_MIDDLE_BOARD_POSITION, X_Y_MIDDLE_BOARD_POSITION));
        assertEquals(new Pair<>(X_Y_MIDDLE_BOARD_POSITION, X_Y_MIDDLE_BOARD_POSITION), knight.getPosition());
    }

    @Test
    public void testKnightGetPossibleMovementsInvalidPosition() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(INVALID_X_Y_POSITION, INVALID_X_Y_POSITION));
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(BOARD_DIMENSION));
    }

    @Test
    public void testKnightGetPossibleMovementsPositionNotSet() {
        ChessPiece knight = factory.createKnight();
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(BOARD_DIMENSION));
    }

    @Test
    public void testKnightGetPossibleMovements() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(X_Y_MIDDLE_BOARD_POSITION, X_Y_MIDDLE_BOARD_POSITION));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION + 1, X_Y_MIDDLE_BOARD_POSITION + 2),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION + 2, X_Y_MIDDLE_BOARD_POSITION + 1),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION - 1, X_Y_MIDDLE_BOARD_POSITION + 2),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION - 2, X_Y_MIDDLE_BOARD_POSITION + 1),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION - 1, X_Y_MIDDLE_BOARD_POSITION - 2),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION - 2, X_Y_MIDDLE_BOARD_POSITION - 1),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION + 1, X_Y_MIDDLE_BOARD_POSITION - 2),
                new Pair<>(X_Y_MIDDLE_BOARD_POSITION + 2, X_Y_MIDDLE_BOARD_POSITION - 1)
        );
        assertSameCollection(resultExpected, knight.getPossibleMovements(BOARD_DIMENSION));
    }

    @Test
    public void testKnightGetPossibleMovementsOnEdge() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(X_EDGE_POSITION, Y_EDGE_POSITION));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(X_EDGE_POSITION - 2, Y_EDGE_POSITION + 1),
                new Pair<>(X_EDGE_POSITION - 1, Y_EDGE_POSITION + 2),
                new Pair<>(X_EDGE_POSITION - 2, Y_EDGE_POSITION - 1),
                new Pair<>(X_EDGE_POSITION - 1, Y_EDGE_POSITION - 2)
        );
        assertSameCollection(resultExpected, knight.getPossibleMovements(BOARD_DIMENSION));
    }

    @Test
    public void testKnightGetPossibleHits() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(X_EDGE_POSITION, Y_EDGE_POSITION));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(X_EDGE_POSITION - 2, Y_EDGE_POSITION + 1),
                new Pair<>(X_EDGE_POSITION - 1, Y_EDGE_POSITION + 2),
                new Pair<>(X_EDGE_POSITION - 2, Y_EDGE_POSITION - 1),
                new Pair<>(X_EDGE_POSITION - 1, Y_EDGE_POSITION - 2)
        );
        assertSameCollection(resultExpected, knight.getPossibleHits(BOARD_DIMENSION));
    }

    @Test
    public void testPawnGetPositionNotInitialized() {
        ChessPiece pawn = factory.createPawn();
        assertThrows(IllegalStateException.class,
                pawn::getPosition);
    }

    @Test
    public void testPawnGetPosition() {
        ChessPiece pawn = factory.createPawn();
        pawn.setNewPosition(new Pair<>(X_Y_MIDDLE_BOARD_POSITION, X_Y_MIDDLE_BOARD_POSITION));
        assertEquals(new Pair<>(X_Y_MIDDLE_BOARD_POSITION, X_Y_MIDDLE_BOARD_POSITION), pawn.getPosition());
    }

    private void assertSameCollection(Collection<Pair<Integer, Integer>> collectionExpected, Collection<Pair<Integer, Integer>> collectionResult) {
        assertTrue(collectionResult.size() == collectionExpected.size()
                && collectionResult.containsAll(collectionExpected)
                && collectionExpected.containsAll(collectionResult));
    }

}
