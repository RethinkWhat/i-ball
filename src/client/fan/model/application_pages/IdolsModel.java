package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdolsModel {

    /**
     * This method returns a List of idols with all of their details
     * @return
     * @throws SQLException
     */
    public List<Idol> getAllIdols() throws SQLException {
        ResultSet idolSet = DataPB.getAllIdols();

        List<Idol> idolList = new ArrayList<>();

        while (idolSet.next()) {
            idolList.add(new Idol(
                    idolSet.getInt("idolID"),
                    idolSet.getString("username"),
                    idolSet.getString("idolName"),
                    idolSet.getString("gCashNumber"),
                    idolSet.getString("idolType"),
                    idolSet.getString("idolStatus").equalsIgnoreCase("Verified") ? true : false,
                    idolSet.getDouble("voiceCallRate"),
                    idolSet.getDouble("videoCallRate"),
                    idolSet.getString("fbAccount"),
                    idolSet.getString("xAccount"),
                    idolSet.getString("igAccount"),
                    idolSet.getString("bio"),
                    idolSet.getString("quote"),
                    idolSet.getString("profilePictureAddress"),
                    idolSet.getString("password")
            ));
        }
        return idolList;
    }
    /**
     * This method searches for idols based on a search term
     * @param searchTerm The term to search for
     * @return A list of idols matching the search term
     * @throws SQLException If a database access error occurs
     */
    public List<Idol> idolSearch(String searchTerm) throws SQLException {
        ResultSet searchResults = DataPB.idolSearch(searchTerm);
        return parseResultSet(searchResults);
    }

    /**
     * This method filters idols based on their type.
     *
     * @param idolType The type of idols to filter
     * @return A list of idols matching the specified type
     * @throws SQLException If a database access error occurs
     */

    public List<Idol> filterIdolsByType(String idolType) throws SQLException {
        ResultSet filteredIdols = DataPB.filterIdolsByType(idolType);
        return parseResultSet(filteredIdols);
    }

    /**
     * Parses a ResultSet into a list of Idol objects
     * @param resultSet The ResultSet to parse
     * @return A list of Idol objects
     * @throws SQLException If a database access error occurs
     */
    private List<Idol> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Idol> idolList = new ArrayList<>();
        while (resultSet.next()) {
            idolList.add(new Idol(
                    resultSet.getInt("idolID"),
                    resultSet.getString("username"),
                    resultSet.getString("idolName"),
                    resultSet.getString("gCashNumber"),
                    resultSet.getString("idolType"),
                    resultSet.getString("idolStatus").equalsIgnoreCase("Verified"),
                    resultSet.getDouble("voiceCallRate"),
                    resultSet.getDouble("videoCallRate"),
                    resultSet.getString("fbAccount"),
                    resultSet.getString("xAccount"),
                    resultSet.getString("igAccount"),
                    resultSet.getString("bio"),
                    resultSet.getString("quote"),
                    resultSet.getString("profilePictureAddress"),
                    resultSet.getString("password")
            ));
        }
        return idolList;
    }


    /**
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) throws Exception{
        IdolsModel model = new IdolsModel();

    }
}
