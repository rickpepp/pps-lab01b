package e2;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {

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
        knight.setNewPosition(new Pair<>(5, 5));
        assertEquals(new Pair<>(5, 5), knight.getPosition());
    }

    @Test
    public void testKnightGetPossibleMovementsInvalidPosition() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(10, 10));
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleMovementsPositionNotSet() {
        ChessPiece knight = factory.createKnight();
        assertThrows(IllegalStateException.class,
                () -> knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleMovements() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(5, 5));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(6, 7),
                new Pair<>(7, 6),
                new Pair<>(4, 7),
                new Pair<>(3, 6),
                new Pair<>(4, 3),
                new Pair<>(3, 4),
                new Pair<>(6, 3),
                new Pair<>(7, 4)
        );
        assertSameCollection(resultExpected, knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleMovementsOnEdge() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(9, 5));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(8, 7),
                new Pair<>(7, 6),
                new Pair<>(8, 3),
                new Pair<>(7, 4)
        );
        assertSameCollection(resultExpected, knight.getPossibleMovements(10));
    }

    @Test
    public void testKnightGetPossibleHits() {
        ChessPiece knight = factory.createKnight();
        knight.setNewPosition(new Pair<>(9, 5));
        Collection<Pair<Integer, Integer>> resultExpected = Arrays.asList(
                new Pair<>(8, 7),
                new Pair<>(7, 6),
                new Pair<>(8, 3),
                new Pair<>(7, 4)
        );
        assertSameCollection(resultExpected, knight.getPossibleHits(10));
    }

    private void assertSameCollection(Collection<Pair<Integer, Integer>> collectionExpected, Collection<Pair<Integer, Integer>> collectionResult) {
        assertTrue(collectionResult.size() == collectionExpected.size()
                && collectionResult.containsAll(collectionExpected)
                && collectionExpected.containsAll(collectionResult));
    }

}
