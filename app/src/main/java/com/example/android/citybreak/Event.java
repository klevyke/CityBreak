package com.example.android.citybreak;

import android.icu.util.DateInterval;

/**
 * Created by Levy on 12.04.2018.
 */

public class Event extends Place {
    DateInterval eventDate;

    /**
     * Constructor for the Events object
     * @param name
     * @param description
     * @param placeImageId
     * @param contactInfo
     * @param eventDate
     */
    public Event(String name, String description, int placeImageId, Contact contactInfo, DateInterval eventDate) {
        super(name, description, placeImageId, contactInfo);
        this.eventDate = eventDate;
    }

    // Get the event dat in string format
    public String getEventDate() {
        return eventDate.toString();
    }
}
