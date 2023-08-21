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

package View.Listeners;

import Model.*;
import View.UI.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RecordsTableListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        UI.getContentDisplayPanel().removeAll();
        //createDefaultUI();
        UI.getContentDisplayPanel().add(UI.getScrollPane());
        UI.getContentDisplayPanel().revalidate();
        UI.getContentDisplayPanel().repaint();
    }

    // This method is to be removed because it contains repeated buggy code cause no updates to the table to happen.
    public void createDefaultUI() {
        /*
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel(Backend.getDataObjects(), Backend.getColumnNames());
        JTable dataTable = new JTable(tableModel);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.setDefaultEditor(Object.class, null);
        dataTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane);
        UI.getContentDisplayPanel().add(panel);
        */
    }
} // End of RecordsTableListener class