package com.example.android.citybreak;

import java.sql.Time;

/**
 * Created by Levy on 12.04.2018.
 */

public class Hours {
    private int openHour;
    private int openMinute;
    private int closingHour;
    private int closingMinute;

    /**
     * Constructor to create
     * @param openHour
     * @param openMinute
     * @param closingHour
     * @param closingMinute
     */
    public Hours(int openHour, int openMinute, int closingHour, int closingMinute) {
        this.openHour = openHour;
        this.openMinute = openMinute;
        this.closingHour = closingHour;
        this.closingMinute = closingMinute;
    }

    public String getHoursString () {
        return openHour+":"+openMinute+" - "+closingHour+":"+closingMinute;
    }
}
