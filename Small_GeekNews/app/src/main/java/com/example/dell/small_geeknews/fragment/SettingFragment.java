package com.example.dell.small_geeknews.fragment;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.CollectP;
import com.example.dell.small_geeknews.presenter.SettingP;
import com.example.dell.small_geeknews.view.CollectV;
import com.example.dell.small_geeknews.view.SettingV;

/**
 * Created by Dell on 2019/4/3.
 */

public class SettingFragment extends BaseFragment<SettingP,SettingV> implements SettingV{
    @Override
    protected SettingP initGeekP() {
        return new SettingP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.setting_fagment;
    }
}
