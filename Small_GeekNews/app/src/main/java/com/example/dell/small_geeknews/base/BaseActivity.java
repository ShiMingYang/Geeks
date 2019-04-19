package com.example.dell.small_geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Dell on 2019/4/3.
 */

public abstract class BaseActivity<p extends BasePresenter,v extends BaseView> extends AppCompatActivity {

    protected p presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter!=null) {
            presenter.bind((v)this);

        }
        initView();
        initListener();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract p initPresenter();

    protected abstract int getlayoutId();
}
