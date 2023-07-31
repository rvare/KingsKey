package View.Listeners;

//import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

public class NewRecordMenuListener implements ActionListener {
    private JFrame frame;
    public NewRecordMenuListener(JFrame f) {
        frame = f;
    }
    public void actionPerformed(ActionEvent evt) {
        frame.setVisible(true);
    }
} // End of NewRecordMenuListener class