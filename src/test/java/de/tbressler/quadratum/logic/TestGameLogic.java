package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for class GameLogic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestGameLogic {

    // Class under test:
    private GameLogic gameLogic;

    // Mocks:
    private Player player1 = mock(Player.class, "player1");
    private Player player2 = mock(Player.class, "player2");

    private IPlayerLogic playerLogic1 = mock(IPlayerLogic.class, "playerLogic1");
    private IPlayerLogic playerLogic2 = mock(IPlayerLogic.class, "playerLogic2");

    private GameBoard gameBoard = mock(GameBoard.class, "gameBoard");


    @Before
    public void setUp() {
        when(gameBoard.getPlayer1()).thenReturn(player1);
        when(gameBoard.getPlayer2()).thenReturn(player2);
        when(playerLogic1.getPlayer()).thenReturn(player1);
        when(playerLogic2.getPlayer()).thenReturn(player2);
        gameLogic = new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }


    @Test(expected = NullPointerException.class)
    public void new_withNullPlayerLogic1_throwsException() {
        new GameLogic(gameBoard, null, playerLogic2);
    }

    @Test(expected = NullPointerException.class)
    public void new_withNullPlayerLogic2_throwsException() {
        new GameLogic(gameBoard, playerLogic1, null);
    }

    @Test(expected = NullPointerException.class)
    public void new_withNullGameBoard_throwsException() {
        when(playerLogic1.getPlayer()).thenReturn(player1);
        when(playerLogic2.getPlayer()).thenReturn(player2);
        new GameLogic(null, playerLogic1, playerLogic2);
    }

    @Test(expected = AssertionError.class)
    public void new_withPlayerLogicHasSamePlayer_throwsException() {
        when(playerLogic1.getPlayer()).thenReturn(player1);
        when(playerLogic2.getPlayer()).thenReturn(player1);
        new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }

    @Test(expected = NullPointerException.class)
    public void new_withPlayerLogic1HasNullPlayer_throwsException() {
        when(playerLogic1.getPlayer()).thenReturn(null);
        when(playerLogic2.getPlayer()).thenReturn(player2);
        new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }

    @Test(expected = NullPointerException.class)
    public void new_withPlayerLogic2HasNullPlayer_throwsException() {
        when(playerLogic1.getPlayer()).thenReturn(player1);
        when(playerLogic2.getPlayer()).thenReturn(null);
        new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }

    @Test(expected = AssertionError.class)
    public void new_withPlayerLogic1HasDifferentPlayerThanGameBoard_throwsException() {
        when(playerLogic1.getPlayer()).thenReturn(player1);
        when(gameBoard.getPlayer1()).thenReturn(mock(Player.class, "someOtherPlayer"));
        new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }

    @Test(expected = AssertionError.class)
    public void new_withPlayerLogic2HasDifferentPlayerThanGameBoard_throwsException() {
        when(playerLogic2.getPlayer()).thenReturn(player2);
        when(gameBoard.getPlayer2()).thenReturn(mock(Player.class, "someOtherPlayer"));
        new GameLogic(gameBoard, playerLogic1, playerLogic2);
    }


    @Test(expected = NullPointerException.class)
    public void startGame_withNullPlayer_throwsException() {
        gameLogic.startGame(null);
    }

    @Test
    public void startGame_clearsSquares() {
        // TODO Test if squares get cleared!
    }


    // --------------------

//    /**
//     * Checks if isStarted() returns true after the game is started.
//     */
//    @Test
//    public void isStarted_returnsTrue_afterGameIsStarted() {
//        gameBoard.startGame(player1);
//        assertEquals(true, gameBoard.isStarted());
//    }
//
//    /**
//     * Checks if the active player is null after the initialization of the game board, because
//     * the game has not started yet.
//     */
//    @Test
//    public void getActivePlayer_returnsNull_afterNew() {
//        assertEquals(null, gameBoard.getActivePlayer());
//    }
//
//    /**
//     * Checks if getActivePlayer() returns player one after the game was started with player one
//     * as active player.
//     */
//    @Test
//    public void getActivePlayer_returnsPlayer1_ifGameIsStartedWithPlayer1() {
//        gameBoard.startGame(player1);
//        assertEquals(player1, gameBoard.getActivePlayer());
//    }
//
//    /**
//     * Checks if getActivePlayer() returns player two after the game was started with player two
//     * as active player.
//     */
//    @Test
//    public void getActivePlayer_returnsPlayer2_ifGameIsStartedWithPlayer2() {
//        gameBoard.startGame(player2);
//        assertEquals(player2, gameBoard.getActivePlayer());
//    }
//
//
//    /**
//     * Checks if an exception is thrown if the active player is null.
//     */
//    @Test(expected = NullPointerException.class)
//    public void startGame_withNull_throwsException() {
//        gameBoard.startGame(null);
//    }
//
//    /**
//     * Checks if an exception is thrown if the active player is not player one or two.
//     */
//    @Test(expected = AssertionError.class)
//    public void startGame_withUnknownPlayer_throwsException() {
//        gameBoard.startGame(mock(Player.class, "unknown-player"));
//    }
//
//    /**
//     * Checks if startGame() clears the game board.
//     */
//    @Test
//    public void startGame_clearsGameBoard() {
//        // Start the game board and place pieces:
//        gameBoard.startGame(player1);
//        gameBoard.placePiece(1, player1);
//        gameBoard.placePiece(5, player2);
//
//        // (Re)start the game:
//        gameBoard.startGame(player1);
//
//        // Check if the game board is empty:
//        for(int i = 0; i < 64; i++)
//            assertEquals(true, gameBoard.isFieldEmpty(i));
//    }
//
//    /**
//     * Checks if startGame() notifies the game board listeners that the active player has changed.
//     */
//    @Test
//    public void startGame_notifiesListeners_onActivePlayerChanged() {
//        gameBoard.startGame(player1);
//        verify(listener, times(1)).onActivePlayerChanged(player1);
//    }
//
//    /**
//     * Checks if startGame() notifies the game board listeners that the game has started.
//     */
//    @Test
//    public void startGame_notifiesListeners_onGameStarted() {
//        gameBoard.startGame(player1);
//        verify(listener, times(1)).onGameStarted(player1);
//    }
//
//
//    /**
//     * Checks if isStarted() returns false after the initialization of the game board, because
//     * the game has not started yet.
//     */
//    @Test
//    public void isStarted_returnsFalse_afterNew() {
//        assertEquals(false, gameBoard.isStarted());
//    }
//
//    /**
//     * Checks if an exception is thrown if the given player is not the active player.
//     */
//    @Test(expected = AssertionError.class)
//    public void placePiece_withInactivePlayer_throwsException() {
//        gameBoard.placePiece(0, player2);
//    }
//
//    /**
//     * Checks if an exception is thrown if game is not started yet.
//     */
//    @Test(expected = AssertionError.class)
//    public void placePiece_ifGameIsNotStarted_throwsException() {
//        gameBoard.placePiece(0, player1);
//    }

    // --------------------


    @Test
    public void getGameBoard_returnsGameBoard() {
        assertEquals(gameBoard, gameLogic.getGameBoard());
    }


    @Test
    public void getSquares_returnsEmptySet() {
        assertEquals(0, gameLogic.getSquares().size());
    }


    @Test(expected = NullPointerException.class)
    public void addGameLogicListener_withNull_throwsException() {
        gameLogic.addGameLogicListener(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeGameLogicListener_withNull_throwsException() {
        gameLogic.removeGameLogicListener(null);
    }

}
