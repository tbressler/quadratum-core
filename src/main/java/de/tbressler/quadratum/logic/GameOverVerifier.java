package de.tbressler.quadratum.logic;

import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;

import static de.tbressler.quadratum.logic.GameOverVerifier.GameOverState.*;
import static de.tbressler.quadratum.logic.GameOverVerifier.PossibleMoves.*;
import static de.tbressler.quadratum.utils.SquareUtils.getPossiblePieces;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class GameOverVerifier {

    /**
     * The game over state.
     */
    public enum GameOverState {
        /** The game is not over. */
        NOT_OVER,
        /** The game is over and player 1 won. */
        PLAYER1_WON,
        /** The game is over and player 2 won. */
        PLAYER2_WON,
        /** The game is over and the game is a draw. */
        GAME_DRAW
    }

    protected enum PossibleMoves {
        BOTH_PLAYERS,
        ONLY_PLAYER1,
        ONLY_PLAYER2,
        NO_PLAYER
    }

    /* The minimum score for a player to win the game. */
    private final int minScore;

    /* The minimum difference between the player scores to win the game. */
    private final int minDifference;


    /**
     * Creates the game over verifier.
     *
     * @param minScore The minimum score to win (suggested 150).
     * @param minDifference The minimum difference between score (suggested 15).
     */
    public GameOverVerifier(int minScore, int minDifference) {
        if (minScore < 1) throw new AssertionError("minScore must be greater than 0!");
        if (minDifference < 1) throw new AssertionError("minDifference must be greater than 0!");
        this.minScore = minScore;
        this.minDifference = minDifference;
    }

    /**
     * Checks if the game is over.
     *
     * @param gameBoard The game board, must not be null.
     * @param squareCollector The current squares, must not be null.
     * @param nextPlayer The next player, must not be null.
     * @return The game over state, never null.
     */
    public GameOverState isGameOver(IReadOnlyGameBoard gameBoard, SquareCollector
            squareCollector, Player nextPlayer) {
        requireNonNull(gameBoard);
        requireNonNull(squareCollector);
        requireNonNull(nextPlayer);

        int scorePlayer1 = squareCollector.getScoreForPlayer(gameBoard.getPlayer1());
        int scorePlayer2 = squareCollector.getScoreForPlayer(gameBoard.getPlayer2());

        // Check if one player has won the game
        if ((scorePlayer1 >= minScore) || (scorePlayer2 >= minScore)) {

            int dif = scorePlayer1 - scorePlayer2;

            if (dif >= minDifference) {
                return PLAYER1_WON;
            } else if (dif <= -minDifference) {
                return PLAYER2_WON;
            }
        }

        // Check if more moves are possible:
        switch (canPlayersDoMoreSquares(gameBoard)) {
            case NO_PLAYER:
                return getGameDrawState(scorePlayer1, scorePlayer2);
            case ONLY_PLAYER1:
                return (nextPlayer.equals(gameBoard.getPlayer1())) ?
                        NOT_OVER : getGameDrawState(scorePlayer1, scorePlayer2);
            case ONLY_PLAYER2:
                return (nextPlayer.equals(gameBoard.getPlayer2())) ?
                        NOT_OVER : getGameDrawState(scorePlayer1, scorePlayer2);
            case BOTH_PLAYERS:
                return NOT_OVER;
            default:
                throw new AssertionError("Unknown return value!");
        }
    }

    private GameOverState getGameDrawState(int scorePlayer1, int scorePlayer2) {
        if (scorePlayer1 > scorePlayer2)
            return PLAYER1_WON;
        else if (scorePlayer2 > scorePlayer1)
            return PLAYER2_WON;
        else
            return GAME_DRAW;
    }

    private PossibleMoves canPlayersDoMoreSquares(IReadOnlyGameBoard gameBoard) {
        Player player1 = gameBoard.getPlayer1();

        int[] possible;
        Player[] pieces = new Player[4];

        boolean hasPlayer1;
        boolean hasPlayer2;
        boolean hasEmpty;

        boolean player1CanMove = false;
        boolean player2CanMove = false;

        for (int i = 0; i < 55; i++) {

            pieces[0] = gameBoard.getPiece(i);

            for (int j = i + 1; j < 64; j++) {

                pieces[1] = gameBoard.getPiece(j);

                possible = getPossiblePieces(i, j);
                if (possible.length != 2)
                    continue;

                pieces[2] = gameBoard.getPiece(possible[0]);
                pieces[3] = gameBoard.getPiece(possible[1]);

                hasPlayer1 = false;
                hasPlayer2 = false;
                hasEmpty = false;

                for (int m = 0; m < 4; m++)
                    if (pieces[m] == null)
                        hasEmpty = true;
                    else if (pieces[m] == player1)
                        hasPlayer1 = true;
                    else
                        hasPlayer2 = true;

                if (hasPlayer1 && !hasPlayer2 && hasEmpty)
                    player1CanMove = true;
                else if (hasPlayer2 && !hasPlayer1 && hasEmpty)
                    player2CanMove = true;
                else if (!hasPlayer1 && !hasPlayer2 && hasEmpty)
                    return BOTH_PLAYERS;

                if (player1CanMove && player2CanMove)
                    return BOTH_PLAYERS;
            }
        }

        if (player1CanMove)
            return ONLY_PLAYER1;
        else if (player2CanMove)
            return ONLY_PLAYER2;

        return NO_PLAYER;
    }

}
