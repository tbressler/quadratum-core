package de.tbressler.quadratum.model;

import de.tbressler.quadratum.utils.SquareUtils;

import static de.tbressler.quadratum.utils.SquareUtils.score;
import static java.util.Arrays.sort;
import static java.util.Objects.requireNonNull;

/**
 * A square, which consists of 4 pieces of one player.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class Square {

    /* The pieces of the square. */
    private final int[] pieces;

    /* The score of the square. */
    private final int score;

    /* The player that scored the square. */
    private final Player player;

    /**
     * Creates a square.
     *
     * @param pieces The 4 pieces of the square, must not be null or empty.
     * @param player  The player that scored this square, must not be null.
     */
    public Square(int[] pieces, Player player) {
        if (requireNonNull(pieces).length != 4)
            throw new AssertionError("pieces array must contain 4 elements!");
        if (!SquareUtils.isSquare(pieces))
            throw new AssertionError("pieces must form a square!");

        sort(pieces);
        this.pieces = pieces;
        this.score = score(pieces);

        this.player = requireNonNull(player);
    }

    public int[] getSortedPieces() {
        return pieces;
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
