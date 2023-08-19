import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowClosingDemo {
    private static JFrame mainFrame;
    public static void main(String [] args) {
        mainFrame = new JFrame("KingsKey");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(200,200);

        mainFrame.addWindowListener(new WindowClosingListener());
        mainFrame.setVisible(true);
    }

    private static class WindowClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            JFrame saveChangesConfirmationWindow = new JFrame("Save Changes?");
            saveChangesConfirmationWindow.setSize(250, 100);
            saveChangesConfirmationWindow.setLocationRelativeTo(null);
            saveChangesConfirmationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // setting up text labels
            JPanel messageHolder = new JPanel();
            messageHolder.setLayout(new BoxLayout(messageHolder, BoxLayout.Y_AXIS));
            JLabel saveMessage = new JLabel("It looks like you have unsaved changes.");
            saveMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel saveMessagePt2 = new JLabel("Do you want to save?");
            saveMessagePt2.setAlignmentX(Component.CENTER_ALIGNMENT);
            messageHolder.add(saveMessage);
            messageHolder.add(saveMessagePt2); 

            JPanel buttonPanel = new JPanel();
            JButton yesButton = new JButton("Yes");
            yesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        //try to save here
                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                    finally{//provides better guarentee that this code block will run
                        saveChangesConfirmationWindow.dispose();
                        mainFrame.dispose();
                        System.exit(0);
                    }
                }
            });

            JButton noButton = new JButton("No");
            noButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveChangesConfirmationWindow.dispose();
                    mainFrame.dispose();
                    System.exit(0);
                }
            });

            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);

            saveChangesConfirmationWindow.getContentPane().add(BorderLayout.NORTH, messageHolder);
            saveChangesConfirmationWindow.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
            saveChangesConfirmationWindow.setVisible(true);
        }
    }
}
