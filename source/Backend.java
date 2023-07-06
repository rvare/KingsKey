import java.util.*;
import java.io.*;

public class Backend {
    private ArrayList<DataContainer> userData;
    private static boolean exists = false;
    private static Backend instance = null;
    private static String dataFilePath = "../testers/TestData";

    private Backend() {
        userData = new ArrayList<DataContainer>();
    }

    public static Backend createInstance() {
        if (exists == false) {
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
            while ((temp = bf.readLine()) != null) {
                encryptedLines.add(temp);
            }
            bf.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
        decryptedLines = decryptData(encryptedLines);
        String[] parsedLine;
        
        for (String line : decryptedLines) {
            parsedLine = parseLine(line);
            userData.add(new DataContainer(parsedLine));
        }
    }

    private ArrayList<String> decryptData(ArrayList<String> data) { // Implement decryption algorithm later
        return data;
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
} // End of Backend class