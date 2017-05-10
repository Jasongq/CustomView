package com.gq.usage.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * @Created : 2017/3/22
 * @Author :GuoQiang
 * @Description :bannerAdapter
 */

public class BannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> imageList;

    public BannerAdapter(Context mContext, List<String> imageList) {
        this.mContext = mContext;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return null == imageList ? 0 : imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext).load(imageList.get(position))
                /*.fallback(R.drawable.ic_error)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_placeholder)*/
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }
}
