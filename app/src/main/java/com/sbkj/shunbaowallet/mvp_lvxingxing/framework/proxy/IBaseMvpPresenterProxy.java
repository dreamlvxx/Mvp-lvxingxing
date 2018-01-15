package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.proxy;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory.IPresenterMvpFactory;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public interface IBaseMvpPresenterProxy<V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> {
    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(IPresenterMvpFactory<V, M, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    IPresenterMvpFactory<V, M, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();
}
