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
public class AttractionsFragment extends Fragment {

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
        attractions.add(new Attraction("Mathias Rex Satue", "This classified historic monument, conceived by János Fadrusz and opened in 1902, represents Matthias Corvinus.", R.drawable.matyaskiraly, new Contact("Unirii street 1"), "Story of the attraction"));

        // Create the adapter for attractions
        AttractionAdapter itemsAdapter = new AttractionAdapter(getActivity(), attractions);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                AttractionAdapter attractionAdapter = (AttractionAdapter) parent.getAdapter();
                Attraction attraction = attractionAdapter.getItem(position);
                DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && showInSplitScreen(parent.getRootView())) {
                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(attraction.getPlaceName());
                } else {
                    Log.v("Fragment", "Fragment not visible");
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            DetailsActivity.class);
                    intent.putExtra("name", attraction.getPlaceName());
                    intent.putExtra("description", attraction.getPlaceDescription());
                    intent.putExtra("image", attraction.getPlaceImageId());
                    intent.putExtra("contact", attraction.getPlaceContactInfo());
                    intent.putExtra("story", attraction.getAttractionStory());
                    startActivity(intent);
                }
            }
        });

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

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
