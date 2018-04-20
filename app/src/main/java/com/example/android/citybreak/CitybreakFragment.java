package com.example.android.citybreak;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Superclass for the classes that implements Fragments and uses some custom common functions
 * Created by Levy on 19.04.2018.
 */

public class CitybreakFragment extends Fragment {
    public final static String EXTRA_IMAGE = "image";
    public final static String EXTRA_NAME = "name";
    public final static String EXTRA_DESCRIPTION = "description";
    public final static String EXTRA_EVENTSTARTDATE = "eventStartDate";
    public final static String EXTRA_EVENTENDDATE = "eventEndDate";
    public final static String EXTRA_TYPE = "type";
    public final static String EXTRA_CONTACT = "contact";
    public final static String EXTRA_HOURS = "hours";
    public final static String EXTRA_STORY = "story";
    public final static String EXTRA_RATING = "rating";

    /**
     * Function to update the details fragment on large displays for events
     * @param event
     */
    protected void updateDetailsFragment(Event event, DetailsFragment fragment) {

        // update the common texts
        updateCommonFragmentDetails(event.getPlaceName(), event.getPlaceDescription(),event.getPlaceImageId(),event.getPlaceContactInfo(), fragment);

        // Get the LinearLayout where additional information will be displayed
        LinearLayout additionalSection = fragment.getView().findViewById(R.id.additional);
        additionalSection.removeAllViewsInLayout();

        // Display the event dates
        Date eventStartDate = event.getEventStartDate();
        Date eventEndDate = event.getEventEndDate();

        // Check if the date variables are set
        if ( eventStartDate != null && eventEndDate!= null) {
            // Check if the start date is equal with end date, display one date if soo
            if (eventStartDate == eventEndDate) {
                String text = formatedDate(eventStartDate);
                addInTextView(additionalSection, getString(R.string.label_date), text);
            } else {
                String text = getString(R.string.date_interval, formatedDate(eventStartDate), formatedDate(eventEndDate));
                addInTextView(additionalSection, getString(R.string.label_date), text);
            }
        }
    }

    /**
     * Function to update the details fragment on large displays for restaurants
     * @param restaurant
     */
    protected void updateDetailsFragment(Restaurant restaurant, DetailsFragment fragment) {

        // update the common texts
        updateCommonFragmentDetails(restaurant.getPlaceName(), restaurant.getPlaceDescription(),restaurant.getPlaceImageId(),restaurant.getPlaceContactInfo(), fragment);

        // Get the LinearLayout where additional information will be displayed
        LinearLayout additionalSection = fragment.getView().findViewById(R.id.additional);
        additionalSection.removeAllViewsInLayout();

        // Set the hours
        Hours hours = restaurant.getOpenHours();
        addInTextView(additionalSection, getString(R.string.label_hours), hours.getHoursString());

        addInTextView(additionalSection, getString(R.string.label_rating), String.valueOf(restaurant.getRestaurantRating()));

    }

    /**
     * Function to update the details fragment on large displays for attractions
     * @param attraction
     */
    protected void updateDetailsFragment(final Attraction attraction, DetailsFragment fragment) {

        // update the common texts
        updateCommonFragmentDetails(attraction.getPlaceName(), attraction.getPlaceDescription(),attraction.getPlaceImageId(),attraction.getPlaceContactInfo(), fragment);

        // Get the LinearLayout where additional information will be displayed
        LinearLayout additionalSection = fragment.getView().findViewById(R.id.additional);
        additionalSection.removeAllViewsInLayout();

        // create a TextView
        TextView linkView = new TextView(getContext());
        linkView.setText(getString(R.string.more_text));

        // set an OnClickListener on this TextView
        linkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initiate an intent to view the sites
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(attraction.getAttractionStory()));
                startActivity(i);
            }
        });
        additionalSection.addView(linkView);

    }

    /**
     * Function to update the details fragment on large displays for killtimes
     * @param killtime
     */
    protected void updateDetailsFragment(Killtime killtime, DetailsFragment fragment) {

        // update the common texts
        updateCommonFragmentDetails(killtime.getPlaceName(), killtime.getPlaceDescription(),killtime.getPlaceImageId(),killtime.getPlaceContactInfo(), fragment);

        // Get the LinearLayout where additional information will be displayed
        LinearLayout additionalSection = fragment.getView().findViewById(R.id.additional);
        additionalSection.removeAllViewsInLayout();

        // Set the hours
        Hours hours = killtime.getOpenHours();
        addInTextView(additionalSection, getString(R.string.label_hours), hours.getHoursString());
    }

    /**
     * Update common TextViews and add the set contact details
     * @param name
     * @param description
     * @param image
     * @param contact
     * @param fragment
     */
    private void updateCommonFragmentDetails (String name, String description, int image, Contact contact, DetailsFragment fragment) {
        // Set the image resource
        ImageView imageView = fragment.getView().findViewById(R.id.image);
        imageView.setImageResource(image);
        imageView.setVisibility(View.VISIBLE);

        // Set the name
        TextView nameTextView = (TextView) fragment.getView().findViewById(R.id.name);
        nameTextView.setText(name);

        // Set the description
        TextView descriptionTextView = fragment.getView().findViewById(R.id.description);
        descriptionTextView.setText(description);

        // Get the LinearLayout where contact information will be displayed
        LinearLayout contactSection = fragment.getView().findViewById(R.id.contact);
        //  Remove all child views
        contactSection.removeAllViewsInLayout();

        // Show the section title
        TextView title =  fragment.getView().findViewById(R.id.contact_header);
        title.setVisibility(View.VISIBLE);

        // Add the address with the label (This is set in every category)
        addInTextView(contactSection, getString(R.string.label_address),contact.getPlaceAddress());

        // Add the phone number with the label if set
        if (contact.hasPhoneSet()) {
            addInTextView(contactSection, getString(R.string.label_phone),contact.getPlacePhone());
        }

        // Add the web address with the label if set
        if (contact.hasWebSet()) {
            addInTextView(contactSection, getString(R.string.label_web),contact.getPlaceWeb());
        }
    }

    /**
     * Function to start an activity for Killtime objects
     * @param killtime
     */
    protected void viewInDetailsActivity (Killtime killtime) {
        // Create an intent with common extras
        Intent intent = createIntentWithCommonExtras(killtime.getPlaceName(), killtime.getPlaceDescription(), killtime.getPlaceImageId(), killtime.getPlaceContactInfo());
        // Put specific extras
        intent.putExtra(EXTRA_HOURS, killtime.getOpenHours());
        intent.putExtra(EXTRA_TYPE, killtime.getLocationType());
        // Start the activity
        startActivity(intent);
    }

    /**
     * Function to start an activity for Event objects
     * @param event
     */
    protected void viewInDetailsActivity (Event event) {
        // Create an intent with common extras
        Intent intent = createIntentWithCommonExtras(event.getPlaceName(), event.getPlaceDescription(), event.getPlaceImageId(), event.getPlaceContactInfo());
        // Put specific extras
        intent.putExtra(EXTRA_EVENTSTARTDATE, event.getEventStartDate());
        intent.putExtra(EXTRA_EVENTENDDATE, event.getEventEndDate());
        // Start the activity
        startActivity(intent);
    }

    /**
     * Function to start an activity for Restaurant objects
     * @param restaurant
     */
    protected void viewInDetailsActivity (Restaurant restaurant) {
        // Create an intent with common extras
        Intent intent = createIntentWithCommonExtras(restaurant.getPlaceName(), restaurant.getPlaceDescription(), restaurant.getPlaceImageId(), restaurant.getPlaceContactInfo());
        // Put specific extras
        intent.putExtra(EXTRA_HOURS, restaurant.getOpenHours());
        intent.putExtra(EXTRA_RATING, restaurant.getRestaurantRating());
        // Start the activity
        startActivity(intent);
    }

    /**
     * Function to start an activity for Attraction objects
     * @param attraction
     */
    protected void viewInDetailsActivity (Attraction attraction) {
        // Create an intent with common extras
        Intent intent = createIntentWithCommonExtras(attraction.getPlaceName(), attraction.getPlaceDescription(), attraction.getPlaceImageId(), attraction.getPlaceContactInfo());
        // Put specific extras
        intent.putExtra(EXTRA_STORY, attraction.getAttractionStory());
        // Start the activity
        startActivity(intent);
    }

    /**
     * Helper function to set the common attributes as extras
     * @param name name of place
     * @param description short description
     * @param image image resource ID
     * @param contact Contact object with contact info
     * @return an intent with extras set
     */
    private Intent createIntentWithCommonExtras (String name, String description, int image, Contact contact) {
        Intent intent = new Intent(getActivity().getApplicationContext(),
                DetailsActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_IMAGE, image);
        intent.putExtra(EXTRA_CONTACT, contact);
        return intent;
    }

    /**
     * Check if it must be displayed  in split screen
     *
     * @param context
     */
    protected Boolean showInSplitScreen(View context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        if (dpWidth > 1000) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper function to create a Textview with label in a specified ViewGroup
     * @param parent
     * @param label
     * @param text
     */
    protected void addInTextView(ViewGroup parent, String label, String text) {
        TextView textView = new TextView(parent.getContext());
        textView.setText(getString(R.string.label_and_text, label, text));
        parent.addView(textView);
    }

    /**
     * Helper function to return a date as a formated String for display
     * source: https://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
     * @param date
     * @return as String
     */
    protected String formatedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_pattern));
        return dateFormat.format(date);
    }

    /**
     * Helper function to return a formated String as Date object
     * source: https://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
     * @param eventDate
     * @return
     */
    protected Date dateFromString (String eventDate) {
        Date mDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.date_pattern));
        try {
            mDate = sdf.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }
}
