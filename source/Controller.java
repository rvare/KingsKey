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

import View.Listeners.*;
import View.UI.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Color;
import java.awt.Insets;
import java.io.*;
import java.util.*;

import Model.*;
import Model.Trie.*;

public class Controller {
    private static String defaultTheme = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    // May contain certain global constants needed.
    // Have constant that represents data file directory/path
    public static void main(String[] args) {
        File hashToken = new File("../hashToken");
        Backend backend = Backend.getInstance();

        initializeTheme(defaultTheme);
        //setDarkNimbusTheme();

        if (!hashToken.exists() && !hashToken.isFile()) {
            SetUpWindow setUp = new SetUpWindow();
            setUp.firstTimeSetup();
        }

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.showLogin();

        //Backend.getData();
        if (loginWindow.getPlaintextPassword() != null) {
            backend.setMasterPassword(loginWindow.getPlaintextPassword());
            backend.gatherUserData();
            UI gui = new UI();
            gui.createUI();
            Password.loadPasswordCheckingResources();
        } 
        else {
            System.exit(0);
        }

/*
        // find out what themes are available
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }
*/
    } // End of main

    private static void initializeTheme(String theme) {
        try {
            UIManager.setLookAndFeel(theme);
        }
        catch (Exception ex) {
            System.out.println("Look and Feel not set");
        }
    }

    private static void setDarkNimbusTheme () {
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
        }
        catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Nimbus: Unsupported Look and feel!");
        }
    } // End of setDarkNimbusTheme
} // End of Controller class