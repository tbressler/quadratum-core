package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for class GameOverVerifier.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestGameOverVerifier {

    // Class under test:
    private GameOverVerifier gameOverVerifier;

    // Mocks:
    private Player player1 = mock(Player.class, "player1");
    private Player player2 = mock(Player.class, "player2");

    private GameBoard gameBoard = mock(GameBoard.class, "gameBoard");


    @Before
    public void setUp() {
        when(gameBoard.getPlayer1()).thenReturn(player1);
        when(gameBoard.getPlayer2()).thenReturn(player2);
        gameOverVerifier = new GameOverVerifier(150,15);
    }


    @Test(expected = AssertionError.class)
    public void new_withNegativeMinimumScore_throwsException() {
        new GameOverVerifier(-1, 15);
    }

    @Test(expected = AssertionError.class)
    public void new_withMinimumScore0_throwsException() {
        new GameOverVerifier(0, 15);
    }

    @Test(expected = AssertionError.class)
    public void new_withNegativeMinimumDifference_throwsException() {
        new GameOverVerifier(150, -1);
    }

    @Test(expected = AssertionError.class)
    public void new_withMinimumDifference0_throwsException() {
        new GameOverVerifier(150, 0);
    }

}
