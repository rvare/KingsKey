import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

class RecordMenuListener implements ActionListener {
    private JFrame newRecordFrame;
    private JPanel formLabelPanel;
    private JPanel formFieldPanel;
    private JPanel gridPanel;
    
    public void actionPerformed(ActionEvent evt) {
        newRecordFrame = new JFrame();
        formLabelPanel = new JPanel();
        formFieldPanel = new JPanel();
        gridPanel = new JPanel(new GridLayout(4, 4));
        
        // Set up labels and fields
        setUpLabelsFields();
        
        // Set up buttons
        setUpButtons();
        
        newRecordFrame.getContentPane().add(BorderLayout.CENTER, gridPanel);
        newRecordFrame.setSize(400, 250);
        newRecordFrame.setResizable(false);
        newRecordFrame.setVisible(true);
    }
    
    private void createFormLabels() {
        // Keeping this old code for future use
        /*
        formLabelPanel = new JPanel();
        formLabelPanel.setLayout(new BoxLayout(formLabelPanel, BoxLayout.Y_AXIS));
        JLabel websiteLabel = new JLabel("Website");
        JLabel accountLabel = new JLabel("Account");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        formLabelPanel.add(websiteLabel);
        formLabelPanel.add(accountLabel);
        formLabelPanel.add(emailLabel);
        formLabelPanel.add(passwordLabel);
        
        testPanel.add(websiteLabel);
        testPanel.add(accountLabel);
        testPanel.add(emailLabel);
        testPanel.add(passwordLabel);
        
        //newRecordFrame.getContentPane().add(BorderLayout.WEST, formLabelPanel);
        */
    }
    
    private void createFormFields() {
        // Keeping this old code for future use
        /*
        formFieldPanel.setLayout(new BoxLayout(formFieldPanel, BoxLayout.Y_AXIS));
        
        JTextField websiteField = new JTextField(30);
        JTextField accountField = new JTextField(30);
        JTextField emailField = new JTextField(30);
        JTextField passwordField = new JTextField(30);

        formFieldPanel.add(websiteField);
        formFieldPanel.add(accountField);
        formFieldPanel.add(emailField);
        formFieldPanel.add(passwordField);
        
        testPanel.add(websiteField);
        testPanel.add(accountField);
        testPanel.add(emailField);
        testPanel.add(passwordField);
        
        //newRecordFrame.getContentPane().add(BorderLayout.EAST, formFieldPanel);
        */
    }
    
    private void setUpLabelsFields() {
        JLabel websiteLabel = new JLabel("Website");
        JLabel accountLabel = new JLabel("Account");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        
        JTextField websiteField = new JTextField(30);
        JTextField accountField = new JTextField(30);
        JTextField emailField = new JTextField(30);
        JTextField passwordField = new JTextField(30);
        
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
        JPanel buttonPanel = new JPanel();
        JButton genPassButton = new JButton("Generate Password");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(genPassButton);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        newRecordFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
    }
}