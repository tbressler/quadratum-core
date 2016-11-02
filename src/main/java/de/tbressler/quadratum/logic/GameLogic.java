package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.IGameBoardListener;
import de.tbressler.quadratum.model.Player;
import de.tbressler.quadratum.model.Square;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * The game logic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class GameLogic {

    /*The game board. */
    private final GameBoard gameBoard;

    /* The player logic of player1. */
    private final IPlayerLogic playerLogic1;

    /* The player logic of player2. */
    private final IPlayerLogic playerLogic2;

    /* The squares. */
    private Set<Square> squares = new HashSet<>();


    /**
     * Creates the game logic.
     *
     * @param gameBoard The game board, must not be null.
     * @param playerLogic1 The logic for player 1, must not be null.
     * @param playerLogic2 The logic for player 2, must not be null.
     */
    public GameLogic(GameBoard gameBoard, IPlayerLogic playerLogic1, IPlayerLogic playerLogic2) {
        this.gameBoard = requireNonNull(gameBoard);
        this.playerLogic1 = requireNonNull(playerLogic1);
        this.playerLogic2 = requireNonNull(playerLogic2);

        Player player1 = requireNonNull(playerLogic1.getPlayer());
        Player player2 = requireNonNull(playerLogic2.getPlayer());

        checkPlayers(gameBoard, player1, player2);

        initGameBoard();
    }

    /* Checks if players of player logic and game board are corrent. */
    private void checkPlayers(GameBoard gameBoard, Player player1, Player player2) {
        if (player1.equals(player2))
            throw new AssertionError("playerLogic1 and playerLogic2 uses the same player!");
        if (!player1.equals(gameBoard.getPlayer1()))
            throw new AssertionError("Player 1 of game board is different to player " +
                    "of playerLogic1!");
        if (!player2.equals(gameBoard.getPlayer2()))
            throw new AssertionError("Player 2 of game board is different to player " +
                    "of playerLogic2!");
    }

    /* Adds listeners to game board. */
    private void initGameBoard() {
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
     * (Re)starts game.
     *
     * @param player The active player, who can do the first turn. Must not be null.
     */
    public void startGame(Player player) {
        squares.clear();
        gameBoard.startGame(requireNonNull(player));
    }


    /**
     * Returns the game board.
     *
     * @return The game board, never null.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }


    /**
     * Returns all found squares of the current game.
     *
     * @return A set of squares or an empty set.
     */
    public Set<Square> getSquares() {
        return squares;
    }

}
