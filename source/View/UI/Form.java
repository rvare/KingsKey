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

import View.Listeners.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Form extends JFrame {
    private JPanel gridPanel;
    private JPanel buttonPanel;

    private JTable dataTable;
    
    private JTextField passwordField;
    private JTextField websiteField;
    private JTextField accountField;
    private JTextField emailField;
    
    private int currRow;
    private int currCol;
    
    private String mode;
    
    public Form() {
        gridPanel = new JPanel(new GridLayout(4, 4));
        getContentPane().add(BorderLayout.CENTER, gridPanel);
        setLocationRelativeTo(null);
        
        dataTable = UI.getDataTable();
        
        setUpLabelsAndFields();
        setUpButtons();
        
        setSize(400, 250);
        setResizable(false);
        mode = "New";
    }
    
    public Form(final String modeType) { // dummy is used to call this constructor. Does nothing. Will find a better solution
        this(); // Call the default constructor
        mode = modeType;
    }
    
    private void setUpLabelsAndFields() {
        JLabel websiteLabel = new JLabel("Website");
        JLabel accountLabel = new JLabel("Account");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        
        websiteField = new JTextField(30);
        accountField = new JTextField(30);
        emailField = new JTextField(30);
        passwordField = new JTextField(30);
        
        gridPanel.add(websiteLabel);
        gridPanel.add(websiteField);
        gridPanel.add(accountLabel);
        gridPanel.add(accountField);
        gridPanel.add(emailLabel);
        gridPanel.add(emailField);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordField);
    } // End of setUpLabelsAndFields
    
    private void setUpButtons() {
        buttonPanel = new JPanel();
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OkButtonListener());
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        
        JButton genPassButton = new JButton("Generate Password");
        genPassButton.addActionListener(new GeneratePasswordListener());
        
        buttonPanel.add(genPassButton);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);
    } // End of setUpButtons

    public void getTableInfo() {
        currRow = dataTable.getSelectedRow();
        websiteField.setText((String)dataTable.getValueAt(currRow, 0));
        accountField.setText((String)dataTable.getValueAt(currRow, 1));
        emailField.setText((String)dataTable.getValueAt(currRow, 2));
        passwordField.setText((String)dataTable.getValueAt(currRow, 3));
    } // End of getTableInfo
    
    private class OkButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Stuff that sends data to table
            String[] row = new String[4];
            row[0] = websiteField.getText();
            row[1] = accountField.getText();
            row[2] = emailField.getText();
            row[3] = passwordField.getText();
            DefaultTableModel model = (DefaultTableModel) UI.getDataTable().getModel();
            
            if (mode.equals("New")) { // Create new record
                model.addRow(new Object [] {row[0], row[1], row [2], row[3]});
                updateDatabase(model);
                Backend.setBooleanUnsavedChanges(true);
                clearForm();
            }
            else { // Write to the table
                dataTable.setValueAt(row[0], currRow, 0);
                dataTable.setValueAt(row[1], currRow, 1);
                dataTable.setValueAt(row[2], currRow, 2);
                dataTable.setValueAt(row[3], currRow, 3);
                updateDatabase(model);
                Backend.setBooleanUnsavedChanges(true);
            }
            
            //UI.getContentDisplayPanel().repaint();
            dispose();
        }
    } // End of OkButtonListener

    private void clearForm() {
        websiteField.setText("");
        accountField.setText("");
        emailField.setText("");
        passwordField.setText("");
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
    
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Cancel, meaning don't save data or changes
            dispose();
        }
    }
    
    private class GeneratePasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            passwordField.setText(Password.generatePassword());
        }
    }
} // End of Form class