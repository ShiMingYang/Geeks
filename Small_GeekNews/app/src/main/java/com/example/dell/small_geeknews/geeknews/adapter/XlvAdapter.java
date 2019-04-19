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
import com.example.dell.small_geeknews.geeknews.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.geeknews.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.geeknews.geekbean.StoriesBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/17.
 */

public class XlvAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private ArrayList<StoriesBean> mlist= new ArrayList<>();
    private ArrayList<DayNewsBean.TopStoriesBean> mbanners= new ArrayList<>();

    private String mdata;


    public XlvAdapter(Context context) {
        mcontext = context;



    }

    @Override
    public int getItemViewType(int position) {
        if (mbanners.size() > 0) {
            if (position == 0) {
                return 1;
            } else if (position == 1) {
                return 2;
            } else {
                return 3;
            }
        } else {
            if (position == 0) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        if (viewType == 1) {
            View inflate = inflater.inflate(R.layout.banners, null);
            return new BannerViewHolder(inflate);
        } else if (viewType == 2) {
            View inflate = inflater.inflate(R.layout.texts, null);
            return new TextViewHolder(inflate);
        } else {
            View inflate = inflater.inflate(R.layout.geeklist, null);
            return new ListViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == 1) {
            BannerViewHolder holder1 = (BannerViewHolder) holder;
              holder1.mBannerss.setImages(mbanners).setImageLoader(new ImageLoader() {
                  @Override
                  public void displayImage(Context context, Object path, ImageView imageView) {
                      DayNewsBean.TopStoriesBean path1 = (DayNewsBean.TopStoriesBean) path;
                      Glide.with(mcontext).load(path1.getImage()).into(imageView);
                  }
              }).start();
        } else if (viewType==2) {
            TextViewHolder holder1 = (TextViewHolder) holder;

            holder1.mTv.setText(mdata);
        }else {
              int  newposition=position-1;
            if (mbanners.size()>0) {
                newposition-=1;

            }
            ListViewHolder holder1 = (ListViewHolder) holder;
            holder1.mTv.setText(mlist.get(newposition).getTitle());

            Glide.with(mcontext).load(mlist.get(newposition).getImages().get(0)).into(holder1.mPic);
        }

    }

    @Override
    public int getItemCount() {
        if (mbanners.size() > 0) {
            return mlist.size() + 1 + 1;
        } else {
            return mlist.size() + 1;
        }
    }

    public void setBeforedata(MoreDayNewsBean moreDayNewsBean) {
        mdata = moreDayNewsBean.getDate();

        mlist.clear();
        mbanners.clear();
        if (moreDayNewsBean.getStories() != null && moreDayNewsBean.getStories().size() > 0){
            mlist.addAll(moreDayNewsBean.getStories());
        }
        notifyDataSetChanged();
    }



    public void setData(DayNewsBean dayNewsBean) {
        mdata=dayNewsBean.getDate();

        mbanners.clear();
        if (dayNewsBean.getTop_stories().size() > 0 && dayNewsBean.getTop_stories() != null) {
            mbanners.addAll(dayNewsBean.getTop_stories());
        }
        mlist.clear();
        if (dayNewsBean.getStories() != null && dayNewsBean.getStories().size() > 0) {
            mlist.addAll(dayNewsBean.getStories());
        }
        notifyDataSetChanged();
    }

    class BannerViewHolder extends XRecyclerView.ViewHolder {
        Banner mBannerss;

        public BannerViewHolder(View itemView) {
            super(itemView);
            this.mBannerss = (Banner) itemView.findViewById(R.id.bannerss);
        }
    }

    class TextViewHolder extends XRecyclerView.ViewHolder {
        TextView mTv;
        public TextViewHolder(View itemView) {
            super(itemView);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    class ListViewHolder extends XRecyclerView.ViewHolder {
        ImageView mPic;
        TextView mTv;
        public ListViewHolder(View itemView) {
            super(itemView);
            this.mPic = (ImageView) itemView.findViewById(R.id.pic);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }


}
