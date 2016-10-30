package de.tbressler.quadratum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * A game board.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class GameBoard {

    /* Player one. */
    private final Player player1;

    /* Player two. */
    private final Player player2;

    /* The active player. */
    private Player activePlayer;

    /* The fields of the game board. */
    private int[] board = new int[64];

    /* Is true if the game was started, otherwise false. */
    private boolean isStarted = false;

    /* The game board listeners. */
    private List<IGameBoardListener> listeners = new ArrayList<>();


    /**
     * Creates a game board with the two given players.
     *
     * @param player1 Player one, must not be null.
     * @param player2 Player two, must not be null or equal to player one.
     */
    public GameBoard(Player player1, Player player2) {
        if (Objects.equals(player1, player2))
            throw new AssertionError("player1 must not be equal to player2!");
        this.player1 = requireNonNull(player1);
        this.player2 = requireNonNull(player2);
    }

    /**
     * Returns the first player.
     *
     * @return The first player, never null.
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player.
     *
     * @return The second player, never null.
     */
    public Player getPlayer2() {
        return player2;
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
     * Starts the game. Clears the game board if a game was started before.
     *
     * @param activePlayer The active player, who can do the first turn. Must not be null.
     */
    public void startGame(Player activePlayer) {
        checkActivePlayer(activePlayer);

        clearGameBoard();
        setActivePlayerTo(activePlayer);

        isStarted = true;
    }

    /* Checks if the active player is valid. */
    private void checkActivePlayer(Player activePlayer) {
        if (!(requireNonNull(activePlayer).equals(player1) ||
              requireNonNull(activePlayer).equals(player2)))
            throw new AssertionError("Only player one or two can be set as active player!");
    }

    /** Clears the game board. */
    private void clearGameBoard() {
        for (int i = 0; i < 64; i++)
            board[i] = 0;
    }

    /* Changes the active player to the given player. */
    private void setActivePlayerTo(Player player) {
        this.activePlayer = player;
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
     * Adds a listener to the game board.
     *
     * @param listener the listener, must not be null.
     */
    public void addGameBoardListener(IGameBoardListener listener) {
        listeners.add(requireNonNull(listener));
    }

    /**
     * Removes a listener from the game board.
     *
     * @param listener the listener, must not be null.
     */
    public void removeGameBoardListener(IGameBoardListener listener) {
        listeners.remove(requireNonNull(listener));
    }

}
