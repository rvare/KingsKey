package View.Listeners;

import View.UI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

public class EditRecordMenuListener implements ActionListener {
    Form frame;

    public EditRecordMenuListener(Form f) {
        frame = f;
    }

    public void actionPerformed(ActionEvent evt) {
        frame.getTableInfo();
        frame.setVisible(true);
    }
} // End of EditRecordMenuListener class