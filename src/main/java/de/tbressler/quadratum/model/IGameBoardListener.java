package de.tbressler.quadratum.model;

/**
 * A listener for the game board.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public interface IGameBoardListener {

    void onActivePlayerChanged(Player newPlayer);

    void onPiecePlaced(int index, Player player);

}
