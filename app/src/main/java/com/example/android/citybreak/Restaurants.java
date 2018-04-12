package com.example.android.citybreak;

/**
 * Created by Levy on 12.04.2018.
 */

public class Restaurants extends Place {
    private Hours openHours;
    private int restaurantRating;

    /**
     * Constructor for Restaurant object
     * @param name name of the place
     * @param description description of the place
     * @param placeImageId image resource id of the image from the place
     * @param contactInfo Contact object with contact data
     * @param openHours Hours object containing the restaurants hours
     * @param rating Restaurants rating
     */
    public Restaurants(String name, String description, int placeImageId, Contact contactInfo, Hours openHours, int rating) {
        super(name, description, placeImageId, contactInfo);
        this.openHours = openHours;
        this.restaurantRating = rating;
    }

    // Get the restaurants hours
    public Hours getOpenHours() {
        return openHours;
    }

    // Get the restaurant rating
    public int getRestaurantRating() {
        return restaurantRating;
    }
}
