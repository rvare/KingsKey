package View.Listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Password;
import View.UI.*;

public class PreferenceMenuListener implements ActionListener {
    //private static JLabel currentTheme;
    public void actionPerformed(ActionEvent evt) {
        JFrame preferenceFrame = new JFrame("Themes");
        preferenceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        preferenceFrame.setSize(250, 250);
        preferenceFrame.setLocationRelativeTo(null);
        preferenceFrame.setResizable(false);
        JPanel themePanel = new JPanel();
        themePanel.setLayout(new BoxLayout(themePanel, BoxLayout.Y_AXIS));
        themePanel.setBorder(new EmptyBorder(10, 50, 0, 0));
        
        JRadioButton option1 = new JRadioButton("Metal Look and Feel");
        option1.setActionCommand("javax.swing.plaf.metal.MetalLookAndFeel");
        JRadioButton option2 = new JRadioButton("Nimbus Look and Feel");
        option2.setActionCommand("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
        JRadioButton option3 = new JRadioButton("Motif Look and Feel");
        option3.setActionCommand("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        JRadioButton option4 = new JRadioButton("Windows Look and Feel");
        option4.setActionCommand("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        JRadioButton option5 = new JRadioButton("Windows Classic");
        option5.setActionCommand("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        JRadioButton option6 = new JRadioButton("Dark Nimbus");
        //option6 not available yet. need a fix. currently does default nimbus instead as placeholder
        option6.setActionCommand("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        ButtonGroup themes = new ButtonGroup();
        themes.add(option1);
        themes.add(option2);
        themes.add(option3);
        themes.add(option4);
        themes.add(option5);
        themes.add(option6);

        JLabel currentTheme = new JLabel(getTheme());
        preferenceFrame.getContentPane().add(BorderLayout.NORTH, currentTheme);
        themePanel.add(option1);
        themePanel.add(option2);
        themePanel.add(option3);
        themePanel.add(option4);
        themePanel.add(option5);
        themePanel.add(option6);
        preferenceFrame.getContentPane().add(BorderLayout.CENTER, themePanel);

        JPanel buttonPanel = new JPanel();

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTheme = themes.getSelection().getActionCommand();
                setTheme(selectedTheme);
                currentTheme.setText(getTheme());
            }
        });

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTheme;
                try {
                    selectedTheme = themes.getSelection().getActionCommand();
                    setTheme(selectedTheme);
                    currentTheme.setText(getTheme());
                } 
                catch (NullPointerException error) {
                    //do something here
                    System.out.println("No theme selected.");
                }
                preferenceFrame.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                preferenceFrame.dispose();
            }
        });
        
        buttonPanel.add(applyButton);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        preferenceFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        
        //JOptionPane.showMessageDialog(preferenceFrame, "Settings", "Preferences",
            //JOptionPane.PLAIN_MESSAGE);
        themePanel.setVisible(true);
        preferenceFrame.setVisible(true);
    } // End of action performed method

    private void setTheme(String selectedTheme) {
        try {
            UIManager.setLookAndFeel(selectedTheme);
            SwingUtilities.updateComponentTreeUI(UI.getMainFrame());
        }
        catch (Exception ex) {
            //Themes.setDarkNimbusTheme();
            ex.printStackTrace();
            //Themes.setDarkNimbusTheme();
        }
    }

    private String getTheme() {
        //LookAndFeel look = UIManager.getLookAndFeel();
        LookAndFeel look = UIManager.getLookAndFeel();
        return look.getName();
    }

} // End of PreferenceMenuListener class