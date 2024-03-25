package shared;

public class User {

    // Primary key of user
    private int userID;

    // Username of user
    private String username;

    //Email of user
    private String email;

    // Password of user
    private String password;

    // GCashNumber associated with user
    private String GCashNumber;

    // Location of profile picture of user
    private String ppAddress;

    /**
     * Default constructor
     * @param userID
     * @param username
     * @param email
     * @param password
     * @param GCashNumber
     * @param ppAddress
     */
    public User(int userID, String username, String email, String password, String GCashNumber, String ppAddress) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.GCashNumber = GCashNumber;
        this.ppAddress = ppAddress;
    }

    /**
     * Method to get userID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Method to set userID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Method to get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set the password of user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get gcashNumber
     * @return
     */
    public String getGCashNumber() {
        return GCashNumber;
    }

    /**
     * Method to setGCashNumber
     * @param GCashNumber
     */
    public void setGCashNumber(String GCashNumber) {
        this.GCashNumber = GCashNumber;
    }

    /**
     * Method to get ppaddress
     * @return
     */
    public String getPpAddress() {
        return ppAddress;
    }

    /**
     * Method to set ppAddress
     * @param ppAddress
     */
    public void setPpAddress(String ppAddress) {
        this.ppAddress = ppAddress;
    }
}
