package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;

/**
 * The interface for player logic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public interface IPlayerLogic {

    /**
     * Returns the player of this logic.
     *
     * @return The player, never null.
     */
    Player getPlayer();

    /**
     * Requests a move at the player logic.
     *
     * @param gameBoard The game board (read-only), never null.
     * @param callback The callback of the game logic, never null.
     */
    void requestMove(IReadOnlyGameBoard gameBoard, ILogicCallback callback);

}
