package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

class NewRecordMenuListener implements ActionListener {
    JFrame frame;
    NewRecordMenuListener(JFrame f) {
        frame = f;
    }
    public void actionPerformed(ActionEvent evt) {
        //Form f = new Form();
        frame.setVisible(true);
    }
}