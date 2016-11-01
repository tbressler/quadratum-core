package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.IGameBoardListener;
import de.tbressler.quadratum.model.Player;

import static java.util.Objects.requireNonNull;

/**
 * The game logic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class GameLogic {

    /*The game board. */
    private GameBoard gameBoard;

    /* The player logic of player1. */
    private final IPlayerLogic playerLogic1;

    /* The player logic of player2. */
    private final IPlayerLogic playerLogic2;


    /**
     * Creates the game logic.
     *
     * @param playerLogic1 The logic for player 1, must not be null.
     * @param playerLogic2 The logic for player 2, must not be null.
     */
    public GameLogic(IPlayerLogic playerLogic1, IPlayerLogic playerLogic2) {
        this.playerLogic1 = requireNonNull(playerLogic1);
        this.playerLogic2 = requireNonNull(playerLogic2);

        Player player1 = requireNonNull(playerLogic1.getPlayer());
        Player player2 = requireNonNull(playerLogic2.getPlayer());

        if (player1.equals(player2))
            throw new AssertionError("playerLogic1 and playerLogic2 uses the same player!");

        initGameBoard(player1, player2);
    }

    /* Creates the game board and adds listener. */
    private void initGameBoard(Player player1, Player player2) {
        gameBoard = new GameBoard(player1, player2);
        gameBoard.addGameBoardListener(new IGameBoardListener() {
            @Override
            public void onActivePlayerChanged(Player newPlayer) {
                // TODO Not implemented yet!
            }

            @Override
            public void onPiecePlaced(int index, Player player) {
                // TODO Not implemented yet!
            }

            @Override
            public void onGameStarted(Player activePlayer) {
                // TODO Not implemented yet!
            }
        });
    }


    /**
     * Returns the game board.
     *
     * @return The game board, never null.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

}
