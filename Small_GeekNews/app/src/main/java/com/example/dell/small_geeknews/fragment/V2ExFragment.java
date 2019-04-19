package com.example.dell.small_geeknews.fragment;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.V2exP;
import com.example.dell.small_geeknews.presenter.ZhiHuP;
import com.example.dell.small_geeknews.view.V2exV;
import com.example.dell.small_geeknews.view.ZhiHuV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Dell on 2019/4/3.
 */

public class V2ExFragment extends BaseFragment<V2exP,V2exV> implements V2exV{
    @Override
    protected V2exP initGeekP() {
        return new V2exP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.v2ex_fagment;
    }

    String mUrl="https://www.v2ex.com/";

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素，因为数据显示只有一个，所以直接get调用
                    Element Tabs = doc.select("div#Tabs").first();
                     //查找带有href属性的a元素
                    Elements AllTabs = Tabs.select("a[href]");
                    for (Element elements :AllTabs) {
                        String href = elements.attr("href");
                        String tab = elements.text();
                        
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
