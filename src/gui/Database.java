package gui;

import gui.model.Player;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Information relating to the Game Database
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public final class Database {

    // Database Attributes
    private final String dbURL = "jdbc:derby:Game;create=true";
    private final String dbUsername = "sudoku";
    private final String dbPassword = "sudoku";
    private Connection conn;
    private Statement statement;

    /**
     * Constructs a Database
     */
    protected Database() {
        if (!connect()) {
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "A connection to the database could not be established.\nThis most likely occured because another instance is open.\nIn embedded mode, only one process is allowed to access the derby database.\nThe application is heavily dependent on its database to manage its players.", "Another Instance is Open", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
            System.exit(0);
        }
        dbSetup();
    }

    /**
     * Initializes a connection with the database.
     *
     * @return true if connected, else false
     */
    protected boolean connect() {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("DATABASE: Connected");
            statement = conn.createStatement();
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return success;
    }

    /**
     * Configures Database Tables for Sudoku Game
     */
    protected void dbSetup() {
        try {
            // Create Player table if it doesn't exist
            if (!checkTableExists("Players")) {
                //statement.executeUpdate("DROP TABLE Players"); // --> Can be used here to reset 'Players' table.
                statement.executeUpdate("CREATE TABLE Players (FULLNAME VARCHAR(255), EMAIL VARCHAR(255), PASSWORD VARCHAR(255), SCORE INTEGER)");
                System.out.println("Players table was created.");

                // Load sample data into Players table
                statement.executeUpdate("INSERT INTO Players (FULLNAME, EMAIL, PASSWORD, SCORE) VALUES "
                        + "('Emily Example', 'emily@example.com', 'example', 30), "
                        + "('Thomas Tester', 'test@example.com', 'test', 20), "
                        + "('Jane Doe', 'doe@example.com', 'jane', 10)");
                System.out.println("Sample data was added to 'Players' table.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    /**
     * Checks if chosen table exists in database
     *
     * @param tableName the table to check for
     * @return true if table exists, else false
     */
    protected boolean checkTableExists(String tableName) {
        boolean tableExists = false;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rsMeta = dbMetaData.getTables(null, null, null, null);
            while (rsMeta.next()) {
                String currentTableName = rsMeta.getString("TABLE_NAME");
                if (currentTableName.equalsIgnoreCase(tableName)) {
                    tableExists = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return tableExists;
    }

    /**
     * Checks if user credentials are correct
     *
     * @param email the user's email
     * @param password the user's password
     * @return true if details match database, else false
     */
    protected boolean checkLogin(String email, String password) {
        boolean validLogin = false;
        try {
            ResultSet rs = statement.executeQuery("SELECT FULLNAME FROM Players WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'");
            if (rs.next()) {
                // Login is Valid
                validLogin = true;
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return validLogin;
    }

    /**
     * Registers a user in the database (passwords are not hashed!)
     *
     * @param fullname the user's full name
     * @param email the user's email address
     * @param password the user's password
     * @return true if successful registration, else false
     */
    protected boolean registerUser(String fullname, String email, String password) {
        boolean success = false;
        try {
            if (!checkLogin(email, password)) {
                statement.executeUpdate("INSERT INTO Players (FULLNAME, EMAIL, PASSWORD, SCORE) VALUES ('" + fullname + "', '" + email + "', '" + password + "', 0)");
                success = true;
                System.out.println("User " + fullname + " was successfully added into the database");
            } else {
                System.out.println("User " + fullname + " already exists, choose other credentials.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }

    /**
     * Update the user's score
     *
     * @param player the player affected
     */
    protected void updateHighscore(Player player) {
        try {
            statement.executeUpdate("UPDATE Players SET SCORE = " + player.getScore() + " WHERE EMAIL = '" + player.getEmail() + "'");
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retrieves high scores for ranking table
     *
     * @return the list of players' high scores
     */
    protected ArrayList<Player> getHighScores() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                players.add(new Player(rs.getString("FULLNAME"), rs.getInt("SCORE")));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return players;
    }

    /**
     * Loads the player from the database
     *
     * @param email the player's email address
     * @param pass the player's password
     * @return a Player object of the current user
     */
    protected Player loadPlayer(String email, String pass) {
        Player player = null;
        if (checkLogin(email, pass)) {
            try {
                ResultSet rs = statement.executeQuery("SELECT * FROM Players WHERE EMAIL = '" + email + "' AND password = '" + pass + "'");
                if (rs.next()) {
                    // Retrieve Player Information and Construct the player
                    player = new Player(rs.getString("FULLNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getInt("SCORE"));
                }
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return player;
    }
}
