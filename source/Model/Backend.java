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
    private static String dataFilePath = "../UserDataFile";
    private static Backend instance;
    private static Object[][] data;
    private static final String[] columnNames = { "Site", "Email", "Username", "password" };
    private static String masterPassword;
    
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
        catch (IOException ioEx) {
            //ioEx.getMessage();
            ioEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
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
    } // End of makeCipher

    private ArrayList<String> decryptData(String password, boolean mode, File dataFile, FileInputStream fileStream) {
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

    private void encryptData(String password, boolean mode, File dataFile, FileOutputStream fos) {
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
            File dataFile = new File(dataFilePath);
            FileOutputStream fos = new FileOutputStream(dataFile);
            //b.encryptData("password", true, dataFile, fos);
            b.encryptData(masterPassword, true, dataFile, fos);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setMasterPassword(String pass) {
        masterPassword = pass;
    }
} // End of Backend class