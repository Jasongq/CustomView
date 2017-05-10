package com.gq.usage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gq.usage.R;
import com.gq.usage.activity.EdittextActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/05/02
 *     desc   : 4
 *     version: 1.0
 * </pre>
 */
public class FourFragment extends BaseFragment {
    private final String TAG = FourFragment.class.getSimpleName();

    Unbinder unbinder;
    @BindView(R.id.EditTv)
    AppCompatTextView EditTv;
    @BindView(R.id.moreTv)
    AppCompatTextView moreTv;
    private boolean isPrepared;// 判断是否已经初始化完成。

    /**
     * Create a new instance of the fragment
     */
    public static FourFragment newInstance(String info) {
        Bundle args = new Bundle();
        FourFragment fragment = new FourFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.EditTv, R.id.moreTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.EditTv:
                startActivity(new Intent(getActivity(), EdittextActivity.class));
                break;
            case R.id.moreTv:
                break;
        }
    }
}
