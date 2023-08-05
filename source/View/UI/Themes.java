package View.UI;

import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Color;
import java.util.Map;

public class Themes {
    //UIDefaults defaults = UIManager.getDefaults();
    //NimbusLookAndFeel darkNimbus;
    //UIManager.set
    public static void setDarkNimbusTheme (){
        // Dark LAF
        try {
            //NimbusLookAndFeel darkNimbus = new NimbusLookAndFeel();
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
            
            SwingUtilities.updateComponentTreeUI(UI.getMainFrame());
        } 
        catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Nimbus: Unsupported Look and feel!");
        }
    }
}
