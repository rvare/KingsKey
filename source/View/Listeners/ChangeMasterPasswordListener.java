package View.Listeners;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Backend;

public class ChangeMasterPasswordListener implements ActionListener{
    private JTextField oldPasswordTextbox;
    private JTextField newPasswordTextbox;
    private JFrame frame;
    private JLabel warningLabel;

    public void actionPerformed(ActionEvent e){
        frame = new JFrame("Change password");
        frame.setSize(350, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel infoPanel = new JPanel(new GridLayout(2,2));
        JLabel oldPasswordLabel = new JLabel("Enter old password: ");
        oldPasswordTextbox = new JTextField(30);
        JLabel newPasswordLabel = new JLabel("Enter new password: ");
        newPasswordTextbox = new JTextField(30);

        warningLabel = new JLabel("Error: Old password incorrect.");
        warningLabel.setVisible(false);
        infoPanel.add(oldPasswordLabel);
        infoPanel.add(oldPasswordTextbox);
        infoPanel.add(newPasswordLabel);
        infoPanel.add(newPasswordTextbox);

        JPanel buttonPanel = new JPanel();

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(new ChangePasswordButtonListener());
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(cancelButton);
        frame.getContentPane().add(BorderLayout.NORTH, warningLabel);
        frame.getContentPane().add(BorderLayout.CENTER, infoPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setVisible(true);

    }

    private class ChangePasswordButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Backend b = Backend.getInstance();
            //if old password matches change to new password
            if(oldPasswordTextbox.getText().equals(b.getMasterPassword())) {
                b.setMasterPassword(newPasswordTextbox.getText());
                //initiate save to make sure next run opens datafile without error
                Backend.saveRecords();
                //updates token for future logins
                updateAuthenticationToken(newPasswordTextbox.getText());
                frame.dispose();
            }
            else{
                warningLabel.setVisible(true);
            }
        }
    }

    private void updateAuthenticationToken(String updatedPassword) {
        try {
            File authToken = new File("../testers/authenticationTokenHash");
            FileOutputStream fos = new FileOutputStream(authToken);
            Backend backend = Backend.getInstance();
            byte[] hashedPassword = backend.hashPassword(updatedPassword).getBytes();
            fos.write(hashedPassword);
            fos.close();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
