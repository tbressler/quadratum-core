package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.Player;
import de.tbressler.quadratum.model.Square;

/**
 * A listener for the game logic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public interface IGameLogicListener {

    /**
     * Method is called when the game is over.
     *
     * @param winner The winner of the game or null.
     */
    void onGameOver(Player winner);

    /**
     * Method is called when a new square was found.
     *
     * @param square The new square that was found, never null.
     */
    void onNewSquareFound(Square square);

    /**
     * Method is called when the active player changed.
     *
     * @param activePlayer The active player, never null.
     */
    void onActivePlayerChanged(Player activePlayer);

    /**
     * Method is called when a game was started.
     *
     * @param activePlayer The active player, never null.
     */
    void onGameStarted(Player activePlayer);

}
