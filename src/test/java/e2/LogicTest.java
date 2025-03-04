package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  @Test
  public void testInvalidNegativeSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(-100));
  }

  @Test
  public void testInvalidZeroSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(0));
  }

  // Size need to be >= 2 to put a pawn and knight in different position
  @Test
  public void testInvalidOneSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(1));
  }

  @Test
  public void testInvalidNegativeKnightPosition() {
    assertThrows(IndexOutOfBoundsException.class,
            () -> new LogicsImpl(2, new Pair<>(-1, 0), new Pair<>(6, 7)));
  }

  @Test
  public void testInvalidMajorOfSizeKnightPosition() {
    assertThrows(IndexOutOfBoundsException.class,
            () -> new LogicsImpl(2, new Pair<>(0, 0), new Pair<>(6, 7)));
  }

  @Test
  public void testValidTrueHitFunction() {
    Logics logics = new LogicsImpl(10, new Pair<>(5, 5), new Pair<>(6, 7));
    assertTrue(logics.hit(6, 7));
  }

  @Test
  public void testValidFalseHitFunction() {
    Logics logics = new LogicsImpl(10, new Pair<>(5, 5), new Pair<>(6, 7));
    assertFalse(logics.hit(6, 6));
  }

  @Test
  public void testInvalidHitFunction() {
    Logics logics = new LogicsImpl(10, new Pair<>(5, 5), new Pair<>(6, 7));
    assertThrows(IndexOutOfBoundsException.class,
            () -> logics.hit(10, 6));
  }
}
