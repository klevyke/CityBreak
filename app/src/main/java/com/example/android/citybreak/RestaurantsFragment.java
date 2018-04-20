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
public class RestaurantsFragment extends CitybreakFragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get the resources
        Resources res = getResources();

        // Create the ArrayList of attractions
        final ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

        // source: https://www.facebook.com/restaurantlivada/
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_livada),
                res.getString(R.string.restaurant_livada_description),
                R.drawable.livada,
                new Contact(
                        res.getString(R.string.restaurant_livada_address),
                        res.getString(R.string.restaurant_livada_web),
                        res.getString(R.string.restaurant_livada_phone)),
                new Hours(10, 0, 23, 0),
                4.10)
        );

        // source: http://www.baracca.ro/eng/index.html
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_baracca),
                res.getString(R.string.restaurant_baracca_description),
                R.drawable.baracca,
                new Contact(
                        res.getString(R.string.restaurant_baracca_address),
                        res.getString(R.string.restaurant_baracca_web),
                        res.getString(R.string.restaurant_baracca_phone)),
                new Hours(12, 0, 23, 0),
                4.50)
        );

        // source: https://www.facebook.com/Rhedey/ and rhedeycafe.ro
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_rhedey),
                res.getString(R.string.restaurant_rhedey_description),
                R.drawable.rhedey,
                new Contact(
                        res.getString(R.string.restaurant_rhedey_address),
                        res.getString(R.string.restaurant_rhedey_web),
                        res.getString(R.string.restaurant_rhedey_phone)),
                new Hours(9, 0, 23, 0),
                4.60)
        );
        // source: https://www.facebook.com/BerariaKlausenBurger/
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_klausenburger),
                res.getString(R.string.restaurant_klausenburger_description),
                R.drawable.klausenburger,
                new Contact(
                        res.getString(R.string.restaurant_klausenburger_address),
                        res.getString(R.string.restaurant_klausenburger_web),
                        res.getString(R.string.restaurant_klausenburger_phone)),
                new Hours(10, 0, 2, 0),
                4.40)
        );
        // source: facebook.com/1568Bistro/
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_bistro1568),
                res.getString(R.string.restaurant_bistro1568_description),
                R.drawable.bistro1568,
                new Contact(
                        res.getString(R.string.restaurant_bistro1568_address),
                        res.getString(R.string.restaurant_bistro1568_web),
                        res.getString(R.string.restaurant_bistro1568_phone)
                        ),
                new Hours(10, 0, 2, 0),
                4.40)
        );

        // Create the adapter for attractions
        RestaurantAdapter itemsAdapter = new RestaurantAdapter(getActivity(), restaurants);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set an OnClickListener on list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                RestaurantAdapter restaurantAdapter = (RestaurantAdapter) parent.getAdapter();
                Restaurant restaurant = restaurantAdapter.getItem(position);

                // If there is a large display, show the details in a fragment in the same activity otherwise start a new intent to view the details
                if (showInSplitScreen(parent.getRootView())) {
                    DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                    if (fragment != null) {
                        updateDetailsFragment(restaurant, fragment);
                    }
                } else {
                    viewInDetailsActivity(restaurant);
                }
            }
        });

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }
}
