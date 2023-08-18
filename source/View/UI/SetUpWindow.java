package View.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;

import Model.Backend;

public class SetUpWindow extends JFrame {
    private String password;
    private SetUpDialog dialog;

    public SetUpWindow() {
        dialog = new SetUpDialog(this, true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void firstTimeSetup() {
        dialog.setVisible(true);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}

class SetUpDialog extends JDialog {
    private JTextField passwordTextField;

    public SetUpDialog(final SetUpWindow parentFrame, final boolean modal) {
        super(parentFrame, modal);
        this.setTitle("First Time Set Up");
        this.makeDescription();
        this.makePasswordField();
        this.makeConfirmButton();

        pack();
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void makeDescription() {
        JLabel description = new JLabel("Enter in your master password", SwingConstants.CENTER);
        description.setBorder(new EmptyBorder(10, 100, 10, 100));
        description.setFont(new Font("Sans", Font.PLAIN, 16));
        this.add(description, BorderLayout.NORTH);
    }

    private void makePasswordField() {
        JPanel fieldPanel = new JPanel(new GridLayout(1, 2));
        fieldPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fieldPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Sans", Font.PLAIN, 16));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(passwordLabel);
        fieldPanel.add(labelPanel);

        passwordTextField = new JTextField(20);
        JPanel passwordTextPanel = new JPanel(new FlowLayout());
        passwordTextPanel.add(passwordTextField);
        fieldPanel.add(passwordTextPanel);

        this.add(fieldPanel, BorderLayout.CENTER);
    }

    private void makeConfirmButton() {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ConfirmationButtonListener());
        this.add(BorderLayout.SOUTH, confirmButton);
    }

    private class ConfirmationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Call hashing
            Backend backend = Backend.getInstance();
            String hashedPassword = backend.hashPassword(passwordTextField.getText());

            // Save hash to file
            try {
                File hashToken = new File("../hashToken");
                FileOutputStream fos = new FileOutputStream(hashToken);

                fos.write(hashedPassword.getBytes());
                fos.close();
            }
            catch (IOException ioEx) {
                ioEx.printStackTrace();
            }

            dispose();
        }
    }
}