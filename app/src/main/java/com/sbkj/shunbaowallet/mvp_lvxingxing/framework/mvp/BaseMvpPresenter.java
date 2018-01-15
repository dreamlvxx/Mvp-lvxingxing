package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreateModel;

/**
 * @author lvxingxing
 */
public class BaseMvpPresenter<V extends IBaseMvpView, M extends IBaseMvpModel> {

    private static final String TAG = "BaseMvpPresenter";
    /**
     * V层view
     */
    protected V mView;
    protected M mModel;

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
    }

    /**
     * 绑定View
     */
    public void onAttachMvpView(V mvpView) {
        Logger.d("执行到onAttachMvpView");
        mView = mvpView;
        //在绑定视图的同时，把model创建出来
        CreateModel annotation = getClass().getAnnotation(CreateModel.class);
        Class<M> aClass = null;
        if (annotation != null) {
            aClass = (Class<M>) annotation.value();
        }
        try {
            mModel = aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Model创建失败!检查是否声明了@CreateModel(xx.class)注解");
        }
    }

    /**
     * 解除绑定View
     */
    public void onDetachMvpView() {
        mView = null;
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPresenter() {
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState 存储bundle
     */
    public void onSaveInstanceState(Bundle outState) {
    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前MvpView
     */
    protected V getMvpView() {
        return mView;
    }

    /**
     * 获取M层model
     *
     * @return 返回当前的model
     */
    protected M getModel() {
        return mModel;
    }
}