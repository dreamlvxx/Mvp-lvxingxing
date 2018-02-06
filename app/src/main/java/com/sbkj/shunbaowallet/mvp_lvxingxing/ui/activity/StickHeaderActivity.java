package com.sbkj.shunbaowallet.mvp_lvxingxing.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sbkj.shunbaowallet.mvp_lvxingxing.R;
import com.sbkj.shunbaowallet.mvp_lvxingxing.adapter.AnimalsHeadersAdapter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.contract.StickHeaderContract;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreatePresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpActivity;
import com.sbkj.shunbaowallet.mvp_lvxingxing.listener.DividerDecoration;
import com.sbkj.shunbaowallet.mvp_lvxingxing.listener.RecyclerItemClickListener;
import com.sbkj.shunbaowallet.mvp_lvxingxing.presenter.StickHeaderPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import butterknife.BindView;

/**
 * @author lvxingxing
 */
@CreatePresenter(StickHeaderPresenter.class)
public class StickHeaderActivity
        extends BaseMvpActivity<StickHeaderContract.IStickHeaderActivity, StickHeaderContract.IStickHeaderModel, StickHeaderContract.IStickHeaderPresenter>
        implements StickHeaderContract.IStickHeaderActivity {

    @BindView(R.id.rv_stickActivity)
    RecyclerView mRecyclerView;

    private AnimalsHeadersAdapter adapter;

    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected int layoutResId() {
        return R.layout.activity_stick_header;
    }

    @Override
    protected void viewAction(Bundle saveBundle) {
        adapter = new AnimalsHeadersAdapter();
        getMvpPresenter().setRefreshViewData(StickHeaderActivity.this);
        initRecyclerView();
        setRefreshLayoutListener();
    }

    private void initRecyclerView() {
        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        mRecyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        mRecyclerView.addItemDecoration(new DividerDecoration(this));

        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener = new StickyRecyclerHeadersTouchListener(mRecyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View header, int position, long headerId) {
                TextView tv = (TextView) header.findViewById(R.id.tv11);

                Toast.makeText(StickHeaderActivity.this,
                        "longId id"+headerId+"data is " + tv.getText().toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });


        mRecyclerView.addOnItemTouchListener(touchListener);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    Toast.makeText(StickHeaderActivity.this, "这是header", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(StickHeaderActivity.this,
                        "" + adapter.getItem(position),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }));


    }

    @Override
    public void setRecyclerViewContent(String[] data) {
        adapter.addAll(data);
        mRecyclerView.setAdapter(adapter);
    }

    public void setRefreshLayoutListener(){
        //设置头布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

        //设置脚布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });



        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(StickHeaderActivity.this, "finish lodeMore", Toast.LENGTH_SHORT).show();
                        mSmartRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(StickHeaderActivity.this, "finish lodeMore", Toast.LENGTH_SHORT).show();
                      mSmartRefreshLayout.finishLoadmore();
                    }
                }, 3000);
            }
        });
    }
}
