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

public class Backend {
    private ArrayList<DataContainer> userData;
    private static String dataFilePath = "../testers/EncryptedData2";
    private static Backend instance;
    private static Object[][] data;
    private static final String[] columnNames = { "Site", "Email", "Username", "password" };
    
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
        ArrayList<String> encryptedLines = new ArrayList<String>();
        String temp;

        try {
            decryptedLines = decryptData("password", false);

            // Initialize 2D array to hold data for JTable
            int numberOfColumns = decryptedLines.get(0).split(";").length;
            data = new Object[decryptedLines.size()][numberOfColumns];
            
            // Put data into 2D array
            for (int i = 0; i < decryptedLines.size(); i++) {
                data[i] = decryptedLines.get(i).split(";");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // decryptedLines = decryptData(encryptedLines);
        decryptedLines = encryptedLines;
        String[] parsedLine;

        for (String line : decryptedLines) {
            parsedLine = parseLine(line);
            userData.add(new DataContainer(parsedLine));
        }
    } // End of gatherUserData

    private Cipher makeCipher(String password, boolean encryptMode) {
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
    }

    private ArrayList<String> decryptData(String password, boolean mode) { // Implement decryption algorithm later
        try {
            byte[] encryptedData;
            byte[] decryptedData;
            File theFile = new File("../testers/out");
            FileInputStream encryptedFile = new FileInputStream(theFile);

            Cipher cipher = makeCipher(password, false);

            encryptedData = new byte[(int)theFile.length()];
            encryptedFile.read(encryptedData);
            encryptedFile.close();

            decryptedData = cipher.doFinal(encryptedData);
            
            String text = new String(decryptedData);
            String[] textLines = text.split("\n");
            
            return new ArrayList<String>(Arrays.asList(textLines));
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    } // End of decryptData

    private void encryptData(String password, boolean mode) { // Temporary stub
        try {
        byte[] decryptedData;
        byte[] encryptedData;
        File outFile = new File("../testers/out");
        Cipher cipher = makeCipher(password, true);
        FileOutputStream outStream = new FileOutputStream(outFile);

        String line;
        String dataLines = "";
        for (int i = 0; i < data.length; i++) {
                line = String.format("%s;%s;%s;%s\n", (String)data[i][0], (String)data[i][1], (String)data[i][2], (String)data[i][3]);
                dataLines += line;
        }

        decryptedData = dataLines.getBytes();
        encryptedData = cipher.doFinal(decryptedData);
        outStream.write(encryptedData);
        outStream.close();
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

    private String[] parseLine(String unparsedLine) {
        return unparsedLine.split(";");
    }

    public DataContainer sendData(int index) {
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
        } catch (Exception e) {
            e.printStackTrace();
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
        Backend b = Backend.getInstance();
        try {
            // You can replace this with "../testers/TestData"

            /*
                We need to rewrite a good portion of stuff to do this properly and by design.
                It would make it a lot less work if we did.
            */

            // Encryption stuff

            // Writing to file
            /*
            for (DataContainer record : userData) {
                bufferedWriter.write(record.toString());
            }
            */
           File f = new File(dataFilePath);
        String line;
        String encryptedLine = "";
        for (int i = 0; i < data.length; i++) {
                line = String.format("%s;%s;%s;%s\n", (String)data[i][0], (String)data[i][1], (String)data[i][2], (String)data[i][3]);
                encryptedLine += line;
        }

        b.encryptData("password", true);

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
} // End of Backend class