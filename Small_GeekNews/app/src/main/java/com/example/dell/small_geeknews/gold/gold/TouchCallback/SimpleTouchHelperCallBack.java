package com.example.dell.small_geeknews.gold.gold.TouchCallback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Dell on 2019/4/19.
 */

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {
    //回调接口
    private TouchCallback touchCallback;
    private boolean mSwipeEnable = true;

    public SimpleTouchHelperCallBack(TouchCallback touchCallback) {
        this.touchCallback = touchCallback;
    }
    /**
     *返回可以滑动的方向,一般使用makeMovementFlags
     * (int,int)或makeFlag(int, int)来构造我们的返回值
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //允许向左滑动
        int swipe=ItemTouchHelper.LEFT;
        //drag,拖拽的方向
        //swipe 滑动方向
        return makeMovementFlags(drag,swipe);
    }
    /**
     *拖动item时回调,可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，表示被拖动的ViewHolder已经移动到了目的位置
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        touchCallback.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        //最后返回true，表示被拖动的ViewHolder已经移动到了目的位置
        return true;
    }

    /**
     * 当用户左右滑动item时达到删除条件就会调用,一般为一半,条目继续滑动删除,否则弹回
     * @param viewHolder
     * @param direction
     */

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
         touchCallback.onItemDelete(viewHolder.getAdapterPosition());
    }
    /**
     * 支持滑动,即可以调用到onSwiped()方法,默认是true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }

    /**
     * 设置是否支持滑动
     * @param enable
     */
    public void setSwipeEnable(boolean enable){
        mSwipeEnable = enable;
    }

}
