package gui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Information about the Sudoku Grid
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class Grid implements Iterable<Cell> {

    // Grid Attributes
    private final int SIZE = 9;
    private final Cell[][] cells;
    private final List<Cell> cellList;
    private final List<List<Cell>> subgrids;
    private final Difficulty difficulty;

    /**
     * Constructs a Grid object
     *
     * @param diff the difficulty level of the grid
     */
    public Grid(Difficulty diff) {
        this.cells = new Cell[this.SIZE][this.SIZE];
        this.cellList = new ArrayList<>(this.SIZE * this.SIZE);
        this.subgrids = generateSubgrids();
        this.difficulty = diff;
        initialiseGrid();
    }

    /**
     * Sets up the Sudoku grid
     */
    private void initialiseGrid() {
        for (int row = 0; row < this.SIZE; row++) {
            for (int column = 0; column < this.SIZE; column++) {
                Cell cell = new Cell(row, column);
                this.cells[row][column] = cell;
                this.cellList.add(cell);
                this.subgrids.get(cell.getPosition().getSubgrid()).add(cell);
            }
        }
    }

    /**
     * Generate a list of cell lists for subgrid formation
     *
     * @return the gridList containing lists of cells
     */
    private List<List<Cell>> generateSubgrids() {
        List<List<Cell>> gridList = new ArrayList<>(this.SIZE);
        for (int i = 0; i < this.SIZE; i++) {
            gridList.add(new ArrayList<>());
        }
        return gridList;
    }

    /**
     * @return a list of all cells in this grid
     */
    public List<Cell> getCellList() {
        return this.cellList;
    }

    /**
     * @return a list of cells belonging to each subgrid
     */
    public List<List<Cell>> getSubgrids() {
        return this.subgrids;
    }

    /**
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Instantly Reveals the solution
     *
     * @param entireGrid whether or not every cell in the grid should be
     * revealed
     */
    public void hint(boolean entireGrid) {
        ArrayList<Cell> emptyCells = new ArrayList();

        for (Cell cell : cellList) {
            if (cell.isEmpty()) {
                emptyCells.add(cell);
            }
        }

        Collections.shuffle(emptyCells);

        for (Cell cell : emptyCells) {
            if (entireGrid) {
                cell.userValue = cell.getSolutionValue();
                cell.setLocked(true);
            } else if (!entireGrid && cell.isEmpty()) {
                cell.setUserValue(cell.getSolutionValue());
                cell.setLocked(true);
                return;
            }
        }
    }

    /**
     * Checks whether the current grid is solved (by user)
     *
     * @return true if solved, else false
     */
    public boolean isSolved() {
        for (Cell cell : this) {
            if (cell.userValue != cell.getSolutionValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calls storeProvisionalValue() method on every Cell within the grid.
     */
    public void provisionCells() {
        for (Cell cell : this) {
            cell.storeProvisionalValue();
        }
    }

    /**
     * Calls fetchProvisionalValue() method on every Cell within the grid.
     */
    public void fetchCellProvision() {
        for (Cell cell : this) {
            cell.fetchProvisionalValue();
        }
    }

    /**
     * Evaluates whether the grid is filled or not
     *
     * @return true if filled, else false
     */
    public boolean isFilled() {
        for (int i = 0; i < this.cellList.size(); i++) {
            if (this.cellList.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the user's value meets the constraints of the placement rules
     *
     * @param cell the cell in which to place the value
     * @param value the value to go into that cell
     * @return true if all constraints have been met, else false
     */
    public boolean meetsConstraints(Cell cell, int value) {
        return checkRow(cell.getPosition().getRow(), value)
                && checkColumn(cell.getPosition().getColumn(), value)
                && checkSubgrid(cell, value);
    }

    /**
     * Check if the value occurs only once in the entire row
     *
     * @param row the row in which to check
     * @param value the value for which to check
     * @return true if the value occurs only once, else false
     */
    private boolean checkRow(int row, int value) {
        for (Cell cell : cells[row]) {
            if (value == cell.getUserValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the value occurs only once in the entire column
     *
     * @param column the column in which to check
     * @param value the value for which to check
     * @return true if the value occurs only once, else false
     */
    private boolean checkColumn(int column, int value) {
        for (Cell[] columnCells : cells) {
            if (value == columnCells[column].getUserValue()) {
                //System.err.println("CELL col: " + columnCells[column].getPosition() + " conflicts.");
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the value occurs only once in the chosen sub-grid
     *
     * @param currentCell the cell in which to check
     * @param value the value for which to check
     * @return true if the value occurs only once, else false
     */
    private boolean checkSubgrid(Cell currentCell, int value) {
        for (Cell cell : subgrids.get(currentCell.getPosition().getSubgrid())) {
            if (value == cell.getUserValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get cell in a grid position (1A-9I)
     *
     * @param row row containing cell
     * @param column column containing cell
     * @return the cell at the specified position
     */
    public Cell getCell(int row, char column) {
        return this.cells[row - 1][(Character.toUpperCase(column) - 65)];
    }

    /**
     * Get cell at integer coordinate position
     *
     * @param xPos x position (1-9)
     * @param yPos y position (1-9)
     * @return the cell at the specified position
     */
    public Cell getCell(int xPos, int yPos) {
        return this.cells[xPos - 1][yPos - 1];
    }

    /**
     * @return Displays a visual representation of the Sudoku grid in the
     * console.
     */
    @Override
    public String toString() {
        // Output string to append to
        String result = "\n";
        result += printColumnChars();

        // Top Border
        result += ("\n   |---------|---------|---------|\n");

        // For each row of cells
        for (int i = 0; i < 9; i++) {
            // Append row numbers with correct padding
            if (i == 3 || i == 6) {
                result += ("   |---------|---------|---------|\n");
            }
            result += " " + (i + 1) + " |";

            // Append a representation of the cells
            for (int j = 0; j < 9; j++) {
                result += (cells[i][j]);
                if (j == 2 || j == 5) {
                    result += ("|");
                }
            }
            result += ("| " + (i + 1) + "\n");
        }

        // Bottom Border
        result += ("   |---------|---------|---------|\n");
        result += printColumnChars();
        return result;
    }

    /**
     * Produce column letters with correct padding
     *
     * @return a row of space-separated column headers (A-I)
     */
    private String printColumnChars() {
        String output = "";
        // Append column letters with correct padding
        for (int i = 1; i <= this.SIZE; i++) {
            if (i == 1) {
                output += "     " + (char) (i + 64);
            } else {
                if (i == 4 || i == 7) {
                    output += "   " + (char) (i + 64);
                } else {
                    output += "  " + (char) (i + 64);
                }
            }
        }
        return output;
    }

    /**
     * @return a unique cell iterator
     */
    @Override
    public ListIterator<Cell> iterator() {
        return shuffleCells().listIterator();
    }

    /**
     * Shuffles the list of cells in the grid.
     *
     * @return a shuffled list of cells
     */
    public ArrayList<Cell> shuffleCells() {
        ArrayList<Cell> shuffledCells = new ArrayList<>(cellList);
        Collections.shuffle(shuffledCells);
        return shuffledCells;
    }
}
