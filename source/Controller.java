import View.Listeners.*;
import View.UI.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import java.awt.Insets;

import Model.*;
import Model.Trie.*;

public class Controller {
    private static String defaultTheme = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    // May contain certain global constants needed.
    // Have constant that represents data file directory/path
    public static void main(String[] args) {

        initializeTheme(defaultTheme);
        //setDarkNimbusTheme();

        LoginWindow lw = new LoginWindow();
        lw.showLogin();

        //Backend.getData();
        /*Backend backend = Backend.getInstance();
        backend.gatherUserData();
        UI gui = new UI();
        gui.createUI(); */

        // find out what themes are available
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }
        
        /*
            Declare backend as Backend
            Declare applicationUI as UI
            TRY // Will be used to handle errors and handle them appropriately
                Call Backend.createInstance() assign to backend
                Pass file path constant to backend
                    Will decrypt data file and intialize backend
                Initialize applicationUI and pass backend to it
                WHILE status == active
                    // Might use a switch statement instead with special constants
                    // Usually faster to use switch statement
                    IF user adds new record, signal from UI
                        Pass new data to backend
                    ELSE IF user selects to change record, signal from UI
                        Update data in backend
                        Update applicationUI
                    ELSE IF user deletes a record, signal from UI
                        Get new password from application UI
                        Update data in backend with new password
                        Update applicationUI
                    ELSE IF user changes theme, signal from UI
                        Update applicationUI with appropriate arguments
                    ELSE IF user generates new password for record, signal from UI
                        Get new password from applicationUI
                        Update data in backend with new password
                        Update applicationUI
                    END IF-ELSE
                END WHILE
            CATCH
                Error handling
            END TRY-CATCH
            Clean up operations happen here when user is done
        */
    }

    private static void initializeTheme(String theme) {
        try {
            UIManager.setLookAndFeel(theme);
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
    }

    private static void setDarkNimbusTheme (){
        // Dark LAF
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            UIManager.put("nimbusBase", Color.decode("#4a0363")); //lighter purple
            UIManager.put("nimbusBlueGrey", Color.decode("#4a0363")); //dark purple
            UIManager.put("control", Color.BLACK);
            UIManager.put("Menu[Enabled].textForeground", Color.GREEN);

            //UIManager.getLookAndFeelDefaults().put("MenuItem.contentMargins", new Insets(0,0,0,0));
            UIManager.put("MenuItem.opaque", true);
            UIManager.getLookAndFeelDefaults().put("MenuItem[Enabled].textForeground", Color.GREEN);

            UIManager.put("text", Color.GREEN);
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", Color.YELLOW);
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            //UIManager.put("MenuItem[Enabled].textForeground", Color.RED);
            
            /*UIManager.put("Menu[Disabled].textForeground", Color.RED);
            UIManager.put("MenuBar.disabledText", Color.decode("#8a27af"));
            UIManager.put("Table.dropLineColor", Color.BLACK);
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusGreen", new Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));*/
            
            //SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Nimbus: Unsupported Look and feel!");
        }
    }
} // End of Controller class

/*
NOTES
=====

- There should be some form of data validation for some of the operations
- Even though the psuedo code uses an if statement, we could use switch instead
    - Switch statements are faster
        - Doesn't need to check each if-else
    - Can make unique values that represent signals
- Will need to research on the different exceptions to handle them appropriately
    - You can have more than one catch, which will make it easy to handle certain errors
- 
*/