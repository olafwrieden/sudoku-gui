package gui.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a new Sudoku puzzle.
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class Generator {

    // Generator Attributes
    private Grid newGrid;
    private int numEmpty;
    private final Solution solution;

    /**
     * Constructs a new solution to the grid.
     */
    public Generator() {
        // Create a solution for the new instance
        this.solution = new Solution();
    }

    /**
     * Generates a difficulty-dependent Sudoku grid.
     *
     * @param diff the user-selected difficulty
     * @return a valid grid with open cells to be filled by the user
     */
    public Grid generateGrid(Difficulty diff) {

        // Get a solution for the grid
        this.setGrid(new Grid(diff));
        this.solution.solveFor(getGrid()).findSolution(getGrid().getCellList(), 1);

        // Set the solution value for each cell
        for (Cell cell : getGrid()) {
            cell.setSolutionValue();
        }

        // Remove some digits from the grid
        setNumEmpty(diff.numEmptyCells());
        emptyCells(getNumEmpty());

        // Save & return a valid grid with open cells 
        this.getGrid().provisionCells();
        return this.getGrid();
    }

    /**
     * @param grid the grid which contains the cells
     * @return a list of all empty cells on the grid
     */
    public static List<Cell> allEmptyCells(Grid grid) {
        List<Cell> emptyList = new ArrayList<>();
        // Add the cell to emptyList if cell is empty
        for (Cell cell : grid) {
            if (cell.isEmpty()) {
                emptyList.add(cell);
            }
        }
        return emptyList;
    }

    /**
     * Removes difficulty-depended number of digits from the grid.
     *
     * @param numRemove the amount of digits (cells) to empty
     */
    public void emptyCells(int numRemove) {
        // Set every cell provisional value
        getGrid().provisionCells();

        // Execute for each cell in the grid
        while (getGrid().iterator().hasNext()) {

            // If the cell is not empty, empty it
            Cell cell = getGrid().iterator().next();
            cell.storeProvisionalValue();

            if (!cell.isEmpty()) {
                cell.setUserValue(0);
            } else {
                continue;
            }

            // Find a solution for the grid
            this.solution.solveFor(getGrid());

            // Stop emptying cell if the specified number of empty cells is reached
            if (numRemove == allEmptyCells(getGrid()).size()) {
                break;
            } else {
                // If a unique solution can be produced with missing cells
                if (this.solution.findSolution(allEmptyCells(getGrid()), 3) != 1) {
                    getGrid().fetchCellProvision();
                } else {
                    cell.storeProvisionalValue();
                }
            }
        }

        // Lock all hint cells      
        lockHints();
    }

    /**
     * Lock leftover cells (hints) to prevent them from being edited by the
     * user.
     */
    private void lockHints() {
        for (Cell cell : getGrid()) {
            if (cell.isEmpty()) {
                cell.setLocked(false);
            } else {
                cell.setLocked(true);
            }
        }
    }

    /**
     * @return the number of intended empty cells
     */
    public int getNumEmpty() {
        return numEmpty;
    }

    /**
     * @param numEmpty the number of intended empty cells
     */
    private void setNumEmpty(int numEmpty) {
        this.numEmpty = numEmpty;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return newGrid;
    }

    /**
     * @param newGrid the new grid to set
     */
    private void setGrid(Grid newGrid) {
        this.newGrid = newGrid;
    }
}
