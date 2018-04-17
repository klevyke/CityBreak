package com.example.android.citybreak;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class CategoryAdapter extends FragmentPagerAdapter {
    // get the app resources
    private String tabTitles[];

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        Resources res = context.getResources();
        tabTitles = new String[]{
                res.getString(R.string.tab_title_about),
                res.getString(R.string.tab_title_attractions),
                res.getString(R.string.tab_title_restaurants),
                res.getString(R.string.tab_title_events),
                res.getString(R.string.tab_title_kill_time)
        };
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
