package com.example.dell.small_geeknews.fragment;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.GankP;
import com.example.dell.small_geeknews.presenter.ZhiHuP;
import com.example.dell.small_geeknews.view.GankV;
import com.example.dell.small_geeknews.view.ZhiHuV;

/**
 * Created by Dell on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankP,GankV> implements GankV{
    @Override
    protected GankP initGeekP() {
        return new GankP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.gank_fagment;
    }
}
