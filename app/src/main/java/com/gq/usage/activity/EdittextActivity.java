package com.gq.usage.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gq.usage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/05/10
 *     desc   : Edittext
 *     version: 1.0
 * </pre>
 */
public class EdittextActivity extends BaseActivity {
    private final String TAG = EdittextActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private EdittextActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        ButterKnife.bind(this);
        instance = this;
        initView();
    }

    private EdittextActivity getInstance() {
        return instance;
    }

    private void initView() {
        setSupportActionBar(toolbar);

    }
}
