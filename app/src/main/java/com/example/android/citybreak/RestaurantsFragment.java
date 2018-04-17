package com.example.android.citybreak;


import android.content.Intent;
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
        restaurants.add(new Restaurant("Livada", "Beautiful restaurant with tree garden in the old town of the city", R.drawable.livada, new Contact("Clinici street 6", "026458554545", "livada.ro"), new Hours(10, 00, 23, 00), 4.80));

        // Create the adapter for attractions
        RestaurantAdapter itemsAdapter = new RestaurantAdapter(getActivity(), restaurants);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                RestaurantAdapter restaurantAdapter = (RestaurantAdapter) parent.getAdapter();
                Restaurant restaurant = restaurantAdapter.getItem(position);
                DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && showInSplitScreen(parent.getRootView())) {
                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(restaurant.getPlaceName());
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            DetailsActivity.class);
                    intent.putExtra("name", restaurant.getPlaceName());
                    intent.putExtra("description", restaurant.getPlaceDescription());
                    intent.putExtra("image", restaurant.getPlaceImageId());
                    intent.putExtra("contact", restaurant.getPlaceContactInfo());
                    intent.putExtra("hours", restaurant.getOpenHours());
                    startActivity(intent);
                }
            }
        });


        // Return the View
        return rootView;
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
