package client.fan.model.application_pages;

import client.fan.view.application_pages.BookingView;
import shared.res.Idol;

import java.util.ArrayList;
import java.util.List;

/**
 * The BookingModel provides methods for viewing idol information and handling the booking of a session.
 */
public class BookingModel {
    /**
     * Holds the idol details.
     */
    private List<String> idolDetails;
    /**
     * Constructs a BookingModel with a specified idol.
     * @param idol The specified idol.
     */
    public BookingModel(Idol idol) {
        idolDetails = new ArrayList<>();
        idolDetails.add(idol.getProfilePictureAddress());
        idolDetails.add(idol.getIdolName());
        idolDetails.add(idol.getIdolType());
        idolDetails.add(idol.getFbAccount());
        idolDetails.add(idol.getIgAccount());
        idolDetails.add(idol.getxAccount());
        idolDetails.add(idol.getQuote());
        idolDetails.add(idol.getBio());
    }

    /**
     * Retrieves the current List of idolDetails.
     * @return The current idolDetails.
     */
    public List<String> getIdolDetails() {
        return idolDetails;
    }
}
