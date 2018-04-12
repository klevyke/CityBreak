package com.example.android.citybreak;

/**
 * Created by Levy on 12.04.2018.
 */

public class Restaurants extends Place {
    private Hours openHours;
    private int restaurantRating;

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
