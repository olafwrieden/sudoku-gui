package gui.model;

import java.util.Objects;
import javax.swing.JTextField;

/**
 * Information about an individual cell in the grid
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class Cell extends JTextField {

    // Cell Attributes
    protected int userValue;
    protected int solutionValue;
    protected int provisionalValue;
    private boolean locked;
    private final CellPosition position;
    private static final String COLOUR_RED = "\u001B[31m";
    private static final String COLOUR_RESET = "\u001B[0m";

    /**
     * Constructs a Cell object at row and column
     *
     * @param row the row of the cell position
     * @param column the column of the cell position
     */
    public Cell(int row, int column) {
        this.position = new CellPosition(row, column);
    }

    /**
     * @return the position of this cell
     */
    public CellPosition getPosition() {
        return this.position;
    }

    /**
     * @return true if cell is locked, else false
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * @param locked locks / unlocks the cell to prevent editing
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * @return true if cell is empty (ie. 0), false if cell isn't empty (ie. not
     * 0)
     */
    public boolean isEmpty() {
        return getUserValue() == 0;
    }

    /**
     * @return the user chosen value for this cell
     */
    public int getUserValue() {
        return this.userValue;
    }

    /**
     * @param userValue the user's chosen value for this cell
     */
    public void setUserValue(int userValue) {
        this.userValue = userValue;
    }

    /**
     * @return the cell's true (solution) value
     */
    public int getSolutionValue() {
        return this.solutionValue;
    }

    /**
     * Set the solution value of the cell to userValue
     */
    public void setSolutionValue() {
        this.solutionValue = userValue;
    }

    /**
     * Set provisional value of the cell to user's entry
     */
    public void storeProvisionalValue() {
        this.provisionalValue = this.userValue;
    }

    /**
     * Gets the provisional value of the cell
     */
    public void fetchProvisionalValue() {
        this.userValue = this.provisionalValue;
    }

    /**
     * @return the visual representation of this cell in the grid
     */
    @Override
    public String toString() {
        if (this.isLocked()) {
            // Uncomment the following line if you don't use NetBeans:
            //return "[" + getUserValue() + "]";

            // NetBeans only! Print locked/generated cells in red (irrelevamt for GUI):
            return "[" + COLOUR_RED + getUserValue() + COLOUR_RESET + "]";
        }
        return ("[" + (isEmpty() ? "_" : getUserValue()) + "]");
    }

    /**
     * @return the cell's text description
     */
    public String cellDescription() {
        // Description: Cell position + cell's subgrid + cell's value/empty
        String description = "Cell at " + getPosition() + " (subgrid " + (getPosition().getSubgrid() + 1) + ")";
        description += (isLocked() ? " cannot be edited. " : (!isEmpty() ? " contains " + getUserValue() + "." : " is clear."));
        return description;
    }

    /**
     * Compares 'this' cell with input cell
     *
     * @param object the input object that will be compared to 'this' object
     * (Cell)
     * @return true if objects equal, else false
     */
    @Override
    public boolean equals(Object object) {
        return object != null
                && object.getClass() == this.getClass()
                && ((Cell) object).getUserValue() == this.getUserValue()
                && ((Cell) object).getPosition().getRow() == this.getPosition().getRow()
                && ((Cell) object).getPosition().getColumn() == this.getPosition().getColumn();
    }

    /**
     * @return the generated hash code for the cell
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.userValue;
        hash = 79 * hash + this.solutionValue;
        hash = 79 * hash + this.provisionalValue;
        hash = 79 * hash + (this.locked ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.position);
        return hash;
    }
}
