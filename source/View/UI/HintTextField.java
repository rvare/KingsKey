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
} // End of HintTextField class