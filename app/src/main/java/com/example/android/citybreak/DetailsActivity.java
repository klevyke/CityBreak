package com.example.android.citybreak;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the data from the other activity
        Bundle extras = getIntent().getExtras();

        // Set the image resource
        ImageView image = findViewById(R.id.image);
        image.setImageResource(extras.getInt("image"));
        image.setVisibility(View.VISIBLE);

        TextView name = findViewById(R.id.name);
        name.setText(extras.getString("name"));

        TextView description = findViewById(R.id.description);
        description.setText(extras.getString("description"));

        Contact contactInfo = extras.getParcelable("contact");
        LinearLayout parent = findViewById(R.id.contact);

        addInTextView(parent, "Address:",contactInfo.getPlaceAddress());

        if (contactInfo.hasPhoneSet()) {
            addInTextView(parent, "Phone:",contactInfo.getPlacePhone());
        }
        if (contactInfo.hasWebSet()) {
            addInTextView(parent, "Web:",contactInfo.getPlaceWeb());
        }

        Hours hours = extras.getParcelable("hours");

        if ( hours != null) {
            addInTextView(parent, "Hours:", hours.getHoursString());
        }

        String attractionStory = extras.getString("story");

        if ( attractionStory != null) {
            addInTextView(parent, "\n\nThe story of this attraction: \n\n", attractionStory);
        }
    }

    public void addInTextView(ViewGroup parent, String label, String text) {
        //LinearLayout.LayoutParams attributes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setText(label+" "+text);
        parent.addView(textView);
    }
}
