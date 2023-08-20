package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

import View.UI.*;
import View.UI.ErrorMessageWindow;

public class EditRecordMenuListener implements ActionListener {
    private Form frame;

    public EditRecordMenuListener(final Form form) {
        frame = form;
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            frame.getTableInfo();
            frame.setVisible(true);
        }
        catch (IndexOutOfBoundsException outOfBoundsEx) {
            //CopyToClipBoardListener.nothingSelectedErrorMessage();
            ErrorMessageWindow.makeMessage("No item selected. Please select an item to edit.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} // End of EditRecordMenuListener class