package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PreferenceMenuListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JFrame preferenceFrame = new JFrame();
        preferenceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(preferenceFrame, "Settings", "Preferences",
            JOptionPane.PLAIN_MESSAGE);
    }
} // End of PreferenceMenuListener class