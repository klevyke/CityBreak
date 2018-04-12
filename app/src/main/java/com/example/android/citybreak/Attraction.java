package com.example.android.citybreak;

import java.lang.reflect.Constructor;

/**
 * Created by Levy on 12.04.2018.
 */

public class Attraction extends Place {
    private String attractionStory;

    /**
     * Constructor for Attraction object
     * @param name name of the place
     * @param description description of the place
     * @param placeImageId image resource id of the image from the place
     * @param contactInfo Contact object with contact data
     * @param attractionStory more info about this object/place
     */
    public Attraction(String name, String description, int placeImageId, Contact contactInfo, String attractionStory) {
        super(name, description, placeImageId, contactInfo);
        this.attractionStory = attractionStory;
    }

    // Get the story of the attraction
    public String getAttractionStory() {
        return attractionStory;
    }
}