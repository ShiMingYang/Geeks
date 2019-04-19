package com.example.im_huanxin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.im_huanxin.utils.ToastUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUname;
    private EditText mPwd;
    /**
     * 登录
     */
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mUname = (EditText) findViewById(R.id.uname);
        mPwd = (EditText) findViewById(R.id.pwd);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                register();
                break;
        }
    }

    //注册
    private void register() {
        final String psd = mPwd.getText().toString().trim();
        final String name = mUname.getText().toString().trim();
        if (TextUtils.isEmpty(psd) || TextUtils.isEmpty(name)){
            ToastUtil.showShort("用户名或者密码不能为空");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(name, psd);//同步方法
                    showToast("注册成功");
                    finish();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    showToast("注册失败");
                }
            }
        }).start();
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
