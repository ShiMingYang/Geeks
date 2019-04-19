package com.example.im_huanxin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dell on 2019/4/10.
 */

class ChatXlvAdapter extends RecyclerView.Adapter{
    public ArrayList<EMMessage> mlist;
    private OnItemClickLisener mlisener;

    public ChatXlvAdapter(ArrayList<EMMessage> list) {

        mlist = list;
    }


    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage = mlist.get(position);
        if(!emMessage.getUserName().equals(emMessage.getFrom())){
            return 1;
        }else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType==1){
            View inflate = inflater.inflate(R.layout.item_chat, null);
            return new ListViewHolder(inflate);
        }else {
            View inflate = inflater.inflate(R.layout.item_chat2, null);
            return new ListViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        EMMessage emMessage = mlist.get(position);
        String from = emMessage.getFrom();
        EMMessageBody body = emMessage.getBody();
        long msgTime = emMessage.getMsgTime();
        String format = formatTime(msgTime);

        String path = "";
        String[] split = body.toString().split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] split1 = s.split(":");
            for (int j = 0; j < split1.length; j++) {
                String s1 = split1[j];
                if (s1.startsWith("/storage/emulated")) {
                    path = s1;
                }
            }
        }
        int viewType = getItemViewType(position);
        if (viewType==1) {
            ListViewHolder holder1 = (ListViewHolder) holder;
           // holder1.mTv1.setText("来自:"+from+" 的消息,"+" "+format+"\r\n"+body.toString());
            holder1.mTv1.setText(body.toString());
            holder1.mTv2.setText("来自:"+from+" 的消息,"+" "+format+"\r\n");
          // holder1.mpic.setImageResource(R.mipmap.weixin);
        }else {
            ListViewHolder2 holder1 = (ListViewHolder2) holder;
            //holder1.mTv1.setText("来自:"+from+" 的消息,"+" "+format+"\r\n"+body.toString());
            holder1.mTv1.setText(body.toString());
            holder1.mTv2.setText("来自:"+from+" 的消息,"+" "+format+"\r\n");
           // holder1.mpic.setImageResource(R.mipmap.a);
        }

        final String finalPath = path;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlisener != null){
                    mlisener.OnItemClickLisener(position, finalPath);
                }
            }
        });
    }

    private String formatTime(long msgTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date date = new Date(msgTime);

        return dateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

            TextView mTv1;
            TextView mTv2;
            ImageView mpic;

            public ListViewHolder(View itemView) {
                super(itemView);

                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
                this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
                this.mpic = (ImageView) itemView.findViewById(R.id.pic);
            }
        }
    class ListViewHolder2 extends RecyclerView.ViewHolder {

            TextView mTv1;
            TextView mTv2;
            ImageView mpic;

            public ListViewHolder2(View itemView) {
                super(itemView);

                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
                this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
                this.mpic = (ImageView) itemView.findViewById(R.id.pic);
            }
        }

     public interface OnItemClickLisener{
             void OnItemClickLisener(int position,String autopath);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    public void addData(ArrayList<EMMessage> list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }

    public void addSingleMsg(EMMessage message) {
        mlist.add(message);
        notifyDataSetChanged();
    }
}
