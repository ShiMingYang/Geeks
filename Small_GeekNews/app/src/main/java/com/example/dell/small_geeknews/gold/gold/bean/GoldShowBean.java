package com.example.dell.small_geeknews.gold.gold.bean;

import java.io.Serializable;

/**
 * @author xts
 *         Created by asus on 2019/4/18.
 */

public class GoldShowBean implements Serializable{
    public String title;
    public boolean isChecked;

    public GoldShowBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
