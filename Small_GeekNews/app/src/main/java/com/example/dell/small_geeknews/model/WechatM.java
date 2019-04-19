package com.example.dell.small_geeknews.model;

import android.util.Log;

import com.example.dell.small_geeknews.base.BaseModel;
import com.example.dell.small_geeknews.callback.WechatCallBack;
import com.example.dell.small_geeknews.wechat.api.WechatServer;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/19.
 */

public class WechatM extends BaseModel {

    public void getdata(String key,int num,int page,final WechatCallBack wechatCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WechatServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WechatServer wechatServer = retrofit.create(WechatServer.class);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("key",key);
        map1.put("num",num);
        map1.put("page",page);
        Observable<WecahtBean> wechatdata = wechatServer.wechatdata(map1);

        wechatdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WecahtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WecahtBean wecahtBean) {
                    wechatCallBack.onSuccess(wecahtBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setWechatSoData(int page, String query, final WechatCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WechatServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WechatServer wechatServer = retrofit.create(WechatServer.class);


        wechatServer.wechatSodata(page,query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WecahtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WecahtBean wecahtBean) {
                        if (wecahtBean.getCode() == 200){
                            callBack.onSuccess(wecahtBean);
                        }else {
                            Log.i("tag", "onNext: " + 655555656);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
