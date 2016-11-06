package de.tbressler.quadratum.logic.players;

import de.tbressler.quadratum.logic.ILogicCallback;
import de.tbressler.quadratum.model.IReadOnlyGameBoard;
import de.tbressler.quadratum.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;

/**
 * Tests for class BotPlayerLogic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestBotPlayerLogic {

    // Class under test:
    private BotPlayerLogic botPlayerLogic;


    // Mocks:
    private Player player = mock(Player.class, "player");

    private ILogicCallback logicCallback = mock(ILogicCallback.class, "logicCallback");

    private IReadOnlyGameBoard gameBoard = mock(IReadOnlyGameBoard.class, "gameBoard");

    private Random random = mock(Random.class, "random");



    @Before
    public void setUp() {
        botPlayerLogic = new BotPlayerLogic(player);
        botPlayerLogic.setRandom(random);
    }


    /**
     * Checks if an exception is thrown if the player is null.
     */
    @Test(expected = NullPointerException.class)
    public void new_withNullPlayer_throwsException() {
        new BotPlayerLogic(null);
    }


    @Test(expected = NullPointerException.class)
    public void requestMove_withNullGameBoard_throwsException() {
        botPlayerLogic.requestMove(null, logicCallback);
    }

    @Test(expected = NullPointerException.class)
    public void requestMove_withNullLogicCallback_throwsException() {
        botPlayerLogic.requestMove(gameBoard, null);
    }

}
