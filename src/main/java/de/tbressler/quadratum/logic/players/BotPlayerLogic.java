package de.tbressler.quadratum.logic.players;

import de.tbressler.quadratum.logic.ILogicCallback;
import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;

import java.util.Random;

import static com.google.common.base.MoreObjects.toStringHelper;
import static de.tbressler.quadratum.utils.SquareUtils.getPossiblePieces;
import static de.tbressler.quadratum.utils.SquareUtils.score;
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

        int[] playerHeatMap = new int[64];
        int[] opponentHeatMap = new int[64];

        Player[] pieces = new Player[4];
        int[] possible;

        int scoreForSquare;
        int playerScore;
        int opponentScore;
        int numberOfPlayerPieces;
        int numberOfOpponentPieces;

        // Create heat maps for player and opponent:
        for (int i = 0; i < 55; i++) {
            pieces[0] = gameBoard.getPiece(i);
            for (int j = i + 1; j < 64; j++) {
                pieces[1] = gameBoard.getPiece(j);

                possible = getPossiblePieces(i, j);
                if (possible.length != 2)
                    continue;

                pieces[2] = gameBoard.getPiece(possible[0]);
                pieces[3] = gameBoard.getPiece(possible[1]);

                numberOfPlayerPieces = 0;
                numberOfOpponentPieces = 0;

                for (int p = 0; p < 4; p++)
                    if (pieces[p] == getPlayer())
                        numberOfPlayerPieces++;
                    else if (pieces[p] != null)
                        numberOfOpponentPieces++;

                // Calculate possible score of square:
                scoreForSquare = score(i, j, possible[0], possible[1]);
                // ... add chance for opponent to get this square:
                playerScore = scoreForSquare * numberOfPlayerPieces;
                // ... add chance for player to get this square:
                opponentScore = scoreForSquare * numberOfOpponentPieces;

                if ((numberOfOpponentPieces > 0) && (numberOfPlayerPieces == 0)) {
                   // ... square is not occupied by opponent and not yet blocked by player.
                   opponentHeatMap[i] += opponentScore;
                   opponentHeatMap[j] += opponentScore;
                   opponentHeatMap[possible[0]] += opponentScore;
                   opponentHeatMap[possible[1]] += opponentScore;
                } else if (numberOfOpponentPieces == 0) {
                   // ... square is not blocked by opponent.
                   playerHeatMap[i] += playerScore;
                   playerHeatMap[j] += playerScore;
                   playerHeatMap[possible[0]] += playerScore;
                   playerHeatMap[possible[1]] += playerScore;
                }
            }
        }
    }


    @Override
    public String toString() {
        return toStringHelper(this)
                .add("randomizeMoves", randomizeMoves)
                .toString();
    }

}
