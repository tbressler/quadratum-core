package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
    public void startGame_withPlayer1_startsGameAtGameBoardWithPlayer1() {
        gameLogic.startGame(player1);
        verify(gameBoard, times(1)).startGame(player1);
    }

    @Test
    public void startGame_withPlayer2_startsGameAtGameBoardWithPlayer2() {
        gameLogic.startGame(player2);
        verify(gameBoard, times(1)).startGame(player2);
    }

    @Test
    public void startGame_clearsSquares() {
        // TODO Test if squares get cleared!
    }


    @Test
    public void getGameBoard_returnsGameBoard() {
        assertEquals(gameBoard, gameLogic.getGameBoard());
    }


    @Test
    public void getSquares_returnsEmptySet() {
        assertEquals(0, gameLogic.getSquares().size());
    }

}
