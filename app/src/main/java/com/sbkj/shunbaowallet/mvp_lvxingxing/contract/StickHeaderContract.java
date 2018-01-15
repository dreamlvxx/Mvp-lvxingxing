package com.sbkj.shunbaowallet.mvp_lvxingxing.contract;

import android.content.Context;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;

/**
 * Created by lvxingxing on 2017/12/23.
 *
 * @author lvxingxing
 */

public interface StickHeaderContract {

    abstract class IStickHeaderPresenter extends BaseMvpPresenter<IStickHeaderActivity,IStickHeaderModel>{
        /**
         * 给RecyclerView
         */
        public abstract void setRefreshViewData(Context con);
    }

    interface IStickHeaderActivity extends IBaseMvpView{
        /**
         * 给recyclerview绑定数据源
         * @param datas 数据源
         */
        void setRecyclerViewContent(String[] datas);
    }

    interface IStickHeaderModel extends IBaseMvpModel{

    }

}
