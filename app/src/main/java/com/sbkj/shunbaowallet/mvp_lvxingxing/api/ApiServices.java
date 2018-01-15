package com.sbkj.shunbaowallet.mvp_lvxingxing.api;

import com.sbkj.shunbaowallet.mvp_lvxingxing.model.MainModel;

import io.reactivex.Observable;

/**
 * Created by lvxingxing on 2018/1/8.
 *
 * @author lvxingxing
 */

public interface ApiServices {


    Observable<MainModel> execute();

}
