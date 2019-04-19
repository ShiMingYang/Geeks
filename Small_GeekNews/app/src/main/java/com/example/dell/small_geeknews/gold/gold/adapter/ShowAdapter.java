package com.example.dell.small_geeknews.gold.gold.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.gold.gold.ShowActivity;
import com.example.dell.small_geeknews.gold.gold.TouchCallback.TouchCallback;
import com.example.dell.small_geeknews.gold.gold.bean.GoldShowBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.http.POST;

/**
 * Created by Dell on 2019/4/18.
 */

public class ShowAdapter extends RecyclerView.Adapter implements TouchCallback{
    private ShowActivity mContext;
    private ArrayList<GoldShowBean> mlist;

    public ShowAdapter(ShowActivity showActivity, ArrayList<GoldShowBean> list) {

        mContext = showActivity;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.show_gold, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        final GoldShowBean bean = mlist.get(position);
        holder1.mTv.setText(bean.title);

        holder1.mSc.setChecked(bean.isChecked);
        holder1.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override //设置数据
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onItemMove(int fromposition, int toposition) {
        Collections.swap(mlist,fromposition, toposition);
        //局部刷新,索引混乱,越界
        notifyItemMoved(fromposition,toposition);
    }

    @Override
    public void onItemDelete(int position) {
        mlist.remove(position);
        //刷新条目删除后的数据
        // 局部刷新,索引混乱,越界
        notifyItemRemoved(position);
    }

    class ListViewHolder extends XRecyclerView.ViewHolder {
        TextView mTv;
        SwitchCompat mSc;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);
            this.mSc = (SwitchCompat) itemView.findViewById(R.id.sc);
        }
    }

}
