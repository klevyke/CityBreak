package com.example.android.citybreak;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Levy on 12.04.2018.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    /**
     * Constructor for the custom ArrayAdapter
     * @param context context ot the adapter
     * @param restaurant ArrayList of Restaurant objects
     */
    public RestaurantAdapter(@NonNull Context context, ArrayList<Restaurant> restaurant) {
        super(context, 0 , restaurant);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.attraction_item, parent, false);
        }

        // Get the current Attraction object
        Restaurant currentRestaurant = getItem(position);

        // Set the name TextView
        TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentRestaurant.getPlaceName());

        // Set the description TextView
        TextView description = listItemView.findViewById(R.id.description);
        description.setText(currentRestaurant.getPlaceDescription());

        // Set the image
        ImageView image = listItemView.findViewById(R.id.image);
        image.setImageResource(currentRestaurant.getPlaceImageId());

        return listItemView;
    }
}
