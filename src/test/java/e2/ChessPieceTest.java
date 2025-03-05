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
}
