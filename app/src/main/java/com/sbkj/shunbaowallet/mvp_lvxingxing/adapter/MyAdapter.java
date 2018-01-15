package com.sbkj.shunbaowallet.mvp_lvxingxing.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.sbkj.shunbaowallet.mvp_lvxingxing.R;
import com.sbkj.shunbaowallet.mvp_lvxingxing.bean.Person;

import java.util.List;

/**
 * Created by lvxingxing on 2017/12/13.
 *
 * @author lvxingxing
 */

public class MyAdapter extends BaseMultiItemQuickAdapter<Person, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyAdapter(List<Person> data) {
        super(data);
        init();
        addItemType(Person.CUSTOM_TYPE, R.layout.item_rv_home);
        addItemType(Person.IMAGE_TYPE, R.layout.item_rv_home_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        switch (helper.getItemViewType()) {
            case Person.CUSTOM_TYPE:
                helper.setText(R.id.tv_item_name, item.getName());
                helper.setText(R.id.tv_item_id, item.getId());
                helper.setText(R.id.tv_item_sex, item.getSex());
                break;

            case Person.IMAGE_TYPE:

                break;
            default:
                break;
        }

    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }
}
