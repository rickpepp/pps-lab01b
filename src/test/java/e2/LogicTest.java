package e2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

  @Test
  public void testInvalidNegativeSize() {
    assertThrows(IllegalArgumentException.class,
            () -> new LogicsImpl(-1));
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
}
