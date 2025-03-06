package e2;
import org.junit.jupiter.api.*;

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


}
