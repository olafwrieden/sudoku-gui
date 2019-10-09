package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.APP_GREEN;
import static gui.SudokuGame.BKGD_DARK_GRAY;
import static gui.SudokuGame.BKGD_LIGHT_GRAY;
import static java.awt.Component.CENTER_ALIGNMENT;
import gui.model.Cell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * View for a Sudoku Game Panel
 * 
 * @author Olaf Wrieden
 * @version 1.0
 */
public class GamePanel extends JPanel {
    
    // Game Panel Attributes
    private List<Cell> viewCellList;
    private final JButton endGameBtn;
    private final JButton viewRulesBtn;
    private final JButton hintBtn;
    private final JPanel grid;
    private JLabel levelTitle;
    private JLabel timeLabel;
    
    /**
     * Constructs a Game Panel.
     */
    public GamePanel() {
        
        this.setLayout(new BorderLayout());

        // Banner
        JPanel banner = new JPanel();
        banner.setLayout(new BoxLayout(banner, BoxLayout.LINE_AXIS));
        banner.setPreferredSize(new Dimension(1000, 115));
        banner.setBackground(BKGD_DARK_GRAY);
        banner.setAlignmentX(CENTER_ALIGNMENT);
        
            // Sudoku Logo
            LogoImage jP1 = new LogoImage(115, 115);
            jP1.setBackground(BKGD_DARK_GRAY);
            jP1.setPreferredSize(new Dimension(115, 115));
            jP1.setMaximumSize(new Dimension(115, 115));
            jP1.setAlignmentY(CENTER_ALIGNMENT);
            
            // Spacing
            banner.add(Box.createRigidArea(new Dimension(5,0)));
            banner.add(jP1);
            
            // Dynamic Banner Content
            JPanel jP2 = new JPanel();
            jP2.setBackground(BKGD_DARK_GRAY);
            jP2.setPreferredSize(new Dimension(200, 100));
            jP2.setLayout(new GridLayout(2,0));
            
                timeLabel = new JLabel();
                timeLabel.setFont(new Font("Avenir", Font.PLAIN, 36));
                timeLabel.setForeground(Color.WHITE);
                timeLabel.setVerticalAlignment(JLabel.BOTTOM);
                timeLabel.setHorizontalAlignment(JLabel.RIGHT);
                jP2.add(timeLabel);

                levelTitle = new JLabel();
                levelTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
                levelTitle.setForeground(Color.WHITE);
                levelTitle.setVerticalAlignment(JLabel.TOP);
                levelTitle.setHorizontalAlignment(JLabel.RIGHT);
                jP2.add(levelTitle);
                
            banner.add(jP2);
            banner.add(Box.createRigidArea(new Dimension(15,0)));
        this.add(banner, BorderLayout.NORTH);

        // Main Content
        JPanel main = new JPanel();
        main.setLayout(null);
        main.setBackground(BKGD_DARK_GRAY);           
                       
            JPanel actions = new JPanel();
            actions.setLayout(new GridLayout(3,1));
            actions.setSize(135, 90);
            actions.setLocation(0, 415 - actions.getHeight());

                // Get Hint Button
                hintBtn = new AppJButton("HINT", 14, BKGD_LIGHT_GRAY, APP_GREEN);
                actions.add(hintBtn);
            
                // View Rules Button
                viewRulesBtn = new AppJButton("VIEW RULES", 14, BKGD_LIGHT_GRAY, APP_GREEN);
                actions.add(viewRulesBtn);
            
                // Sign Out Button
                endGameBtn = new AppJButton("END GAME", 14, BKGD_LIGHT_GRAY, APP_GREEN);
                actions.add(endGameBtn);
            main.add(actions);
            
            // Game Grid Panel
            grid = new JPanel();
            grid.setLayout(new GridLayout(9, 9));
            grid.setPreferredSize(new Dimension(120, 120));
            grid.setMaximumSize(new Dimension(433, 433));
            grid.setBorder(new LineBorder(APP_GREEN, 2));
            grid.setBackground(BKGD_DARK_GRAY.darker());
            grid.setForeground(Color.white);
            grid.setLocation(285, 0);
            grid.setSize(400, 400);
            
        main.add(grid);          
        this.add(main);
    }    
    
    /**
     * @return the endGameBtn
     */
    public JButton getEndGameBtn() {
        return endGameBtn;
    }

    /**
     * @return the viewRulesBtn
     */
    public JButton getViewRulesBtn() {
        return viewRulesBtn;
    }

    /**
     * @return the hintBtn
     */
    public JButton getHintBtn() {
        return hintBtn;
    }

    /**
     * @return the levelTitle
     */
    public JLabel getLevelTitle() {
        return levelTitle;
    }

    /**
     * @param levelTitle the levelTitle to set
     */
    public void setLevelTitle(JLabel levelTitle) {
        this.levelTitle = levelTitle;
    }

    /**
     * @return the timeLabel
     */
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    /**
     * @param timeLabel the timeLabel to set
     */
    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    /**
     * @return the grid
     */
    public JPanel getGrid() {
        return grid;
    }

    /**
     * @return the viewCellList
     */
    public List<Cell> getViewCellList() {
        return viewCellList;
    }

    /**
     * @param viewCellList the viewCellList to set
     */
    public void setViewCellList(List<Cell> viewCellList) {
        this.viewCellList = viewCellList;
    }
}
