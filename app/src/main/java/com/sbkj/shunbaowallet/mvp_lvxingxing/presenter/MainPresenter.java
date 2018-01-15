package com.sbkj.shunbaowallet.mvp_lvxingxing.presenter;

import com.orhanobut.logger.Logger;
import com.sbkj.shunbaowallet.mvp_lvxingxing.contract.MainContract;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreateModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.model.MainModel;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */
@CreateModel(MainModel.class)
public class MainPresenter extends MainContract.IMainPresenter {
    private static final String TAG = "MainPresenter";
    @Override
    public void method3() {
        Logger.d("getMvp==========="+getMvpView());
        getMvpView().clickView();
    }

    @Override
    public void getData() {
        Logger.d(getMvpView());
        getMvpView().updateContentList(getModel().requestData());





    }

}
