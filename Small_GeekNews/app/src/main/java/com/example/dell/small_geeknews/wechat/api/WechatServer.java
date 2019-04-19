package com.example.dell.small_geeknews.wechat.api;

import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Dell on 2019/4/19.
 */

public interface WechatServer {

    public String Url="http://api.tianapi.com/";

    @GET("wxnew?")
    Observable<WecahtBean> wechatdata(@QueryMap HashMap<String,Object> map);

    @GET("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&")
    Observable<WecahtBean> wechatSodata(@Query("page") int page ,@Query("word") String world);
}
