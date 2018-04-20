package com.example.android.citybreak;

import android.content.res.Resources;
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
public class AttractionsFragment extends CitybreakFragment {

    public AttractionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create the ArrayList of attractions
        final ArrayList<Attraction> attractions = new ArrayList<Attraction>();
        attractions.add(new Attraction(
                getString(R.string.attraction_mathiasrex),
                getString(R.string.attraction_mathiasrex_description),
                R.drawable.matyaskiraly,
                new Contact( getString(R.string.attraction_mathiasrex_address)),
                getString(R.string.attraction_mathiasrex_story))
        );

        //source: https://en.wikipedia.org/wiki/Cluj-Napoca
        attractions.add(new Attraction(
                getString(R.string.attraction_mirrorstreet),
                getString(R.string.attraction_mirrorstreet_description),
                R.drawable.mirrorstreet,
                new Contact( getString(R.string.attraction_mirrorstreet_address)),
                getString(R.string.attraction_mirrorstreet_story))
        );

        //source: https://ro.wikipedia.org/wiki/Bastionul_Croitorilor_din_Cluj-Napoca
        attractions.add(new Attraction(
                getString(R.string.attraction_tailorsbastion),
                getString(R.string.attraction_tailorsbastion_description),
                R.drawable.tailorsbastion,
                new Contact( getString(R.string.attraction_tailorsbastion_address)),
                getString(R.string.attraction_tailorsbastion_story))
        );

        //source: https://en.wikipedia.org/wiki/St._Michael%27s_Church,_Cluj-Napoca
        attractions.add(new Attraction(
                getString(R.string.attraction_szentmihaly),
                getString(R.string.attraction_szentmihaly_description),
                R.drawable.szentmihaly,
                new Contact( getString(R.string.attraction_szentmihaly_address)),
                getString(R.string.attraction_szentmihaly_story))
        );

        //source: https://en.wikipedia.org/wiki/Matthias_Corvinus_House
        attractions.add(new Attraction(
                getString(R.string.attraction_mathiasrexbirthplace),
                getString(R.string.attraction_mathiasrexbirthplace_description),
                R.drawable.mattiasresxbirthplace,
                new Contact( getString(R.string.attraction_mathiasrexbirthplace_address)),
                getString(R.string.attraction_mathiasrexbirthplace_story))
        );

        // Create the adapter for attractions
        AttractionAdapter itemsAdapter = new AttractionAdapter(getActivity(), attractions);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set an OnClickListener on list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                AttractionAdapter attractionAdapter = (AttractionAdapter) parent.getAdapter();
                Attraction attraction = attractionAdapter.getItem(position);

                // If there is a large display, show the details in a fragment in the same activity otherwise start a new intent to view the details
                if (showInSplitScreen(parent.getRootView())) {
                    DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                    if (fragment != null) {
                        updateDetailsFragment(attraction, fragment);
                    }
                } else {
                    viewInDetailsActivity(attraction);
                }
            }
        });

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }
}
