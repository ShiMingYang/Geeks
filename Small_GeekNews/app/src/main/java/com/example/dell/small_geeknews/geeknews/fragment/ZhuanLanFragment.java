package com.example.dell.small_geeknews.geeknews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.XiangQingActivity.ZhuanXiangActivity;
import com.example.dell.small_geeknews.geeknews.adapter.ZhuanLanAdapter;
import com.example.dell.small_geeknews.geeknews.geekapi.GeekServer;
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

public class ZhuanLanFragment extends Fragment {
    private View view;
    private RecyclerView mRlv;
    private ArrayList<ZhuanLanBean.DataBean> list;
    private ZhuanLanAdapter adapter;

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
        Observable<ZhuanLanBean> data = geekServer.zhuanlanData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhuanLanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhuanLanBean zhuanLanBean) {
                    list.addAll(zhuanLanBean.getData());
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
        mRlv.setLayoutManager(new GridLayoutManager(getContext(),2));
        list = new ArrayList<>();
        adapter = new ZhuanLanAdapter(getContext(), list);
        mRlv.setAdapter(adapter);

        adapter.SetOnItemClickLisener(new ZhuanLanAdapter.OnItemClickLisener() {

            private Intent intent;

            @Override
            public void OnItemClickLisener(int position) {
                intent = new Intent(getContext(), ZhuanXiangActivity.class);
                ZhuanLanBean.DataBean dataBean = list.get(position);

                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
    }
}
