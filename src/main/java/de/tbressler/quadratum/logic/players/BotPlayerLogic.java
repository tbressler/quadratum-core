package de.tbressler.quadratum.logic.players;

import de.tbressler.quadratum.logic.ILogicCallback;
import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;

import java.util.Random;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

/**
 * The player logic for a AI players (bots).
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class BotPlayerLogic extends AbstractPlayerLogic {

    /* True if moves should be randomized. */
    private boolean randomizeMoves = false;

    /* Random number generator. */
    private Random random = new Random();


    /**
     * Creates the bot player logic.
     *
     * @param player The player, must not be null.
     */
    public BotPlayerLogic(Player player) {
        super(player);
    }


    /**
     * Enables or disables randomization of moves.
     *
     * @param randomizeMoves True if moves should be randomized.
     */
    public void setRandomizeMoves(boolean randomizeMoves) {
        this.randomizeMoves = randomizeMoves;
    }


    /**
     * Sets the random number generator. This method should only be used for testing purposes.
     *
     * @param random The random number generator, must not be null.
     */
    void setRandom(Random random) {
        this.random = requireNonNull(random);
    }


    @Override
    public void requestMove(IReadOnlyGameBoard gameBoard, ILogicCallback callback) {
        requireNonNull(gameBoard);
        requireNonNull(callback);



    }


    @Override
    public String toString() {
        return toStringHelper(this)
                .add("randomizeMoves", randomizeMoves)
                .toString();
    }

}
