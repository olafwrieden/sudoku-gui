package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.APP_GREEN;
import static gui.SudokuGame.BKGD_DARK_GRAY;
import static gui.SudokuGame.BKGD_LIGHT_GRAY;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Home Panel View of Sudoku Game
 * 
 * @author Olaf Wrieden
 * @version 1.0
 */
public class HomePanel extends JPanel {
    
    // Home Panel Attributes
    private final JButton signoutBtn;
    private final JButton newGameBtn;
    private final JButton viewRulesBtn;
    private JTable highscores;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel levelSelectionModel = new DefaultComboBoxModel();
    private final JComboBox levelSelector;
    private JLabel nameLabel;
    
    /**
     * Constructs a Home Panel View.
     */
    public HomePanel() {
        this.setLayout(new BorderLayout());

        // Banner
        JPanel banner = new JPanel();
        banner.setLayout(new BoxLayout(banner, BoxLayout.LINE_AXIS));
        banner.setPreferredSize(new Dimension(1000, 115));
        banner.setBackground(BKGD_DARK_GRAY);
        banner.setAlignmentX(CENTER_ALIGNMENT);
            
            // Logo
            LogoImage jP1 = new LogoImage(115, 115);
            jP1.setBackground(BKGD_DARK_GRAY);
            jP1.setPreferredSize(new Dimension(115, 115));
            jP1.setMaximumSize(new Dimension(115, 115));
            jP1.setAlignmentY(CENTER_ALIGNMENT);
            
            // Spacing
            banner.add(Box.createRigidArea(new Dimension(5,0)));
            banner.add(jP1);
            banner.add(Box.createRigidArea(new Dimension(15,0)));

            // Dynamic Banner Content
            JPanel jP2 = new JPanel();
            jP2.setBackground(BKGD_DARK_GRAY);
            jP2.setPreferredSize(new Dimension(200, 100));
            jP2.setLayout(new GridLayout(2,0));
                JLabel welcomeLabel = new JLabel("WELCOME");
                welcomeLabel.setFont(new Font("Avenir", Font.PLAIN, 28));
                welcomeLabel.setForeground(Color.WHITE);
                welcomeLabel.setVerticalAlignment(JLabel.BOTTOM);
                jP2.add(welcomeLabel);

                nameLabel = new JLabel("Player");
                nameLabel.setFont(new Font("Avenir", Font.PLAIN, 18));
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setVerticalAlignment(JLabel.TOP);
                jP2.add(nameLabel);
            banner.add(jP2);
            
        this.add(banner, BorderLayout.NORTH);

        // Main Content
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(0,2));

            // Left Options
            JPanel left = new JPanel();
            left.setBackground(BKGD_DARK_GRAY);
            left.setLayout(null);
                
                JPanel game = new JPanel();
                game.setLayout(new GridLayout(2,0));
                game.setLocation(100, 50);
                game.setSize(300, 120);
                game.setBackground(BKGD_LIGHT_GRAY);
                    // New Game Button
                    newGameBtn = new AppJButton("START A NEW GAME", 20, BKGD_LIGHT_GRAY, APP_GREEN);
                    game.add(newGameBtn);
                    
                    // Difficulty Level
                    levelSelector = new JComboBox();
                    levelSelector.setModel(levelSelectionModel);
                    game.add(levelSelector);
                    
                left.add(game);
                
                game = new JPanel();
                game.setLayout(new GridLayout());
                game.setLocation(100, 200);
                game.setSize(300, 120);
                    // Previous Game Button
                    viewRulesBtn = new AppJButton("SHOW ME THE RULES", 20, BKGD_LIGHT_GRAY, APP_GREEN);
                    game.add(viewRulesBtn);
                left.add(game);
                

                JPanel actions = new JPanel();
                actions.setLayout(new GridLayout());
                actions.setLocation(0, 385);
                actions.setSize(115, 30);
                    // Sign out Button
                    signoutBtn = new AppJButton("SIGN OUT", 14, BKGD_LIGHT_GRAY, APP_GREEN);
                    actions.add(signoutBtn);
                left.add(actions);
            main.add(left);

            // Right Options
            JPanel right = new JPanel();
            right.setBackground(BKGD_DARK_GRAY);
            right.setLayout(null);
                    
                // High Score Table               
                highscores = new JTable() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                
                tableModel = (DefaultTableModel) highscores.getModel(); 
                tableModel.setColumnIdentifiers(new String[] {"Score", "Player"});
                highscores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
                highscores.getTableHeader().setFont(new Font(this.getFont().getFontName(), Font.PLAIN, this.getFont().getSize()));
                highscores.getTableHeader().setBackground(BKGD_LIGHT_GRAY);
                highscores.getTableHeader().setForeground(APP_GREEN);
                highscores.getTableHeader().setReorderingAllowed(false);
                highscores.setAutoCreateRowSorter(true);
                highscores.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
                highscores.setBackground(BKGD_LIGHT_GRAY);
                highscores.setForeground(APP_GREEN);
                highscores.setShowVerticalLines(false);
                highscores.setShowHorizontalLines(false);
                highscores.getRowSorter().toggleSortOrder(0);
                
                JScrollPane scores = new JScrollPane(highscores);
                scores.setLocation(100, 50);
                scores.setSize(300, 270);
                scores.setBorder(new LineBorder(Color.BLACK,0));
                scores.getViewport().setBackground(BKGD_LIGHT_GRAY);
                
                right.add(scores);
                
            main.add(right);

        this.add(main);
    }
    
    /**
     * @return the signoutBtn
     */
    public JButton getSignoutBtn() {
        return signoutBtn;
    }

    /**
     * @return the newGameBtn
     */
    public JButton getNewGameBtn() {
        return newGameBtn;
    }

    /**
     * @return the levelSelector
     */
    public JComboBox getLevelSelector() {
        return levelSelector;
    }

    /**
     * @return the showRulesBtn
     */
    public JButton getViewRulesBtn() {
        return viewRulesBtn;
    }

    /**
     * @return the nameLabel
     */
    public JLabel getNameLabel() {
        return nameLabel;
    }

    /**
     * @return the highscores
     */
    public JTable getHighscores() {
        return highscores;
    }

    /**
     * @param highscores the highscores to set
     */
    public void setHighscores(JTable highscores) {
        this.highscores = highscores;
    }

    /**
     * @return the tableModel
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /**
     * @param tableModel the tableModel to set
     */
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * @return the levelSelectionModel
     */
    public DefaultComboBoxModel getLevelSelectionModel() {
        return levelSelectionModel;
    }
}