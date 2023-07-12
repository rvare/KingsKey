public class DataContainer {
    private String websiteName;
    private String accountName;
    private String userEmail;
    private String userPassword;

    public DataContainer() { } // Default constructor; does nothing

    public DataContainer(String[] data) {
        this.websiteName = data[0];
        this.accountName = data[1];
        this.userEmail = data[2];
        this.userPassword = data[3];
    }

    // Setters
    public String setWebsiteName(String websiteName) {
        return this.websiteName = websiteName;
    }

    public String setAccountName(String accountName) {
        return this.accountName = accountName;
    }

    public String setUserEmail(String userEmail) {
        return this.userEmail = userEmail;
    }

    public String setUserPassword(String userPassword) {
        return this.userPassword = userPassword;
    }

    // Getters
    public String getWebsiteName() {
        return this.websiteName;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public String toString() { // For testing purposes
        return String.format("%s %s %s %s", websiteName, accountName, userEmail, userPassword);
    }
} // End of DataContainer class