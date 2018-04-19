package com.example.android.citybreak;


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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KilltimeFragment extends Fragment {


    public KilltimeFragment() {
        // Required empty public constructor
    }
    // Get the resources

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get the resources
        Resources res = getResources();

        // Create the ArrayList of attractions
        final ArrayList<Killtime> killTime = new ArrayList<Killtime>();

        //source: https://en.wikipedia.org/wiki/Cluj-Napoca_Central_Park
        killTime.add(new Killtime(
                getString(R.string.killtime_centralpark),
                getString(R.string.killtime_centralpark_description),
                R.drawable.centralpark,
                new Contact(getString(R.string.killtime_centralpark_address)),
                new Hours(0,0,24,00),
                getString(R.string.type_recreation)));

        // Data from lasertagcluj.ro
        killTime.add(new Killtime(
                getString(R.string.killtime_lasertag),
                getString(R.string.killtime_lasertag_description),
                R.drawable.lasertag,
                new Contact(getString(R.string.killtime_lasertag_address), getString(R.string.killtime_lasertag_web)),
                new Hours(10,0,24,00),
                getString(R.string.type_entertainment)));

        // Data from https://www.facebook.com/killtimelivada/
        killTime.add(new Killtime(
                getString(R.string.killtime_sportpark),
                getString(R.string.killtime_sportpark_description),
                R.drawable.sportpark,
                new Contact(getString(R.string.killtime_sportpark_address)),
                new Hours(10,0,24,00),
                getString(R.string.type_sport)));

        // Data from https://www.facebook.com/killtimelivada/
        killTime.add(new Killtime(
                getString(R.string.killtime_vivo),
                getString(R.string.killtime_vivo_description),
                R.drawable.vivo,
                new Contact(getString(R.string.killtime_vivo_address)),
                new Hours(10,0,24,00),
                getString(R.string.type_shopping)));

        // Data from https://www.facebook.com/killtimelivada/
        killTime.add(new Killtime(
                getString(R.string.killtime_iulius),
                getString(R.string.killtime_iulius_description),
                R.drawable.iulius,
                new Contact(getString(R.string.killtime_iulius_address)),
                new Hours(10,0,24,00),
                getString(R.string.type_shopping)));

        // Create the adapter for attractions
        KilltimeAdapter itemsAdapter = new KilltimeAdapter(getActivity(), killTime);

        // Get the listview to pe populated
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                KilltimeAdapter eventAdapter = (KilltimeAdapter) parent.getAdapter();
                Killtime killtime = eventAdapter.getItem(position);
                DetailsFragment fragment = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && showInSplitScreen(parent.getRootView())) {
                    // Inflate the layout for this fragment
                    // View rootView = fragment.getLayoutInflater().inflate(R.layout.activity_details, fragment.getView(), false);

                    ImageView image = fragment.getView().findViewById(R.id.image);
                    image.setImageResource(killtime.getPlaceImageId());
                    image.setVisibility(View.VISIBLE);

                    TextView name = (TextView) fragment.getView().findViewById(R.id.name);
                    name.setText(killtime.getPlaceName());

                    TextView description = fragment.getView().findViewById(R.id.description);
                    description.setText(killtime.getPlaceDescription());

                    LinearLayout additionalSection = fragment.getView().findViewById(R.id.additional);
                    additionalSection.removeAllViewsInLayout();
                    addInTextView(additionalSection, "Type:", killtime.getLocationType());

                    Contact contactInfo = killtime.getPlaceContactInfo();
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

                    Hours hours = killtime.getOpenHours();
                    addInTextView(contactSection, "Hours:", hours.getHoursString());

                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            DetailsActivity.class);
                    intent.putExtra("name", killtime.getPlaceName());
                    intent.putExtra("description", killtime.getPlaceDescription());
                    intent.putExtra("image", killtime.getPlaceImageId());
                    intent.putExtra("contact", killtime.getPlaceContactInfo());
                    intent.putExtra("hours", killtime.getOpenHours());
                    intent.putExtra("type", killtime.getLocationType());
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

    public void addInTextView(ViewGroup parent, String label, String text) {
        //LinearLayout.LayoutParams attributes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(getContext());
        textView.setText(label+" "+text);
        parent.addView(textView);
    }
}
