import java.awt.event.*;
import javax.swing.*;

public class SimpleGui1B implements ActionListener{
    JButton button = new JButton("click me!");
    
    public static void main(String [] args){
        SimpleGui1B gui = new SimpleGui1B();
        gui.go();
    }
    public void go(){
        JFrame frame = new JFrame();
        button.addActionListener(this);
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        button.setText("I've been clicked!");
    }
}
