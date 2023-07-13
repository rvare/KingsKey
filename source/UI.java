import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI {
    static JFrame mainFrame;
    static JPanel contentDisplay;
    static JTable dataTable;
    public static void main(String [] args) {       
        UI gui = new UI();
        /*special note: the controller class will have to call Backend.getData(); in the future 
            to populate the 2D array that resides in the Backend class but for now this is a placeholder*/
        Backend.getData();
        gui.createUI();  
    }

    public void createUI() {
        //create the top level frame that hold everything
        mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        
        //create the options menu 
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, BoxLayout.Y_AXIS));

        //creating small menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem option1 = new JMenuItem("Option");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem preferencesMenu = new JMenuItem("Preferences"); // Preferences
        preferencesMenu.addActionListener(new PreferenceMenuListener());
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AboutMenuListener());
        
        fileMenu.add(option1);
        menuBar.add(fileMenu);
        editMenu.add(preferencesMenu);
        menuBar.add(editMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        //create a panel to hold the second row of buttons
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton newItemButton = new JButton("New");
        JButton passwordGeneratorButton = new JButton("Password Generator");
        JButton copy = new JButton("Copy to Clipboard");
        copy.addActionListener(new CopyToClipBoardListener());
        firstRow.add(newItemButton);
        firstRow.add(passwordGeneratorButton);
        firstRow.add(copy);

        //adding event listeners
        passwordGeneratorButton.addActionListener(new PasswordGeneratorButtonListener());
        newItemButton.addActionListener(new NewItemButtonListener());

        optionsMenu.add(firstRow);
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider);

        //creating content display section
        contentDisplay = new JPanel(new BorderLayout());
        contentDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));

        dataTable = new JTable(Backend.getDataObjects(), Backend.getColumnNames());
        JScrollPane scrollPane = new JScrollPane(dataTable);
        contentDisplay.add(scrollPane);

        //putting the sections into root layout
        mainFrame.getContentPane().add(BorderLayout.NORTH, optionsMenu);
        mainFrame.getContentPane().add(BorderLayout.CENTER, contentDisplay); 
        //mainFrame.pack();

        //making everything visible
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
} // End of UI class