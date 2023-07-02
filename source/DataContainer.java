public class DataContainer {
    private String websiteName;
    private String accountName;
    private String userEmail;
    private string userPassword;

    public DataContainer() { } // Default constructor; does nothing

    public DataContainer(Stirng[] data) {
        this.websiteName = data[0];
        this.accountName = data[1];
        this.userEmail = data[2];
        this.userPassword = data[3];
    }

    // Setters
    public setWebsiteName(Stirng websiteName) {
        this.websiteName = websiteName;
    }

    public setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Sting getUserPassword() {
        return this.userPassword;
    }
}