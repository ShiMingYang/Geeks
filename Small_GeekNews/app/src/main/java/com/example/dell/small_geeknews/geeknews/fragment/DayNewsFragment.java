package com.example.dell.small_geeknews.geeknews.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.adapter.XlvAdapter;
import com.example.dell.small_geeknews.geeknews.geekapi.GeekServer;
import com.example.dell.small_geeknews.geeknews.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.geeknews.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.geeknews.riLi.RiLiActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class DayNewsFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView mRlv;
    private XlvAdapter adapter;

    private FloatingActionButton mFloatbutton;

    private int intDate;
   // private long durationMills = CircularAnimUtil.PERFECT_MILLS;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.daynews, null);

        initView(inflate);
        initData();
        initData2();
        return inflate;
    }

    private void initData2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);
        Observable<MoreDayNewsBean> data1 = geekServer.moreDayNewsData(intDate);
        data1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreDayNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoreDayNewsBean moreDayNewsBean) {
                        adapter.setBeforedata(moreDayNewsBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);
        final Observable<DayNewsBean> data = geekServer.daynewsData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DayNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DayNewsBean dayNewsBean) {


                        adapter.setData(dayNewsBean);
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

        mFloatbutton = (FloatingActionButton) inflate.findViewById(R.id.floatbutton);
        mFloatbutton.setOnClickListener(this);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new XlvAdapter(getContext());
        mRlv.setAdapter(adapter);


        mRlv.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String date = data.getStringExtra("date");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
            String todayDate = df.format(new Date());

            if (date.equals(todayDate)) {
                initData();
            } else {
                //因为获取出来的数据是当前日期的前一天,所以要给他加1
                intDate = Integer.valueOf(date);
                intDate += 1;
                initData2();
            }


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.floatbutton:
                Intent intent = new Intent(getContext(),RiLiActivity.class);
                startActivityForResult(intent,100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //action();
                }
                break;
            case R.id.rlv:
                break;
        }
    }

   /* @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void action() {

            int[] location = new int[2];
            mFloatbutton.getLocationInWindow(location);
            final int cx = location[0] + mFloatbutton.getWidth() / 2;
            final int cy = location[1] + mFloatbutton.getHeight() / 2;
            final ImageView view = new ImageView(getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(R.color.colorAccent);
            final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
            int w = decorView.getWidth();
            int h = decorView.getHeight();
            decorView.addView(view, w, h);

            // 计算中心点至view边界的最大距离
            int maxW = Math.max(cx, w - cx);
            int maxH = Math.max(cy, h - cy);
            final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
            // 若使用默认时长，则需要根据水波扩散的距离来计算实际时间
            if (durationMills == PERFECT_MILLS) {
                // 算出实际边距与最大边距的比率
                double rate = 1d * finalRadius / maxRadius;
                // 水波扩散的距离与扩散时间成正比
                durationMills = (long) (PERFECT_MILLS * rate);
            }
            final long finalDuration = durationMills;
            anim.setDuration(finalDuration);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Intent intent = new Intent(getContext(),RiLiActivity.class);
                    startActivityForResult(intent,100);
                    // 默认渐隐过渡动画.
                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    // 默认显示返回至当前Activity的动画.
                    mFloatbutton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Animator anim =
                                    ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                            anim.setDuration(finalDuration);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    try {
                                        decorView.removeView(view);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            anim.start();
                        }
                    }, 1000);
                }
            });
            anim.start();
        }
*/
}
