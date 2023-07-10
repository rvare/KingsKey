import java.awt.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI{

    //static Object [][] data;
    //static final String [] columnNames = {"Site","Email","Username","password"};

    static JFrame mainFrame;
    static JPanel contentDisplay;
    public static void main(String [] args){       
        UI gui = new UI();
        /*special note: the controller class will have to call Backend.getData(); in the future 
            to populate the 2D array that resides in the Backend class but for now this is a placeholder*/
        Backend.getData();
        gui.createUI();  
    }

    public void createUI(){
        //create the top level frame that hold everything
        mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000,600);
        
        //create the options menu 
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, BoxLayout.Y_AXIS));

        //creating small menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem option1 = new JMenuItem("Option");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem option2 = new JMenuItem("Option");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AboutMenuListener());
        
        fileMenu.add(option1);
        menuBar.add(fileMenu);
        editMenu.add(option2);
        menuBar.add(editMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        //create a panel to hold the second row of buttons
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton newItemButton = new JButton("New");
        JButton genPasswordButton = new JButton("Generate Password");
        JButton copy = new JButton("Copy to Clipboard");
        firstRow.add(newItemButton);
        firstRow.add(genPasswordButton);
        firstRow.add(copy);

        //adding event listeners
        genPasswordButton.addActionListener(new GenPasswordButtonListener());
        newItemButton.addActionListener(new NewItemButtonListener());

        optionsMenu.add(firstRow);
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider);

        //creating content display section
        contentDisplay = new JPanel(new BorderLayout());
        contentDisplay.setBorder(new EmptyBorder(10,10,10,10));

        JTable dataTable = new JTable(Backend.getDataObjects(), Backend.getColumnNames());
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
    
    /*public static Object [][] getData(){
        try{
            String path = "testers\\TestData";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String str = "";
            while((str = bufferedReader.readLine()) != null){
                list.add(str);
            }
            int numberOfColumns = list.get(0).split(";").length;
            Object [][] data = new Object[list.size()][numberOfColumns];
            for(int i = 0; i < list.size(); i++){
                data[i] = list.get(i).split(";");
            }
            bufferedReader.close();
            return data;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }*/
    
    public static JPanel getContentDisplayPanel(){
        return contentDisplay;
    }

    /*public static Object [][] getDataObjects(){
        return data;
    }

    public static String [] getColumnNames(){
        return columnNames;
    }*/
}