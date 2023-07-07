import java.io.*;
import java.util.*;

public class DecryptDataTest {
    public static void main(String[] args) {
        String line = null;
        ArrayList<String> lines = new ArrayList<String>();
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader("./EncryptedTestData"));
            line = bf.readLine();
            bf.close();
        }
        catch(Exception e) { }
        
        byte[] b = line.getBytes();
        
        for (int i = 0; i < b.length; i++) {
            //System.out.println(i);
            //i -= 1;
            //System.out.println(i);
            b[i] -= 1;
        }
        
        String line2 = new String(b);
        
        System.out.println(line);
        System.out.println(line2);
        
        String[] line3 = line2.split("\n");
        for (String i : line3) {
            System.out.println(i);
        }
        
        String[] line4;
        for (String i : line3) {
            line4 = i.split(";");
            System.out.println(String.format("%s %s %s %s", line4[0], line4[1], line4[2], line4[3]));
        }
        
        ArrayList<String> arr = decrypt(line);
        System.out.println(arr);
    }
    
    public static ArrayList<String> decrypt(final String line) {
        byte[] rawData = line.getBytes(); // Gets bytes of the string
        
        for (int i = 0; i < rawData.length; i++) {
            rawData[i] -= 1; // Shift the ASCII values to the left by 1
        }
        
        String decryptedData = new String(rawData);
        String[] arrayOfLines = decryptedData.split("\n"); // Since everything is one big line, create an array
               
        ArrayList<String> decryptedLines = new ArrayList<String>(Arrays.asList(arrayOfLines));
        
        return decryptedLines;
    }
}