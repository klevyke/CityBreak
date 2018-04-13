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

        // Populate the list using the adapter created
        listView.setAdapter(itemsAdapter);

        // Return the View
        return rootView;
    }

}
