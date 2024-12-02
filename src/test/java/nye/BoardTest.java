package nye;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testDropDisc() {
        Board board = new Board(6, 7);
        board.dropDisc(0, "Yellow");

        // Ellenőrizze, hogy a korong megfelelően került a táblába
        assertEquals("Yellow", board.getCell(5, 0));
    }

    @Test
    public void testInvalidDrop() {
        Board board = new Board(6, 7);
        board.dropDisc(0, "Red");

        // Ha a tábla tele van, nem lehet leejteni a korongot
        assertFalse(board.dropDisc(0, "Yellow"));
    }
}
