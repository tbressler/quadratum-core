package de.tbressler.quadratum.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

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


    /**
     * getPlayer1() must always return player one.
     */
    @Test
    public void getPlayer1_returnsPlayerOne() {
        assertEquals(player1, gameBoard.getPlayer1());
    }


    /**
     * getPlayer2() must always return player two.
     */
    @Test
    public void getPlayer2_returnsPlayerTwo() {
        assertEquals(player2, gameBoard.getPlayer2());
    }


    /**
     * Checks if the active player is null after the initialization of the game board, because
     * the game has not started yet.
     */
    @Test
    public void getActivePlayer_returnsNull_afterNew() {
        assertEquals(null, gameBoard.getActivePlayer());
    }

    /**
     * Checks if getActivePlayer() returns player one after the game was started with player one
     * as active player.
     */
    @Test
    public void getActivePlayer_returnsPlayer1_ifGameIsStartedWithPlayer1() {
        gameBoard.startGame(player1);
        assertEquals(player1, gameBoard.getActivePlayer());
    }

    /**
     * Checks if getActivePlayer() returns player two after the game was started with player two
     * as active player.
     */
    @Test
    public void getActivePlayer_returnsPlayer2_ifGameIsStartedWithPlayer2() {
        gameBoard.startGame(player2);
        assertEquals(player2, gameBoard.getActivePlayer());
    }


    /**
     * Checks if an exception is thrown if the active player is null.
     */
    @Test(expected = NullPointerException.class)
    public void startGame_withNull_throwsException() {
        gameBoard.startGame(null);
    }

    /**
     * Checks if an exception is thrown if the active player is not player one or two.
     */
    @Test(expected = AssertionError.class)
    public void startGame_withUnknownPlayer_throwsException() {
        gameBoard.startGame(mock(Player.class, "unknown-player"));
    }

    /**
     * Checks if startGame() clears the game board.
     */
    @Test
    public void startGame_clearsGameBoard() {

        // TODO Check if game board is cleared after starting the game.

    }


    /**
     * Checks if isStarted() returns false after the initialization of the game board, because
     * the game has not started yet.
     */
    @Test
    public void isStarted_returnsFalse_afterNew() {
        assertEquals(false, gameBoard.isStarted());
    }

    /**
     * Checks if isStarted() returns true after the game is started.
     */
    @Test
    public void isStarted_returnsTrue_afterGameIsStarted() {
        gameBoard.startGame(player1);
        assertEquals(true, gameBoard.isStarted());
    }


    @Test(expected = NullPointerException.class)
    public void addGameBoardListener_withNull_throwsException() {
        gameBoard.addGameBoardListener(null);
    }


    @Test(expected = NullPointerException.class)
    public void removeGameBoardListener_withNull_throwsException() {
        gameBoard.removeGameBoardListener(null);
    }

}
