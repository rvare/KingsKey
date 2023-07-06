import java.util.Calendar;
public class PasswordTest {
    public static void main(String[] args) {
        System.out.println(PasswordTest.generatePassword());
    }
    public static String generatePassword() {
        Calendar cal = Calendar.getInstance();
        long dateInMillis = cal.getTimeInMillis();
        
        long randomNum = (long)(Math.pow(10,17) * Math.random());
        long randomPassword = randomNum * dateInMillis * ((long)cal.hashCode() >>> 2);
        
        return Long.toHexString(randomPassword);
    }
}