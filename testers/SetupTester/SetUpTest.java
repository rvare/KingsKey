import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class SetUpTest {
    public static void main(String[] args) throws Exception {
        File f = new File("./testFile");
        SetUpWindow sw = new SetUpWindow();

        // The following would be in the Controller
        if (!f.exists() && !f.isFile()) {
            System.out.println("File doesn't exists, will make it now");

            sw.firstTimeSetup();

            FileOutputStream fw = new FileOutputStream(f);
            fw.write("Bla".getBytes());
            fw.close();
        }
        else {
            System.out.println("File exists, no need to make");
        }

        System.exit(0);
    }
}

class SetUpWindow extends JFrame {
    private String password;
    private SetUpDialog p;

    public SetUpWindow() {
        p = new SetUpDialog(this, true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void firstTimeSetup() {
        p.setVisible(true);
    }

    public void setPassword(String p) {
        this.password = p;
    }
}

class SetUpDialog extends JDialog {
    private JLabel passwordLabel;
    private JTextField passField;
    private JButton confirmButton;

    public SetUpDialog(final SetUpWindow parentFrame, final boolean modal) {
        super(parentFrame, modal);
        this.setTitle("First Time Set Up");
        this.makeDescription();
        this.makePassField();
        this.makeConfirmButton();

        pack();
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void makeDescription() {
        JLabel description = new JLabel("Enter in your master password", SwingConstants.CENTER);
        description.setBorder(new EmptyBorder(10, 100, 20, 100));
        description.setFont(new Font("Sans", Font.PLAIN, 16));
        this.add(description, BorderLayout.NORTH);
    }

    private void makePassField() {
        JPanel loginPanel = new JPanel(new GridLayout(1, 2));
        loginPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loginPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        passwordLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Sans", Font.PLAIN, 16));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(passwordLabel);
        loginPanel.add(labelPanel);

        passField = new JTextField(20);
        JPanel fieldPanel = new JPanel(new FlowLayout());
        fieldPanel.add(passField);
        loginPanel.add(fieldPanel);

        this.add(loginPanel, BorderLayout.CENTER);
    }

    private void makeConfirmButton() {
        confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ConfirmationButtonListener());
        this.add(BorderLayout.SOUTH, confirmButton);
    }

    private class ConfirmationButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Call hashing
            // Save hash to file
            dispose();
        }

    }
}