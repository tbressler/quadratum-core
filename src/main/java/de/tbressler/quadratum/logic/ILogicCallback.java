package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.Player;

/**
 * The interface for logic callbacks.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
interface ILogicCallback {

    /**
     * Make the move on the game board.
     *
     * @param index The field index, between 0..63.
     * @param player The player, never null.
     * @return True if the move was successful or false if not.
     */
    boolean makeMove(int index, Player player);

}
