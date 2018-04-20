package com.example.android.citybreak;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Levy on 12.04.2018.
 */

public class Contact implements Parcelable {
    private String contactAddress;
    private String contactPhone;
    private String contactWeb;
    private Boolean contactPhoneSet = true;
    private Boolean contactWebSet = true;

    /**
     * Constructor for whole contact
     * @param address place address
     * @param phone place phone number
     * @param web place web address
     */
    public Contact (String address, String web, String phone) {
        contactAddress = address;
        contactWeb = web;
        contactPhone = phone;
    }

    /**
     * Constructor without web address
     * @param address place address
     * @param web place phone number
     */
    public Contact (String address, String web) {;
        contactAddress = address;
        contactWeb = web;
        contactPhoneSet = false;
    }

    /**
     * Constructor without web address and phone
     * @param address place address
     */
    public Contact (String address) {
        contactAddress = address;
        contactWebSet = false;
        contactPhoneSet = false;
    }

    // Get the address
    public String getPlaceAddress() {
        return contactAddress;
    }
    // Get the phone number
    public String getPlacePhone() {
        return contactPhone;
    }
    // Get the web address
    public String getPlaceWeb() {
        return contactWeb;
    }

    // Check if phone is set
    public Boolean hasPhoneSet() {
        return contactPhoneSet;
    }

    // Check if phone is set
    public Boolean hasWebSet() {
        return contactWebSet;
    }

    protected Contact(Parcel in) {
        contactAddress = in.readString();
        contactPhone = in.readString();
        contactWeb = in.readString();
        byte contactPhoneSetVal = in.readByte();
        contactPhoneSet = contactPhoneSetVal == 0x02 ? null : contactPhoneSetVal != 0x00;
        byte contactWebSetVal = in.readByte();
        contactWebSet = contactWebSetVal == 0x02 ? null : contactWebSetVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactAddress);
        dest.writeString(contactPhone);
        dest.writeString(contactWeb);
        if (contactPhoneSet == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (contactPhoneSet ? 0x01 : 0x00));
        }
        if (contactWebSet == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (contactWebSet ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
