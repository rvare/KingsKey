import java.util.*;
import java.io.*;

public class Backend {
    private ArrayList<DataContainer> userData;
    // private static boolean exists = false;
    // private static Backend instance = null;
    private static String dataFilePath = "../testers/TestData";
    private static Backend INSTANCE;
    private static Object[][] data;
    private static final String[] columnNames = { "Site", "Email", "Username", "password" };

    private Backend() {
        userData = new ArrayList<DataContainer>();
    }

    public static Backend createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Backend();
        }
        return INSTANCE;
    }

    /*
     * public static Backend createInstance() {
     * if (exists == false) {
     * instance = new Backend();
     * }
     * 
     * return instance;
     * }
     */

    public void gatherUserData() {
        ArrayList<String> decryptedLines;
        ArrayList<String> encryptedLines = new ArrayList<String>();
        String temp;

        try {
            BufferedReader bf = new BufferedReader(new FileReader(dataFilePath));
            while ((temp = bf.readLine()) != null) {
                encryptedLines.add(temp);
            }
            bf.close();
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
    }

    private ArrayList<String> decryptData(final String line) { // Implement decryption algorithm later
        byte[] rawData = line.getBytes(); // Gets bytes of the string

        for (int i = 0; i < rawData.length; i++) {
            rawData[i] -= 1; // Shift the ASCII values to the left by 1
        }

        String decryptedData = new String(rawData);
        String[] arrayOfLines = decryptedData.split("\n"); // Since everything is one big line, create an array

        ArrayList<String> decryptedLines = new ArrayList<String>(Arrays.asList(arrayOfLines));

        return decryptedLines;
    }

    private ArrayList<String> encryptData() { // Temporary stub
        return new ArrayList<String>();
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
            String path = "testers\\TestData";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
            int numberOfColumns = list.get(0).split(";").length;
            data = new Object[list.size()][numberOfColumns];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i).split(";");
            }
            bufferedReader.close();
            //return data;
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }

    public static Object [][] getDataObjects(){
        return data;
    }

    public static String [] getColumnNames(){
        return columnNames;
    }
} // End of Backend class