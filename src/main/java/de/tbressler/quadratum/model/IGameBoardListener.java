package de.tbressler.quadratum.model;

/**
 * A listener for the game board.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public interface IGameBoardListener {

    /**
     * This method is called, if the active player has changed.
     *
     * @param newPlayer The new player, who is now active.
     */
    void onActivePlayerChanged(Player newPlayer);

    /**
     * This method is called, if a piece was placed on the game board.
     *
     * @param index The index where the piece was placed.
     * @param player The player who has placed the piece.
     */
    void onPiecePlaced(int index, Player player);

    /**
     * This method is called, if the game was (re)started.
     *
     * @param activePlayer The active player.
     */
    void onGameStarted(Player activePlayer);

}
