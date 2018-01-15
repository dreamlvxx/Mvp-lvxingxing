package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.IPresenterMvpFactory;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.PresenterMvpFactoryImpl;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy.BaseMvpProxy;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy.IBaseMvpPresenterProxy;

/**
 * @author lvxingxing
 * @date 2017/12/20
 * @description  继承Fragment的MvpFragment基类
 */
public class BaseMvpFragment<V extends IBaseMvpView, M extends IBaseMvpModel,P extends BaseMvpPresenter<V,M>> extends Fragment implements IBaseMvpPresenterProxy<V,M, P> {

    private static final String TAG = "BaseMvpFragment";
    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V,M, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,M, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onCreate((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    /**
     * 可以实现自己PresenterMvpFactory工厂
     *
     * @param presenterFactory PresenterFactory类型
     */

    @Override
    public void setPresenterFactory(IPresenterMvpFactory presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    /**
     * 获取创建Presenter的工厂
     *
     * @return PresenterMvpFactory类型
     */


    @Override
    public IPresenterMvpFactory<V, M, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 获取Presenter
     * @return P
     */
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
