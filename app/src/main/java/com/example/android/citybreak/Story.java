package com.example.android.citybreak;

/**
 * Created by Levy on 12.04.2018.
 */

public class Story {
    String aboutCity;

    /**
     * Constructor
     * @param aboutCity the text about city;
     */
    public Story(String aboutCity) {
        this.aboutCity = aboutCity;
    }

    // Get the text about the city
    public String getAboutCity() {
        return aboutCity;
    }
}
