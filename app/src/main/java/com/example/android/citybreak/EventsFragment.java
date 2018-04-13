package com.example.android.citybreak;

import android.icu.util.DateInterval;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        // Create the ArrayList of attractions
        final ArrayList<Event> events = new ArrayList<Event>();
        // Data from https://www.facebook.com/restaurantlivada/
        events.add(new Event("Untold","Biggest music festival in Central/Easter Europe with various type of electronic music.", R.drawable.livada, new Contact("Central Park - Cluj Arena"), dateFromString("02/08/2018"), dateFromString("05/08/2018")));

        // Create the adapter for attractions
        EventAdapter itemsAdapter = new EventAdapter(getActivity(), events);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }

    private Date dateFromString (String eventDate) {
        Date mDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            mDate = sdf.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }

}
