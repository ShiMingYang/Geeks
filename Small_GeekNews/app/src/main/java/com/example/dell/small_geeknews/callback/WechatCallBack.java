package com.example.dell.small_geeknews.callback;

import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

/**
 * Created by Dell on 2019/4/19.
 */

public interface WechatCallBack {

    void onSuccess(WecahtBean wecahtBean);

    void onFail(String string);
}
