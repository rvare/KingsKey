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

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import View.Listeners.*;
import Model.*;

public class UI {
    private static JFrame mainFrame;
    private static JPanel firstRow;
    private static JPanel contentDisplay;
    private static JPanel recordsPane;
    private static JPanel passwordPane;
    private static JTable dataTable;
    private static JScrollPane scrollPane;
    private static DeleteRowButton deleteRowButton;

    public void createUI() {
        // Had to move these two lines because a null reference was being
        //   passed for the Form class. The original is commented out.
        DefaultTableModel tableModel = new DefaultTableModel(Backend.getDataObjects(), Backend.getColumnNames());
        dataTable = new JTable(tableModel);
        dataTable.addFocusListener(new DeleteRecordListener());

        // Create the top level frame that hold everything
        mainFrame = new JFrame();
        mainFrame.setTitle("Kings Key");

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowClosingListener());
        mainFrame.setSize(1000, 600);
        mainFrame.setLocationRelativeTo(null);
        
        // Create the options menu 
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, BoxLayout.Y_AXIS));

        // Creating small menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newRecordItem = new JMenuItem("New record");
        newRecordItem.addActionListener(new NewRecordMenuListener(new Form()));
        JMenuItem saveRecords = new JMenuItem("Save");
        saveRecords.addActionListener(new SaveListener());
        JMenu editMenu = new JMenu("Edit");
        //JMenuItem preferencesMenu = new JMenuItem("Preferences");
        //preferencesMenu.addActionListener(new PreferenceMenuListener());
        JMenuItem editRecordItem = new JMenuItem("Edit Record");
        editRecordItem.addActionListener(new EditRecordMenuListener(new Form("EditMode")));
        JMenuItem changeMasterPassword = new JMenuItem("Change Master Password");
        changeMasterPassword.addActionListener(new ChangeMasterPasswordListener());
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AboutMenuListener());
        
        fileMenu.add(newRecordItem);
        fileMenu.add(saveRecords);
        menuBar.add(fileMenu);
        //editMenu.add(preferencesMenu);
        editMenu.add(editRecordItem);
        editMenu.add(changeMasterPassword);
        menuBar.add(editMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        // Create a panel to hold the second row of buttons
        firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton getRecordTableButton = new JButton("Records");
        JButton passwordTesterButton = new JButton("Password Tester");
        JButton copyButton = new JButton("Copy to Clipboard");
        deleteRowButton = new DeleteRowButton();
        // Copy.addActionListener(new CopyToClipBoard Listener(dataTable));
        firstRow.add(getRecordTableButton);
        firstRow.add(passwordTesterButton);
        firstRow.add(copyButton);
        firstRow.add(deleteRowButton);
        deleteRowButton.setVisible(false);

        // Adding event listeners
        passwordTesterButton.addActionListener(new PasswordGeneratorButtonListener());
        getRecordTableButton.addActionListener(new RecordsTableListener());

        optionsMenu.add(firstRow);
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider);

        // Creating content display section
        contentDisplay = new JPanel(new BorderLayout());
        contentDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));

        // DefaultTableModel tableModel = new DefaultTableModel(Backend.getDataObjects(), Backend.getColumnNames());
        // dataTable = new JTable(tableModel);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.setDefaultEditor(Object.class, null);
        dataTable.setAutoCreateRowSorter(true);
        copyButton.addActionListener(new CopyToClipBoardListener(dataTable));
        scrollPane = new JScrollPane(dataTable);
        contentDisplay.add(scrollPane);

        // Putting the sections into root layout
        mainFrame.getContentPane().add(BorderLayout.NORTH, optionsMenu);
        mainFrame.getContentPane().add(BorderLayout.CENTER, contentDisplay); 
        //mainFrame.pack();

        // Making everything visible
        mainFrame.setVisible(true);
        optionsMenu.setVisible(true);
        firstRow.setVisible(true);
        contentDisplay.setVisible(true);
    } // End of createUI

    private void createRecordsUI() {

    }

    private void createGeneratePasswordUI() {

    }
    
    public static JPanel getContentDisplayPanel() {
        return contentDisplay;
    }
    
    public static JTable getDataTable() {
        return dataTable;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static JPanel getRecordsPane() {
        return contentDisplay;
    }

    public static JPanel getFirstRowPanel() {
        return firstRow;
    }

    public static DeleteRowButton getDeleteRowButton() {
        return deleteRowButton;
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getPasswordGeneratorPane() {
        return new JPanel();
    }
} // End of UI class