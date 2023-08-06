package View.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame {
    public boolean showLogin() {
        PassDialog p = new PassDialog(this, true);
        p.setVisible(true);
        System.out.println("Returns UI");
        return true;
    }
}

class PassDialog extends JDialog {
    private JLabel passwordLabel;
    private JTextField passField;
    private JButton loginButton;

    public PassDialog(final JFrame parentFrame, boolean modal) {
        super(parentFrame, modal);
        this.setTitle("Login");
        this.makeProgramTitleLabel();
        this.makeLoginField();
        this.makeLoginButton();
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

        JTextField loginField = new JTextField(20);
        loginPanel.add(loginField);

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
            dispose();
        }
    }
}