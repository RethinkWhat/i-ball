package shared.login_register;

import shared.res.DataPB;

import java.sql.SQLException;

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
    public Object validateUser(String username, String password){
        try {
            return DataPB.validateUser(username, password);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
