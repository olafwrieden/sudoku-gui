package gui;

import gui.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Database Connection.
 *
 * @author olafwrieden
 */
public class DatabaseTest {

    /**
     * Test of connect method, of class Database.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        Database instance = new Database();
        instance.connect();
    }

    /**
     * Test of checkTableExists method, of class Database.
     */
    @Test
    public void testCheckTableExists() {
        System.out.println("checkTableExists");
        String tableName = "Players";
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.checkTableExists(tableName);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkLogin method, of class Database.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String email = "emily@example.com";
        String password = "example";
        Database instance = new Database();
        boolean expResult = true;
        boolean result = instance.checkLogin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class Database.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String fullname = "Max Mason";
        String email = "max@example.com";
        String password = "masonm";
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.registerUser(fullname, email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadPlayer method, of class Database.
     */
    @Test
    public void testLoadPlayer() {
        System.out.println("loadPlayer");
        String email = "emily@example.com";
        String pass = "example";
        Database instance = new Database();
        Player expResult = null;
        Player result = instance.loadPlayer(email, pass);
        assertEquals(expResult, result);
    }
}
