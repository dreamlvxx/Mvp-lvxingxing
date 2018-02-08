package com.sbkj.shunbaowallet.mvp_lvxingxing.network;

import com.sbkj.shunbaowallet.mvp_lvxingxing.api.ApiServices;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lvxingxing on 2018/2/8.
 *
 * @author lvxingxing
 */

public class HttpUtils{
    private static HttpUtils httpUtils;
    public static  Retrofit mRetrofit  = null;
    public static Observable<BaseEntity> mMainModelObservable;
    private static ApiServices apiServices;
    public HttpUtils() {
        OkHttpClient mOkHttpClient=new OkHttpClient.Builder()  .build();

        mRetrofit =new Retrofit.Builder()
                .baseUrl("http://ww.xxlibili.com:8009")
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        apiServices =mRetrofit.create(ApiServices.class);
    }

    public   ApiServices Api(){
        return apiServices;
    }

    public static HttpUtils getInstance(){
        if (httpUtils == null){
            synchronized (HttpUtils.class){
                if (httpUtils == null){
                    httpUtils =new HttpUtils();
                }
            }
        }
        return  httpUtils;
    }

}
