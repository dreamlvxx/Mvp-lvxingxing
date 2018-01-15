package com.sbkj.shunbaowallet.mvp_lvxingxing.contract;

import com.sbkj.shunbaowallet.mvp_lvxingxing.bean.Person;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;

import java.util.ArrayList;

/**
 * Created by lvxingxing on 2017/12/13.
 *
 * @author lvxingxing
 */

public interface MainContract {
    /**
     * 抽象类
     * 第一个参数是view接口类型
     * 第二个参数是model接口类型
     */
    abstract class IMainPresenter extends BaseMvpPresenter<IMainActivity, IMainModel> {
        /**
         * presenter中的方法
         */
        public abstract void method3();

        /**
         * 获取数据
         */
        public abstract void getData();
    }

    /**
     *
     */
    interface IMainActivity extends IBaseMvpView {
        /**
         * 点击view
         */
        void clickView();

        /**
         * 获取数据
         */
        void loadMoreData();

        /**
         * 更新数据
         *
         * @param list 更新到的数据源
         */
        void updateContentList(ArrayList<Person> list);
    }

    /**
     * 抽象model表示
     */
    interface IMainModel extends IBaseMvpModel {

        /**
         * model中的方法
         */
        void modelMethod1();

        /**
         * 网络获取数据
         *
         * @return 返回的数据源
         */
        ArrayList<Person> requestData();
    }

}
