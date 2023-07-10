import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GenPasswordButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        // mainFrame.getContentPane().remove(UI.getContentDisplayPanel());
        UI.getContentDisplayPanel().removeAll();
        createGeneratePasswordUI();
        UI.getContentDisplayPanel().revalidate();
        UI.getContentDisplayPanel().repaint();
    }

    public void createGeneratePasswordUI() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField placeholder = new JTextField("placeholder");
        JButton generate = new JButton("Generate");
        panel.add(placeholder);
        panel.add(generate);
        UI.getContentDisplayPanel().add(panel);
    }
}