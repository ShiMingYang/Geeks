package com.example.dell.small_geeknews.gold.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseActivity;
import com.example.dell.small_geeknews.gold.gold.TouchCallback.SimpleTouchHelperCallBack;
import com.example.dell.small_geeknews.gold.gold.adapter.ShowAdapter;
import com.example.dell.small_geeknews.gold.gold.bean.GoldShowBean;
import com.example.dell.small_geeknews.presenter.EmptyP;
import com.example.dell.small_geeknews.view.EmptyV;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyP, EmptyV> implements EmptyV {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private ArrayList<GoldShowBean> list;
    private ShowAdapter showAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        list = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra("data");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.back1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finishclose();//关闭界面时候要把数据带回去
            }
        });

        //页面展示
        rlv.setLayoutManager(new LinearLayoutManager(this));
       rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        showAdapter = new ShowAdapter(this,list);
        rlv.setAdapter(showAdapter);

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack
                = new SimpleTouchHelperCallBack(showAdapter);
       //设置左边滑动删除
        simpleTouchHelperCallBack.setSwipeEnable(false);

        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);

        helper.attachToRecyclerView(rlv);

    }

    private void finishclose() {
        //关闭界面时候要把数据带回去
        Intent intent = new Intent();
        intent.putExtra("data",list);
        setResult(200,intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        finishclose();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_show;
    }


}
