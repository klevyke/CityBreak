package com.example.android.citybreak;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Levy on 12.04.2018.
 */

public class Hours implements Parcelable {
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

    public String getHoursString (Context context) {
        return context.getString(R.string.hours_pattern, String.format("%02d", openHour), String.format("%02d", openMinute), String.format("%02d",closingHour), String.format("%02d",closingMinute));
    }

    // Parcelable implementation generated on parcelablr.com

    protected Hours(Parcel in) {
        openHour = in.readInt();
        openMinute = in.readInt();
        closingHour = in.readInt();
        closingMinute = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(openHour);
        dest.writeInt(openMinute);
        dest.writeInt(closingHour);
        dest.writeInt(closingMinute);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Hours> CREATOR = new Parcelable.Creator<Hours>() {
        @Override
        public Hours createFromParcel(Parcel in) {
            return new Hours(in);
        }

        @Override
        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };
}
