package com.example.android.citybreak;


import android.content.Context;
import android.content.Intent;
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

        // Create the ArrayList of attractions
        final ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

        // source: https://www.facebook.com/restaurantlivada/
        restaurants.add(new Restaurant("Livada", "Beautiful restaurant with tree garden in the old town of the city", R.drawable.livada, new Contact("6 Clinici street", "+4 0722 111 115", "restaurantlivada.ro"), new Hours(10, 0, 23, 0), 4.10));
        // source: http://www.baracca.ro/eng/index.html
        restaurants.add(new Restaurant("Baracca", "Baracca is the place where life is celebrated every moment!", R.drawable.baracca, new Contact("8A Napoca Street", "+4 0732 155 177", "baracca.ro"), new Hours(12, 0, 23, 0), 4.50));
        // source: https://www.facebook.com/Rhedey/ and rhedeycafe.ro
        restaurants.add(new Restaurant("Rhédey", "The Rhédey House is a piece of history in itself. It is the history of the city’s main square.", R.drawable.rhedey, new Contact("Piata Unirii", "+4 0364 156 905", "rhedeycafe.ro"), new Hours(9, 0, 23, 0), 4.60));
        // source: https://www.facebook.com/BerariaKlausenBurger/
        restaurants.add(new Restaurant("Klausen Burger", "Roof-top restaurant serving good food and craft beer.", R.drawable.klausenburger, new Contact("Ferdinand street 22", "+4 0372 717 552", "facebook.com/BerariaKlausenBurger/"), new Hours(10, 0, 2, 0), 4.40));
        // source: facebook.com/1568Bistro/
        restaurants.add(new Restaurant("Bistro 1568", "A mix of traditional romanian, hungarian, saxon, jewish and armenian food.", R.drawable.bistro1568, new Contact("14 B-dul 21 Decembrie 1989", "+4 0770 163 152", "facebook.com/1568Bistro/"), new Hours(10, 0, 2, 0), 4.40));

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
