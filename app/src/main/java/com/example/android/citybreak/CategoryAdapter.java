package com.example.android.citybreak;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "About","Attractions", "Restaurants", "Events", "Kill-time" };

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

         if (position == 0) {
            return new AboutFragment();
        } else if (position == 1){
           return new AttractionsFragment();
        } else if (position == 2){
            return new RestaurantsFragment();
        } else if (position == 3){
            return new EventsFragment();
        } else {
            return new KilltimeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
