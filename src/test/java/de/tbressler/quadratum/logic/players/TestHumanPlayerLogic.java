package de.tbressler.quadratum.logic.players;

import de.tbressler.quadratum.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Tests for class HumanPlayerLogic.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class TestHumanPlayerLogic {

    // Class under test:
    private HumanPlayerLogic humanPlayerLogic;


    // Mocks:
    private Player player = mock(Player.class, "player");



    @Before
    public void setUp() {
        humanPlayerLogic = new HumanPlayerLogic(player);
    }


    /**
     * Checks if an exception is thrown if the player is null.
     */
    @Test(expected = NullPointerException.class)
    public void new_withNullPlayer_throwsException() {
        new HumanPlayerLogic(null);
    }

}
