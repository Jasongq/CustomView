package com.gq.usage.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gq.usage.MyApplication;
import com.gq.usage.R;
import com.gq.widget.manager.SystemBarTintManager;
import com.gq.widget.utils.DisplayUtils;

/**
 * @Created : 2017/3/16
 * @Author :GuoQiang
 * @Description :
 */

public class BaseActivity extends AppCompatActivity {
    private Toast mToast;
    private BaseActivity mContext;
    private MyApplication myApplication;
    protected SystemBarTintManager tintManager;
    private int mColorId= R.color.colorPrimaryDark;//状态栏的默认背景色

//    private UpdateUtils updateUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        if (null==myApplication){
            myApplication=(MyApplication)getApplication();
        }
        //初始化沉浸式
        if (isNeedLoadStatusBar()) {
            setStatusBar();
        }
        //--------
        addActivity();
    }

    /**
     * 状态栏颜色
     */
    public void setStatusBar() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager=new SystemBarTintManager(mContext);
        tintManager.setStatusBarTintEnabled(true);// 激活状态栏设置
        tintManager.setNavigationBarTintEnabled(true);// 激活导航栏设置
        // 自定义颜色  不设置的话，默认是个浅灰色
        tintManager.setStatusBarTintResource(getColorId());
        //官方在Android6.0中提供了亮色状态栏模式，配置很简单:
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    /**
     * 如果子类使用非默认的StatusBar,就重写此方法,传入布局的id
     */
    protected void setColorId() {
//        this.mColorId=R.color.color_red;//子类重写方式
    }

    protected int getColorId() {
        return mColorId;
    }
    /**
     * 子类是否需要实现沉浸式,默认需要
     * @return
     */
    protected boolean isNeedLoadStatusBar() {
        return true;
    }
    /**
     * 吐司提示
     * @param context
     * @param message
     */
    public void ToastShort(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public void ToastLong( Context context,  String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 描述：Toast提示文本.
     * @param resId 文本的资源ID
     */
    public void ToastShort( Context context,  int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), "" + context.getResources().getText(resId), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }
    public void ToastShortBottom( Context context,  int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), "" + context.getResources().getText(resId), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.setGravity(Gravity.BOTTOM, 0, DisplayUtils.dip2px(context,80));
        mToast.show();
    }

    public void ToastLong( Context context,  int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), "" + context.getResources().getText(resId), Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }
    /**
     * 取消吐司显示
     */
    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
    /**
     * 当用户点击A中按钮来到B时，假设B全部遮挡住了A，
     * 将依次执行A:onPause -> B:onCreate -> B:onStart -> B:onResume -> A:onStop。
     *
     * 此时如果点击Back键，将依次执行B:onPause -> A:onRestart -> A:onStart -> A:onResume -> B:onStop -> B:onDestroy。
     *
     *此时如果按下Back键，系统返回到桌面，并依次执行A:onPause -> A:onStop -> A:onDestroy。
     *
     * 此时如果按下Home键（非长按），系统返回到桌面，
     * 并依次执行A:onPause -> A:onStop。由此可见，Back键和Home键主要区别在于是否会执行onDestroy。
     *
     * 此时如果长按Home键，不同手机可能弹出不同内容，Activity生命周期未发生变化
     *
     * */
    private void addActivity() {
        myApplication.addActivity(mContext);
    }
    //销毁当个Activity方法
    public void removeActivity() {
        myApplication.removeActivity(mContext);// 调用myApplication的销毁单个Activity方法
    }
    //销毁所有Activity方法
    public void removeALLActivity() {
        myApplication.removeALLActivity();// 调用myApplication的销毁所有Activity方法
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 打开软键盘
     */
    public void openKeybord() {
        View view = getCurrentFocus();
        if (view!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(),InputMethodManager.RESULT_SHOWN);
        }
    }
}
