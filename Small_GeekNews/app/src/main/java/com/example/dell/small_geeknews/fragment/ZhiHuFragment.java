package com.example.dell.small_geeknews.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.geeknews.adapter.VpAdapter;
import com.example.dell.small_geeknews.geeknews.fragment.DayNewsFragment;
import com.example.dell.small_geeknews.geeknews.fragment.HotFragment;
import com.example.dell.small_geeknews.geeknews.fragment.ZhuanLanFragment;
import com.example.dell.small_geeknews.presenter.ZhiHuP;
import com.example.dell.small_geeknews.view.ZhiHuV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/3.
 */

//石明洋  1808B  知乎界面
public class ZhiHuFragment extends BaseFragment<ZhiHuP, ZhiHuV>{


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;

    @Override
    protected ZhiHuP initGeekP() {
        return new ZhiHuP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.zhihu_fagment;
    }



    @Override
    protected void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DayNewsFragment());
        fragments.add(new ZhuanLanFragment());
        fragments.add(new HotFragment());

        ArrayList<Integer> titles = new ArrayList<>();
        titles.add(R.string.daynews);
        titles.add(R.string.zhuanlan);
        titles.add(R.string.hot);
        VpAdapter vpAdapter = new VpAdapter(getContext(),getChildFragmentManager(), fragments, titles);
        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);


    }



}
