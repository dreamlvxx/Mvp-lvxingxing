package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation;

import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}
