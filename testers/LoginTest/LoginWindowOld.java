

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginWindowOld extends JFrame {
    private JDialog dialog;

    public LoginWindow() {
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        JPanel windowPanel = makeWindowPanel();
        this.add(BorderLayout.CENTER, windowPanel);
        JPanel fieldPanel = makeFieldPanel();
        makeLabels(windowPanel, fieldPanel);
        JTextField loginField = new JTextField(20);
        fieldPanel.add(loginField);
        windowPanel.add(fieldPanel);
        makeLoginButton();
    }

    private JPanel makeWindowPanel() {
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        return windowPanel;
    }

    private JPanel makeFieldPanel() {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fieldPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        return fieldPanel;
    }

    private void makeLabels(JPanel windowPanel, JPanel fieldPanel) {
        JLabel programTitle = new JLabel("Kings Key", SwingConstants.CENTER);
        programTitle.setBorder(new EmptyBorder(10, 0, 20, 0));
        programTitle.setFont(new Font("Sans", Font.PLAIN, 24));
        programTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //programTitle.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        //windowPanel.add(programTitle);
        this.add(BorderLayout.NORTH, programTitle);

        JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Sans", Font.PLAIN, 16));
        fieldPanel.add(passwordLabel);
    }

    private void makeLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loginButton.addActionListener(new LoginButtonListener());
        this.add(BorderLayout.SOUTH, loginButton);
    }

    public boolean showLogin() {
        //dialog = new JDialog(this, "Login", true);
        //dialog.setVisible(true);
        setVisible(true);
        System.out.println("Returns UI");
        return true;
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }
}