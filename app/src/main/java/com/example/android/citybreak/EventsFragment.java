package com.example.android.citybreak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends CitybreakFragment {


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
        // source: tiff.ro
        events.add(new Event(
                getString(R.string.event_tiff),
                getString(R.string.event_tiff_description),
                R.drawable.tiff,
                new Contact(getString(R.string.event_tiff_address), getString(R.string.event_tiff_web)),
                dateFromString(getString(R.string.event_tiff_startdate)),
                dateFromString(getString(R.string.event_tiff_enddate))
        ));
        // source: untold.ro
        events.add(new Event(
                getString(R.string.event_untold),
                getString(R.string.event_untold_description),
                R.drawable.untold,
                new Contact(getString(R.string.event_untold_address), getString(R.string.event_untold_web)),
                dateFromString(getString(R.string.event_untold_startdate)),
                dateFromString(getString(R.string.event_untold_enddate))
        ));
        // source: electriccastle.ro
        events.add(new Event(
                getString(R.string.event_electric),
                getString(R.string.event_electric_description),
                R.drawable.electric,
                new Contact(getString(R.string.event_electric_address), getString(R.string.event_electric_web)),
                dateFromString(getString(R.string.event_electric_startdate)),
                dateFromString(getString(R.string.event_electric_enddate))
        ));
        // source: magyarnapok.ro
        events.add(new Event(
                getString(R.string.event_hundays),
                getString(R.string.event_hundays_description),
                R.drawable.hundays,
                new Contact(getString(R.string.event_hundays_address), getString(R.string.event_hundays_web)),
                dateFromString(getString(R.string.event_hundays_startdate)),
                dateFromString(getString(R.string.event_hundays_enddate))
        ));
        // source: jazzinthepark.ro
        events.add(new Event(
                getString(R.string.event_jazzinthepark),
                getString(R.string.event_jazzinthepark_description),
                R.drawable.jazzinthepark,
                new Contact(getString(R.string.event_jazzinthepark_address), getString(R.string.event_jazzinthepark_web)),
                dateFromString(getString(R.string.event_jazzinthepark_startdate)),
                dateFromString(getString(R.string.event_jazzinthepark_enddate))
        ));

        // Create the adapter for events
        EventAdapter itemsAdapter = new EventAdapter(getActivity(), events);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set an OnClickListener on list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                EventAdapter eventAdapter = (EventAdapter) parent.getAdapter();
                Event event = eventAdapter.getItem(position);

                // If there is a large display, show the details in a fragment in the same activity otherwise start a new intent to view the details
                if (showInSplitScreen(parent.getRootView())) {
                    DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                    if (fragment != null) {
                        updateDetailsFragment(event, fragment);
                    }
                } else {
                    viewInDetailsActivity(event);
                }
            }
        });

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }
}
