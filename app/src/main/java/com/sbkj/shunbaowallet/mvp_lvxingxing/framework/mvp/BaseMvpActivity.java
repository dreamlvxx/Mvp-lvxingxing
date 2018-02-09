package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sbkj.shunbaowallet.mvp_lvxingxing.ActivityManager;
import com.sbkj.shunbaowallet.mvp_lvxingxing.BuildConfig;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.IPresenterMvpFactory;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.PresenterMvpFactoryImpl;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy.BaseMvpProxy;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy.IBaseMvpPresenterProxy;
import com.sbkj.shunbaowallet.mvp_lvxingxing.network.ActivityLifeCycleEvent;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public abstract class BaseMvpActivity<V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> extends AppCompatActivity implements IBaseMvpPresenterProxy<V, M, P> {
    private static final String TAG = "BaseMvpActivity";
    private static final String PRESENTER_SAVE_KEY = "personalKey";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, M, P> mProxy;

    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    /**
     * 万一用户要重写onCreate  ，也必须继承父类的才行
     */
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, M, P>createFactory(getClass()));
        mProxy.onCreate((V) this);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        //打印log
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.IS_SHOW_LOG;
            }
        });
        //初始化butterkniff
        ButterKnife.bind(this);
        //管理所有activity
        ActivityManager.getAppManager().addActivity(this);
        //执行activity中的一些操作
        viewAction(savedInstanceState);
    }

    /**
     * 返回布局id
     *
     * @return 返回值
     */
    protected abstract int layoutResId();

    /**
     * view层的操作
     *
     * @param saveBundle 存储的bundle
     */
    protected abstract void viewAction(Bundle saveBundle);

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());//统计页面
        MobclickAgent.onResume(this);//统计时长
    }

    @CallSuper
    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
        mProxy.onDestroy();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @CallSuper
    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, M, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @CallSuper
    @Override
    public IPresenterMvpFactory<V, M, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @CallSuper
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }

    /**
     * @param clz 要跳转的activity
     *            不可以被重写
     */
    @CallSuper
    public final void goToActivity(Class<?> clz) {
        startActivity(new Intent(BaseMvpActivity.this, clz));
    }

    /**
     * @param clazz  要跳转的activity
     * @param bundle 携带的参数
     */
    @CallSuper
    public void goToActivity(Class<?> clazz, Bundle bundle) {
        Intent t = new Intent(BaseMvpActivity.this, clazz.getClass());
        t.putExtras(bundle);
        startActivity(t);
    }


}
