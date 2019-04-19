package com.example.dell.small_geeknews.gold.gold.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.gold.gold.bean.GoldShowBean;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/18.
 */

public class Vpadapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> mfragments;
    private ArrayList<GoldShowBean> mtitles;
    private ArrayList<String> newtitles = new ArrayList<>();

    public Vpadapter(FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<GoldShowBean> titles) {
        super(fm);
        mfragments = fragments;
        mtitles = titles;

        for (int i = 0; i < mtitles.size(); i++) {
            //判断选中后的title，添加进去
            GoldShowBean bean = mtitles.get(i);
            if (bean.isChecked) {
                newtitles.add(bean.title);
            }
        }
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
        return newtitles.get(position);
    }
}
