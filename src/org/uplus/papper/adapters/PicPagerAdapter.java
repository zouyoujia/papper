package org.uplus.papper.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.uplus.papper.entities.Pic;
import org.uplus.papper.fragments.PicFragment;
import org.uplus.papper.jobs.FetchPicJob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youjia.zyj on 2014/5/31.
 */
public class PicPagerAdapter extends FragmentStatePagerAdapter {
    public static final int SIZE = 3;
    List<Fragment> fragments = new ArrayList<Fragment>();

    public PicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        if (fragments.size() <= i) {
            fragment = new PicFragment();
            Bundle args = new Bundle();
            args.putInt(PicFragment.ARG_CHANNEL, i);
            fragment.setArguments(args);
            fragments.add(fragment);
        } else {
            fragment = fragments.get(i);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FetchPicJob.TITLES[position];
    }

    @Override
    public int getCount() {
        return FetchPicJob.TITLES.length;
    }
}
