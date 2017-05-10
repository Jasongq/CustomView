package com.gq.usage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/05/10
 *     desc   : BaseFragment
 *     version: 1.0
 * </pre>
 */
public class BaseFragment extends Fragment {
    /**
     * 当前界面是否呈现给用户的状态标志
     */
    protected boolean isVisible;

    /**
     * 重写Fragment父类生命周期方法，在onCreate之前调用该方法，实现Fragment数据的缓加载.
     * @param isVisibleToUser 当前是否已将界面显示给用户的状态
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;// 相当于Fragment的onResume
            onVisible();
        }else {
            isVisible=false;// 相当于Fragment的onPause
            onInvisible();
        }
    }
    /**
     * 当界面呈现给用户，即设置可见时执行，进行加载数据的方法
     * 在用户可见时加载数据，而不在用户不可见的时候加载数据，是为了防止控件对象出现空指针异常
     */
    protected  void onVisible() {
        setlazyLoad();
    }
    /**
     * 当界面还没呈现给用户，即设置不可见时执行
     */
    protected  void onInvisible() {
    }
    /**
     * 加载数据方法
     */
    protected void setlazyLoad() {
    }
    public static BaseFragment newInstance(String info) {
        Bundle args = new Bundle();
        BaseFragment fragment = new BaseFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }
}
