package com.sbkj.shunbaowallet.mvp_lvxingxing.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cocosw.bottomsheet.BottomSheet;
import com.orhanobut.logger.Logger;
import com.sbkj.shunbaowallet.mvp_lvxingxing.R;
import com.sbkj.shunbaowallet.mvp_lvxingxing.adapter.MyAdapter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.bean.Person;
import com.sbkj.shunbaowallet.mvp_lvxingxing.contract.MainContract;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.annotation.CreatePresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.framework.mvp.BaseMvpActivity;
import com.sbkj.shunbaowallet.mvp_lvxingxing.presenter.MainPresenter;
import com.sbkj.shunbaowallet.mvp_lvxingxing.utils.CameraUtils;
import com.sbkj.shunbaowallet.mvp_lvxingxing.utils.ToastUtil;
import com.sbkj.shunbaowallet.mvp_lvxingxing.widgets.AdImageView;
import com.sbkj.shunbaowallet.mvp_lvxingxing.widgets.RvLoadMoreView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

@CreatePresenter(MainPresenter.class)
public class MainActivity extends BaseMvpActivity<MainContract.IMainActivity, MainContract.IMainModel, MainContract.IMainPresenter> implements MainContract.IMainActivity, BaseQuickAdapter.RequestLoadMoreListener{
    private static final String TAG = "MainActivity";
    public RecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.appBar_main)
    public AppBarLayout mAppBarLayout;

    @BindView(R.id.imageView)
    ImageView mImageView;

    @BindView(R.id.fab_classify)
    public FloatingActionButton mFloatingActionButton;

    private BottomSheet mBottomSheet;

    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/xingxing.jpg");

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void viewAction(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

        }

        Logger.d(getMvpPresenter());
        getMvpPresenter().method3();
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickView();
                goToActivity(StickHeaderActivity.class);
//                PermissionUtil.requestPermission(new PermissionUtil.RequestPermission() {
//                    @Override
//                    public void onRequestPermissionSuccess() {
//                        CameraUtils.takePicture(MainActivity.this, fileUri, 140);
//                    }
//
//                    @Override
//                    public void onRequestPermissionFailure(List<String> permissions) {
//
//                    }
//
//                    @Override
//                    public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
//
//                    }
//                }, new RxPermissions(MainActivity.this), Manifest.permission.CAMERA);

            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_home);
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setId(i + "");
            person.setName("xx" + i);
            person.setSex("boy");
            if (i % 5 == 0) {
                person.itemType = Person.IMAGE_TYPE;
            }
            list.add(person);
        }
        mMyAdapter = new MyAdapter(list);
        mMyAdapter.setLoadMoreView(new RvLoadMoreView());
        mMyAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setAdapter(mMyAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //通过Rv滑动监听，实现知乎滑动图片效果
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    int type = mLinearLayoutManager.getItemViewType(view);
                    if (type == Person.IMAGE_TYPE) {
                        AdImageView adImageView = (AdImageView) view.findViewById(R.id.adImageView);
                        if (adImageView.getVisibility() == View.VISIBLE) {
                            adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mFloatingActionButton.hide();
                } else {

                    mFloatingActionButton.show();
                }
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mMyAdapter.loadMoreComplete();
        loadMoreData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "MainActivity onSaveInstanceState 测试");
        outState.putString("test", "test_save");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void clickView() {
        Toast.makeText(this, "哈哈哈哈啊", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMoreData() {
        getMvpPresenter().getData();
    }

    @Override
    public void updateContentList(ArrayList<Person> list) {
        mMyAdapter.addData(list);
        mMyAdapter.notifyDataSetChanged();
    }

    private void showBottomSheet() {
        if (null == mBottomSheet) {
            initBottomSheet();
        }
        mBottomSheet.show();
    }

    private void initBottomSheet() {
        mBottomSheet = new BottomSheet.Builder(this, R.style.BottomSheet_StyleDialog)
                .title("选择分类")
                .sheet(R.menu.gank_io_custom_bottom_sheet)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int viewId) {
                        ToastUtil toastUtil3 = new ToastUtil(MainActivity.this, R.layout.toast_msg, "带图片自定义时长toast");
                        toastUtil3.show(5000);
                    }
                }).build();
    }

    @OnClick({R.id.fab_classify})
    public void clickA(View v) {
        switch (v.getId()) {
            case R.id.fab_classify:
                showBottomSheet();

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = CameraUtils.getBitmapFromUri(fileUri, this);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}