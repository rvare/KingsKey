package View.UI;

import View.Listeners.*;
import Model.*;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UI {
    static JFrame mainFrame;
    static JPanel contentDisplay;

    static JPanel recordsPane;
    static JPanel passwordPane;

    static JTable dataTable;

    public void createUI() {
        // Had to move these two lines because a null reference was being
        //   passed for the Form class. The original is commented out.
        DefaultTableModel tableModel = new DefaultTableModel(Backend.getDataObjects(), Backend.getColumnNames());
        dataTable = new JTable(tableModel);

        // Create the top level frame that hold everything
        mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        
        // Create the options menu 
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, BoxLayout.Y_AXIS));

        // Creating small menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newRecordItem = new JMenuItem("New");
        newRecordItem.addActionListener(new NewRecordMenuListener(new Form()));
        JMenu editMenu = new JMenu("Edit");
        JMenuItem preferencesMenu = new JMenuItem("Preferences");
        preferencesMenu.addActionListener(new PreferenceMenuListener());
        JMenuItem editRecordItem = new JMenuItem("Edit Record");
        editRecordItem.addActionListener(new EditRecordMenuListener(new Form("EditMode")));
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AboutMenuListener());
        
        fileMenu.add(newRecordItem);
        menuBar.add(fileMenu);
        editMenu.add(preferencesMenu);
        editMenu.add(editRecordItem);
        menuBar.add(editMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        // Create a panel to hold the second row of buttons
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton getRecordTableButton = new JButton("Records");
        JButton passwordGeneratorButton = new JButton("Password Generator");
        JButton copy = new JButton("Copy to Clipboard");
        // Copy.addActionListener(new CopyToClipBoardListener(dataTable));
        firstRow.add(getRecordTableButton);
        firstRow.add(passwordGeneratorButton);
        firstRow.add(copy);

        // Adding event listeners
        passwordGeneratorButton.addActionListener(new PasswordGeneratorButtonListener());
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
        copy.addActionListener(new CopyToClipBoardListener(dataTable));
        JScrollPane scrollPane = new JScrollPane(dataTable);
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

    private void createRecordsUI() {

    }

    private void createGeneratePasswordUI() {

    }

    public static JPanel getRecordsPane() {
        return contentDisplay;
    }

    public JPanel getPasswordGeneratorPane() {
        return new JPanel();
    }
} // End of UI class