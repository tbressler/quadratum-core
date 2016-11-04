package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import de.tbressler.quadratum.model.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Range.closed;
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

    /* Player of player logic 1. */
    private final Player player1;

    /* Player of player logic 2. */
    private final Player player2;

    /* The squares. */
    private Set<Square> squares = new HashSet<>();

    /* The active player logic. */
    private IPlayerLogic activePlayerLogic;

    /* Is true if the game was started, otherwise false. */
    private boolean isStarted = false;

    /* The listeners. */
    private List<IGameLogicListener> listeners = new ArrayList<>();

    /* Callback for the player logic. */
    private ILogicCallback playerLogicCallback = new ILogicCallback() {
        @Override
        public boolean makeMove(int index, Player player) {
            if (!isStarted)
                throw new AssertionError("Game is not started!");
            if (!player.equals(activePlayerLogic.getPlayer()))
                throw new AssertionError("The player is not active!");
            if (!closed(0, 63).contains(index))
                throw new AssertionError("Index must be between 0 and 63!");

            if (!gameBoard.isFieldEmpty(index))
                return false;

            gameBoard.placePiece(index, player);

            switchActivePlayer();

            return true;
        }
    };


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

        this.player1 = requireNonNull(playerLogic1.getPlayer());
        this.player2 = requireNonNull(playerLogic2.getPlayer());

        checkPlayers(gameBoard, player1, player2);
    }

    /* Checks if players of player logic and game board are corrent. */
    private void checkPlayers(GameBoard gameBoard, Player player1, Player player2) {
        if (player1.equals(player2))
            throw new AssertionError("playerLogic1 and playerLogic2 uses the same player!");
        if ((!player1.equals(gameBoard.getPlayer1())) || (!player2.equals(gameBoard.getPlayer2())))
            throw new AssertionError("Players of logic and game board doesn't match!");
    }


    /**
     * Starts the game. Clears the game board if a game was started before.
     *
     * @param player The active player, who can do the first turn. Must not be null.
     */
    public void startGame(Player player) {
        checkStartGamePrecondition(player);

        gameBoard.clear();
        squares.clear();

        isStarted = true;

        fireOnGameStarted(player);

        if (player.equals(player1))
            setActivePlayerLogicTo(playerLogic1);
        else
            setActivePlayerLogicTo(playerLogic2);
    }

    /* Checks if the active player is valid. */
    private void checkStartGamePrecondition(Player activePlayer) {
        if (!(requireNonNull(activePlayer).equals(player1) ||
                requireNonNull(activePlayer).equals(player2)))
            throw new AssertionError("Player is unknown at the game board!");
    }

    /* Notifies the game board listeners that the game has started. */
    private void fireOnGameStarted(Player activePlayer) {
        for (IGameLogicListener listener : listeners)
            listener.onGameStarted(activePlayer);
    }

    private void switchActivePlayer() {
        if (activePlayerLogic.equals(playerLogic1))
            setActivePlayerLogicTo(playerLogic2);
        else
            setActivePlayerLogicTo(playerLogic1);
    }

    /* Changes the active player to the given player. */
    private void setActivePlayerLogicTo(IPlayerLogic playerLogic) {
        this.activePlayerLogic = playerLogic;
        fireOnActivePlayerChanged(playerLogic.getPlayer());

        // Request move at the player logic.
        playerLogic.requestMove(gameBoard, playerLogicCallback);
    }

    /* Notifies all listeners that the active player has changed. */
    private void fireOnActivePlayerChanged(Player player) {
        for (IGameLogicListener listener: listeners)
            listener.onActivePlayerChanged(player);
    }


    /**
     * Returns the active player or null if the game has not started yet.
     *
     * @return The active player or null.
     */
    public Player getActivePlayer() {
        if (activePlayerLogic == null)
            return null;
        return activePlayerLogic.getPlayer();
    }

    /**
     * Returns the active player logic or null if the game has not started yet.
     *
     * @return The active player logic or null.
     */
    public IPlayerLogic getActivePlayerLogic() {
        return activePlayerLogic;
    }


    /**
     * Returns true if the game was started, otherwise the method returns false.
     *
     * @return true if the game was started, otherwise false.
     */
    public boolean isStarted() {
        return isStarted;
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


    /**
     * Adds a listener to the game logic.
     *
     * @param listener the listener, must not be null.
     */
    public void addGameLogicListener(IGameLogicListener listener) {
        listeners.add(requireNonNull(listener));
    }

    /**
     * Removes a listener from the game logic.
     *
     * @param listener the listener, must not be null.
     */
    public void removeGameLogicListener(IGameLogicListener listener) {
        listeners.remove(requireNonNull(listener));
    }

}
