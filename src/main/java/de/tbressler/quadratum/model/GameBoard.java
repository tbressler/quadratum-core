package de.tbressler.quadratum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Range.closed;
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
        checkStartGamePrecondition(activePlayer);

        clearGameBoard();

        isStarted = true;

        setActivePlayerTo(activePlayer);
        fireOnGameStarted(activePlayer);
    }

    /* Checks if the active player is valid. */
    private void checkStartGamePrecondition(Player activePlayer) {
        if (!(requireNonNull(activePlayer).equals(player1) ||
              requireNonNull(activePlayer).equals(player2)))
            throw new AssertionError("Player is unknown at the game board!");
    }

    /** Clears the game board. */
    private void clearGameBoard() {
        for (int i = 0; i < 64; i++)
            board[i] = 0;
    }

    /* Notifies the game board listeners that the game has started. */
    private void fireOnGameStarted(Player activePlayer) {
        for (IGameBoardListener listener : listeners)
            listener.onGameStarted(activePlayer);
    }


    /* Changes the active player to the given player. */
    private void setActivePlayerTo(Player player) {
        this.activePlayer = player;
        fireOnActivePlayerChanged(player);
    }

    /* Notifies all listeners that the active player has changed. */
    private void fireOnActivePlayerChanged(Player player) {
        for (IGameBoardListener listener: listeners)
            listener.onActivePlayerChanged(player);
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
     * Place a piece on the game board.
     *
     * @param index The field index, between 0 and 63.
     * @param player The player, must not be null.
     */
    public void placePiece(int index, Player player) {
        checkPlacePiecePrecondition(index, player);

        // Place piece and set next player:
        if (player.equals(player1)) {
            board[index] = 1;
            setActivePlayerTo(player2);
        } else if (player == player2) {
            board[index] = 2;
            setActivePlayerTo(player1);
        }

        fireOnPiecePlaced(index, player);
    }

    /* Checks the preconditions for placing a piece on the game board. */
    private void checkPlacePiecePrecondition(int index, Player player) {
        if (!isStarted())
            throw new AssertionError("The game is not started yet!");
        checkFieldIndex(index);
        checkStartGamePrecondition(player);
        if (!getActivePlayer().equals(player))
            throw new AssertionError("The player is not active currently!");

        // Check if field is empty
        if (!isFieldEmpty(index))
            throw new AssertionError("The given field index is not empty!");
    }

    /* Notifies all listeners that a piece was placed on the game board. */
    private void fireOnPiecePlaced(int index, Player player) {
        for(IGameBoardListener listener : listeners)
            listener.onPiecePlaced(index, player);
    }

    /**
     * Returns true if the field is empty. Otherwise this method returns false.
     *
     * @param index The field index, between 0 and 63.
     * @return true if the field is empty or false.
     */
    public boolean isFieldEmpty(int index) {
        checkFieldIndex(index);
        return (board[index] == 0);
    }

    /* Checks if index is in range. */
    private void checkFieldIndex(int index) {
        if (!closed(0, 63).contains(index))
            throw new AssertionError("Index must be between 0 and 63!");
    }


    /**
     * Returns the player who placed the piece on the game board or null if no piece was placed
     * on the given field.
     *
     * @param index The field index, between 0 and 63.
     * @return The player who placed the piece or null if no piece was placed.
     */
    public Player getPiece(int index) {
        checkFieldIndex(index);
        int piece = board[index];
        if (piece == 1)
            return player1;
        else if (piece == 2)
            return player2;
        return null;
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
