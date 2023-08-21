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

package View.Listeners;

import View.UI.*;
import Model.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PasswordGeneratorButtonListener implements ActionListener {
    private JPanel panel;

    public void actionPerformed(ActionEvent event) {
        // mainFrame.getContentPane().remove(UI.getContentDisplayPanel());
        UI.getContentDisplayPanel().removeAll();
        createGeneratePasswordUI();
        UI.getContentDisplayPanel().revalidate();
        UI.getContentDisplayPanel().repaint();
    }

    public void createGeneratePasswordUI() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        /*JPanel generatePassJPanel = new JPanel();
        JLabel placeholder = new JLabel("password output");
        JButton generate = new JButton("Generate Password");

        // Listener: pressing generate password button will generate password
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String pass = Password.generatePassword();
                placeholder.setText(pass);
                // Automatically copy to clipboard
                StringSelection selectedString = new StringSelection(pass);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selectedString, null);
            }
        });*/

        // Creating panels for password testing layout
        JPanel resultLabelHolder = new JPanel();
        resultLabelHolder.setLayout(new BoxLayout(resultLabelHolder, BoxLayout.Y_AXIS));
        resultLabelHolder.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel textBoxAndButtonHolder = new JPanel();
        JPanel passwordTestPanel = new JPanel();
        //passwordTestPanel.setLayout(new BoxLayout(passwordTestPanel, BoxLayout.Y_AXIS));
        passwordTestPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Panel holds result output label
        JLabel passwordStrengthResults = new JLabel("Password Strength: ?");
        passwordStrengthResults.setFont(passwordStrengthResults.getFont().deriveFont(20.0f));
        JLabel passwordEntropyResults = new JLabel("Password Entropy: ?");
        passwordEntropyResults.setFont(passwordEntropyResults.getFont().deriveFont(20.0f));
        JLabel passwordCommonalityResults = new JLabel("Is the password common?: N/A");
        passwordCommonalityResults.setFont(passwordCommonalityResults.getFont().deriveFont(20.0f));
        JLabel hint = new JLabel("Hint:Try to go for at least 60 entropy for a decent password.");
        hint.setFont(hint.getFont().deriveFont(20.0f));
        resultLabelHolder.add(passwordCommonalityResults);
        resultLabelHolder.add(passwordStrengthResults);
        resultLabelHolder.add(passwordEntropyResults);
        resultLabelHolder.add(hint);
        
        // Panel holds textbox and button
        HintTextField textField = new HintTextField("Enter Password", 40);
        textField.setPreferredSize(new Dimension(700, 35));
        textField.setFont(textField.getFont().deriveFont (15.0f));
        JButton testPasswordButton = new JButton("Test Password");
        testPasswordButton.setFont(testPasswordButton.getFont().deriveFont(15.0f));
        textBoxAndButtonHolder.add(textField);
        textBoxAndButtonHolder.add(testPasswordButton);

        // Listener: this will test password and output result
        testPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String pass = textField.getText();
                String result = Password.checkPasswordStrength(pass);
                String isCommonPassword = Password.isCommonPassword(pass);
                passwordStrengthResults.setText(result);
                passwordEntropyResults.setText("Password Entropy: "+Password.calculateEntropy(pass));
                passwordCommonalityResults.setText("Is the password common?: "+isCommonPassword);
            }
        });

        passwordTestPanel.setFocusable(true);
        
        // Adding everything to a parent panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        passwordTestPanel.add(resultLabelHolder, c);
        c.gridy = 1;
        passwordTestPanel.add(textBoxAndButtonHolder, c);
        //allows textbox to be deselected when clicking this area
        passwordTestPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                passwordTestPanel.requestFocusInWindow();
            }
        });
        passwordTestPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        /*generatePassJPanel.add(placeholder);
        generatePassJPanel.add(generate);
        generatePassJPanel.setFocusable(true);
        //allows textbox to be deselected when clicking this area
        generatePassJPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                generatePassJPanel.requestFocusInWindow();
            }
        });

        generatePassJPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(generatePassJPanel);*/
        panel.add(passwordTestPanel);
        UI.getContentDisplayPanel().add(panel);
    } // End of generatePasswordUI
} // End of PasswordGeneratorButtonListener class