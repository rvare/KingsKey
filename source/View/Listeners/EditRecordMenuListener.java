package View.Listeners;

import View.UI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

public class EditRecordMenuListener implements ActionListener {
    private Form frame;

    public EditRecordMenuListener(Form f) {
        frame = f;
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            frame.getTableInfo();
            frame.setVisible(true);
        }
        catch (IndexOutOfBoundsException e) {
            CopyToClipBoardListener.nothingSelectedErrorMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} // End of EditRecordMenuListener class