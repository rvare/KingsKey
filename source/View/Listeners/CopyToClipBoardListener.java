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
    public CopyToClipBoardListener(JTable t) {
        table = t;
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
        catch(IndexOutOfBoundsException e) {
            //nothingSelectedErrorMessage();
            ErrorMessageWindow.makeMessage("No item selected. Please select an item.");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void nothingSelectedErrorMessage() {
        JFrame frame = new JFrame();
        frame.setResizable(true);
        frame.setSize(350,100);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Error: No item selected. Please select an item.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label);
        frame.setVisible(true);
    }
} // End of CopyToClipBoarListener class