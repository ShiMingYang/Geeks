package com.example.dell.small_geeknews.base;

/**
 * Created by Dell on 2019/4/3.
 */

public abstract class BasePresenter<v extends BaseView> {

    protected v mView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(v baseview) {
        this.mView=baseview;
    }

    public void onDestroy(){
        mView=null;

    }
}
