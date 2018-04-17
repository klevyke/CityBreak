package com.example.android.citybreak;

import android.content.Intent;
import android.icu.util.DateInterval;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create the ArrayList of events
        final ArrayList<Event> events = new ArrayList<Event>();
        // Data from https://www.facebook.com/restaurantlivada/
        events.add(new Event("Untold","Biggest music festival in Central/Easter Europe with various type of electronic music.", R.drawable.livada, new Contact("Central Park - Cluj Arena"), dateFromString("2018.08.02"), dateFromString("2018.08.05")));

        // Create the adapter for events
        EventAdapter itemsAdapter = new EventAdapter(getActivity(), events);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                EventAdapter eventAdapter = (EventAdapter) parent.getAdapter();
                Event event = eventAdapter.getItem(position);
                DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && showInSplitScreen(parent.getRootView())) {
                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(event.getPlaceName());
                } else {
                    Log.v("Fragment", "Fragment not visible");
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            DetailsActivity.class);
                    intent.putExtra("name", event.getPlaceName());
                    intent.putExtra("description", event.getPlaceDescription());
                    intent.putExtra("image", event.getPlaceImageId());
                    intent.putExtra("contact", event.getPlaceContactInfo());
                    intent.putExtra("eventStart", event.getEventStartDate());
                    intent.putExtra("eventEnd", event.getEventEndDate());
                    startActivity(intent);
                }
            }
        });

        // Return the View
        return rootView;
    }

    private Date dateFromString (String eventDate) {
        Date mDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            mDate = sdf.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }

    /**
     * Check if it must be displayed  in split screen
     *
     * @param context
     */
    private Boolean showInSplitScreen(View context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        if (dpWidth > 1000) {
            return true;
        } else {
            return false;
        }
    }
}
