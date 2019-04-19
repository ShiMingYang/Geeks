package com.example.dell.small_geeknews.fragment;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.MainAboutP;
import com.example.dell.small_geeknews.presenter.SettingP;
import com.example.dell.small_geeknews.view.MainAboutV;
import com.example.dell.small_geeknews.view.SettingV;

/**
 * Created by Dell on 2019/4/3.
 */

public class MainAboutFragment extends BaseFragment<MainAboutP,MainAboutV> implements MainAboutV{
    @Override
    protected MainAboutP initGeekP() {
        return new MainAboutP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.main_fagment;
    }
}
