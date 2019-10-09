package gui.panels;

import static gui.SudokuGame.BKGD_DARK_GRAY;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Logo of Sudoku Game Application
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class LogoImage extends JPanel {

    // Logo Attributes
    private BufferedImage logo;

    /**
     * Constructs a Logo with the preferred dimensions
     *
     * @param width the width of the logo
     * @param height the hight of the logo
     */
    public LogoImage(int width, int height) {
        try {
            this.logo = ImageIO.read(getClass().getResource("/logo.png"));
            setPreferredSize(new Dimension(width, width));
        } catch (Exception ex) {
            System.err.println("Error Logo Image: " + ex);
            setBackground(BKGD_DARK_GRAY);
        }
    }

    /**
     * Paints the logo to the container
     *
     * @param g the graphics space
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (this.logo != null) {
            g.drawImage(this.logo, 0, 0, 115, 115, this);
        } else {
            super.paintComponent(g);
        }
    }
}
