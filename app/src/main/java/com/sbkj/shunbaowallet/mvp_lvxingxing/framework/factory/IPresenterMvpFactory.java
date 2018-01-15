package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public interface IPresenterMvpFactory<V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
