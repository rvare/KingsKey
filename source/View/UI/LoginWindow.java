package View.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Backend;

public class LoginWindow extends JFrame {
    private String plaintextPassword;
    public boolean showLogin() {
        PassDialog p = new PassDialog(this, true);
        p.setVisible(true);
        return true;
    }

    public void setPlaintextPassword(String pass) {
        plaintextPassword = pass;
    }
    
    public String getPlaintextPassword() {
        return plaintextPassword;
    }

}

class PassDialog extends JDialog {
    private JLabel passwordLabel;
    private JTextField passField;
    private JButton loginButton;
    private String actualPassword;
    private LoginWindow loginWindowReference;

    public PassDialog(final LoginWindow parentFrame, boolean modal) {
        super(parentFrame, modal);
        this.setTitle("Login");
        this.makeProgramTitleLabel();
        this.makeLoginField();
        this.makeLoginButton();
        getActualPassword();
        loginWindowReference = parentFrame;
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void makeProgramTitleLabel() {
        JLabel programTitle = new JLabel("Kings Key", SwingConstants.CENTER);
        programTitle.setBorder(new EmptyBorder(10, 100, 20, 100));
        programTitle.setFont(new Font("Sans", Font.PLAIN, 24));
        this.add(programTitle, BorderLayout.NORTH);
    }

    private void makeLoginField() {
        JPanel loginPanel = new JPanel(new GridLayout(1, 2));
        loginPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loginPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Sans", Font.PLAIN, 16));
        loginPanel.add(passwordLabel);

        passField = new JTextField(20);
        loginPanel.add(passField);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        centerPanel.add(loginPanel);

        this.add(centerPanel);
    }

    private void makeLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loginButton.addActionListener(new LoginButtonListener());
        this.add(BorderLayout.SOUTH, loginButton);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // call hashing function
            Backend backend = Backend.getInstance();
            String hashedPassword = backend.hashPassword(passField.getText());

            if (hashedPassword.equals(actualPassword)) {
                loginWindowReference.setPlaintextPassword(passField.getText());
                dispose();
            }
        }
    }//end of LoginButtonListener

    private void getActualPassword() {
         try {
            //String path = "../testers/authenticationTokenHash"; // testers\\TestData -> May need to use for Windows
            String path = "../hashToken";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            actualPassword = bufferedReader.readLine();
            bufferedReader.close();
         }
         catch(IOException e) {
            e.printStackTrace();
            actualPassword = "pass";
         }
    }
}