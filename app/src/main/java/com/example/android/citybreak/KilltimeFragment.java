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
public class KilltimeFragment extends CitybreakFragment {

    public KilltimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create the ArrayList of attractions
        final ArrayList<Killtime> killTime = new ArrayList<Killtime>();

        //source: https://en.wikipedia.org/wiki/Cluj-Napoca_Central_Park
        killTime.add(new Killtime(
                getString(R.string.killtime_centralpark),
                getString(R.string.killtime_centralpark_description),
                R.drawable.centralpark,
                new Contact(getString(R.string.killtime_centralpark_address)),
                new Hours(0,0,24,0),
                getString(R.string.type_recreation))
        );

        // source: lasertagcluj.ro
        killTime.add(new Killtime(
                getString(R.string.killtime_lasertag),
                getString(R.string.killtime_lasertag_description),
                R.drawable.lasertag,
                new Contact(getString(R.string.killtime_lasertag_address), getString(R.string.killtime_lasertag_web)),
                new Hours(10,0,24,0),
                getString(R.string.type_entertainment))
        );

        // source: http://complexsportivgheorgheni.ro
        killTime.add(new Killtime(
                getString(R.string.killtime_sportpark),
                getString(R.string.killtime_sportpark_description),
                R.drawable.sportpark,
                new Contact(getString(R.string.killtime_sportpark_address)),
                new Hours(10,0,24,0),
                getString(R.string.type_sport))
        );

        // source: https://www.vivo-shopping.com/ro/cluj/magazine
        killTime.add(new Killtime(
                getString(R.string.killtime_vivo),
                getString(R.string.killtime_vivo_description),
                R.drawable.vivo,
                new Contact(getString(R.string.killtime_vivo_address)),
                new Hours(10,0,24,0),
                getString(R.string.type_shopping))
        );

        // source: http://iuliusmall.com/cluj/
        killTime.add(new Killtime(
                getString(R.string.killtime_iulius),
                getString(R.string.killtime_iulius_description),
                R.drawable.iulius,
                new Contact(getString(R.string.killtime_iulius_address)),
                new Hours(10,0,24,0),
                getString(R.string.type_shopping))
        );

        // Create the adapter for attractions
        KilltimeAdapter itemsAdapter = new KilltimeAdapter(getActivity(), killTime);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set an OnClickListener on list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                KilltimeAdapter eventAdapter = (KilltimeAdapter) parent.getAdapter();
                Killtime killtime = eventAdapter.getItem(position);

                // If there is a large display, show the details in a fragment in the same activity otherwise start a new intent to view the details
                if (showInSplitScreen(parent.getRootView())) {
                    DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                    if (fragment != null) {
                        updateDetailsFragment(killtime, fragment);
                    }
                } else {
                    viewInDetailsActivity(killtime);
                }
            }
        });

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }
}
