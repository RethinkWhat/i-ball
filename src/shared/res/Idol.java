package shared.res;

public class Idol {

    // Primary key for idol object
    private int idolID;

    //Idol username
    private String username;

    // Name of idol
    private String idolName;

    // The GCash Number of the idol
    private String gCashNumber;

    // The type of idol (gamer, singer, etc.)
    private String idolType;

    // Whether the idol is confirmed or pending
    private boolean idolStatus;

    // The rate the idol will charge for a voice call booking
    private double voiceCallRate;

    // The rate the idol will charge for a video call booking
    private double videoCallRate;

    // Idol's fb account link
    private String fbAccount;

    // Idol's x account link
    private String xAccount;

    // Idol's ig account link
    private String igAccount;

    // idol's bio
    private String bio;

    // idol's quote
    private String quote;

    // The location of idol's profile picture
    private String profilePictureAddress;

    // Idol password
    private String password;

    public Idol(int idolID, String username, String idolName, String gCashNumber, String idolType, boolean idolStatus, double voiceCallRate, double videoCallRate, String fbAccount, String xAccount, String igAccount, String bio, String quote, String profilePictureAddress, String password) {
        this.idolID = idolID;
        this.username = username;
        this.idolName = idolName;
        this.gCashNumber = gCashNumber;
        this.idolType = idolType;
        this.idolStatus = idolStatus;
        this.voiceCallRate = voiceCallRate;
        this.videoCallRate = videoCallRate;
        this.fbAccount = fbAccount;
        this.xAccount = xAccount;
        this.igAccount = igAccount;
        this.bio = bio;
        this.quote = quote;
        this.profilePictureAddress = profilePictureAddress;
        this.password = password;
    }

    /**
     * Getter for idol id
     * @return idolID
     */
    public int getIdolID() {
        return idolID;
    }

    /**
     * Setter for idol id
     * @param idolID
     */
    public void setIdolID(int idolID) {
        this.idolID = idolID;
    }

    /**
     * Method to get idol username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to get the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * method to get the name of the idol
     */
    public String getIdolName() {
        return idolName;
    }

    /**
     * method to set the name of the idol
     * @param idolName
     */
    public void setIdolName(String idolName) {
        this.idolName = idolName;
    }

    /**
     * Method to get the GCashNumber
     * @return GCashNumber
     */
    public String getGCashNumber() {
        return gCashNumber;
    }

    /**
     * Method to set the gcash number
     * @param gCashNumber
     */
    public void setGCashNumber(String gCashNumber) {
        this.gCashNumber = gCashNumber;
    }

    /**
     * Method to get the idolType
     * @return idolType
     */
    public String getIdolType() {
        return idolType;
    }

    /**
     * Method to set the idol type
     * @param idolType
     */
    public void setIdolType(String idolType) {
        this.idolType = idolType;
    }

    /**
     * Method to get idolStatus
     * @return idolStatus
     */
    public boolean isIdolStatus() {
        return idolStatus;
    }

    /**
     * Method to set the idol status
     * @param idolStatus
     */
    public void setIdolStatus(boolean idolStatus) {
        this.idolStatus = idolStatus;
    }

    /**
     * Method to get the voice call rate
     * @return voiceCallRate
     */
    public double getVoiceCallRate() {
        return voiceCallRate;
    }

    /**
     * Method to set the voice call rate
     * @param voiceCallRate
     */
    public void setVoiceCallRate(double voiceCallRate) {
        this.voiceCallRate = voiceCallRate;
    }

    /**
     * Method to get the VideoCallRate
     * @return
     */
    public double getVideoCallRate() {
        return videoCallRate;
    }

    /**
     * Method to set the video call rate
     * @param videoCallRate
     */
    public void setVideoCallRate(double videoCallRate) {
        this.videoCallRate = videoCallRate;
    }

    /**
     * Method to get the fb account of idol
     * @return
     */
    public String getFbAccount() {
        return fbAccount;
    }

    /**
     * Method to set the fb account of idol
     * @param fbAccount
     */
    public void setFbAccount(String fbAccount) {
        this.fbAccount = fbAccount;
    }

    /**
     * Method to get the x account of user
     * @return xAccount
     */
    public String getxAccount() {
        return xAccount;
    }

    /**
     * Method to set the xAccount of user
     * @param xAccount
     */
    public void setxAccount(String xAccount) {
        this.xAccount = xAccount;
    }

    /**
     * Method to get the igAccount of user
     * @return
     */
    public String getIgAccount() {
        return igAccount;
    }

    /**
     * Method to set the igAccount of idol
     * @param igAccount
     */
    public void setIgAccount(String igAccount) {
        this.igAccount = igAccount;
    }

    /**
     * Method to get the bio of idol
     * @return bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Method to set the bio of idol
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Method to get the profile picture address
     * @return
     */
    public String getProfilePictureAddress() {
        return profilePictureAddress;
    }

    /**
     * Method to set the profile picture address
     * @param profilePictureAddress
     */
    public void setProfilePictureAddress(String profilePictureAddress) {
        this.profilePictureAddress = profilePictureAddress;
    }

    /**
     * Method to get gcash number
     * @return gcashNumber
     */
    public String getgCashNumber() {
        return gCashNumber;
    }

    /**
     * Method to set gcash number
     * @param gCashNumber
     */
    public void setgCashNumber(String gCashNumber) {
        this.gCashNumber = gCashNumber;
    }

    /**
     * method to get quote
     * @return quote
     */
    public String getQuote() {
        return quote;
    }

    /**
     * method to set quote
     * @param quote
     */
    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return idolID + "," +
                username + "," +
                idolName + "," +
                gCashNumber + "," +
                idolType + "," +
                (idolStatus ? "Verified" : "Unverified") + "," +
                voiceCallRate + "," +
                videoCallRate + "," +
                fbAccount + "," +
                xAccount + "," +
                igAccount + "," +
                bio + "," +
                quote + "," +
                profilePictureAddress + "," +
                password;
    }
}
