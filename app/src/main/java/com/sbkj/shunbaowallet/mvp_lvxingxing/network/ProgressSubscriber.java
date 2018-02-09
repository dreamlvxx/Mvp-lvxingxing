package com.sbkj.shunbaowallet.mvp_lvxingxing.network;


import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author lvxingxing 2018/2/9
 */

public abstract class ProgressSubscriber<T> implements ProgressCancelListener, Observer<T> {


    private SimpleLoadDialog dialogHandler;

    public ProgressSubscriber(Context context) {
        dialogHandler = new SimpleLoadDialog(context, this, true);
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.show();
        }
    }


    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler = null;
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (false) { //这里自行替换判断网络的代码
            onDoError("网络不可用");
        } else if (e instanceof ApiException) {
            onDoError(e.getMessage());
        } else {
            onDoError("请求失败，请稍后再试...");
        }
        dismissProgressDialog();
    }


    @Override
    public void onCancelProgress() {

    }

    /**
     * 当请求成功的时候回调
     *
     * @param t 数据
     */
    protected abstract void onSuccess(T t);

    /**
     * 当请求失败的时候回调
     *
     * @param message 错误信息
     */
    protected abstract void onDoError(String message);
}
