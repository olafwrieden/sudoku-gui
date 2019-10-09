package gui;

import gui.model.Player;
import gui.model.Grid;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * This is the Sudoku Game (MODEL) Manager.
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class SudokuGame {

    // Sudoku App Colors
    public static final Color BKGD_DARK_GRAY = new Color(42, 54, 63);
    public static final Color BKGD_LIGHT_GRAY = new Color(62, 74, 83);
    public static final Color APP_GREEN = new Color(146, 208, 80);

    // Sudoku Model Attributes
    private final Database sudokuDB;
    private ArrayList highscores;
    private Player player;
    private Grid puzzle;
    private int hintsUsed;
    private Timer timer;

    /**
     * Default Sudoku Model Constructor.
     */
    public SudokuGame() {
        this.sudokuDB = new Database();
        setHighScores();
    }

    /**
     * Increases the player's score at completion.
     *
     * @param pointsAwarded the number of points to increase by
     */
    public void increaseScore(int pointsAwarded) {
        this.player.setScore(this.player.getScore() + pointsAwarded);
        this.sudokuDB.updateHighscore(this.player);
    }

    /**
     * @return the sudokuDB
     */
    public Database getSudokuDB() {
        return sudokuDB;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the puzzle
     */
    public Grid getPuzzle() {
        return puzzle;
    }

    /**
     * @param puzzle the puzzle to set
     */
    public void setPuzzle(Grid puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * @return the latest list of scores
     */
    public ArrayList getHighscores() {
        setHighScores();
        return highscores;
    }

    /**
     * Retrieves an updated version of the scores
     */
    private void setHighScores() {
        this.highscores = getSudokuDB().getHighScores();
    }

    /**
     * @return the number of hints used
     */
    public int getHintsUsed() {
        return hintsUsed;
    }

    /**
     * @param hintsUsed the number of hints used
     */
    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    /**
     * @return a string-formatted version of hints used
     */
    public String getStringHintsUsed() {
        return this.getHintsUsed() + "/" + this.getPuzzle().getDifficulty().getMaxHints();
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
