package com.example.dell.small_geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/3.
 */

public abstract class BaseFragment<p extends BasePresenter,v extends BaseView> extends Fragment implements BaseView {

    protected p presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getlayoutId(), null);
        unbinder = ButterKnife.bind(this, inflate);
        presenter = initGeekP();
        if (presenter !=null) {
            presenter.bind(this);
        }
        initView();
        initListener();
        initData();
        return inflate;
    }

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void initView() {

    }

    protected abstract p initGeekP();

    protected abstract int getlayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter =null;
    }
}
