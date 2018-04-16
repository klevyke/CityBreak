package com.example.android.citybreak;

/**
 * Created by Levy on 12.04.2018.
 */

public class Contact {
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
    public Contact (String address, String phone, String web) {
        contactAddress = address;
        contactPhone = phone;
        contactWeb = web;
    }

    /**
     * Constructor without web address
     * @param address place address
     * @param phone place phone number
     */
    public Contact (String address, String phone) {
        contactAddress = address;
        contactPhone = phone;
        contactWebSet = false;
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
    public String getPlaceTelephone() {
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


}
