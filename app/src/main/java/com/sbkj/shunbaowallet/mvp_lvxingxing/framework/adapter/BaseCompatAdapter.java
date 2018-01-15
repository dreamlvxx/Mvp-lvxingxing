package com.sbkj.shunbaowallet.mvp_lvxingxing.framework.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sbkj.shunbaowallet.mvp_lvxingxing.widgets.RvLoadMoreView;

import java.util.List;

/**
 *
 * @author lvxingxing
 * @date 2017/12/14
 *
 */

public abstract class BaseCompatAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,
        K> {

    public BaseCompatAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }

    public BaseCompatAdapter(@Nullable List<T> data) {
        super(data);
        init();
    }

    public BaseCompatAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    private void init(){
        setLoadMoreView(new RvLoadMoreView());
        setEnableLoadMore(true);
        openLoadAnimation();//开启默认动画载入（仅开启加载新item时开启动画）
    }
}
