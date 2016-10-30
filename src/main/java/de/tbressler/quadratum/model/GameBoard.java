package de.tbressler.quadratum.model;

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

    /* Is true if the game was started, otherwise false. */
    private boolean isStarted = false;


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
     * Changes the active player to the given player.
     *
     * @param player The new active player.
     */
    private void setActivePlayerTo(Player player) {
        this.activePlayer = player;
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
     * Returns true if the game was started, otherwise the method returns false.
     *
     * @return true if the game was started, otherwise false.
     */
    public boolean isStarted() {
        return isStarted;
    }
}
