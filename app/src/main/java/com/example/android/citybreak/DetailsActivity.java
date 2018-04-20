package com.example.android.citybreak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the extra data from parent activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Set the image resource
        ImageView image = findViewById(R.id.image);
        image.setImageResource(extras.getInt(EXTRA_IMAGE));
        image.setVisibility(View.VISIBLE);

        // Set the name
        TextView name = findViewById(R.id.name);
        name.setText(extras.getString(EXTRA_NAME));

        // Set the description
        TextView description = findViewById(R.id.description);
        description.setText(extras.getString(EXTRA_DESCRIPTION));

        // Get events start and end the dates
        Date eventStartDate = (Date) intent.getSerializableExtra(EXTRA_EVENTSTARTDATE );
        Date eventEndDate = (Date) intent.getSerializableExtra(EXTRA_EVENTENDDATE);

        // Get the general LinearLayout to be appended
        LinearLayout generalSection = findViewById(R.id.general);

        // Check if the date variables are set
        if ( eventStartDate != null && eventEndDate!= null) {
            // Check if the start date is equal with end date, display one date if soo
            if (eventStartDate == eventEndDate) {
                String text = formatedDate(eventStartDate);
                addInTextView(generalSection, getString(R.string.label_date), text);
            } else {
                String text =  getString(R.string.date_interval, formatedDate(eventStartDate), formatedDate(eventEndDate));
                addInTextView(generalSection, getString(R.string.label_date), text);
            }
        }

        // Get the type if set and add to general section
        String type = extras.getString(EXTRA_TYPE);
        if ( type != null) {
            addInTextView(generalSection, getString(R.string.label_type), type);
        }

        // Get the general LinearLayout to be appended
        LinearLayout contactSection = findViewById(R.id.contact);

        // Get the Contact object to be displayed
        Contact contactInfo = extras.getParcelable(EXTRA_CONTACT);

        // Add the address with the label (This is set in every category)
        addInTextView(contactSection, getString(R.string.label_address),contactInfo.getPlaceAddress());

        // Add the phone number with the label if set
        if (contactInfo.hasPhoneSet()) {
            addInTextView(contactSection, getString(R.string.label_phone),contactInfo.getPlacePhone());
        }

        // Add the web address with the label if set
        if (contactInfo.hasWebSet()) {
            addInTextView(contactSection, getString(R.string.label_web),contactInfo.getPlaceWeb());
        }

        // Get the Hours object
        Hours hours = extras.getParcelable(EXTRA_HOURS);

        // Add the hours with the label if set
        if ( hours != null) {
            addInTextView(generalSection, getString(R.string.label_hours), hours.getHoursString(this));
        }

        // Get the link to the story if set
        final String attractionStory = extras.getString(EXTRA_STORY);
        if ( attractionStory != null) {

            // Add the Texview as clickable text
            TextView linkView = new TextView(this);
            linkView.setText(getString(R.string.more_text));

            // set an OnClickListener on this TextView
            linkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Initiate an intent to view the sites
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(attractionStory));
                    startActivity(i);
                }
            });
            generalSection.addView(linkView);
        }
    }

    /**
     * Helper function to create a Textview with label in a specified ViewGroup
     * @param parent
     * @param label
     * @param text
     */
    public void addInTextView(ViewGroup parent, String label, String text) {
        TextView textView = new TextView(this);
        textView.setText(getString(R.string.label_and_text, label, text));
        parent.addView(textView);
    }

    /**
     * Helper function to return a date as a formated String for display
     * source: https://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
     * @param date
     * @return as String
     */
    public String formatedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_pattern));
        return dateFormat.format(date);
    }
}
