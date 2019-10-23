package com.li.gohome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.li.gohome.R;
import com.li.gohome.baen.CommonModelListBean;
import com.li.gohome.baen.ExampleBaseBean;
import com.li.gohome.baen.HomeModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerListener {
    List<ExampleBaseBean> datas = new ArrayList<>();
    public static final int BANNER_TYPE = 0;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == BANNER_TYPE){
            return new BannerHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.top_layout,parent,false
            ));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BannerHolder){
//放图片地址的集合
            List<String> list_path = new ArrayList<>();
            //放标题的集合
            List<String> list_title = new ArrayList<>();
            final CommonModelListBean bannerBean = (CommonModelListBean)datas.get(position);
            Log.v("kk",bannerBean.getCommonModelListBean().get(0).getIcon()+"==");
            for (int a = 0; a < bannerBean.getCommonModelListBean().size(); a++) {
                list_path.add(bannerBean.getCommonModelListBean().get(a).getIcon());
                list_title.add("好好学习" + a);
            }
            //设置内置样式，共有六种可以点入方法内逐一体验使用。
            ((BannerHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器，图片加载器在下方
            ((BannerHolder) holder).banner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            ((BannerHolder) holder).banner.setImages(list_path);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            ((BannerHolder) holder).banner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            ((BannerHolder) holder).banner.setBannerTitles(list_title);
            //设置轮播间隔时间
            ((BannerHolder) holder).banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            ((BannerHolder) holder).banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            ((BannerHolder) holder).banner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(this)
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        Log.v("kk",datas.size()+"");
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(datas.size()>0){
            return datas.get(position).getViewType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    public class BannerHolder extends RecyclerView.ViewHolder{
        Banner banner;
        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.top_item);
        }
    }

    public void addData(HomeModel model){
        CommonModelListBean bannerList = new CommonModelListBean();
        bannerList.setCommonModelListBean(model.getbannerList());
        bannerList.setViewType(BANNER_TYPE);
        datas.add(bannerList);
        notifyDataSetChanged();

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
