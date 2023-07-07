import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI{

    static Object [][] data;
    static final String [] columnNames = {"Site","Email","Username","password"};
    public static void main(String [] args){       
        UI gui = new UI();
        data = getData();
        gui.createUI();  
    }

    public void createUI(){
        //create the top level frame that hold everything
        JFrame mainFrame = new JFrame();

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
        JMenuItem option3 = new JMenuItem("Option");
        
        fileMenu.add(option1);
        menuBar.add(fileMenu);
        editMenu.add(option2);
        menuBar.add(editMenu);
        helpMenu.add(option3);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        //create a panel to hold the second row of buttons
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton newItem = new JButton("New");
        JButton generatePassword = new JButton("Generate Password");
        JButton copy = new JButton("Copy to Clipboard");
        firstRow.add(newItem);
        firstRow.add(generatePassword);
        firstRow.add(copy);


        optionsMenu.add(firstRow);
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider);

        //creating content display section
        JPanel contentDisplay = new JPanel(new BorderLayout());
        contentDisplay.setBorder(new EmptyBorder(10,10,10,10));

        JTable dataTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        //JButton placeholder = new JButton("content display placeholder");
        contentDisplay.add(scrollPane);

        //putting the sections into root layout
        mainFrame.getContentPane().add(BorderLayout.NORTH, optionsMenu);
        mainFrame.getContentPane().add(BorderLayout.CENTER, contentDisplay); 

        //making everything visible
        mainFrame.setVisible(true);
        optionsMenu.setVisible(true);
       // firstRow.setVisible(true);
        firstRow.setVisible(true);
        contentDisplay.setVisible(true);

    }
    
    public static Object [][] getData(){
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
    }
}