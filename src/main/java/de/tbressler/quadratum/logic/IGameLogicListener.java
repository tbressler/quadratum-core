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

    void onActivePlayerChanged(Player player);

    void onGameStarted(Player activePlayer);

}
