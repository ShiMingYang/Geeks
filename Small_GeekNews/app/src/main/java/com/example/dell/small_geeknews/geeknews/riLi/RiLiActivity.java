package com.example.dell.small_geeknews.geeknews.riLi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.geekapi.GeekServer;
import com.example.dell.small_geeknews.geeknews.geekbean.MoreDayNewsBean;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

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

public class RiLiActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "RiLiActivity";
    private Toolbar mToolbar;
    private CalendarView mCalendarview;
    private ImageView mIv;
    private MaterialCalendarView mCalendarView;
    /**
     * 确定
     */
    private Button mBtn;
    private String datess;
    private String mData;

    /**
     * 确定
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ri_li);
        initView();



    }




    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mIv.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date oldDate = date.getDate();
                //设置日期格式
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                mData = df.format(oldDate);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv:
                finish();
                break;
            case R.id.btn:
                if (mData != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date",mData);
                    setResult(200,intent);
                }
                finish();
                break;
        }
    }
}
