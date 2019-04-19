package com.example.dell.small_geeknews.geeknews.adapter;

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
import com.example.dell.small_geeknews.geeknews.geekbean.ZhuanLanBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/17.
 */

public class ZhuanLanAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private ArrayList<ZhuanLanBean.DataBean> mlist;
    private OnItemClickLisener mlisener;

    public ZhuanLanAdapter(Context context, ArrayList<ZhuanLanBean.DataBean> list) {

        mcontext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.zhuanlanitem, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(mlist.get(position).getDescription());
        holder1.mTv2.setText(mlist.get(position).getName());

        Glide.with(mcontext).load(mlist.get(position).getThumbnail())
                .into(holder1.mPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlisener!=null) {
                    mlisener.OnItemClickLisener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

     public interface OnItemClickLisener{
             void OnItemClickLisener(int position);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    class ListViewHolder extends XRecyclerView.ViewHolder {
            ImageView mPic;
            TextView mTv1;
            TextView mTv2;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.pic);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
                this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
            }
        }
}
