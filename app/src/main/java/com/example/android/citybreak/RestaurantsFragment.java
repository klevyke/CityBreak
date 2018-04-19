package com.example.android.citybreak;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                        res.getString(R.string.restaurant_livada_phone),
                        res.getString(R.string.restaurant_livada_web)),
                new Hours(10, 0, 23, 0),
                4.10));

        // source: http://www.baracca.ro/eng/index.html
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_baracca),
                res.getString(R.string.restaurant_baracca_description),
                R.drawable.baracca,
                new Contact(
                        res.getString(R.string.restaurant_baracca_address),
                        res.getString(R.string.restaurant_baracca_phone),
                        res.getString(R.string.restaurant_baracca_web)),
                new Hours(12, 0, 23, 0),
                4.50));

        // source: https://www.facebook.com/Rhedey/ and rhedeycafe.ro
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_rhedey),
                res.getString(R.string.restaurant_rhedey_description),
                R.drawable.rhedey,
                new Contact(
                        res.getString(R.string.restaurant_rhedey_address),
                        res.getString(R.string.restaurant_rhedey_phone),
                        res.getString(R.string.restaurant_rhedey_web)),
                new Hours(9, 0, 23, 0),
                4.60));
        // source: https://www.facebook.com/BerariaKlausenBurger/
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_klausenburger),
                res.getString(R.string.restaurant_klausenburger_description),
                R.drawable.klausenburger,
                new Contact(
                        res.getString(R.string.restaurant_klausenburger_address),
                        res.getString(R.string.restaurant_klausenburger_phone),
                        res.getString(R.string.restaurant_klausenburger_web)),
                new Hours(10, 0, 2, 0),
                4.40));
        // source: facebook.com/1568Bistro/
        restaurants.add(new Restaurant(
                res.getString(R.string.restaurant_bistro1568),
                res.getString(R.string.restaurant_bistro1568_description),
                R.drawable.bistro1568,
                new Contact(
                        res.getString(R.string.restaurant_bistro1568_address),
                        res.getString(R.string.restaurant_bistro1568_phone),
                        res.getString(R.string.restaurant_bistro1568_web)),
                new Hours(10, 0, 2, 0),
                4.40));

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
                    // Inflate the layout for this fragment
                    // View rootView = fragment.getLayoutInflater().inflate(R.layout.activity_details, fragment.getView(), false);

                    ImageView image = fragment.getView().findViewById(R.id.image);
                    image.setImageResource(restaurant.getPlaceImageId());
                    image.setVisibility(View.VISIBLE);

                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(restaurant.getPlaceName());

                    TextView description = fragment.getView().findViewById(R.id.description);
                    description.setText(restaurant.getPlaceDescription());

                    Contact contactInfo = restaurant.getPlaceContactInfo();
                    LinearLayout contactSection = fragment.getView().findViewById(R.id.contact);
                    contactSection.removeAllViewsInLayout();

                    TextView title =  fragment.getView().findViewById(R.id.contact_header);
                    title.setVisibility(View.VISIBLE);

                    addInTextView(contactSection, "Address:",contactInfo.getPlaceAddress());

                    if (contactInfo.hasPhoneSet()) {
                        addInTextView(contactSection, "Phone:",contactInfo.getPlacePhone());
                    }
                    if (contactInfo.hasWebSet()) {
                        addInTextView(contactSection, "Web:",contactInfo.getPlaceWeb());
                    }

                    Hours hours = restaurant.getOpenHours();
                    addInTextView(contactSection, "Hours:", hours.getHoursString());

                    addInTextView(contactSection, "Rating:", String.valueOf(restaurant.getRestaurantRating()));

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

    public void addInTextView(ViewGroup parent, String label, String text) {
        //LinearLayout.LayoutParams attributes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(getContext());
        textView.setText(label+" "+text);
        parent.addView(textView);
    }
}
