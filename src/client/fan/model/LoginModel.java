package client.fan.model;

import shared.DataPB;

/**
 * The LoginModel provides methods for validating user credentials, encrypting passwords,
 * and handling user account login.
 */
public class LoginModel {

    /**
     * Method to handle validating whether user login attempt is successful
     * @param username : String
     * @param password : String
     * @return userExists
     */
    public boolean validateUser(String username, String password) {
        return DataPB.validateUser(username, password);
    }
}
