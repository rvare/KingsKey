package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

import Model.Backend;

public class SaveListener implements ActionListener {
    JFrame noticeFrame;

    public SaveListener() {
        noticeFrame = new JFrame();
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(noticeFrame, "Contents saved", "Confirmation", JOptionPane.PLAIN_MESSAGE);
        Backend.saveRecords();
    }
}