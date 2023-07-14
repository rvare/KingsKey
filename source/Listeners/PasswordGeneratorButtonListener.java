import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class PasswordGeneratorButtonListener implements ActionListener {
    JPanel panel;
    public void actionPerformed(ActionEvent event) {
        // mainFrame.getContentPane().remove(UI.getContentDisplayPanel());
        UI.getContentDisplayPanel().removeAll();
        createGeneratePasswordUI();
        UI.getContentDisplayPanel().revalidate();
        UI.getContentDisplayPanel().repaint();
    }

    public void createGeneratePasswordUI() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel generatePassJPanel = new JPanel();
        JLabel placeholder = new JLabel("password output");
        JButton generate = new JButton("Generate Password");

        //listener: pressing generate password button will generate password
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String pass = Password.generatePassword();
                placeholder.setText(pass);
            }
        });

        JPanel passwordTestPanel = new JPanel();
        JTextField textField = new JTextField("Enter Password", 20);
        JButton testPasswordButton = new JButton("Test Password");

        passwordTestPanel.add(textField);
        passwordTestPanel.add(testPasswordButton);
        generatePassJPanel.add(placeholder);
        generatePassJPanel.add(generate);
        panel.add(generatePassJPanel);
        panel.add(passwordTestPanel);
        UI.getContentDisplayPanel().add(panel);
    }
}