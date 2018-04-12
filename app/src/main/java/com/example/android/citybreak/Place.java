package com.example.android.citybreak;

import android.location.Address;

/**
 * Created by Levy on 12.04.2018.
 */

public class Place {
    String placeName;
    String placeDescription;
    int placeImageId;
    Contact placeContactInfo;

    /**
     * Constructor for Place object
     * @param name name of the place
     * @param description description of the place
     * @param placeImageId image resource id of the image from the place
     * @param contactInfo contact data
     */
    public Place (String name, String description, int placeImageId, Contact contactInfo ) {
        placeName = name;
        placeDescription = description;
        placeContactInfo = contactInfo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public int getPlaceImageId() {
        return placeImageId;
    }

    public Contact getPlaceContactInfo() {
        return placeContactInfo;
    }
}
