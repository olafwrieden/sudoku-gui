package gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Cell Data.
 *
 * @author olafwrieden
 */
public class CellTest {

    /**
     * Test of isLocked method, of class Cell.
     */
    @Test
    public void testIsLockedTrue() {
        System.out.println("isLocked");
        Cell instance = new Cell(1, 1);
        instance.setLocked(true);
        boolean expResult = true;
        boolean result = instance.isLocked();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocked method, of class Cell.
     */
    @Test
    public void testSetLockedFalse() {
        System.out.println("setLocked");
        boolean locked = false;
        Cell instance = new Cell(1, 1);
        instance.setLocked(locked);
    }

    /**
     * Test of isEmpty method, of class Cell.
     */
    @Test
    public void testIsEmptyTrue() {
        System.out.println("isEmpty");
        Cell instance = new Cell(1, 1);
        instance.setUserValue(0);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserValue method, of class Cell.
     */
    @Test
    public void testGetUserValueEight() {
        System.out.println("getUserValue");
        Cell instance = new Cell(5, 2);
        instance.setUserValue(8);
        int expResult = 8;
        int result = instance.getUserValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Cell.
     */
    @Test
    public void testToStringThree() {
        System.out.println("toString");
        Cell instance = new Cell(5, 2);
        instance.setUserValue(3);
        String expResult = "[3]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
