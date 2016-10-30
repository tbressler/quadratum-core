package de.tbressler.quadratum.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Tests for class GameBoard.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestGameBoard {

    // Class under test:
    private GameBoard gameBoard;

    // Mocks:
    private Player player1;
    private Player player2;
    private IGameBoardListener listener;


    @Before
    public void setUp() {
        player1 = mock(Player.class, "player1");
        player2 = mock(Player.class, "player2");

        listener = mock(IGameBoardListener.class, "listener");

        gameBoard = new GameBoard(player1, player2);
    }


    @Test(expected = NullPointerException.class)
    public void new_withNullPlayerOne_throwsException() {
        new GameBoard(null, player2);
    }

    @Test(expected = NullPointerException.class)
    public void new_withNullPlayerTwo_throwsException() {
        new GameBoard(player1, null);
    }

    @Test(expected = AssertionError.class)
    public void new_withEqualPlayers_throwsException() {
        new GameBoard(player1, player1);
    }


    @Test
    public void getPlayer1_returnsPlayerOne() {
        assertEquals(player1, gameBoard.getPlayer1());
    }


    @Test
    public void getPlayer2_returnsPlayerTwo() {
        assertEquals(player2, gameBoard.getPlayer2());
    }
}
