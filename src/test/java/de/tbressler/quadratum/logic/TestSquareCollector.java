package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import de.tbressler.quadratum.model.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for class SquareCollector.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestSquareCollector {

    // Class under test:
    private SquareCollector squareCollector;

    // Mocks:
    private Player player1 = mock(Player.class, "player1");
    private Player player2 = mock(Player.class, "player2");

    private GameBoard gameBoard = mock(GameBoard.class, "gameBoard");


    @Before
    public void setUp() {
        when(gameBoard.getPlayer1()).thenReturn(player1);
        when(gameBoard.getPlayer2()).thenReturn(player2);
        squareCollector = new SquareCollector();
    }

    @Test
    public void getSquares_afterConstruction_returnsEmptySet() {
        assertTrue(squareCollector.getSquares().isEmpty());
    }

    @Test
    public void getSquares_after2SquaresDetected_returnsSetWith2Squares() {
        // TODO Write test!
    }

    @Test
    public void getSquares_afterReset_clearsSquares() {
        // TODO Fill squares!
        squareCollector.reset();
        assertTrue(squareCollector.getSquares().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void detect_withNullGameBoard_throwsException() {
        squareCollector.detect(null, player1);
    }

    @Test(expected = NullPointerException.class)
    public void detect_withNullPlayer_throwsException() {
        squareCollector.detect(gameBoard, null);
    }

    @Test
    public void detect_withValidSquareOnGameBoardForPlayer1_returnsSetWithSquare() {
        when(gameBoard.getPiece(0)).thenReturn(player1);
        when(gameBoard.getPiece(1)).thenReturn(player1);
        when(gameBoard.getPiece(8)).thenReturn(player1);
        when(gameBoard.getPiece(9)).thenReturn(player1);

        Set<Square> result = squareCollector.detect(gameBoard, player1);

        assertEquals(1, result.size());
        assertTrue(result.contains(new Square(new int[]{0,1,8,9}, player1)));
    }

    @Test
    public void detect_withValidSquareOnGameBoardForPlayer2_returnsSetWithSquare() {
        when(gameBoard.getPiece(0)).thenReturn(player2);
        when(gameBoard.getPiece(1)).thenReturn(player2);
        when(gameBoard.getPiece(8)).thenReturn(player2);
        when(gameBoard.getPiece(9)).thenReturn(player2);

        Set<Square> result = squareCollector.detect(gameBoard, player2);

        assertEquals(1, result.size());
        assertTrue(result.contains(new Square(new int[]{0,1,8,9}, player2)));
    }

    @Test
    public void detect_withEmptyGameBoard_returnsEmptySetForPlayer1() {
        Set<Square> result = squareCollector.detect(gameBoard, player1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void detect_withEmptyGameBoard_returnsEmptySetForPlayer2() {
        Set<Square> result = squareCollector.detect(gameBoard, player2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void detect_withInvalidSquareOnGameBoard1_returnsEmptySet() {
        when(gameBoard.getPiece(1)).thenReturn(player1);
        when(gameBoard.getPiece(15)).thenReturn(player1);
        when(gameBoard.getPiece(40)).thenReturn(player1);
        when(gameBoard.getPiece(62)).thenReturn(player1);

        Set<Square> result = squareCollector.detect(gameBoard, player1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void detect_withInvalidSquareOnGameBoard2_returnsEmptySet() {
        when(gameBoard.getPiece(1)).thenReturn(player1);
        when(gameBoard.getPiece(15)).thenReturn(player1);
        when(gameBoard.getPiece(48)).thenReturn(player2);
        when(gameBoard.getPiece(62)).thenReturn(player1);

        Set<Square> result = squareCollector.detect(gameBoard, player1);
        assertTrue(result.isEmpty());
    }

}
