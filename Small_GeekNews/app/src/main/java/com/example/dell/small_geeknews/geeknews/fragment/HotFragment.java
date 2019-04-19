package com.example.dell.small_geeknews.geeknews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.adapter.HotAdapter;
import com.example.dell.small_geeknews.geeknews.geekapi.GeekServer;
import com.example.dell.small_geeknews.geeknews.geekbean.HotBean;
import com.example.dell.small_geeknews.geeknews.geekbean.ZhuanLanBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/17.
 */

public class HotFragment extends Fragment {
    private View view;
    private RecyclerView mRlv;
    private ArrayList<HotBean.RecentBean> list;
    private HotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zhuanlan, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);
        Observable<HotBean> data = geekServer.hotData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                    list.addAll(hotBean.getRecent());
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

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        adapter = new HotAdapter(getContext(),list);
        mRlv.setAdapter(adapter);

    }
}
