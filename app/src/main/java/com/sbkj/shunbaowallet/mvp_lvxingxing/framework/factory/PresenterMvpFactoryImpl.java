package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.factory;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreatePresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpModel;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.IBaseMvpView;

/**
 * @author lvxingxing
 */
public class PresenterMvpFactoryImpl<V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> implements IPresenterMvpFactory<V, M, P> {
    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;


    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V>       当前View实现的接口类型
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseMvpView, M extends IBaseMvpModel, P extends BaseMvpPresenter<V, M>> PresenterMvpFactoryImpl<V, M, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, M, P>(aClass);
    }

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}