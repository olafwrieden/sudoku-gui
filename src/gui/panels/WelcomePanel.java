package gui.panels;

import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Welcome Panel View
 *
 * @author Olaf Wrieden
 * @version 1.0
 */
public class WelcomePanel extends JPanel {

    // Welcome Panel Attributes
    private final CardLayout cardLayoutManager = new CardLayout();
    private final SignUpPanel signUpPanel = new SignUpPanel();
    private final SignInPanel signInPanel = new SignInPanel();
    private final JPanel slider = new JPanel();

    /**
     * Constructs a Welcome Panel
     */
    public WelcomePanel() {

        this.setLayout(new GridLayout(0, 2));

        slider.setLayout(this.cardLayoutManager);
        slider.add(this.signUpPanel);
        slider.add(this.signInPanel);

        // Adding to Panel
        this.add(new WelcomeImage(500, 550));
        this.add(slider);
    }

    /**
     * @return the cardLayoutManager
     */
    public CardLayout getCardLayoutManager() {
        return cardLayoutManager;
    }

    /**
     * @return the slider
     */
    public JPanel getSlider() {
        return slider;
    }

    /**
     * @return the signUpPanel
     */
    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    /**
     * @return the signInPanel
     */
    public SignInPanel getSignInPanel() {
        return signInPanel;
    }
}
