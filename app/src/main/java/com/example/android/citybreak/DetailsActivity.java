package com.example.android.citybreak;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the data from the other activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Set the image resource
        ImageView image = findViewById(R.id.image);
        image.setImageResource(extras.getInt("image"));
        image.setVisibility(View.VISIBLE);

        TextView name = findViewById(R.id.name);
        name.setText(extras.getString("name"));

        TextView description = findViewById(R.id.description);
        description.setText(extras.getString("description"));

        Date eventStartDate = (Date) intent.getSerializableExtra("eventStart");
        Date eventEndDate = (Date) intent.getSerializableExtra("eventEnd");

        LinearLayout generalSection = findViewById(R.id.general);

        if ( eventStartDate != null && eventEndDate!= null) {
            if (eventStartDate == eventEndDate) {
                String text = formatedDate(eventStartDate);
                addInTextView(generalSection, "Date:", text);
            } else {
                String text = formatedDate(eventStartDate)+" - "+formatedDate(eventEndDate);
                addInTextView(generalSection, "Date:", text);
            }
        }

        String type = extras.getString("type");

        if ( type != null) {
            addInTextView(generalSection, "Type:", type);
        }

        Contact contactInfo = extras.getParcelable("contact");
        LinearLayout contactSection = findViewById(R.id.contact);

        addInTextView(contactSection, "Address:",contactInfo.getPlaceAddress());

        if (contactInfo.hasPhoneSet()) {
            addInTextView(contactSection, "Phone:",contactInfo.getPlacePhone());
        }
        if (contactInfo.hasWebSet()) {
            addInTextView(contactSection, "Web:",contactInfo.getPlaceWeb());
        }

        Hours hours = extras.getParcelable("hours");

        if ( hours != null) {
            addInTextView(contactSection, "Hours:", hours.getHoursString());
        }

        final String attractionStory = extras.getString("story");

        if ( attractionStory != null) {
            TextView linkView = new TextView(this);
            linkView.setText(getString(R.string.more_text));
            linkView.setGravity(Gravity.RIGHT);
            linkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(attractionStory));
                    startActivity(i);
                }
            });
            contactSection.addView(linkView);
        }

    }

    public void addInTextView(ViewGroup parent, String label, String text) {
        //LinearLayout.LayoutParams attributes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setText(label+" "+text);
        parent.addView(textView);
    }

    public String formatedDate(Date date) {
        Date mDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(date);
    }
}
