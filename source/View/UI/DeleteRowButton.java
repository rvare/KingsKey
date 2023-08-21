/* This file is part of Kings Key project
 * Copyright (C) 2023 Richard E. Varela (rvare) and Henry Kong.
 * 
 * Kings Key is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kings Key is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR PARTICULAR PURPOSE. See GNU General Public Licnese for more details.
 * 
 * You should have recieved a copy of the GNU General Public License along
 * with Kings Key. If not, see <https://www.gnu.org/licenses/>.
 */

package View.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Backend;

public class DeleteRowButton extends JButton implements ActionListener {
    
    public DeleteRowButton() {
        super("Delete row");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        JFrame confirmationFrame = new JFrame("Confirm");
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmationFrame.setSize(240, 100);
        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setResizable(false);
        JLabel question = new JLabel("Are you sure you want to delete row?");
        question.setBorder(new EmptyBorder(10, 15, 0, 0));
        JPanel buttonPanel = new JPanel();

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteRow();
                UI.getMainFrame().requestFocus();
                confirmationFrame.dispose();
            }
        });

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmationFrame.dispose();
            }
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        confirmationFrame.getContentPane().add(BorderLayout.NORTH, question);
        confirmationFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        confirmationFrame.setVisible(true);
    } // End of actionPerformed
    
    private void deleteRow() {
        int currRow = UI.getDataTable().getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) UI.getDataTable().getModel();
        model.removeRow(currRow);
        updateDatabase(model);
        Backend.setBooleanUnsavedChanges(true);
    }

    private void updateDatabase(DefaultTableModel model) {
        // saving changes to backend Object [][] data
        int nRow = model.getRowCount(), nCol = model.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = model.getValueAt(i, j);
            }
        }
        Backend.updateDatabase(tableData);
    }
}// End of DeleteRowButton class
