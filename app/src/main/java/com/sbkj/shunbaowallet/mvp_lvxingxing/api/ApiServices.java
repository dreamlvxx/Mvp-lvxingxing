package com.sbkj.shunbaowallet.mvp_lvxingxing.api;

import com.sbkj.shunbaowallet.mvp_lvxingxing.bean.Person;
import com.sbkj.shunbaowallet.mvp_lvxingxing.network.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lvxingxing on 2018/1/8.
 *
 * @author lvxingxing
 */

public interface ApiServices {

    /**
     *
     * @return 统一请求 返回数据源
     */
    @GET
    Observable <BaseEntity<Person>>  getJson();

    @GET
    Observable <BaseEntity<Person>>  getJson1();

    @GET
    Observable <BaseEntity<Person>>  getJson2();

    @GET
    Observable <BaseEntity<Person>>  getJson3();

}
