package com.gq.usage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyApplication extends Application {
    private static Context mContext;
    private static MyApplication instance;//为了实现每次使用该类时不创建新的对象而创建的静态对象
    private static List<Activity> allActivityList;//用于存放所有启动的Activity的集合
    public static synchronized MyApplication getInstance() {
        return instance;
    }
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
        allActivityList = new ArrayList<>();

    }
    //---------------
    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!allActivityList.contains(activity)) {
            allActivityList.add(activity);
        }
    }
    /**
     * 销毁单个Activity
     */
    public  void removeActivity(Activity activity) {
        //判断当前集合中存在该Activity
        if (allActivityList.contains(activity)) {
            allActivityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public static void removeALLActivity() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : allActivityList) {
            activity.finish();
        }
    }
}
