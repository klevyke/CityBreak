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

public class AttractionAdapter extends ArrayAdapter<Attraction>{
    /**
     * Constructor for the custom ArrayAdapter
     * @param context context ot the adapter
     * @param attraction ArrayList of Attraction objects
     */
    public AttractionAdapter(@NonNull Context context, ArrayList<Attraction> attraction) {
        super(context, 0 , attraction);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the current Attraction object
        Attraction currentAttraction = getItem(position);

        // Set the name TextView
        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentAttraction.getPlaceName());

        // Set the description TextView
        TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentAttraction.getPlaceDescription());

        // Set the image
        ImageView image = (ImageView)listItemView.findViewById(R.id.image);
        image.setImageResource(currentAttraction.getPlaceImageId());
        image.setVisibility(View.VISIBLE);

        return listItemView;
    }
}
