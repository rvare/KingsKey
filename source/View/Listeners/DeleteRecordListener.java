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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DeleteRecordListener implements FocusListener{
    public void focusGained(FocusEvent e) {
        UI.getDeleteRowButton().setVisible(true);
    }

    public void focusLost(FocusEvent e) {
        UI.getDeleteRowButton().setVisible(false);
    }
}
