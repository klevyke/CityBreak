package com.example.android.citybreak;

/**
 * Created by Levy on 12.04.2018.
 */

public class Killtime extends Place {
    private Hours openHours;
    private String locationType;

    /**
     *
     * @param name name of the place
     * @param description description of the place
     * @param placeImageId image resource id of the image from the place
     * @param contactInfo Contact object with contact data
     * @param openHours Hours object containing the restaurants hours
     * @param locationType the type of the location
     */
    public Killtime(String name, String description, int placeImageId, Contact contactInfo, Hours openHours, String locationType) {
        super(name, description, placeImageId, contactInfo);
        this.openHours = openHours;
        this.locationType = locationType;
    }

    // Get the restaurants hours
    public Hours getOpenHours() {
        return openHours;
    }

    // Get the type of the location
    public String getLocationType() {
        return locationType;
    }
}
