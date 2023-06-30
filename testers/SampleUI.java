import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SampleUI{
    public static void main(String [] args){       
        SampleUI gui = new SampleUI();
        gui.createUI();  
    }

    public void createUI(){
        //create the top level frame that hold everything
        JFrame mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000,600);
        
        //create the options menu that has two rows of options stacked
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, BoxLayout.Y_AXIS));
        //optionsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //create a panel to hold the first row of buttons
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        
        JButton file = new JButton("File");
        JButton edit = new JButton("Edit");
        JButton help = new JButton("Help");
        firstRow.add(file);
        firstRow.add(edit);
        firstRow.add(help);
        //firstRow.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //create a panel to hold the second row of buttons
        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

        JButton newItem = new JButton("New");
        JButton generatePassword = new JButton("Generate Password");
        JButton copy = new JButton("Copy to Clipboard");
        secondRow.add(newItem);
        secondRow.add(generatePassword);
        secondRow.add(copy);
        //secondRow.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //inserting the rows into the options menu
        optionsMenu.add(firstRow);
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider);
        optionsMenu.add(secondRow);
        JSeparator divider2 = new JSeparator(SwingConstants.HORIZONTAL);
        optionsMenu.add(divider2);

        //creating content display section
        JPanel contentDisplay = new JPanel(new BorderLayout());
        contentDisplay.setBorder(new EmptyBorder(10,10,10,10));
        JButton placeholder = new JButton("content display placeholder");
        contentDisplay.add(placeholder);

        //putting the sections into root layout
        mainFrame.getContentPane().add(BorderLayout.NORTH, optionsMenu);
        mainFrame.getContentPane().add(BorderLayout.CENTER, contentDisplay); 

        //making everything visible
        mainFrame.setVisible(true);
        optionsMenu.setVisible(true);
        firstRow.setVisible(true);
        secondRow.setVisible(true);
        contentDisplay.setVisible(true);

    }
}