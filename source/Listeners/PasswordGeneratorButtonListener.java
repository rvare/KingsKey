import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        panel = new JPanel(new FlowLayout());
        JLabel placeholder = new JLabel("password output");
        JButton generate = new JButton("Generate Password");
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String pass = Password.generatePassword();
                placeholder.setText(pass);
            }
        });
        panel.add(placeholder);
        panel.add(generate);
        UI.getContentDisplayPanel().add(panel);
    }
}