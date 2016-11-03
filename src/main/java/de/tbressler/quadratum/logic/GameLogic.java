package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.GameBoard;
import de.tbressler.quadratum.model.Player;
import de.tbressler.quadratum.model.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    /* Player of player logic 1. */
    private final Player player1;

    /* Player of player logic 2. */
    private final Player player2;

    /* The squares. */
    private Set<Square> squares = new HashSet<>();

    /* The active player. */
    private Player activePlayer;

    /* Is true if the game was started, otherwise false. */
    private boolean isStarted = false;

    /* The listeners. */
    private List<IGameLogicListener> listeners = new ArrayList<>();


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

        setActivePlayerTo(player);
        fireOnGameStarted(player);
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

    /* Changes the active player to the given player. */
    private void setActivePlayerTo(Player player) {
        this.activePlayer = player;
        fireOnActivePlayerChanged(player);
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
        return activePlayer;
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
