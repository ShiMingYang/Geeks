package com.example.dell.small_geeknews.geeknews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.geeknews.XiangQingActivity.ZhuanXiangActivity;
import com.example.dell.small_geeknews.geeknews.geekbean.IdZhuanlanBean;
import com.example.dell.small_geeknews.geeknews.geekbean.ZhuanLanBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/17.
 */

public class ZhuanXiangAdapter extends RecyclerView.Adapter{
    private ZhuanXiangActivity mCOntext;
    private ArrayList<IdZhuanlanBean.StoriesBean> mlist;

    public ZhuanXiangAdapter(ZhuanXiangActivity zhuanXiangActivity, ArrayList<IdZhuanlanBean.StoriesBean> list) {

        mCOntext = zhuanXiangActivity;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mCOntext).inflate(R.layout.hotitem, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(mlist.get(position).getTitle());

        Glide.with(mCOntext).load(mlist.get(position).getImages().get(0)).into(holder1.mPic);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class ListViewHolder extends XRecyclerView.ViewHolder {
            ImageView mPic;
            TextView mTv1;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.pic);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);

            }
        }
}
