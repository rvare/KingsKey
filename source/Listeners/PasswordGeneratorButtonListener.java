package Listeners;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
                //automatically copy to clipboard
                StringSelection selectedString = new StringSelection(pass);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selectedString, null);
            }
        });
        //creating panels for password testing layout
        JPanel resultLabelHolder = new JPanel();
        JPanel textBoxAndButtonHolder = new JPanel();
        JPanel passwordTestPanel = new JPanel();
        passwordTestPanel.setLayout(new BoxLayout(passwordTestPanel, BoxLayout.Y_AXIS));
        //panel holds result output label
        JLabel passwordStrengthResults = new JLabel("Password Strength: ?");
        resultLabelHolder.add(passwordStrengthResults);
        //panel holds textbox and button
        JTextField textField = new JTextField("Enter Password", 20);
        JButton testPasswordButton = new JButton("Test Password");
        textBoxAndButtonHolder.add(textField);
        textBoxAndButtonHolder.add(testPasswordButton);

        //listener: this will test password and output result
        testPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String pass = textField.getText();
                String result = Password.checkPasswordStrength(pass);
                passwordStrengthResults.setText(result);
            }
        });
        //adding everything to a parent panel
        passwordTestPanel.add(resultLabelHolder);
        passwordTestPanel.add(textBoxAndButtonHolder);
        generatePassJPanel.add(placeholder);
        generatePassJPanel.add(generate);
        panel.add(generatePassJPanel);
        panel.add(passwordTestPanel);
        UI.getContentDisplayPanel().add(panel);
    }
}