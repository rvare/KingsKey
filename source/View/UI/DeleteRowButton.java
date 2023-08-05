package View.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DeleteRowButton extends JButton implements ActionListener{
    
    public DeleteRowButton() {
        super("Delete row");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        JFrame frame = new JFrame("Confirm");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(240, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JLabel question = new JLabel("Are you sure you want to delete row?");
        question.setBorder(new EmptyBorder(10, 15, 0, 0));
        JPanel buttonPanel = new JPanel();

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRow();
                frame.dispose();
            }
        });

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        frame.getContentPane().add(BorderLayout.NORTH, question);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setVisible(true);
    }
    
    private void deleteRow() {
        int currRow = UI.getDataTable().getSelectedRow();
        ((DefaultTableModel)UI.getDataTable().getModel()).removeRow(currRow);
    }

}
