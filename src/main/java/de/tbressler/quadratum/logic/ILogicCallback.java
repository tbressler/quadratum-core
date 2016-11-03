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
     * Make the move.
     *
     * @param index The index of the field, between 0..63.
     * @param player The player, never null.
     */
    void makeMove(int index, Player player);

}
