package com.example.dell.small_geeknews.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.gold.gold.ShowActivity;
import com.example.dell.small_geeknews.gold.gold.adapter.Vpadapter;
import com.example.dell.small_geeknews.gold.gold.bean.GoldShowBean;
import com.example.dell.small_geeknews.gold.gold.fragment.Fragment_gold;
import com.example.dell.small_geeknews.presenter.GoldP;
import com.example.dell.small_geeknews.view.GoldV;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dell on 2019/4/3.
 */

public class GoldFragment extends BaseFragment<GoldP, GoldV> implements GoldV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<GoldShowBean> titles;
    private ArrayList<BaseFragment> fragments;


    @Override
    protected GoldP initGeekP() {
        return new GoldP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.gold_fagment;
    }

    @Override
    protected void initView() {
        inittitle();

        setFragment();

    }

    private void setFragment() {
        initfragment();//从新设置界面
        Vpadapter vpadapter = new Vpadapter(getChildFragmentManager(),
                fragments, titles);
        vp.setAdapter(vpadapter);
        tab.setupWithViewPager(vp);
    }

    private void initfragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i <titles.size() ; i++) {
            GoldShowBean bean = titles.get(i);
            if (bean.isChecked) {//选中的话就添加几个碎片
                fragments.add(Fragment_gold.newinstance(bean.title));
            }

        }
    }

    private void inittitle() {
        titles = new ArrayList<>();
        titles.add(new GoldShowBean("工具资源",true));
        titles.add(new GoldShowBean("Android",true));
        titles.add(new GoldShowBean("iOS",true));
        titles.add(new GoldShowBean("设计",true));
        titles.add(new GoldShowBean("产品",true));
        titles.add(new GoldShowBean("阅读",true));
        titles.add(new GoldShowBean("前端",true));
        titles.add(new GoldShowBean("后端",true));
    }

    @OnClick(R.id.iv)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv:
            go2Show();
                break;
        }
    }

    private void go2Show() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra("data",titles);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==200) {
            titles = (ArrayList<GoldShowBean>) data.getSerializableExtra("data");
            //刷新界面
            setFragment();

        }

    }
}
