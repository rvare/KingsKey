import Listeners.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Form extends JFrame {
    JPanel gridPanel;
    JPanel buttonPanel;
    JTable dataTable;
    
    JTextField passwordField;
    JTextField websiteField;
    JTextField accountField;
    JTextField emailField;
    
    int currRow;
    int currCol;
    
    String mode;
    
    Form() {
        gridPanel = new JPanel(new GridLayout(4, 4));
        getContentPane().add(BorderLayout.CENTER, gridPanel);
        
        dataTable = UI.getDataTable();
        currRow = dataTable.getRowCount();
        
        setUpLabelsAndFields();
        setUpButtons();
        
        setSize(400, 250);
        setResizable(false);
        mode = "New";
        //setVisible(true);
    }
    
    Form(String dummy) { // dummy is used to call this constructor. Does nothing. Will find a better solution
        this(); // Call the default constructor
        mode = dummy;
        currRow = dataTable.getSelectedRow();
        websiteField.setText((String)dataTable.getValueAt(currRow, 0));
        accountField.setText((String)dataTable.getValueAt(currRow, 1));
        emailField.setText((String)dataTable.getValueAt(currRow, 2));
        passwordField.setText((String)dataTable.getValueAt(currRow, 3));
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
    }
    
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
    }
    
    private class OkButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Stuff that sends data to table
            String[] row = new String[4];
            row[0] = websiteField.getText();
            row[1] = accountField.getText();
            row[2] = emailField.getText();
            row[3] = passwordField.getText();
            
            if (mode.equals("New")) {
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                model.addRow(new Object [] {row[0], row[1], row [2], row[3]});
                //saving changes to backend Object [][] data
                int nRow = model.getRowCount(), nCol = model.getColumnCount();
                Object[][] tableData = new Object[nRow][nCol];
                for (int i = 0 ; i < nRow ; i++) { 
                    for (int j = 0 ; j < nCol ; j++) {
                        tableData[i][j] = model.getValueAt(i,j);
                    }
                }
                Backend.updateDatabase(tableData);
            }
            else {
                dataTable.setValueAt(row[0], currRow, 0);
                dataTable.setValueAt(row[1], currRow, 1);
                dataTable.setValueAt(row[2], currRow, 2);
                dataTable.setValueAt(row[3], currRow, 3);
            }
            
            dispose();
        }
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
}