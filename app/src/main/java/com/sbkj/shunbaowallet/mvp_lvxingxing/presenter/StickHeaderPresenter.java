package com.sbkj.shunbaowallet.mvp_lvxingxing.presenter;

import android.content.Context;

import com.sbkj.shunbaowallet.mvp_lvxingxing.R;
import com.sbkj.shunbaowallet.mvp_lvxingxing.contract.StickHeaderContract;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreateModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.model.StickModel;

/**
 * Created by lvxingxing on 2017/12/23.
 *
 * @author lvxingxing
 */
@CreateModel(StickModel.class)
public class StickHeaderPresenter extends StickHeaderContract.IStickHeaderPresenter {

    @Override
    public void setRefreshViewData(Context con) {
        String[] strArr ={"Adsfsdcs","Adfddfdffd","Basdasd","Csadads","Dasdasd"};
        String[] arr =con.getResources().getStringArray(R.array.animals);
        getMvpView().setRecyclerViewContent(arr);
    }

}
