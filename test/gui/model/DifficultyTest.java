package gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests certain Difficulty elements.
 *
 * @author olafwrieden
 */
public class DifficultyTest {

    /**
     * Test of getMaxHints method, of class Difficulty.
     */
    @Test
    public void testGetMaxHintsAdvanced() {
        System.out.println("getMaxHints");
        Difficulty instance = Difficulty.ADVANCED;
        int expResult = 8;
        int result = instance.getMaxHints();
        assertEquals(expResult, result);
    }

    /**
     * Test of numEmptyCells method, of class Difficulty.
     */
    @Test
    public void testNumEmptyCellsBeginner() {
        System.out.println("numEmptyCells");
        Difficulty instance = Difficulty.BEGINNER;
        int result = instance.numEmptyCells();
        assertTrue(result >= 18 && result <= 22);
    }
}
