import java.util.*;
import java.io.*;

public class Password {

    static Trie_Node dictionary = new Trie_Node();
    
    public static void main(String [] args) throws IOException{
        loadDictionary();
        String password = "hockey";
        String password2 = "football";
        
        //int score = calculatePasswordStrength(password);
        String generatedPassword = generatePassword();
        System.out.println("The generated password is: "+generatedPassword);
        System.out.println("Searching Results Found = :"+dictionary.search(password));
        System.out.println("Searching Results Found = :"+dictionary.search(password2));
        
        System.out.println("Password Generation");
        System.out.println(Password.generatePassword());
        System.out.println("Checking password strength of:" +password);
        System.out.println(checkPasswordStrength(password));
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
        Calendar cal = Calendar.getInstance();
        long dateInMillis = cal.getTimeInMillis(); // Current day in milliseconds will give better randomness
        
        long randomNum = (long)(Math.pow(10,17) * Math.random());
        long randomPassword = randomNum * dateInMillis * ((long)cal.hashCode() >>> 2); // Use the hash code othe Calendar object for add randomness
        
        return Long.toHexString(randomPassword);
    }

    public static int calculateBruteForceCrackingTime() {
        return 0;
    }

    public static void printResults() {

    }

    public static String checkPasswordStrength(String password) {
        int score = 0;
        int length = password.length();
        StringBuilder result = new StringBuilder("Password Strength: ");
        //checking length
        if(length >= 8 && length <=10) {
            score += 1;
        }
        else if(length >=11 && length <=15) {
            score += 2;
        }
        else if(length >=16) {
            score += 3;
        }

        //check for characteristics
        if(password.matches("(?=.*[a-z]).*")) {  //check for lowercase
            score++;
        }
        if(password.matches("(?=.*[A-Z]).*")) {  //check for uppercase
            score++;
        }
        if(password.matches("(?=.*[0-9]).*")) {  //check for numbers
            score++;
        }
        if(password.matches("(?=.*[!@#$%^&*]).*")) {  //check for special characters
            score++;
        }

        switch(score) {
            case 0: 
                result.append("N/A. You left the field blank.");
                break;
            case 1: 
            case 2: 
            case 3: 
                result.append("Weak");
                break;
            case 4:
            case 5:
                result.append("Moderate");
                break;
            case 6:
            case 7:
                result.append("Strong");
                break;
            default: 
                result.append("Error. Scoring out of specified ranges.");
        }
        return result.toString();
    }
}
