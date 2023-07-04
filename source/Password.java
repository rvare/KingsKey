import java.util.*;
import java.io.*;

public class Password {

    static Trie_Node dictionary = new Trie_Node();
    
    public static void main(String [] args) throws IOException{
        loadDictionary();
        String password = "hockey";
        String password2 = "football";
        
        int score = calculatePasswordStrength(password);
        String generatedPassword = generatePassword();
        System.out.println("The generated password is: "+generatedPassword);
        System.out.println("Searching Results Found = :"+dictionary.search(password));
        System.out.println("Searching Results Found = :"+dictionary.search(password2));

    }
    
    private static void loadDictionary() throws IOException {
        //note it has to use this type of pathing to work in VSCode
        FileReader fileReader = new FileReader("source\\Dictionaries\\example.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            dictionary.insert(line);
        }
        bufferedReader.close();
    }

    public static int calculatePasswordStrength(String password) {

        return 0;
    }

    public static String generatePassword() {
        String generatedPassword = "placeholder";
        
        for(int i = 0; i < 12; i++) {

        }

        return generatedPassword;
    }

    public static int calculateBruteForceCrackingTime() {

        return 0;
    }

    public static void printResults() {

    }
}
