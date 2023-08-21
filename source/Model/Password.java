package Model;

import Model.Trie.*;

import java.util.*;
import java.io.*;
import java.lang.instrument.Instrumentation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;

public class Password {

    private static Trie_Node dictionary = new Trie_Node(); 
    //private static HashSet<String> dictionary = new HashSet<>();
    private static double characterSet = 0;
    
    public static void main(String [] args) throws IOException{
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory();
        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
        // Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        loadPasswordCheckingResources();

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
        System.out.println("Searching Results Found = :"+dictionary.search("the"));
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

    public static void loadPasswordCheckingResources() {
        String filepath;
        long start = System.currentTimeMillis();
        try{
            filepath = "../source/Dictionaries/passwords.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load passwords.txt");
        }

        try{
            filepath = "../source/Dictionaries/eff_large.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load eff_large.txt");
        }     
        
        try{
            filepath = "../source/Dictionaries/english.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load english.txt");
        }

        try{
            filepath = "../source/Dictionaries/female-names.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load female-names.txt");
        }

        try{
            filepath = "../source/Dictionaries/male-names.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load male-names.txt");
        }

        try{
            filepath = "../source/Dictionaries/surnames.txt";
            loadDictionary(filepath);
        }
        catch(IOException e){
            System.out.println("Failed to load surnames.txt");
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start; 
        System.out.println("Loading Resources Time Elapsed: "+ timeElapsed +" ms");
    }
    
    private static void loadDictionary(String filepath) throws IOException {
        //String filepath = "source\\Dictionaries\\passwords.txt";
        //String filepath = "source/Dictionaries/passwords.txt";
        FileReader fileReader = new FileReader(filepath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            dictionary.insert(line);
            //dictionary.add(line);
        }

        bufferedReader.close();
    }
    /* 
    public static String generatePassword() {
        Calendar cal = Calendar.getInstance();
        long dateInMillis = cal.getTimeInMillis(); // Current day in milliseconds will give better randomness
        
        long randomNum = (long)(Math.pow(10,17) * Math.random());
        long randomPassword = randomNum * dateInMillis * ((long)cal.hashCode() >>> 2); // Use the hash code othe Calendar object for add randomness
        
        return Long.toHexString(randomPassword);
    }*/
    public static String generatePassword() {
        return generatePassword(16);
    }

    public static String generatePassword(int length) {
        //minimum length of 6
        if (length < 16) {
            length = 16;
        }

        final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();
        final char[] symbols = "^$?!@#%&()".toCharArray();
        final char[] allChars = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&()".toCharArray();

        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder(); 

        for (int i = 0; i < length-4; i++) {
            password.append(allChars[random.nextInt(allChars.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
        password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
        password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
        password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
        password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);

        return password.toString();
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
            characterSet += 26;
        }
        if(password.matches("(?=.*[A-Z]).*")) {  //check for uppercase
            score++;
            characterSet += 26;
        }
        if(password.matches("(?=.*[0-9]).*")) {  //check for numbers
            score++;
            characterSet += 10;
        }
        if(password.matches("(?=.*[!@#$%^&*()]).*")) {  //check for special characters
            score++;
            characterSet += 10;
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
        //double characterSet = 95.0;
        double numberOfCombinations = (Math.pow(characterSet, passwordLength));
        double entropy = (Math.log(numberOfCombinations) / Math.log(base));
        BigDecimal roundedEntropy = new BigDecimal(Double.toString(entropy));
        roundedEntropy = roundedEntropy.setScale(2, RoundingMode.HALF_UP);
        
        //reset characterSet
        characterSet = 0;
        return roundedEntropy.doubleValue();
    }

    public static String isCommonPassword(String password) {
        if(dictionary.search(password)){
            return "Yes";
        }
        else{
            return "No";
        }
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