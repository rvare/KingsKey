package View.UI;

import javax.swing.*;
import java.awt.*;

public class ErrorMessageWindow {
    public static void makeMessage(final String msg) {
        JFrame errorFrame = new JFrame() ;
        JOptionPane.showMessageDialog(errorFrame, msg, "Error", JOptionPane.ERROR_MESSAGE);
        errorFrame.dispose();
    }
}