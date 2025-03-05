package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int INVALID_SIZE_NEGATIVE = -100;
  private static final int INVALID_SIZE_ZERO = 0;
  private static final int INVALID_SIZE_ONE = 1;
  private static final int VALID_SIZE = 10;
  private static final int VALID_X_PAWN = 5;
  private static final int VALID_Y_PAWN = 5;
  private static final int VALID_X_KNIGHT = 6;
  private static final int VALID_Y_KNIGHT = 7;
  private static final int INVALID_X_KNIGHT = VALID_SIZE;
  private static final int INVALID_Y_KNIGHT = VALID_SIZE;
  private static final int INVALID_SUBZERO_X_KNIGHT = -1;
  private static final int INVALID_SUBZERO_Y_KNIGHT = -1;

  @Test
  public void testInvalidNegativeSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(INVALID_SIZE_NEGATIVE));
  }

  @Test
  public void testInvalidZeroSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(INVALID_SIZE_ZERO));
  }

  // Size need to be >= 2 to put a pawn and knight in different position
  @Test
  public void testInvalidOneSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(INVALID_SIZE_ONE));
  }

  @Test
  public void testInvalidNegativePosition() {
    assertThrows(IndexOutOfBoundsException.class, () -> new LogicsImpl(VALID_SIZE,
            new Pair<>(INVALID_SUBZERO_X_KNIGHT, INVALID_SUBZERO_Y_KNIGHT),
            new Pair<>(VALID_X_PAWN, VALID_Y_PAWN)));
  }

  @Test
  public void testInvalidMajorOfSizePosition() {
    assertThrows(IndexOutOfBoundsException.class, () -> new LogicsImpl(VALID_SIZE,
            new Pair<>(INVALID_X_KNIGHT, INVALID_Y_KNIGHT),
            new Pair<>(VALID_X_PAWN, VALID_Y_PAWN)));
  }

  @Test
  public void testValidTrueHitFunction() {
    Logics logics = new LogicsImpl(VALID_SIZE,
            new Pair<>(VALID_X_KNIGHT, VALID_Y_KNIGHT),
            new Pair<>(VALID_X_PAWN, VALID_Y_PAWN));
    assertTrue(logics.hit(VALID_X_PAWN, VALID_Y_PAWN));
  }

  @Test
  public void testValidFalseHitFunction() {
    Logics logics = new LogicsImpl(VALID_SIZE,
            new Pair<>(VALID_X_KNIGHT, VALID_Y_KNIGHT),
            new Pair<>(VALID_X_PAWN + 1, VALID_Y_PAWN));
    assertFalse(logics.hit(VALID_X_KNIGHT + 1, VALID_Y_KNIGHT + 1));
  }

  @Test
  public void testInvalidHitFunction() {
    Logics logics = new LogicsImpl(VALID_SIZE,
            new Pair<>(VALID_X_KNIGHT, VALID_Y_KNIGHT),
            new Pair<>(VALID_X_PAWN, VALID_Y_PAWN));
    assertThrows(IndexOutOfBoundsException.class,
            () -> logics.hit(INVALID_X_KNIGHT, INVALID_Y_KNIGHT));
  }
}
