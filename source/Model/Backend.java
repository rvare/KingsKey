package Model;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Backend {
    private ArrayList<DataContainer> userData;
    private static String dataFilePath = "../testers/EncryptedTestData";
    private static Backend instance;
    private static Object[][] data;
    private static final String[] columnNames = { "Site", "Email", "Username", "password" };
    

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
            BufferedReader bf = new BufferedReader(new FileReader(dataFilePath));
            String encryptedLine = bf.readLine(); // Temporary statement; shouldn't be like this, but whatever.
            bf.close();

            decryptedLines = decryptData(encryptedLine);

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

    private ArrayList<String> decryptData(final String line) { // Implement decryption algorithm later
        byte[] rawData = line.getBytes(); // Gets bytes of the string

        for (int i = 0; i < rawData.length; i++) {
            rawData[i] -= 1; // Shift the ASCII values to the left by 1
        }

        String decryptedData = new String(rawData);
        String[] arrayOfLines = decryptedData.split("\n"); // Since everything is one big line, create an array

        ArrayList<String> decryptedLines = new ArrayList<String>(Arrays.asList(arrayOfLines));

        return decryptedLines;
    } // End of decryptData

    private ArrayList<String> encryptData() { // Temporary stub
        return new ArrayList<String>();
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
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("../testers/OutTestData.txt"));

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

        String line;
        String encryptedLine;
        for (int i = 0; i < data.length; i++) {
                line = String.format("%s;%s;%s;%s\n", (String)data[i][0], (String)data[i][1], (String)data[i][2], (String)data[i][3]);
                encryptedLine = encryptLine(line);
                bufferedWriter.write(encryptedLine);
        }

            bufferedWriter.close();
            System.out.println("Bla");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
} // End of Backend class