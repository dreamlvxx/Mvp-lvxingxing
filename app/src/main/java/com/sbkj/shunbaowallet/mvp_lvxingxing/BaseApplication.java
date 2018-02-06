package com.sbkj.shunbaowallet.mvp_lvxingxing;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.sbkj.shunbaowallet.mvp_lvxingxing.utils.CrashHandlerUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private RefWatcher refWatcher;
    private static Context mContext;
    protected static int mainThreadId;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决64K问题
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.init(this);
        //开启调试模式（如果不开启debug运行不会上传umeng统计）
        MobclickAgent.setDebugMode(true);
        MobclickAgent.setCatchUncaughtExceptions(true);
        mContext = getApplicationContext();
        mainThreadId = android.os.Process.myTid();
        refWatcher = setupLeakCanary();

        //全部异常捕获
        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
        crashHandlerUtil.init(this);
        crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    //在application里保存某些东西
    public void getSomething() {

    }
}
