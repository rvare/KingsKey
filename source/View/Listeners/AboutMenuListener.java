package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutMenuListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JFrame aboutFrame = new JFrame();
        aboutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JOptionPane is a shortcut to making dialog frames. Super useful
        JOptionPane.showMessageDialog(aboutFrame, "Created by:\n rvare and HenryWK7", "About",
                JOptionPane.PLAIN_MESSAGE);
    }
} // End of AboutMenuListener class