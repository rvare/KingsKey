package View.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

public class CopyToClipBoardListener implements ActionListener {
    private JTable table;
    public CopyToClipBoardListener(JTable t) {
        table = t;
    }

    public void actionPerformed(ActionEvent evt) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        String dataField = (String)table.getValueAt(row, col);
        
        StringSelection selectedString = new StringSelection(dataField);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selectedString, selectedString);
    }
} // End of CopyToClipBoarListener class