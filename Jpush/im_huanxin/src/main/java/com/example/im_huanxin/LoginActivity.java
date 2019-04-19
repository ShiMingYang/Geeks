package com.example.im_huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.im_huanxin.utils.SpUtil;
import com.example.im_huanxin.utils.ToastUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUname;
    private EditText mPwd;
    /**
     * 登录
     */
    private Button mBtn;
    /**
     * 登录
     */
    private Button mBtn2;

    //石明洋 1808D
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否已经自动登陆了,如果登录了直接跳到主页面
      /*  boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
        if (loggedInBefore){
            go2Home();
        }*/
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUname = (EditText) findViewById(R.id.uname);
        mPwd = (EditText) findViewById(R.id.pwd);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;
            case R.id.btn2:
                login();
                break;
        }
    }

    private void login() {

        final String name = mUname.getText().toString();
        final String password = mUname.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(name)){
            ToastUtil.showShort("用户名或者密码不能为空");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(name,password,new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                        showToast("登录成功");
                        SpUtil.setParam(Constants.NAME,name);
                         go2Home();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                        showToast("登录失败");
                    }
                });
            }
        }).start();
    }

    private void go2Home() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showShort(msg);
            }
        });
    }
}
