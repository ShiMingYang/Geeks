package com.example.im_huanxin;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.im_huanxin.utils.SpUtil;
import com.example.im_huanxin.utils.ToastUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mLv;
    private LvAdapter adapter;
    /**
     * 用户：
     */
    private TextView mTv;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniPer();
        initView();
        initFriends();
    }

    private void iniPer() {
        String[] per = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE};

        ActivityCompat.requestPermissions(this, per, 100);
    }
    private void initFriends() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.d(TAG, "run: "+usernames.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addData(usernames);
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initView() {
        mLv = (RecyclerView) findViewById(R.id.lv);
        mTv = (TextView) findViewById(R.id.tv);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        mTv.setText("当前用户:"+name);
        mLv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new LvAdapter(list);
        mLv.setAdapter(adapter);

        adapter.SetOnItemClickLisener(new LvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                go2ChatActivity(adapter.mlist.get(position));
            }
        });


    }

    private void go2ChatActivity(String friendName) {
        Intent intent = new Intent(this,ChatActivity.class);
        intent.putExtra(Constants.NAME,friendName);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 0, "退出登录");
        menu.add(1, 2, 0, "群聊");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
              goout();
                break;
            case 2:
              startActivity(new Intent(MainActivity.this,ChatGroupActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goout() {
        //退出登录

            EMClient.getInstance().logout(true, new EMCallBack() {

                @Override
                public void onSuccess() {
                    // TODO Auto-generated method stub
                    showToast("退出成功");
                    SpUtil.setParam(Constants.NAME,"");
                    go2Login();
                }

                @Override
                public void onProgress(int progress, String status) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    showToast("退出失败");
                }
            });
        }

    private void go2Login() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    public void showToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showShort(msg);
            }
        });
    }
}
