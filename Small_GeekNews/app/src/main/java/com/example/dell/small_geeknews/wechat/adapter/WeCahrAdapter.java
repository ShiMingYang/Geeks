package com.example.dell.small_geeknews.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/4/19.
 */

public class WeCahrAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    public ArrayList<WecahtBean.NewslistBean> mlist;

    public WeCahrAdapter(Context context, ArrayList<WecahtBean.NewslistBean> list) {

        mcontext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.wechat_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(mlist.get(position).getTitle());
        holder1.mTv2.setText(mlist.get(position).getDescription());
        holder1.mTv3.setText(mlist.get(position).getCtime());

        Glide.with(mcontext).load(mlist.get(position).getPicUrl()).into(holder1.mPic);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }




    class ListViewHolder extends XRecyclerView.ViewHolder {
            ImageView mPic;
            TextView mTv1;
            TextView mTv2;
            TextView mTv3;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.iv);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
                this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
                this.mTv3 = (TextView) itemView.findViewById(R.id.tv3);
            }
        }
}
