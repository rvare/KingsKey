import java.util.*;
import java.io.*;

public class SampleBackend {
   ArrayList<String> data;
   private static boolean exists = false;
   private static SampleBackend instance = null;
   private static String DataFilePath = "./TestData";
   
   public static void main(String[] args) {
      SampleBackend sb = SampleBackend.CreateInstance();
      sb.GatherUserData();
   }
   
   private SampleBackend() {
      data = new ArrayList<String>();
      
      GatherUserData();
      
      PrintUserData(); // Ideally, this would've been a method that transfer data to the gui class
      
      EncryptData(); // We would need to find some way of handling this when the user exits the program
   }
   
   public static SampleBackend CreateInstance() {
      if (exists == false) {
         instance = new SampleBackend();
      }
      
      return instance;
   }
   
   private void GatherUserData() {
      try {
         BufferedReader bf = new BufferedReader(new FileReader(DataFilePath));
         String line = null;
         String ParsedLine = null;
         
         while ((line = bf.readLine()) != null) {
            line = DecryptLine(line);
            ParsedLine = ParseString(line);
            data.add(ParsedLine);
         }
         
         bf.close();
      }
      catch(Exception ex) {
         ex.printStackTrace();
      }
   }
   
   private String DecryptLine(String line) {
      // Imagine code here that decrypts the data
      return line;
   }
   
   private void EncrypteData() {
      // Imagine code here the encrypts the user's data
   }
   
   private String ParseString(String line) {
      String[] parse = line.split(";");
      return String.format("%s | %s | %s | %s", parse[0], parse[1], parse[2], parse[3]);
   }
   
   public void PrintUserData() {
      for (String line : data) {
         System.out.println(line);
      }
   }
} // end of class