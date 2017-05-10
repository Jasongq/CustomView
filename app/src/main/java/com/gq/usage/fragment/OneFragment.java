package com.gq.usage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gq.usage.R;
import com.gq.usage.adapter.BannerAdapter;
import com.gq.widget.CircleIndicator;
import com.gq.widget.loop.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/05/02
 *     desc   : 1
 *     version: 1.0
 * </pre>
 */
public class OneFragment extends BaseFragment {
    private final String TAG = OneFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.pagerHeader1)
    LoopViewPager loopViewPager1;
    @BindView(R.id.circleIndicator1)
    CircleIndicator circleIndicator1;
    @BindView(R.id.pagerHeader2)
    ViewPager loopViewPager2;
    @BindView(R.id.circleIndicator2)
    CircleIndicator circleIndicator2;
    @BindView(R.id.pagerHeader3)
    LoopViewPager loopViewPager3;
    @BindView(R.id.circleIndicator3)
    CircleIndicator circleIndicator3;
    private boolean isPrepared;// 判断是否已经初始化完成。

    private List<String> imageList = new ArrayList<>();

    /**
     * Create a new instance of the fragment
     */
    public static OneFragment newInstance(String info) {
        Bundle args = new Bundle();
        OneFragment fragment = new OneFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        isPrepared = true;
        setlazyLoad();
        return view;
    }


    @Override
    protected void setlazyLoad() {
        if (!isPrepared || !isVisible) {
            //判断isPrepared和isVisible只要有一个不为true就不往下执行。也就是仅当初始化完成，并且可见的时候才继续加载，这样的避免了未初始化完成就使用而带来的问题
            return;
        }
    }

    private void initView() {
//        textView.setText(getArguments().getString("info"));
        imageList.add("http://img15.3lian.com/2015/a1/16/d/204.jpg");
        imageList.add("http://img.yanj.cn/store/goods/2093/2093_75db88665f8edbf6db1bb500c64a5dc9.jpg_max.jpg");
        imageList.add("http://img4.3lian.com/img2005/05/19/14.jpg");
        imageList.add("http://pic15.nipic.com/20110803/7180732_211822337168_2.jpg");
        BannerAdapter bannerAdapter = new BannerAdapter(getActivity(), imageList);
        //-------样式1----------
        loopViewPager1.setAdapter(bannerAdapter);
        circleIndicator1.setViewPager(loopViewPager1);
        //-------样式2----------
        loopViewPager2.setAdapter(bannerAdapter);
        circleIndicator2.setViewPager(loopViewPager2);
        //-------样式3----------
        loopViewPager3.setAdapter(bannerAdapter);
        circleIndicator3.setViewPager(loopViewPager3);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
