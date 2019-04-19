package com.example.dell.small_geeknews.geeknews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/17.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private Context mcontext;
    private ArrayList<Fragment> mfragments;
    private ArrayList<Integer> mtitles;

    public VpAdapter(Context context, FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<Integer> titles) {
        super(fm);
        mcontext = context;
        mfragments = fragments;
        mtitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(mtitles.get(position));
    }
}
