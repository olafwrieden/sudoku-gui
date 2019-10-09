package gui.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the CellPosition.
 *
 * @author olafwrieden
 */
public class CellPositionTest {

    /**
     * Test of getRow method, of class CellPosition.
     */
    @Test
    public void testGetRowAtPositionFiveTwo() {
        System.out.println("getRow");
        CellPosition instance = new CellPosition(5, 2);
        int expResult = 5;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn method, of class CellPosition.
     */
    @Test
    public void testGetColumnAtPositionSevenNine() {
        System.out.println("getColumn");
        CellPosition instance = new CellPosition(7, 9);
        int expResult = 9;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubgrid method, of class CellPosition.
     */
    @Test
    public void testGetSubgridAtPositionNineEight() {
        System.out.println("getSubgrid");
        CellPosition instance = new CellPosition(9, 8);
        int expResult = 9;
        int result = instance.getSubgrid();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CellPosition.
     */
    @Test
    public void testToStringAtPositionOneOne() {
        System.out.println("toString");
        CellPosition instance = new CellPosition(1, 1);
        String expResult = "2B";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CellPosition.
     */
    @Test
    public void testToStringAtPositionMinus2Minus3() {
        System.out.println("toString");
        CellPosition instance = new CellPosition(-2, -3);
        String expResult = "-1,-2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
