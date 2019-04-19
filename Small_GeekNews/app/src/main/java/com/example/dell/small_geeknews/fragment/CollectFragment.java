package com.example.dell.small_geeknews.fragment;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.CollectP;
import com.example.dell.small_geeknews.presenter.ZhiHuP;
import com.example.dell.small_geeknews.view.CollectV;
import com.example.dell.small_geeknews.view.ZhiHuV;

/**
 * Created by Dell on 2019/4/3.
 */

public class CollectFragment extends BaseFragment<CollectP,CollectV> implements CollectV{
    @Override
    protected CollectP initGeekP() {
        return new CollectP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.collect_fagment;
    }
}
