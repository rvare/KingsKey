package View.UI;

import javax.swing.JTextField;
import java.awt.event.*;

public class HintTextField extends JTextField implements FocusListener{
    private String hintString;

    public HintTextField (String text, int col) {
        super(text, col);
        hintString = text;
        this.addFocusListener(this);
    }

    public void focusGained(FocusEvent e) {
        if(this.getText().equals(hintString)) {
            this.setText("");
        }
    }

    public void focusLost(FocusEvent e) {
        if(this.getText().equals("")) {
            this.setText(hintString);
            // prevents the form from being processed in this state
            // as it will literally contain "Enter Password" for the password
        }
    }
}
