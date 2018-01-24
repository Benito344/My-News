package com.behague.benjamin.mynews.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.behague.benjamin.mynews.Controllers.Fragments.MostPopularFragment;
import com.behague.benjamin.mynews.Controllers.Fragments.SportsFragment;
import com.behague.benjamin.mynews.Controllers.Fragments.TopStoriesFragment;

/**
 * Created by Benjamin BEHAGUE on 16/01/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    // 2 - Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return (3); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return TopStoriesFragment.newInstance();
            case 1: //Page number 2
                return MostPopularFragment.newInstance();
            case 2 :
                return SportsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Top Stories";
            case 1: //Page number 2
                return "Most Popular";
            case 2 :
                return "Sports";
            default:
                return null;
        }
    }
}
