package de.tbressler.quadratum.logic;

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


    int placePiece();

}
