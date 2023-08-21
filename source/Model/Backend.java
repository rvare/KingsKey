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

package Model;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;

public class Backend {
    private ArrayList<DataContainer> userData;
    private static String dataFilePath = "../UserDataFile";
    private static Backend instance;
    private static Object[][] data;
    private static final String[] columnNames = { "Site", "Email", "Username", "password" };
    private static String masterPassword;
    private static boolean unsavedChanges = false;
    
    private final byte[] salt = {
        (byte)0x43, (byte)0x76, (byte)0x95, (byte)0xc7,
        (byte)0x5b, (byte)0xd7, (byte)0x45, (byte)0x17 
    };

    private Backend() {
        userData = new ArrayList<DataContainer>();
    }

    public static Backend getInstance() {
        if (instance == null) {
            instance = new Backend();
        }
        return instance;
    }

    public void gatherUserData() {
        ArrayList<String> decryptedLines;

        try {
            File dataFile = new File(dataFilePath);
            FileInputStream fis = new FileInputStream(dataFile);

            //decryptedLines = decryptData("password", false, dataFile, fis);
            decryptedLines = decryptData(masterPassword, false, dataFile, fis);
            fis.close();

            // Initialize 2D array to hold data for JTable
            int numberOfColumns = decryptedLines.get(0).split(";").length;
            data = new Object[decryptedLines.size()][numberOfColumns];
            
            // Put data into 2D array
            for (int i = 0; i < decryptedLines.size(); i++) {
                data[i] = decryptedLines.get(i).split(";");
            }

            // decryptedLines = decryptData(encryptedLines);
            // decryptedLines = encryptedLines;
            String[] parsedLine;
            for (String line : decryptedLines) {
                parsedLine = parseLine(line);
                userData.add(new DataContainer(parsedLine));
            }
        }
        catch (FileNotFoundException fileNotFoundEx) {
            fileNotFoundEx.printStackTrace();
        }
        catch (IOException ioEx) {
            //ioEx.getMessage();
            ioEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // End of gatherUserData

    private Cipher makeCipher(final String password, final boolean encryptMode) {
        try {
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 32);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

            if (encryptMode) {
                cipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
            }
            else {
                cipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
            }

            return cipher;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    } // End of makeCipher

    private ArrayList<String> decryptData(final String password, final boolean mode, final File dataFile, final FileInputStream fileStream) {
        try {
            byte[] encryptedData;
            byte[] decryptedData;

            Cipher cipher = makeCipher(password, false);

            encryptedData = new byte[(int)dataFile.length()];
            fileStream.read(encryptedData);

            decryptedData = cipher.doFinal(encryptedData);
            
            String decryptedStringData = new String(decryptedData);
            String[] decryptedLines = decryptedStringData.split("\n");
            
            return new ArrayList<String>(Arrays.asList(decryptedLines));
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return new ArrayList<String>();
        }
    } // End of decryptData

    private void encryptData(final String password, final boolean mode, final File dataFile, final FileOutputStream fos) {
        try {
            byte[] decryptedData;
            byte[] encryptedData;

            Cipher cipher = makeCipher(password, true);

            String line;
            String dataLines = "";
            for (int i = 0; i < data.length; i++) {
                    line = String.format("%s;%s;%s;%s\n", (String)data[i][0], (String)data[i][1], (String)data[i][2], (String)data[i][3]);
                    dataLines += line;
            }

            decryptedData = dataLines.getBytes();
            encryptedData = cipher.doFinal(decryptedData);
            fos.write(encryptedData);
            fos.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String encryptLine(String line) {
        byte[] rawData = line.getBytes();
        for (int i = 0; i < rawData.length; i++) {
            rawData[i] += 1;
        }
        return new String(rawData);
    }

    private String[] parseLine(final String unparsedLine) {
        return unparsedLine.split(";");
    }

    public DataContainer sendData(final int index) {
        return userData.get(index);
    }

    public void printData() {
        for (DataContainer i : userData) {
            System.out.println(i.toString());
        }
    }

    public static void getData() {
        try {
            String path = "../testers/TestData"; // testers\\TestData -> May need to use for Windows
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String str;
            
            // Gather data from file
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
            bufferedReader.close();
            
            // Initialize 2D array to hold data for JTable
            int numberOfColumns = list.get(0).split(";").length;
            data = new Object[list.size()][numberOfColumns];
            
            // Put data into 2D array
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i).split(";");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // End of getData

    public static Object [][] getDataObjects() {
        return data;
    }

    public static String [] getColumnNames() {
        return columnNames;
    }
    
    //updates the data [] after the user inserts a new entry
    public static void updateDatabase(Object [][] newDataObjects) {
        data = newDataObjects;
    }

    public static void saveRecords() {
        Backend backend = Backend.getInstance();
        try {
            // You can replace this with "../testers/TestData"
            File dataFile = new File(dataFilePath);
            FileOutputStream fos = new FileOutputStream(dataFile);
            //b.encryptData("password", true, dataFile, fos);
            backend.encryptData(masterPassword, true, dataFile, fos);
            setBooleanUnsavedChanges(false);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    } // End of saveRecords

    public void setMasterPassword(final String pass) {
        masterPassword = pass;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public String hashPassword(final String password) {
        StringBuilder hashValueHex = new StringBuilder();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            hashValueHex = hashToBytes(hashBytes);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return hashValueHex.toString();
    }

    public StringBuilder hashToBytes(final byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
        String hex;
        for (Byte b : hashBytes) {
            hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString;
    }

    public static void setBooleanUnsavedChanges(final boolean status) {
        unsavedChanges = status;
    } 

    public static boolean unsavedChangeExists() {
        return unsavedChanges;
    }
} // End of Backend class