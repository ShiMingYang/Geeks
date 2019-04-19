package com.example.dell.small_geeknews.view;

import com.example.dell.small_geeknews.base.BaseView;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

/**
 * Created by Dell on 2019/4/3.
 */

public interface WeChatV extends BaseView {

    void setData(WecahtBean wecahtBean);

    void setError(String string);


}
