package com.sbkj.shunbaowallet.mvp_lvxingxing.network;

import com.sbkj.shunbaowallet.mvp_lvxingxing.api.ApiServices;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lvxingxing on 2018/2/8.
 *
 * @author lvxingxing
 */

public class HttpUtils {

    private HttpUtils() {
    }

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    /**
     * 单例获取
     *
     * @return 返回值
     */
    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public ApiServices getDefault() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl("http://ww.xxlibili.com:8009")
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build()
                .create(ApiServices.class);
    }

    /**
     * 执行请求
     *
     * @param ob               被观察者
     * @param subscriber       观察者
     * @param cacheKey         缓存的key
     * @param event            声明周期
     * @param lifecycleSubject 绑定声明周期
     * @param isSave           是否缓存
     * @param forceRefresh     是否强制刷新
     */
    public <T>void toSubscribe(Observable ob, final ProgressSubscriber<T> subscriber, String cacheKey, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, boolean isSave, boolean forceRefresh) {
        //数据预处理
        ObservableTransformer<HttpResult<Object>, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        Observable observable =
                ob.compose(result)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                subscriber.showProgressDialog();
                            }
                        });
        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);

    }
}
