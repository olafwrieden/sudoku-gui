package gui.model;

import java.util.Random;

/**
 * Information about the Difficulty
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public enum Difficulty {

    // Averages with ± 2 (variance), must be less than 64 for solution to be unique.
    // NOTE: The higher the number of blank cells, the longer it takes to produce a unique solution.
    BEGINNER(20, 3),
    INTERMEDIATE(35, 5),
    ADVANCED(43, 8);

    // Difficulty Attributes
    private final int variance = 2;
    private final int maxHints;
    private final int emptyCells;

    /**
     * Generate a pseudo-random number of blank cells.
     *
     * @param averageBlanks number of blanks on average ± 2 (variance)
     * @param maxHints number of hints available for the difficulty level
     */
    Difficulty(int averageBlanks, int maxHints) {
        this.emptyCells = new Random().nextInt(((averageBlanks + variance) - (averageBlanks - variance)) + 1) + (averageBlanks - variance);
        this.maxHints = maxHints;
    }

    /**
     * @return the max number of hints allowed
     */
    public int getMaxHints() {
        return maxHints;
    }

    /**
     * @return the number of empty cells
     */
    public int numEmptyCells() {
        return emptyCells;
    }

    /**
     * @return Title-cased, user-friendly enumerated type representation
     */
    @Override
    public String toString() {
        return new StringBuffer(this.name().length())
                .append(Character.toTitleCase(this.name().charAt(0)))
                .append(this.name().toLowerCase().substring(1)).toString();
    }
}
