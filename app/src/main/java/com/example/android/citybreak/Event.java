package com.example.android.citybreak;

import android.icu.util.DateInterval;

import java.util.Date;

/**
 * Created by Levy on 12.04.2018.
 */

public class Event extends Place {
    private Date eventStartDate;
    private Date eventEndDate;

    /**
     * Constructor for the Events object
     * @param name
     * @param description
     * @param placeImageId
     * @param contactInfo
     * @param eventStartDate
     * @param eventEndDate
     */
    public Event(String name, String description, int placeImageId, Contact contactInfo, Date eventStartDate, Date eventEndDate) {
        super(name, description, placeImageId, contactInfo);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public Date getEventStartdate() {
        return eventStartDate;
    }

    public Date getEventEnddate() {
        return eventEndDate;
    }
}
