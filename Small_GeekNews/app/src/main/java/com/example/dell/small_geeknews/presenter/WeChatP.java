package com.example.dell.small_geeknews.presenter;

import com.example.dell.small_geeknews.base.BasePresenter;
import com.example.dell.small_geeknews.callback.WechatCallBack;
import com.example.dell.small_geeknews.model.WechatM;
import com.example.dell.small_geeknews.view.WeChatV;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

/**
 * Created by Dell on 2019/4/3.
 */

public class WeChatP extends BasePresenter<WeChatV> {

    protected WechatM wechatM;

    @Override
    protected void initModel() {
        wechatM = new WechatM();
    }

    public void getdata(String key, int num, int page) {
        wechatM.getdata(key, num, page, new WechatCallBack() {
            @Override
            public void onSuccess(WecahtBean wecahtBean) {
                mView.setData(wecahtBean);
            }

            @Override
            public void onFail(String string) {
                mView.setError(string);
            }
        });
    }

    public void setWechatSoData(int page, String query) {
        wechatM.setWechatSoData(page, query, new WechatCallBack() {
            @Override
            public void onSuccess(WecahtBean wecahtBean) {
                mView.setData(wecahtBean);
            }

            @Override
            public void onFail(String string) {
                mView.setError(string);
            }
        });
    }
}
