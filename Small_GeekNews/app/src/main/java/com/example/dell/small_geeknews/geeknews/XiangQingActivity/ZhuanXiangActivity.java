package com.example.dell.small_geeknews.geeknews.XiangQingActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.adapter.ZhuanXiangAdapter;
import com.example.dell.small_geeknews.geeknews.geekapi.GeekServer;
import com.example.dell.small_geeknews.geeknews.geekbean.IdZhuanlanBean;
import com.example.dell.small_geeknews.geeknews.geekbean.ZhuanLanBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuanXiangActivity extends AppCompatActivity {

    private static final String TAG = "ZhuanXiangActivity";
    private RecyclerView mRlv;
    private ZhuanXiangAdapter adapter;

    private ArrayList<IdZhuanlanBean.StoriesBean> list;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_xiang);
        id = getIntent().getIntExtra("id",0);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);

        Observable<IdZhuanlanBean> data = geekServer.zhuanlan2Data(id);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IdZhuanlanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IdZhuanlanBean zhuanLanBean) {
                        list.addAll(zhuanLanBean.getStories());
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ZhuanXiangAdapter(this, list);
        mRlv.setAdapter(adapter);
    }
}
