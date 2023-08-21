package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

import View.UI.ErrorMessageWindow;

public class CopyToClipBoardListener implements ActionListener {
    private JTable table;

    public CopyToClipBoardListener(final JTable originalTable) {
        table = originalTable;
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            String dataField = (String)table.getValueAt(row, col);
            
            StringSelection selectedString = new StringSelection(dataField);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selectedString, selectedString);
        }
        catch (IndexOutOfBoundsException outOfBoundsEx) {
            //nothingSelectedErrorMessage();
            ErrorMessageWindow.makeMessage("No item selected. Please select an item.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // End of actionPerformed

    public static void nothingSelectedErrorMessage() {
        JFrame errorFrame = new JFrame();
        errorFrame.setResizable(true);
        errorFrame.setSize(350, 100);
        errorFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Error: No item selected. Please select an item.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        errorFrame.getContentPane().add(label);
        errorFrame.setVisible(true);
    }
} // End of CopyToClipBoarListener class