package Model;

import Model.Trie.*;

import java.util.*;
import java.io.*;
import java.lang.instrument.Instrumentation;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Password {

    static Trie_Node dictionary = new Trie_Node(); // FIX: Is this suppose to be private?
    
    public static void main(String [] args) throws IOException{
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory();
        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
        // Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        loadDictionary();

        // Get amount of free memory within the heap in bytes. This size will increase
        // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();

        String password = "hockey";
        String password2 = "football";
        
        //int score = calculatePasswordStrength(password);
        String generatedPassword = generatePassword();
        System.out.println("The generated password is: "+generatedPassword);
        System.out.println("Searching Results Found = :"+dictionary.search(password));
        System.out.println("Searching Results Found = :"+dictionary.search(password2));
        System.out.println("Searching Results Found = :"+dictionary.search("#golden"));
        System.out.println("Searching Results Found = :"+dictionary.search("reckLe$s"));
        System.out.println("Searching Results Found = :" + dictionary.search("vlad&*"));
        
        System.out.println("\nMemory calculations in bytes");
        System.out.println("Total allocated memory: "+heapSize);
        System.out.println("Max memory including unallocated memory: "+heapMaxSize);
        System.out.println("After loading. Allocated memory that's unused: "+heapFreeSize);
        System.out.println("Used memory in bytes: "+(heapSize-heapFreeSize));
        //System.out.println("Size of the tree is: "+ObjectSizeFetcher.getObjectSize((Object)dictionary));

        System.out.println("\nPassword Generation");
        System.out.println(Password.generatePassword());
        System.out.println("Checking password strength of:" +password);
        System.out.println(checkPasswordStrength(password));
        System.out.println("Entropy calculation: ");
        System.out.println(Password.calculateEntropy(password));
    }
    
    private static void loadDictionary() throws IOException {
        //String filepath = "source\\Dictionaries\\passwords.txt";
        String filepath = "source\\Dictionaries\\example.txt";
        FileReader fileReader = new FileReader(filepath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            dictionary.insert(line);
        }

        bufferedReader.close();
    }

    public static String generatePassword() {
        Calendar cal = Calendar.getInstance();
        long dateInMillis = cal.getTimeInMillis(); // Current day in milliseconds will give better randomness
        
        long randomNum = (long)(Math.pow(10,17) * Math.random());
        long randomPassword = randomNum * dateInMillis * ((long)cal.hashCode() >>> 2); // Use the hash code othe Calendar object for add randomness
        
        return Long.toHexString(randomPassword);
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

    public static double calculateEntropy(String password) {
        //Formula is Entropy = log2(CharacterSet^Length of Password)
        double passwordLength = password.length();
        double base = 2.0;
        double characterSet = 95.0;
        double numberOfCombinations = (Math.pow(characterSet, passwordLength));
        double entropy = (Math.log(numberOfCombinations) / Math.log(base));
        BigDecimal roundedEntropy = new BigDecimal(Double.toString(entropy));
        roundedEntropy = roundedEntropy.setScale(2, RoundingMode.HALF_UP);
        return roundedEntropy.doubleValue();
    }
} // End of Password class

//class to check object size. doesn't work as is. have to run this separately on command line as jar file
/*class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}*/