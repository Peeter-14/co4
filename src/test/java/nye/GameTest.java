package nye;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGameStart() {
        Board board = new Board(6, 7);
        Player player1 = new Player("Player 1", "Yellow");
        Player player2 = new AIPlayer("Red");
        Game game = new Game("Player 1", "AI", 6, 7);

        // Játék elindítása
        assertDoesNotThrow(() -> game.start());
    }

    @Test
    public void testWinner() {
        Board board = new Board(6, 7);
        Player player = new Player("Player 1", "Yellow");
        Game game = new Game("Player 1", "AI", 6, 7);

        // Feltételezzük, hogy a játékos nyer
        board.dropDisc(0, "Yellow");
        board.dropDisc(1, "Yellow");
        board.dropDisc(2, "Yellow");
        boolean winner = board.checkWin("Yellow");

        assertTrue(winner);
    }
}
