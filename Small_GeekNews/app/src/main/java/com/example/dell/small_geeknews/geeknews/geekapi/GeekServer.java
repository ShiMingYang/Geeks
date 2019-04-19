package com.example.dell.small_geeknews.geeknews.geekapi;

import com.example.dell.small_geeknews.geeknews.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.geeknews.geekbean.HotBean;
import com.example.dell.small_geeknews.geeknews.geekbean.IdZhuanlanBean;
import com.example.dell.small_geeknews.geeknews.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.geeknews.geekbean.ZhuanLanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dell on 2019/4/17.
 */

public interface GeekServer {

     public String daynewsUrl="http://news-at.zhihu.com/api/4/";
     //日报
     @GET("news/latest")
     Observable<DayNewsBean> daynewsData();

     @GET("news/before/{date} ")
     Observable<MoreDayNewsBean> moreDayNewsData(@Path("date") int date);

     //专栏数据
     @GET("sections")
     Observable<ZhuanLanBean> zhuanlanData();
     //专栏数据
     @GET("section/{id}")
     Observable<IdZhuanlanBean> zhuanlan2Data(@Path("id") int id);

     //热门数据
     @GET("news/hot")
     Observable<HotBean> hotData();



}
