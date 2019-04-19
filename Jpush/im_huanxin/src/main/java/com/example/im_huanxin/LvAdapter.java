package com.example.im_huanxin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/4/9.
 */

class LvAdapter extends RecyclerView.Adapter{
    public ArrayList<String> mlist;
    private OnItemClickLisener mlisener;

    public LvAdapter(ArrayList<String> list) {

        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(mlist.get(position));
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlisener!=null){
                    mlisener.OnItemClickLisener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void addData(List<String> usernames) {
        mlist.addAll(usernames);
        notifyDataSetChanged();
    }

    public interface OnItemClickLisener{
             void OnItemClickLisener(int position);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    class ListViewHolder extends RecyclerView.ViewHolder {
            ImageView mPic;
            TextView mTv1;

            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.iv);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);

            }
        }
}
