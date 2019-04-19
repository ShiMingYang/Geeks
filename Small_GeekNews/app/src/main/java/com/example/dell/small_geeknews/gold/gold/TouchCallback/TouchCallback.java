package com.example.dell.small_geeknews.gold.gold.TouchCallback;

/**
 * Created by Dell on 2019/4/19.
 */

public interface TouchCallback {

    //移动某个条目到某个位置
     void  onItemMove(int fromposition,int toposition);

    //删除条目
    void onItemDelete(int position);
}
