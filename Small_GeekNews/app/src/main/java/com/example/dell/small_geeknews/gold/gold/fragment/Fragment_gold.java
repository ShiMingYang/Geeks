package com.example.dell.small_geeknews.gold.gold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.EmptyP;
import com.example.dell.small_geeknews.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/18.
 */

public class Fragment_gold extends BaseFragment<EmptyP, EmptyV> implements EmptyV {

    @BindView(R.id.tv)
    TextView tv;
    private Bundle arguments;

    /**
     *
     * * @param text  简单文本
     * @return
     */
    public static Fragment_gold newinstance(String text) {
        Fragment_gold fragment_gold = new Fragment_gold();
        Bundle bundle = new Bundle();
        bundle.putString("data", text);
        fragment_gold.setArguments(bundle);
        return fragment_gold;
    }

    @Override
    protected EmptyP initGeekP() {
        return new EmptyP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        arguments = getArguments();
        String data = arguments.getString("data");
        tv.setText(data);
    }


}
