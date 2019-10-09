package gui.model;

/**
 * Information Relating to a Sudoku Player
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class Player {

    // Player Attributes
    private String fullname;
    private String email;
    private String password;
    private int score;

    /**
     * Constructs a Sudoku Player.
     *
     * @param fullname the player's full name
     * @param email the player's email address
     * @param password the player's password
     * @param score the player's score
     */
    public Player(String fullname, String email, String password, int score) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.score = score;
    }

    /**
     * Constructs a basic player for the scoring table.
     *
     * @param fullname the full name of the player
     * @param score the score of the player
     */
    public Player(String fullname, int score) {
        this.fullname = fullname;
        this.score = score;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
