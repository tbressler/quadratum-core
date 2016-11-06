package de.tbressler.quadratum.logic.players;

import de.tbressler.quadratum.logic.ILogicCallback;
import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;

/**
 * The player logic for a human player.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class HumanPlayerLogic extends AbstractPlayerLogic {

    /**
     * Creates the human player logic.
     *
     * @param player The player, must not be null.
     */
    public HumanPlayerLogic(Player player) {
        super(player);
    }

    @Override
    public void requestMove(IReadOnlyGameBoard gameBoard, ILogicCallback callback) {

    }

}
