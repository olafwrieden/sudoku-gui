package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.APP_GREEN;
import static gui.SudokuGame.BKGD_DARK_GRAY;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Sign Up Panel View
 *
 * @author Olaf Wrieden
 */
public class SignUpPanel extends JPanel {

    // SignUp Panel Attributes
    private final JTextField fullnameText;
    private final JTextField emailText;
    private final JPasswordField passwordText;
    private final AppJButton signupButton;
    private final AppJButton signinButton;

    /**
     * Constructs a Sign Up Panel
     */
    public SignUpPanel() {

        this.setLayout(new GridLayout(9, 0));
        this.setBackground(BKGD_DARK_GRAY);

        // Title Label
        JLabel actionLabel = new JLabel("Sign Up or Sign In");
        actionLabel.setFont(new Font("Avenir", Font.PLAIN, 24));
        actionLabel.setForeground(Color.white);
        actionLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(actionLabel);

        // Full Name Label
        JLabel fullnameLabel = new JLabel("Full Name");
        fullnameLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        fullnameLabel.setForeground(Color.white);
        fullnameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(fullnameLabel);

        // Full Name Text Field
        fullnameText = new JTextField();
        fullnameText.setBackground(BKGD_DARK_GRAY);
        fullnameText.setForeground(Color.white);
        fullnameText.setHorizontalAlignment(JLabel.CENTER);
        fullnameText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        fullnameText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, APP_GREEN));
        this.add(fullnameText);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        emailLabel.setForeground(Color.white);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(emailLabel);

        // Full Name Text Field
        emailText = new JTextField();
        emailText.setBackground(BKGD_DARK_GRAY);
        emailText.setForeground(Color.white);
        emailText.setHorizontalAlignment(JLabel.CENTER);
        emailText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        emailText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, APP_GREEN));
        this.add(emailText);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(passwordLabel);

        // Password Text Field
        passwordText = new JPasswordField();
        passwordText.setBackground(BKGD_DARK_GRAY);
        passwordText.setForeground(Color.white);
        passwordText.setHorizontalAlignment(JLabel.CENTER);
        passwordText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, APP_GREEN));
        this.add(passwordText);

        // Sign Up Button
        signupButton = new AppJButton("Sign Up", 14, APP_GREEN, BKGD_DARK_GRAY);
        this.add(signupButton);

        // Sign In Button
        signinButton = new AppJButton("I am already registered", 10, BKGD_DARK_GRAY, APP_GREEN);
        this.add(signinButton);

    }

    /**
     * Clears text fields in this view.
     */
    public void clear() {
        fullnameText.setText("");
        emailText.setText("");
        passwordText.setText("");
    }

    /**
     * @return the signupButton
     */
    public JButton getSignupButton() {
        return signupButton;
    }

    /**
     * @return the signinButton
     */
    public JButton getSigninButton() {
        return signinButton;
    }

    /**
     * @return the fullnameText
     */
    public JTextField getFullnameText() {
        return fullnameText;
    }

    /**
     * @return the emailText
     */
    public JTextField getEmailText() {
        return emailText;
    }

    /**
     * @return the passwordText
     */
    public JPasswordField getPasswordText() {
        return passwordText;
    }
}
