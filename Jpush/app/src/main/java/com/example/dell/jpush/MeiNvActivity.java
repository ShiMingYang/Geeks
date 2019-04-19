package com.example.dell.jpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MeiNvActivity extends AppCompatActivity {
    private ImageView mIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_nv);
        String url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(mIv);
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
    }
}
