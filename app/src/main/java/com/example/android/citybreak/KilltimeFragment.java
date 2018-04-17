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
public class KilltimeFragment extends Fragment {


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
        // Data from https://www.facebook.com/restaurantlivada/
        killTime.add(new Killtime("Lasertag","Having fun hunting down your friends with a gun.", R.drawable.livada, new Contact("Traian Vuia street nr. 208"), new Hours(10,00,23,00), "entertainment"));

        // Create the adapter for attractions
        KilltimeAdapter itemsAdapter = new KilltimeAdapter(getActivity(), killTime);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                KilltimeAdapter eventAdapter = (KilltimeAdapter) parent.getAdapter();
                Killtime event = eventAdapter.getItem(position);
                DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && showInSplitScreen(parent.getRootView())) {
                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(event.getPlaceName());
                } else {
                    Log.v("Fragment", "Fragment not visible");
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            DetailsActivity.class);
                    intent.putExtra("name", event.getPlaceName());
                    intent.putExtra("description", event.getPlaceDescription());
                    intent.putExtra("image", event.getPlaceImageId());
                    intent.putExtra("contact", event.getPlaceContactInfo());
                    intent.putExtra("hours", event.getOpenHours());
                    intent.putExtra("type", event.getLocationType());
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
