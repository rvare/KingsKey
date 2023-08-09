package View.Listeners;

import View.UI.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//note: all comments can be deleted if we decide to clean up
public class DeleteRecordListener implements FocusListener{

    //private DeleteRowButton deleteRowButton;
    //private Boolean buttonExists = false;
    public void focusGained(FocusEvent e) {
        UI.getDeleteRowButton().setVisible(true);
        //UI.getFirstRowPanel().revalidate();
        //UI.getFirstRowPanel().repaint();
        /*if(!buttonExists) {
            deleteRowButton = new DeleteRowButton();
            deleteRowButton.setEnabled(true);
            buttonExists = true;
            UI.getFirstRowPanel().add(deleteRowButton);
            deleteRowButton.setVisible(true);
            //UI.getFirstRowPanel().revalidate();
            //UI.getFirstRowPanel().repaint();
            UI.getMainFrame().revalidate();
            UI.getMainFrame().repaint();
            deleteRowButton.setFocusable(true);
        }*/
    }

    public void focusLost(FocusEvent e) {
        UI.getDeleteRowButton().setVisible(false);
        //UI.getFirstRowPanel().revalidate();
        //UI.getFirstRowPanel().repaint();
        /*UI.getFirstRowPanel().remove(deleteRowButton);
        buttonExists = false;
        UI.getFirstRowPanel().revalidate();
        UI.getFirstRowPanel().repaint();*/
    }
}