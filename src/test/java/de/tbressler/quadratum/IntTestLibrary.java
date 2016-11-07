package de.tbressler.quadratum;

import de.tbressler.quadratum.logic.GameLogic;
import de.tbressler.quadratum.logic.IGameLogicListener;
import de.tbressler.quadratum.logic.players.HumanPlayerLogic;
import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.IGameBoardListener;
import de.tbressler.quadratum.model.Player;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Integration tests.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class IntTestLibrary {

    // Mocks for listeners:
    private IGameBoardListener boardListener = mock(IGameBoardListener.class, "boardListener");
    private IGameLogicListener logicListener = mock(IGameLogicListener.class, "logicListener");


    @Test
    public void integrationTest_withTwoHumanPlayers() {

        int[] movesOfPlayer1 = new int[] {00, 05, 10};
        int[] movesOfPlayer2 = new int[] {01, 02, 03};

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        GameBoard gameBoard = new GameBoard(player1, player2);
        gameBoard.addGameBoardListener(boardListener);

        HumanPlayerLogic playerLogic1 = new HumanPlayerLogic(player1);
        HumanPlayerLogic playerLogic2 = new HumanPlayerLogic(player2);

        GameLogic gameLogic = new GameLogic(gameBoard, playerLogic1, playerLogic2);
        gameLogic.addGameLogicListener(logicListener);

        gameLogic.startGame(player1);

        verify(logicListener, times(1)).onGameStarted(player1);

        for(int i = 0; i < movesOfPlayer1.length; i++) {

            verify(logicListener, times(1 + i)).onActivePlayerChanged(player1);

            playerLogic1.placePiece(movesOfPlayer1[i]);

            verify(boardListener, times(1)).onPiecePlaced(movesOfPlayer1[i], player1);
            verify(logicListener, times(1 + i)).onActivePlayerChanged(player2);

            playerLogic2.placePiece(movesOfPlayer2[i]);

            verify(boardListener, times(1)).onPiecePlaced(movesOfPlayer2[i], player2);
        }

        // TODO Verify square outcome!
    }

}
