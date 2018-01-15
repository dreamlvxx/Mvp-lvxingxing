package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.IPresenterMvpFactory;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;

/**
 * @author lvxingxing
 *         activity代理类，在这里处理一些内存泄露问题
 */
public class BaseMvpProxy<V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> implements IBaseMvpPresenterProxy<V, M, P> {
    private static final String TAG = "BaseMvpProxy";
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "personalKey";
    /**
     * Presenter工厂类
     */
    private IPresenterMvpFactory<V, M, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachView;

    public BaseMvpProxy(IPresenterMvpFactory<V, M, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getMvpPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, M, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    /**
     * 获取Presenter的工厂类
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public IPresenterMvpFactory<V, M, P> getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     * 如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    @Override
    public P getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePersenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Logger.d("Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }

    /**
     * 绑定Presenter和view
     *
     * @param mvpView V层的实现类
     */
    public void onCreate(V mvpView) {
        getMvpPresenter();
        Logger.d("Proxy onCreate");
        if (mPresenter != null && !mIsAttachView) {
            mPresenter.onAttachMvpView(mvpView);
            mIsAttachView = true;
        }
    }

    public void onResume() {
        Logger.d("执行onResume");

    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        Logger.d("Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttachView) {
            mPresenter.onDetachMvpView();
            mIsAttachView = false;
        }
    }


    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        Logger.d("Proxy onDestroy = ");
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Logger.d("Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Logger.d("Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }
}