package com.example.android.citybreak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create the ArrayList of attractions
        final ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        // Data from https://www.facebook.com/restaurantlivada/
        restaurants.add(new Restaurant("Livada","Beautyfull restaurant with tree garden in the old town of the city", R.drawable.livada, new Contact("Clinici street 6"), new Hours(10,00,23,00), 4.80));

        // Create the adapter for attractions
        RestaurantAdapter itemsAdapter = new RestaurantAdapter(getActivity(), restaurants);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }

}
